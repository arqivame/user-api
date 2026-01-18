package com.arqivame.user.domain.user;

import com.arqivame.user.domain.ValueObject;

public record Email(String value) implements ValueObject {

    public static Email of(final String email) {
        return new Email(email);
    }

}
