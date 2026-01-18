package com.arqivame.user.infrastructure.exception;

import java.util.List;

public abstract class VerboseInfrastructureException extends InfrastructureException {

    private static final boolean VERBOSE = true;

    protected VerboseInfrastructureException(final String message) {
        super(message, null, null, VERBOSE);
    }

    protected VerboseInfrastructureException(
            final String message,
            final List<InfrastructureException.Error> errors) {
        super(message, errors, null, VERBOSE);
    }

    protected VerboseInfrastructureException(
            final String message,
            final List<InfrastructureException.Error> errors,
            final Throwable cause) {
        super(message, errors, cause, VERBOSE);
    }

}
