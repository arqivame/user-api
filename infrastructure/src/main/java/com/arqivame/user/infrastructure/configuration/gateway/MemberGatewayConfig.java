package com.arqivame.user.infrastructure.configuration.gateway;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.arqivame.user.domain.user.gateway.command.UserCommandGateway;
import com.arqivame.user.domain.user.gateway.query.UserQueryGateway;
import com.arqivame.user.infrastructure.external.keycloak.mapper.KeycloakUserMapper;
import com.arqivame.user.infrastructure.external.keycloak.service.KeycloakUserService;
import com.arqivame.user.infrastructure.user.DefaultUserGateway;
import com.arqivame.user.infrastructure.user.persistence.UserJpaRepository;

@Configuration
public class MemberGatewayConfig {

    @Bean
    DefaultUserGateway defaultUserGateway(
            final UserJpaRepository userJpaRepository,
            final KeycloakUserMapper keycloakUserMapper,
            @Qualifier("keycloakUserServiceClientCredentials") final KeycloakUserService clientKeycloakUserService) {
        return new DefaultUserGateway(
                userJpaRepository,
                keycloakUserMapper,
                clientKeycloakUserService);
    }

    @Bean
    UserCommandGateway userCommandGateway(final DefaultUserGateway defaultUserGateway) {
        return defaultUserGateway;
    }

    @Bean
    UserQueryGateway userQueryGateway(final DefaultUserGateway defaultUserGateway) {
        return defaultUserGateway;
    }

}
