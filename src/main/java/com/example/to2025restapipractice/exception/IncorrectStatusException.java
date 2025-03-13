package com.example.to2025restapipractice.exception;

public class IncorrectStatusException extends RuntimeException {

    public IncorrectStatusException() {
        super();
    }

    public IncorrectStatusException(String message) {
        super(message);
    }
}
