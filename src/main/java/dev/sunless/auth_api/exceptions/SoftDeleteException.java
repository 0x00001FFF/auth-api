package dev.sunless.auth_api.exceptions;

public class SoftDeleteException extends RuntimeException {
    public SoftDeleteException(String message, Throwable cause) {
        super(message, cause);
    }
  public SoftDeleteException(String message) {
    super(message);
  }
}
