package jee.exception;

import jakarta.ejb.ApplicationException;

@ApplicationException(rollback = true)
public abstract class BusinessException extends RuntimeException {

    public BusinessException() {
        super();
    }

    public BusinessException(final String message) {
        super(message);
    }

    public BusinessException(final Throwable cause) {
        super(cause);
    }

    public BusinessException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
