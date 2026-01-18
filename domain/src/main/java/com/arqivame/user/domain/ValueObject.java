package com.arqivame.user.domain;

import com.arqivame.user.domain.validation.ValidationHandler;

public interface ValueObject extends Validatable {

    default void validate(ValidationHandler handler) {
    };

}
