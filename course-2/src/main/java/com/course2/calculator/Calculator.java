package com.course2.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator {

    private static final int DEFAULT_SCALE = 10;

    public BigDecimal add(BigDecimal a, BigDecimal b) {
        validateInputs(a, b);
        return a.add(b).setScale(DEFAULT_SCALE, RoundingMode.HALF_UP);
    }

    public BigDecimal subtract(BigDecimal a, BigDecimal b) {
        validateInputs(a, b);
        return a.subtract(b).setScale(DEFAULT_SCALE, RoundingMode.HALF_UP);
    }

    public BigDecimal multiply(BigDecimal a, BigDecimal b) {
        validateInputs(a, b);
        return a.multiply(b).setScale(DEFAULT_SCALE, RoundingMode.HALF_UP);
    }

    public BigDecimal divide(BigDecimal a, BigDecimal b) {
        validateInputs(a, b);
        if (b.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed");
        }
        return a.divide(b, DEFAULT_SCALE, RoundingMode.HALF_UP);
    }

    private void validateInputs(BigDecimal... numbers) {
        for (BigDecimal number : numbers) {
            if (number == null) {
                throw new IllegalArgumentException("Null input is not allowed");
            }
        }
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println("2 + 2 = " + calc.add(new BigDecimal("2"), new BigDecimal("2")));
        System.out.println("5 - 3 = " + calc.subtract(new BigDecimal("5"), new BigDecimal("3")));
        System.out.println("4 * 3 = " + calc.multiply(new BigDecimal("4"), new BigDecimal("3")));
        System.out.println("10 / 2 = " + calc.divide(new BigDecimal("10"), new BigDecimal("2")));
    }
}
