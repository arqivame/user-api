package com.arqivame.user.infrastructure.exception;

import org.springframework.http.HttpStatus;

public abstract class HttpException extends VerboseInfrastructureException {

    private final HttpStatus status;

    protected HttpException(final String message, final HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

}
