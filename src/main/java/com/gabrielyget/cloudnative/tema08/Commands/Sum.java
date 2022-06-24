package com.gabrielyget.cloudnative.tema08.Commands;

import com.gabrielyget.cloudnative.tema08.Commands.Operation;

public class Sum extends Operation {

    @Override
    public OperationsType getOperationType() {
        return OperationsType.SUM;
    }

    @Override
    protected void validation() {

    }

    @Override
    protected Double calculate() {
        return this.value1 + this.value2;
    }
}
