package jee.exception;

public class InternalException extends BusinessException {

    public InternalException() {
        super();
    }

    public InternalException(final String message) {
        super(message);
    }

    public InternalException(final Throwable cause) {
        super(cause);
    }

    public InternalException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
