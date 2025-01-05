package com.openwebinars.rest.users;

public record NewUserCommand(String username, String email, String password) {
}
