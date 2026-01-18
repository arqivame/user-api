package com.arqivame.user.domain.event;

import java.util.Optional;

@FunctionalInterface
public interface EventSource {

    Optional<Event<?>> nextEvent();

}
