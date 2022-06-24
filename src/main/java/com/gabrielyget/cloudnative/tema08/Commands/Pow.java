package com.gabrielyget.cloudnative.tema08.Commands;

import com.gabrielyget.cloudnative.tema08.CustomExceptions.PowException;

import java.lang.Math;

public class Pow extends Operation {

    @Override
    public OperationsType getOperationType() {
        return OperationsType.POW;
    }

    @Override
    protected void validation() {
        if (this.value1 == 0 && this.value2 <= 0) throw new PowException("This is a mathematical indeterminacy. Cannot be calculated");
    }

    @Override
    protected Double calculate() {
        return Math.pow(this.value1, this.value2);
    }

}