package com.arqivame.user.infrastructure.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arqivame.user.application.usecase.user.create.CreateUserInput;
import com.arqivame.user.application.usecase.user.create.CreateUserOutput;
import com.arqivame.user.application.usecase.user.create.CreateUserUseCase;

@RestController
public class TestController {

    private final CreateUserUseCase createUserUseCase;

    public TestController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping("users")
    public ResponseEntity<CreateUserOutput> test(@RequestBody CreateUserInput input) {
        return ResponseEntity.ok(createUserUseCase.execute(input));
    }

}
