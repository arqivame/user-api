package com.arqivame.user.domain.user;

import com.arqivame.user.domain.ValueObject;
import com.arqivame.user.domain.validation.ValidationHandler;

public record PreUser(Username username, Email email, Password password, Nickname nickname) implements ValueObject {

    public static PreUser with(
            final Username username,
            final Email email,
            final Password password,
            final Nickname nickname) {
        return new PreUser(username, email, password, nickname);
    }

    @Override
    public void validate(final ValidationHandler handler) {
        this.username.validate(handler);
        this.email.validate(handler);
        this.password.validate(handler);
        this.nickname.validate(handler);
    }

}
