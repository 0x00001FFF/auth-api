package dev.sunless.auth_api.exceptions;

public class DuplicatedException extends RuntimeException {
    public DuplicatedException(String message) {
        super("%s already is registered in the database".formatted(message));
    }

    public DuplicatedException(String message, Throwable cause) {
        super("%s already is registered in the database".formatted(message), cause);
    }
}
