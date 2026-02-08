package com.arqivame.user.infrastructure.user.message;

import java.time.Instant;
import java.util.UUID;

import com.arqivame.user.domain.user.User;

public record UserCreatedIntegrationMessageV1(
        UUID id,
        String email,
        String username,
        String nickname,
        Boolean active,
        Instant createdAt,
        Instant updatedAt) {

    public static UserCreatedIntegrationMessageV1 from(final User user) {
        return new UserCreatedIntegrationMessageV1(
                user.getId().getValue(),
                user.getEmail().value(),
                user.getUsername().value(),
                user.getNickname().value(),
                user.getActive(),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }

}
