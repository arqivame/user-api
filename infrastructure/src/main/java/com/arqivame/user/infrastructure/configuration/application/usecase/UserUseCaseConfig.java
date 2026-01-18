package com.arqivame.user.infrastructure.configuration.application.usecase;

import static java.util.Objects.requireNonNull;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.arqivame.user.application.usecase.user.create.CreateUserUseCase;
import com.arqivame.user.application.usecase.user.create.DefaultCreateUserUseCase;
import com.arqivame.user.domain.event.EventDispatcher;
import com.arqivame.user.domain.user.gateway.command.UserCommandGateway;

@Configuration
public class UserUseCaseConfig {

    private final EventDispatcher eventDispatcher;
    private final UserCommandGateway userCommandGateway;

    public UserUseCaseConfig(
            final EventDispatcher eventDispatcher,
            final UserCommandGateway userCommandGateway) {
        this.eventDispatcher = requireNonNull(eventDispatcher);
        this.userCommandGateway = requireNonNull(userCommandGateway);
    }

    @Bean
    CreateUserUseCase createUserUseCase() {
        return new DefaultCreateUserUseCase(eventDispatcher, userCommandGateway);
    }

}
