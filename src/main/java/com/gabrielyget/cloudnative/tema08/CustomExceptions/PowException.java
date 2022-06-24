package com.gabrielyget.cloudnative.tema08.CustomExceptions;

public class PowException extends RuntimeException {
    public PowException() {
        super();
    }

    public PowException(Throwable ex) {
        super(ex);
    }

    public PowException(String errorMessage) {
        super(errorMessage);
    }

    public PowException(String errorMessage, Exception exception) {
        super(errorMessage, exception);
    }
}
