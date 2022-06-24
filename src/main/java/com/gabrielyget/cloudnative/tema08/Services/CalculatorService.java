package com.gabrielyget.cloudnative.tema08.Services;


import com.gabrielyget.cloudnative.tema08.Commands.Operation;
import com.gabrielyget.cloudnative.tema08.Commands.OperationsType;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class
CalculatorService {
    ApplicationContext applicationContext;

    private static List<Operation> calculatorHistoric = new ArrayList<>();

    public CalculatorService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Double calculate(Double value1, Double value2, OperationsType operation) {
        Operation calculator = (Operation) applicationContext.getBean(operation.getOperationType());

        calculator.setValue(value1, value2);

        calculatorHistoric.add(calculator);

        return calculator.execute();
    }

    public void clearHistoric() {
        calculatorHistoric.clear();
    }

    public List<Operation> getCalculatorHistoric() {
        return calculatorHistoric;
    }
}
