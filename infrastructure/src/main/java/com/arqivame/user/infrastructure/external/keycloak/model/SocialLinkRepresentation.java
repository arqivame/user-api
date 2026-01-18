package com.arqivame.user.infrastructure.external.keycloak.model;

public record SocialLinkRepresentation(
        String socialProvider,
        String socialUserId,
        String socialUsername) {

}
