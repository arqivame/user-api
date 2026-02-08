package com.arqivame.user.infrastructure.event.message;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

import com.arqivame.user.domain.event.EventMetadata;

public interface EventMessage<D extends Serializable> extends Serializable {

    public Metadata metadata();

    public D data();

    public record Metadata(
            String domain,
            String entity,
            String action,
            String version,
            Instant occurredAt,
            Set<EventEntity> relatedEntities) {

        public record EventEntity(String type, String id) {

        }

        public static Metadata from(final EventMetadata eventMetadata) {
            return new Metadata(
                    eventMetadata.domain(),
                    eventMetadata.entity(),
                    eventMetadata.action(),
                    eventMetadata.version(),
                    eventMetadata.occurredAt(),
                    eventMetadata.relatedEntities().stream()
                            .map(e -> new EventEntity(e.type(), e.id()))
                            .collect(java.util.stream.Collectors.toSet()));
        }

    }

}
