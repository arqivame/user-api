package com.arqivame.user.domain.user;

import static java.util.Objects.isNull;

import java.time.Instant;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import com.arqivame.user.domain.AggregateRoot;
import com.arqivame.user.domain.event.Event;
import com.arqivame.user.domain.event.EventSource;
import com.arqivame.user.domain.exception.InvalidStateException;
import com.arqivame.user.domain.user.event.UserCreatedEvent;
import com.arqivame.user.domain.validation.ValidationError;
import com.arqivame.user.domain.validation.ValidationHandler;
import com.arqivame.user.domain.validation.handler.Notification;

public class User extends AggregateRoot<UserID> implements EventSource {

    private Email email;
    private Username username;
    private Nickname nickname;

    private Boolean active;

    private Instant createdAt;
    private Instant updatedAt;

    private final Queue<Event<?>> events;

    public User(
            final UserID id,
            final Email email,
            final Username username,
            final Nickname nickname,
            final Boolean active,
            final Instant createdAt,
            final Instant updatedAt,
            final Queue<Event<?>> events) {
        super(id);
        this.email = email;
        this.username = username;
        this.nickname = nickname;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

        this.events = isNull(events) ? new LinkedList<>() : new LinkedList<>(events);

        selfValidate();

    }

    public static User with(
            final UserID id,
            final Email email,
            final Username username,
            final Nickname nickname,
            final Boolean active,
            final Instant createdAt,
            final Instant updatedAt,
            final Queue<Event<?>> events) {
        return new User(
                id,
                email,
                username,
                nickname,
                active,
                createdAt,
                updatedAt,
                events);
    }

    public static User create(
            final UserID id,
            final Username username,
            final Email email,
            final Nickname nickname) {

        final Instant now = Instant.now();

        final User user = new User(
                id,
                email,
                username,
                nickname,
                false,
                now,
                now,
                null);

        user.events.add(UserCreatedEvent.create(user));

        return user;
    }

    @Override
    public void validate(final ValidationHandler handler) {

        if (isNull(email))
            handler.append(ValidationError.with("'email' should not be null"));
        else
            email.validate(handler);

        if (isNull(username))
            handler.append(ValidationError.with("'username' should not be null"));
        else
            username.validate(handler);

        if (isNull(nickname))
            handler.append(ValidationError.with("'nickname' should not be null"));
        else
            nickname.validate(handler);

    }

    @Override
    public Optional<Event<?>> nextEvent() {
        return Optional.ofNullable(this.events.poll());
    }

    private void selfValidate() {
        final Notification notification = Notification.create();
        validate(notification);
        if (notification.hasErrors())
            throw InvalidStateException.with(User.class, notification.getDomainErrors());
    }

    public Email getEmail() {
        return email;
    }

    public Username getUsername() {
        return username;
    }

    public Nickname getNickname() {
        return nickname;
    }

    public Boolean getActive() {
        return active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

}
