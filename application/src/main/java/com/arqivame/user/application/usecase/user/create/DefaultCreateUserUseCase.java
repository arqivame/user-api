package com.arqivame.user.application.usecase.user.create;

import static java.util.Objects.requireNonNull;

import com.arqivame.user.domain.event.EventDispatcher;
import com.arqivame.user.domain.exception.ValidationException;
import com.arqivame.user.domain.user.Email;
import com.arqivame.user.domain.user.Nickname;
import com.arqivame.user.domain.user.Password;
import com.arqivame.user.domain.user.PreUser;
import com.arqivame.user.domain.user.User;
import com.arqivame.user.domain.user.Username;
import com.arqivame.user.domain.user.gateway.command.UserCommandGateway;
import com.arqivame.user.domain.validation.handler.Notification;

public class DefaultCreateUserUseCase extends CreateUserUseCase {

    private final EventDispatcher eventDispatcher;

    private final UserCommandGateway userCommandGateway;

    public DefaultCreateUserUseCase(
            final EventDispatcher eventDispatcher,
            final UserCommandGateway userCommandGateway) {
        this.eventDispatcher = requireNonNull(eventDispatcher);
        this.userCommandGateway = requireNonNull(userCommandGateway);
    }

    @Override
    public CreateUserOutput execute(final CreateUserInput input) {

        final Username username = Username.of(input.username());
        final Nickname nickname = Nickname.of(input.username());
        final Email email = Email.of(input.email());
        final Password password = Password.of(input.password());

        final PreUser preUser = PreUser.with(username, email, password, nickname);

        final Notification notification = Notification.create();
        preUser.validate(notification);

        if (notification.hasErrors())
            throw ValidationException.with("Validation error", notification.getErrors());

        final User user = this.userCommandGateway.create(preUser);
        this.eventDispatcher.notify(user);

        return CreateUserOutput.from(user);

    }

}
