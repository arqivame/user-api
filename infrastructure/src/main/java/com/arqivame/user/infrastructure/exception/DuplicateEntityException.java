package com.arqivame.user.infrastructure.exception;

import com.arqivame.user.domain.Entity;

public class DuplicateEntityException extends SilentInfrastructureException {

    private DuplicateEntityException(String entity, String id) {
        super("%s already exists with id %s".formatted(entity, id));
    }

    public static DuplicateEntityException with(Entity<?> entity) {
        return new DuplicateEntityException(entity.getClass().getSimpleName(), entity.getId().getStringValue());
    }

}
