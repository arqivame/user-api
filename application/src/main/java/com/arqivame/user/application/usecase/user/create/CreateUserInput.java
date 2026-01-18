package com.arqivame.user.application.usecase.user.create;

public record CreateUserInput(String username, String email, String password) {

    public static CreateUserInput with(
            final String username,
            final String email,
            final String password) {
        return new CreateUserInput(username, email, password);
    }

    @Override
    public String toString() {
        return "CreateUserInput [username=" + username
                + ", email=" + email
                + ", password=[*****]"
                + "]";
    }
}
