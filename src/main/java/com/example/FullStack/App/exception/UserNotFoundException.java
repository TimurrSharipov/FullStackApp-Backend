package com.example.FullStack.App.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(Long id) {
       super("Couldn't find user with id " + id);
    }
}
