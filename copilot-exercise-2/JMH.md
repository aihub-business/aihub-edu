# Java Microbenchmark Harness (JMH) Guide

## Overview
JMH is a Java harness for building, running, and analyzing nano/micro/milli/macro benchmarks written in Java and other languages targeting the JVM.

## Basic Setup

### Gradle Configuration
```groovy
plugins {
    id 'me.champeau.jmh' version '0.7.1'
}

dependencies {
    jmh 'org.openjdk.jmh:jmh-core:1.37'
    jmh 'org.openjdk.jmh:jmh-generator-annprocess:1.37'
}

jmh {
    includes = ['.*Benchmark.*']  // Regex for benchmark classes
    warmupIterations = 2         // Number of warmup iterations
    iterations = 5               // Number of measurement iterations
    fork = 1                     // Number of forks
    failOnError = true          // Fail build on benchmark error
    resultFormat = 'CSV'        // Result format (CSV, JSON, TEXT, LATEX)
    resultsFile = project.file("${project.buildDir}/reports/jmh/results.csv")
    timeOnIteration = '1s'      // Time per iteration
    timeUnit = 'us'             // Result time unit (ns, us, ms, s)
}
```

## Basic JMH Annotations

### Core Annotations
```java
@State(Scope.Thread)           // Defines benchmark state
@BenchmarkMode(Mode.AverageTime) // Measurement mode
@OutputTimeUnit(TimeUnit.MICROSECONDS) // Result time unit
@Warmup(iterations = 2)        // Warmup settings
@Measurement(iterations = 5)    // Measurement settings
@Fork(1)                       // Number of separate JVM forks
public class CalculatorBenchmark {
    // Benchmark code here
}
```

### Benchmark State
```java
@State(Scope.Thread)  // State per thread
public class BenchmarkState {
    private Calculator calculator;
    private BigDecimal a;
    private BigDecimal b;

    @Setup
    public void setup() {
        calculator = new Calculator();
        a = new BigDecimal("999999.99999");
        b = new BigDecimal("0.00001");
    }

    @TearDown
    public void tearDown() {
        // Cleanup code
    }
}
```

## Common Benchmark Patterns

### 1. Simple Benchmark
```java
@Benchmark
public void basicBenchmark(Blackhole blackhole) {
    blackhole.consume(calculator.add(a, b));
}
```

### 2. Parameterized Benchmark
```java
@State(Scope.Benchmark)
public class ParameterizedBenchmark {
    @Param({"1000", "10000", "100000"})
    public int size;

    @Benchmark
    public void parameterizedBenchmark(Blackhole blackhole) {
        // Use 'size' parameter
    }
}
```

### 3. Multiple Measurement Modes
```java
@Fork(1)
@Warmup(iterations = 2)
@Measurement(iterations = 5)
@BenchmarkMode({Mode.Throughput, Mode.AverageTime})
public class MultipleModesBenchmark {
    @Benchmark
    public void measureMultipleModes() {
        // Benchmark code
    }
}
```

## Advanced JMH Features

### 1. Custom State Injection
```java
@State(Scope.Thread)
public class CustomState {
    @Setup(Level.Trial)  // Once per trial
    public void setupTrial() {}

    @Setup(Level.Iteration)  // Once per iteration
    public void setupIteration() {}

    @Setup(Level.Invocation)  // Once per invocation
    public void setupInvocation() {}
}
```

### 2. Grouped Benchmarks
```java
@GroupThreads(4)  // 4 threads for this group
@Group("calculatorGroup")
@Benchmark
public void groupedBenchmark() {
    // Benchmark code
}
```

### 3. Asymmetric Benchmarks
```java
public class AsyncBenchmark {
    @Benchmark
    @Group("asyncGroup")
    @GroupThreads(1)
    public void producer() {
        // Producer code
    }

    @Benchmark
    @Group("asyncGroup")
    @GroupThreads(3)
    public void consumer() {
        // Consumer code
    }
}
```

## Running Benchmarks

### Command Line Options
```bash
# Run all benchmarks
./gradlew jmh

# Run specific benchmark
./gradlew jmh --include ".*addition.*"

# Run with specific iterations
./gradlew jmh -PjmhIterations=10

# Run with specific warmups
./gradlew jmh -PjmhWarmupIterations=5

# Custom result format
./gradlew jmh -PresultFormat=JSON

# Profiler enabled
./gradlew jmh -PprofilerEnabled=true
```

## Result Analysis

### Sample Output
```
Benchmark                          Mode  Cnt   Score   Error  Units
CalculatorBenchmark.addSimple     avgt   25   2.542 ± 0.127  us/op
CalculatorBenchmark.multiplyLarge avgt   25  15.234 ± 0.542  us/op
```

### Understanding Results
- **Mode**: Measurement mode (avg, thrpt, sample)
- **Cnt**: Number of iterations
- **Score**: Primary result metric
- **Error**: Statistical error margin
- **Units**: Measurement units

## Best Practices

### 1. Dead Code Elimination
```java
@Benchmark
public void correctBenchmark(Blackhole blackhole) {
    BigDecimal result = calculator.add(a, b);
    blackhole.consume(result);  // Prevent optimization
}
```

### 2. Proper State Management
```java
@State(Scope.Thread)
public class StateExample {
    private Object heavyObject;

    @Setup(Level.Trial)
    public void setup() {
        heavyObject = createHeavyObject();
    }

    @TearDown(Level.Trial)
    public void tearDown() {
        heavyObject = null;
    }
}
```

### 3. Fork Isolation
```java
@Fork(value = 3, jvmArgs = {"-Xms2G", "-Xmx2G"})
public class IsolatedBenchmark {
    @Benchmark
    public void isolatedTest() {
        // Code runs in isolated JVM
    }
}
```

## Common Pitfalls

### 1. Constant Folding
```java
// Bad
@Benchmark
public int badBenchmark() {
    return 1 + 2;  // Compiler optimizes this away
}

// Good
@Benchmark
public int goodBenchmark(BenchmarkState state) {
    return state.getA() + state.getB();  // Uses dynamic values
}
```

### 2. Loop Optimization
```java
// Bad
@Benchmark
public void badLoop() {
    for (int i = 0; i < 1000; i++) {
        // Empty loop might be optimized away
    }
}

// Good
@Benchmark
public void goodLoop(Blackhole blackhole) {
    for (int i = 0; i < 1000; i++) {
        blackhole.consume(i);  // Prevents optimization
    }
}
```

## Performance Profiling

### JMH Profilers
```java
@Fork(jvmArgsAppend = {
    "-XX:+UnlockDiagnosticVMOptions",
    "-XX:+DebugNonSafepoints"
})
@Profilers({
    "gc",           // Garbage collector profiling
    "stack",        // Stack profiling
    "perfasm",      // Assembly profiling
    "jfr"           // Java Flight Recorder
})
public class ProfiledBenchmark {
    @Benchmark
    public void profiledTest() {
        // Benchmark code
    }
}
```
