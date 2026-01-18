package com.arqivame.user.domain.event;

import com.arqivame.user.domain.Entity;

public record EventEntity(String type, String id) {

    public static EventEntity of(final Entity<?> entity) {
        return new EventEntity(entity.getClass().getSimpleName(), entity.getId().getStringValue());
    }

}
