package dev.sunless.auth_api.exceptions;

public class DuplicatedPermissionException extends RuntimeException {
    public DuplicatedPermissionException(String message) {
        super("Permission %s already is registered in the database".formatted(message));
    }

    public DuplicatedPermissionException(String message, Throwable cause) {
        super("Permission %s already is registered in the database".formatted(message), cause);
    }
}
