package com.gabrielyget.cloudnative.tema08.Commands;

public class Multiply extends Operation {

    @Override
    public OperationsType getOperationType() {
        return OperationsType.MULTIPLY;
    }

    @Override
    protected void validation() {

    }

    @Override
    protected Double calculate() {
        return this.value1 * this.value2;
    }

}
