package com.arqivame.user.infrastructure.external.keycloak.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.arqivame.user.domain.user.PreUser;
import com.arqivame.user.domain.user.User;
import com.arqivame.user.infrastructure.external.keycloak.model.CredentialRepresentation;
import com.arqivame.user.infrastructure.external.keycloak.model.UserRepresentation;

@Mapper
public interface KeycloakUserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "firstName", ignore = true)
    @Mapping(target = "lastName", ignore = true)
    @Mapping(target = "emailVerified", ignore = true)
    @Mapping(target = "attributes", ignore = true)
    @Mapping(target = "userProfileMetadata", ignore = true)
    @Mapping(target = "self", ignore = true)
    @Mapping(target = "origin", ignore = true)
    @Mapping(target = "createdTimestamp", ignore = true)
    @Mapping(target = "totp", ignore = true)
    @Mapping(target = "federationLink", ignore = true)
    @Mapping(target = "serviceAccountClientId", ignore = true)
    @Mapping(target = "disableableCredentialTypes", ignore = true)
    @Mapping(target = "requiredActions", ignore = true)
    @Mapping(target = "federatedIdentities", ignore = true)
    @Mapping(target = "realmRoles", ignore = true)
    @Mapping(target = "clientRoles", ignore = true)
    @Mapping(target = "clientConsents", ignore = true)
    @Mapping(target = "notBefore", ignore = true)
    @Mapping(target = "applicationRoles", ignore = true)
    @Mapping(target = "socialLinks", ignore = true)
    @Mapping(target = "access", ignore = true)
    @Mapping(target = "username", source = "username.value")
    @Mapping(target = "email", source = "email.value")
    @Mapping(target = "credentials", source = "preUser")
    @Mapping(target = "groups", ignore = true)
    @Mapping(target = "enabled", constant = "false")
    UserRepresentation toUserRepresentation(PreUser preUser);

    @Mapping(target = "firstName", ignore = true)
    @Mapping(target = "lastName", ignore = true)
    @Mapping(target = "emailVerified", ignore = true)
    @Mapping(target = "attributes", ignore = true)
    @Mapping(target = "userProfileMetadata", ignore = true)
    @Mapping(target = "self", ignore = true)
    @Mapping(target = "origin", ignore = true)
    @Mapping(target = "createdTimestamp", ignore = true)
    @Mapping(target = "totp", ignore = true)
    @Mapping(target = "federationLink", ignore = true)
    @Mapping(target = "serviceAccountClientId", ignore = true)
    @Mapping(target = "credentials", ignore = true)
    @Mapping(target = "disableableCredentialTypes", ignore = true)
    @Mapping(target = "requiredActions", ignore = true)
    @Mapping(target = "federatedIdentities", ignore = true)
    @Mapping(target = "realmRoles", ignore = true)
    @Mapping(target = "clientRoles", ignore = true)
    @Mapping(target = "clientConsents", ignore = true)
    @Mapping(target = "notBefore", ignore = true)
    @Mapping(target = "applicationRoles", ignore = true)
    @Mapping(target = "socialLinks", ignore = true)
    @Mapping(target = "access", ignore = true)
    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "username", source = "username.value")
    @Mapping(target = "email", source = "email.value")
    @Mapping(target = "enabled", source = "active")
    UserRepresentation toUserRepresentation(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userLabel", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "secretData", ignore = true)
    @Mapping(target = "credentialData", ignore = true)
    @Mapping(target = "priority", ignore = true)
    @Mapping(target = "device", ignore = true)
    @Mapping(target = "hashedSaltedValue", ignore = true)
    @Mapping(target = "salt", ignore = true)
    @Mapping(target = "hashIterations", ignore = true)
    @Mapping(target = "counter", ignore = true)
    @Mapping(target = "algorithm", ignore = true)
    @Mapping(target = "digits", ignore = true)
    @Mapping(target = "period", ignore = true)
    @Mapping(target = "config", ignore = true)
    @Mapping(target = "type", constant = "password")
    @Mapping(target = "temporary", constant = "false")
    @Mapping(target = "value", source = "password.value")
    CredentialRepresentation toCredentialRepresentation(PreUser preUser);

    default List<CredentialRepresentation> toCredentialRepresentationList(
            final CredentialRepresentation credentialRepresentation) {
        return credentialRepresentation != null ? List.of(credentialRepresentation) : List.of();
    }

}
