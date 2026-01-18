package com.arqivame.user.domain.user;

import com.arqivame.user.domain.ValueObject;
import com.arqivame.user.domain.validation.ValidationError;
import com.arqivame.user.domain.validation.ValidationHandler;

public record Username(String value) implements ValueObject {

    public static Username of(final String username) {
        return new Username(username);
    }

    @Override
    public void validate(ValidationHandler aHandler) {
        if (value == null || value.isBlank()) {
            aHandler.append(ValidationError.with("'username' is required"));
            return;
        }

        if (value.contains(" "))
            aHandler.append(ValidationError.with("'username' cannot contain spaces"));
    }

}
