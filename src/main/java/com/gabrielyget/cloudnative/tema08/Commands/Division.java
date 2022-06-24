package com.gabrielyget.cloudnative.tema08.Commands;

import com.gabrielyget.cloudnative.tema08.CustomExceptions.DivisionException;

public class Division extends Operation {

    @Override
    public OperationsType getOperationType() {
        return OperationsType.DIVISION;
    }

    @Override
    protected void validation() {
        if (this.value1 == 0 && this.value2 == 0) throw new DivisionException("This is a mathematical indeterminacy. Cannot be calculated");
        else if (this.value2 == 0) throw new DivisionException("Division by 0 is undefined");
    }

    @Override
    protected Double calculate() {
        return this.value1 / this.value2;
    }

}
