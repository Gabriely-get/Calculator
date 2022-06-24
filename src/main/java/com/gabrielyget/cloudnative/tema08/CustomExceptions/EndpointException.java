package com.gabrielyget.cloudnative.tema08.CustomExceptions;

public class EndpointException extends RuntimeException {
    public EndpointException() {
        super();
    }

    public EndpointException(Throwable ex) {
        super(ex);
    }

    public EndpointException(String errorMessage) {
        super(errorMessage);
    }

    public EndpointException(String errorMessage, Exception exception) {
        super(errorMessage, exception);
    }
}
