package com.arqivame.user.domain;

import com.arqivame.user.domain.validation.ValidationHandler;

@FunctionalInterface
public interface Validatable {

    void validate(ValidationHandler handler);

}
