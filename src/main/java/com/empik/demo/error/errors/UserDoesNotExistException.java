package com.empik.demo.error.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException(String login) {
        super(String.format("User with login: %s does not exist", login));
    }
}
