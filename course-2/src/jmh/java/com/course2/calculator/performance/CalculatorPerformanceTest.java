package com.course2.calculator.performance;

import com.course2.calculator.Calculator;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 2, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(1)
public class CalculatorPerformanceTest {

    private Calculator calculator;
    private BigDecimal a;
    private BigDecimal b;
    private BigDecimal largePrecisionA;
    private BigDecimal largePrecisionB;

    @Setup
    public void setup() {
        calculator = new Calculator();
        // Regular numbers
        a = new BigDecimal("999999.99999");
        b = new BigDecimal("0.00001");
        // Large precision numbers
        largePrecisionA = new BigDecimal("999999.9999999999999999999999");
        largePrecisionB = new BigDecimal("0.0000000000000000000001");
    }

    @Benchmark
    public void additionPerformance(Blackhole blackhole) {
        blackhole.consume(calculator.add(a, b));
    }

    @Benchmark
    public void subtractionPerformance(Blackhole blackhole) {
        blackhole.consume(calculator.subtract(a, b));
    }

    @Benchmark
    public void multiplicationPerformance(Blackhole blackhole) {
        blackhole.consume(calculator.multiply(a, b));
    }

    @Benchmark
    public void divisionPerformance(Blackhole blackhole) {
        blackhole.consume(calculator.divide(a, b));
    }

    @Benchmark
    public void largePrecisionAdditionPerformance(Blackhole blackhole) {
        blackhole.consume(calculator.add(largePrecisionA, largePrecisionB));
    }

    @Benchmark
    public void largePrecisionMultiplicationPerformance(Blackhole blackhole) {
        blackhole.consume(calculator.multiply(largePrecisionA, largePrecisionB));
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(CalculatorPerformanceTest.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
