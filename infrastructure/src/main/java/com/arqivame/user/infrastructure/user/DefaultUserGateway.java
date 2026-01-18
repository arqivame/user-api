package com.arqivame.user.infrastructure.user;

import static java.util.Objects.requireNonNull;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.arqivame.user.domain.user.Nickname;
import com.arqivame.user.domain.user.PreUser;
import com.arqivame.user.domain.user.User;
import com.arqivame.user.domain.user.UserID;
import com.arqivame.user.domain.user.gateway.command.UserCommandGateway;
import com.arqivame.user.domain.user.gateway.query.UserQueryGateway;
import com.arqivame.user.infrastructure.external.keycloak.mapper.KeycloakUserMapper;
import com.arqivame.user.infrastructure.external.keycloak.service.KeycloakUserService;
import com.arqivame.user.infrastructure.user.persistence.UserJpaEntity;
import com.arqivame.user.infrastructure.user.persistence.UserJpaRepository;

@Component
public class DefaultUserGateway implements UserQueryGateway, UserCommandGateway {

    private final UserJpaRepository userJpaRepository;

    private final KeycloakUserMapper keycloakUserMapper;
    private final KeycloakUserService clientKeycloakUserService;

    public DefaultUserGateway(
            final UserJpaRepository userJpaRepository,
            final KeycloakUserMapper keycloakUserMapper,
            final KeycloakUserService clientKeycloakUserService) {
        this.userJpaRepository = requireNonNull(userJpaRepository);
        this.keycloakUserMapper = requireNonNull(keycloakUserMapper);
        this.clientKeycloakUserService = requireNonNull(clientKeycloakUserService);
    }

    @Override
    public Optional<User> findById(UserID id) {
        return userJpaRepository
                .findById(id.getValue())
                .map(UserJpaEntity::toDomain);
    }

    @Override
    public User create(final PreUser preUser) {
        final String userId = this.clientKeycloakUserService
                .createUser(keycloakUserMapper.toUserRepresentation(preUser));

        try {
            return save(
                    User.create(
                            UserID.of(UUID.fromString(userId)),
                            preUser.username(),
                            preUser.email(),
                            Nickname.of(preUser.username().value())));
        } catch (Exception e) {
            this.clientKeycloakUserService.deleteUser(userId);
            throw e;
        }
    }

    private User save(final User user) {
        userJpaRepository.save(UserJpaEntity.fromDomain(user));
        return user;
    }

}
