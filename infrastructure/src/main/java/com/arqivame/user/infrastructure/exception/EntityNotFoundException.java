package com.arqivame.user.infrastructure.exception;

import com.arqivame.user.domain.Entity;

public class EntityNotFoundException extends SilentInfrastructureException {

    private EntityNotFoundException(String entity, String id) {
        super("%s not found with id %s".formatted(entity, id));
    }

    public static EntityNotFoundException with(Entity<?> entity) {
        return new EntityNotFoundException(entity.getClass().getSimpleName(), entity.getId().getStringValue());
    }

}
