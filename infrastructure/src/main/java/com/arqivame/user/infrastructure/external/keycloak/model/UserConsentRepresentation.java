package com.arqivame.user.infrastructure.external.keycloak.model;

import java.util.List;

public record UserConsentRepresentation(
        String clientId,
        List<String> grantedClientScopes,
        Long createdDate,
        Long lastUpdatedDate,
        List<String> grantedRealmRoles) {

}
