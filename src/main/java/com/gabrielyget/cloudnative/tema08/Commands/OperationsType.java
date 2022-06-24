package com.gabrielyget.cloudnative.tema08.Commands;

public enum OperationsType {
    SUM("SUM"), SUB("SUB"), MULTIPLY("MULTIPLY"), DIVISION("DIVISION"), POW("POW");

    private String operationsType;

    private OperationsType(String operationType) {
        this.operationsType = operationType;
    }

    public String getOperationType() {
        return operationsType;
    }
}
