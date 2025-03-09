package dev.sunless.auth_api.exceptions;

public class DuplicatedException extends RuntimeException {
    public DuplicatedException(String message) {
        super("%s is already registered in the database".formatted(message));
    }

    public DuplicatedException(String message, Throwable cause) {
        super("%s is already registered in the database".formatted(message), cause);
    }
}
