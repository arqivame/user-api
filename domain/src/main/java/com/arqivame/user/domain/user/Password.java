package com.arqivame.user.domain.user;

import com.arqivame.user.domain.ValueObject;
import com.arqivame.user.domain.validation.ValidationError;
import com.arqivame.user.domain.validation.ValidationHandler;

public record Password(String value) implements ValueObject {

    public static Password of(final String password) {
        return new Password(password);
    }

    @Override
    public void validate(ValidationHandler aHandler) {
        if (this.value == null || this.value.isBlank())
            aHandler.append(ValidationError.with("Password is required."));
    }

    @Override
    public final String toString() {
        return "Password[value=****]";
    }

}
