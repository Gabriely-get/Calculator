package com.gabrielyget.cloudnative.tema08.Builders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gabrielyget.cloudnative.tema08.Commands.OperationsType;
import org.springframework.lang.Nullable;

public class JsonResponseBuilder {

    private final ObjectMapper mapper = new ObjectMapper();
    private final ObjectNode response = mapper.createObjectNode();
    private ObjectNode responseError;

    public JsonResponseBuilder() {

    }

    public static JsonResponseBuilder builder() {
        return new JsonResponseBuilder();
    }

    public JsonResponseBuilder withMessage(String message) {
        this.response.put("message",message);
        return this;
    }

    public JsonResponseBuilder withResult(Double result) {
        this.response.put("result", result);
        return this;
    }

    public JsonResponseBuilder withOperation(OperationsType operation) {
        this.response.put("operation", operation.getOperationType());
        return this;
    }

    public JsonResponseBuilder withError(@Nullable String operation, String errorMessage) {
        if (operation != null) this.response.put("operation", operation);
        this.response.put("message", errorMessage);
        this.withinError();
        return this;
    }

    private void withinError() {
        this.responseError = mapper.createObjectNode();
        this.responseError.set("Error", this.response);
    }

    public String build() throws JsonProcessingException {

        return this.responseError == null
                ? mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.response)
                : mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.responseError);
    }
}