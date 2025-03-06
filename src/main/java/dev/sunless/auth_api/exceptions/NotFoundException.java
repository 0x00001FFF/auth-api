package dev.sunless.auth_api.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super("%s not found".formatted(message));
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
