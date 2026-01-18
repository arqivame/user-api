package com.arqivame.user.application.usecase.user.create;

import java.util.UUID;

import com.arqivame.user.domain.user.User;

public record CreateUserOutput(UUID id) {

    public static CreateUserOutput from(final User user) {
        return new CreateUserOutput(user.getId().getValue());
    }

}
