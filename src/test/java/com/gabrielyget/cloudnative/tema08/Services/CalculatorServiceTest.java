package com.gabrielyget.cloudnative.tema08.Services;

import com.gabrielyget.cloudnative.tema08.Commands.OperationsType;
import com.gabrielyget.cloudnative.tema08.Config.AppConfig;
import com.gabrielyget.cloudnative.tema08.CustomExceptions.DivisionException;
import com.gabrielyget.cloudnative.tema08.CustomExceptions.PowException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
public class CalculatorServiceTest {

    @Autowired
    CalculatorService calculatorService;

    @BeforeEach
    public void clearHistoric() {
        calculatorService.clearHistoric();
    }

    @Test
    public void shouldSum() {
        Assertions.assertEquals(3.0, calculatorService.calculate(1.0, 2.0, OperationsType.SUM));
    }

    @Test
    public void shouldReturnExceptionIfSumFirstValueIsNull() {
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> calculatorService.calculate(null, 2.0, OperationsType.SUM) );

        Assertions.assertEquals("Invalid value", illegalArgumentException.getMessage());
    }

    @Test
    public void shouldReturnExceptionIfSumSecondValueIsNull() {
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> calculatorService.calculate(1.0, null, OperationsType.SUM) );

        Assertions.assertEquals("Invalid value", illegalArgumentException.getMessage());
    }

    @Test
    public void shouldSub() {
        Assertions.assertEquals(-1, calculatorService.calculate(1.0, 2.0, OperationsType.SUB));
    }

    @Test
    public void shouldReturnExceptionIfSubFirstValueIsNull() {
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> calculatorService.calculate(null, 2.0, OperationsType.SUB) );

        Assertions.assertEquals("Invalid value", illegalArgumentException.getMessage());
    }

    @Test
    public void shouldReturnExceptionIfSubSecondValueIsNull() {
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> calculatorService.calculate(1.0, null, OperationsType.SUB) );

        Assertions.assertEquals("Invalid value", illegalArgumentException.getMessage());
    }

    @Test
    public void shouldMultiply() {
        Assertions.assertEquals(2.0, calculatorService.calculate(1.0, 2.0, OperationsType.MULTIPLY));
    }

    @Test
    public void shouldReturnExceptionIfMultiplyFirstValueIsNull() {
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> calculatorService.calculate(null, 2.0, OperationsType.MULTIPLY) );

        Assertions.assertEquals("Invalid value", illegalArgumentException.getMessage());
    }

    @Test
    public void shouldReturnExceptionIfMultiplySecondValueIsNull() {
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> calculatorService.calculate(1.0, null, OperationsType.MULTIPLY) );

        Assertions.assertEquals("Invalid value", illegalArgumentException.getMessage());
    }

    @Test
    public void shouldDivision() {
        Assertions.assertEquals(0.5, calculatorService.calculate(1.0, 2.0, OperationsType.DIVISION));
    }

    @Test
    public void shouldReturnExceptionIfDivisionFirstValueIsNull() {
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> calculatorService.calculate(null, 2.0, OperationsType.DIVISION) );

        Assertions.assertEquals("Invalid value", illegalArgumentException.getMessage());
    }

    @Test
    public void shouldReturnExceptionIfDivisionSecondValueIsNull() {
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> calculatorService.calculate(1.0, null, OperationsType.DIVISION) );

        Assertions.assertEquals("Invalid value", illegalArgumentException.getMessage());
    }

    @Test
    public void shouldReturnExceptionIfDivideByZero() {
        DivisionException divisionException = Assertions.assertThrows(DivisionException.class, () ->
                calculatorService.calculate(2.0,0.0, OperationsType.DIVISION));

        Assertions.assertEquals("Division by 0 is undefined", divisionException.getMessage());
    }

    @Test
    public void shouldReturnExceptionIfZeroDivideByZero() {
        DivisionException divisionException = Assertions.assertThrows(DivisionException.class, () ->
                calculatorService.calculate(0.0,0.0, OperationsType.DIVISION));

        Assertions.assertEquals("This is a mathematical indeterminacy. Cannot be calculated", divisionException.getMessage());
    }

    @Test
    public void shouldPow() {
        Assertions.assertEquals(4, calculatorService.calculate(2.0, 2.0, OperationsType.POW));
        Assertions.assertEquals(0, calculatorService.calculate(0.0, 2.0, OperationsType.POW));
        Assertions.assertEquals(1, calculatorService.calculate(2.0, 0.0, OperationsType.POW));
    }

    @Test
    public void shouldReturnExceptionIfPowFirstValueIsNull() {
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> calculatorService.calculate(null, 2.0, OperationsType.POW) );

        Assertions.assertEquals("Invalid value", illegalArgumentException.getMessage());
    }

    @Test
    public void shouldReturnExceptionIfPowSecondValueIsNull() {
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> calculatorService.calculate(1.0, null, OperationsType.MULTIPLY) );

        Assertions.assertEquals("Invalid value", illegalArgumentException.getMessage());
    }

    @Test
    public void shouldReturnExceptionIfPowByExponentZero() {
        PowException powException = Assertions.assertThrows(PowException.class, () ->
                calculatorService.calculate(0.0,0.0, OperationsType.POW));

        Assertions.assertEquals("This is a mathematical indeterminacy. Cannot be calculated", powException.getMessage());
    }

    @Test
    public void shouldReturnExceptionIfPowByExponentNegative() {
        PowException powException = Assertions.assertThrows(PowException.class, () ->
                calculatorService.calculate(0.0,-2.0, OperationsType.POW));

        Assertions.assertEquals("This is a mathematical indeterminacy. Cannot be calculated", powException.getMessage());
    }

    @Test
    public void shouldGetCalculatorHistoric() {
        calculatorService.calculate(1.0, 2.0, OperationsType.SUM);
        calculatorService.calculate(1.0, 2.0, OperationsType.SUB);
        calculatorService.calculate(1.0, 2.0, OperationsType.MULTIPLY);
        calculatorService.calculate(1.0, 2.0, OperationsType.DIVISION);
        calculatorService.calculate(1.0, 2.0, OperationsType.POW);

        Assertions.assertEquals(5, calculatorService.getCalculatorHistoric().size());

        calculatorService.clearHistoric();

        Assertions.assertEquals(0, calculatorService.getCalculatorHistoric().size());
    }
}
