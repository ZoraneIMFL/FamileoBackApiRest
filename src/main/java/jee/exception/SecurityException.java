package jee.exception;

public class SecurityException extends BusinessException {

    public SecurityException() {
        super();
    }

    public SecurityException(final String message) {
        super(message);
    }

    public SecurityException(final Throwable cause) {
        super(cause);
    }

    public SecurityException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
