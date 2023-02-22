package jee.exception;

import jakarta.ejb.ApplicationException;

@ApplicationException(rollback = true)
public abstract class BusinessException extends RuntimeException {

    protected BusinessException() {
        super();
    }

    protected BusinessException(final String message) {
        super(message);
    }

    protected BusinessException(final Throwable cause) {
        super(cause);
    }

    protected BusinessException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
