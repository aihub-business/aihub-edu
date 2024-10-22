package com.course2.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Calculator Tests")
class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Nested
    @DisplayName("Addition Tests")
    class AdditionTests {
        @ParameterizedTest(name = "{0} + {1} = {2}")
        @CsvSource({
                "1.0, 1.0, 2.0",
                "-1.0, 1.0, 0.0",
                "0.1, 0.2, 0.3",
                "999999.99, 0.01, 1000000.00"
        })
        void shouldAddNumbers(String a, String b, String expected) {
            assertThat(calculator.add(new BigDecimal(a), new BigDecimal(b)))
                    .isEqualByComparingTo(new BigDecimal(expected));
        }
    }

    @Nested
    @DisplayName("Division Tests")
    class DivisionTests {
        @ParameterizedTest(name = "{0} / {1} = {2}")
        @CsvSource({
                "6.0, 2.0, 3.0",
                "5.0, 2.0, 2.5",
                "0.0, 5.0, 0.0",
                "10.0, 2.0, 5.0"
        })
        void shouldDivideNumbers(String a, String b, String expected) {
            assertThat(calculator.divide(new BigDecimal(a), new BigDecimal(b)))
                    .isEqualByComparingTo(new BigDecimal(expected));
        }

        @ParameterizedTest(name = "Should throw exception when dividing {0} by zero")
        @CsvSource({"1.0", "0.0", "-1.0"})
        void shouldThrowExceptionWhenDividingByZero(String a) {
            assertThatThrownBy(() ->
                    calculator.divide(new BigDecimal(a), BigDecimal.ZERO))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Division by zero is not allowed");
        }
    }
}
