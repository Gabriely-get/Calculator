package com.gabrielyget.cloudnative.tema08.Commands;

public abstract class Operation {
    protected Double value1;
    protected Double value2;

    public abstract OperationsType getOperationType();
    protected abstract void validation();
    protected abstract Double    calculate();

    public void setValue(Double value1, Double value2) {
        if (value1 == null || value2 == null ) throw new IllegalArgumentException("Invalid value");

        this.value1 = value1;
        this.value2 = value2;
    };

    public final Double execute() {
        validation();
        return calculate();
    }
}
