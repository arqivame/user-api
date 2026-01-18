package com.arqivame.user.application.usecase;

public abstract class UseCase<I, O> {

    public abstract O execute(I input);

}
