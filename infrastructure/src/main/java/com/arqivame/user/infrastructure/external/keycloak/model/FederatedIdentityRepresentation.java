package com.arqivame.user.infrastructure.external.keycloak.model;

public record FederatedIdentityRepresentation(
        String identityProvider,
        String userId,
        String userName) {

}
