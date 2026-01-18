package com.arqivame.user.domain.user.event;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.arqivame.user.domain.event.Event;
import com.arqivame.user.domain.event.EventEntity;
import com.arqivame.user.domain.event.EventMetadata;
import com.arqivame.user.domain.user.User;

public class UserCreatedEvent extends Event<UserCreatedEvent.Data> {

    private static final String ENTITY = "user";
    private static final String ACTION = "created";
    private static final String VERSION = "0.0.1";

    private static final UserCreatedEvent DEFAULT_INSTANCE = new UserCreatedEvent();

    private UserCreatedEvent() {
        super(EventMetadata.create(ENTITY, ACTION, VERSION, Instant.now(), Set.of()), null);
    }

    private UserCreatedEvent(
            Instant occurredAt,
            Set<EventEntity> relatedEntities,
            UserCreatedEvent.Data data) {
        super(EventMetadata.create(ENTITY, ACTION, VERSION, occurredAt, relatedEntities), data);
    }

    public record Data(UUID userId) implements Serializable {

        public static Data of(final User user) {
            return new Data(user.getId().getValue());
        }

    }

    public static UserCreatedEvent create(final User user) {
        return new UserCreatedEvent(
                Instant.now(),
                Stream
                        .of(EventEntity.of(user))
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet()),
                Data.of(user));
    }

    public static String eventKey() {
        return DEFAULT_INSTANCE.key();
    }

}
