package com.arqivame.user.infrastructure.external.keycloak.model;

import java.util.List;

public record UserProfileMetadata(
        List<UserProfileAttributeMetadata> attributes,
        List<UserProfileAttributeGroupMetadata> groups) {

}
