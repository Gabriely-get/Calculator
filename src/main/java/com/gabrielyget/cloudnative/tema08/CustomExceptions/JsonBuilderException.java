package com.gabrielyget.cloudnative.tema08.CustomExceptions;

public class JsonBuilderException extends RuntimeException {

    public JsonBuilderException() {
        super();
    }

    public JsonBuilderException(Throwable ex) {
        super(ex);
    }

    public JsonBuilderException(String errorMessage) {
        super(errorMessage);
    }

    public JsonBuilderException(String errorMessage, Exception exception) {
        super(errorMessage, exception);
    }
}
