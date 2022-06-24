package com.gabrielyget.cloudnative.tema08.CustomExceptions;

public class DivisionException extends RuntimeException {
    public DivisionException() {
        super();
    }

    public DivisionException(Throwable ex) {
        super(ex);
    }

    public DivisionException(String errorMessage) {
        super(errorMessage);
    }

    public DivisionException(String errorMessage, Exception exception) {
        super(errorMessage, exception);
    }
}