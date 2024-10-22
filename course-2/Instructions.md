# Command Reference Guide

## Quick Start Commands 🚀
```bash
# First time setup
./gradlew wrapper --gradle-version 8.8
chmod +x gradlew
./gradlew clean build
```

## Development Commands 💻

### Basic Build & Test
```bash
# Clean and build project
./gradlew clean build

# Run only tests
./gradlew test

# Build without tests
./gradlew build -x test

# Continuous build (watches for changes)
./gradlew build --continuous
```

### Testing Commands 🧪
```bash
# Run all tests
./gradlew test

# Run specific test class
./gradlew test --tests "CalculatorTest"

# Run specific test method
./gradlew test --tests "CalculatorTest.shouldAddNumbers"

# Run tests with debug output
./gradlew test --debug

# Generate test report
./gradlew test jacocoTestReport
```

### Performance Testing ⚡
```bash
# Run all benchmarks
./gradlew jmh

# Run specific benchmark
./gradlew jmh --include ".*additionPerformance.*"

# Run benchmarks with specific iterations
./gradlew jmh -PjmhIterations=10

# Run benchmarks with detailed output
./gradlew jmh --debug

# Export benchmark results to CSV
./gradlew jmh -PresultFormat=CSV -PresultsFile=results.csv
```

### Code Quality 📊
```bash
# Generate JaCoCo coverage report
./gradlew jacocoTestReport

# Check code coverage
./gradlew jacocoTestCoverageVerification

# Format code
./gradlew spotlessApply

# Check code style
./gradlew spotlessCheck
```

### Dependency Management 📦
```bash
# View project dependencies
./gradlew dependencies

# Check for dependency updates
./gradlew dependencyUpdates

# Clear Gradle cache
./gradlew cleanBuildCache
```

## AI-Assisted Development Commands 🤖

### GitHub Copilot
```bash
# Enable Copilot in IntelliJ
Settings -> Plugins -> Marketplace -> GitHub Copilot

# Trigger inline suggestions
Alt + ] (Mac: ⌥ + ])

# Show next suggestion
Alt + [ (Mac: ⌥ + [)

# Open Copilot panel
Ctrl + Shift + A -> "Copilot"
```

### JetBrains AI Assistant
```bash
# Enable AI Assistant
Settings -> Tools -> AI Assistant

# Generate implementation
Select code -> Right-click -> AI Assistant -> Implement

# Generate tests
Right-click on class -> AI Assistant -> Generate Tests

# Explain code
Select code -> Right-click -> AI Assistant -> Explain
```

### Amazon CodeWhisperer
```bash
# Enable CodeWhisperer
Settings -> Tools -> CodeWhisperer

# Accept suggestion
Tab

# Show next suggestion
Alt + Right (Mac: ⌥ + →)

# Show previous suggestion
Alt + Left (Mac: ⌥ + ←)
```

### Tabnine
```bash
# Enable Tabnine
Settings -> Plugins -> Marketplace -> Tabnine

# Accept suggestion
Tab

# Show alternative suggestions
Ctrl + Space
```

## Project-Specific Tasks 📝

### Calculator Development
```bash
# Run calculator demo
./gradlew run

# Run specific operation benchmark
./gradlew jmh --include "Calculator.*multiply.*"

# Test precision
./gradlew test --tests "CalculatorTest.precisionTests"
```

### Documentation Generation
```bash
# Generate Javadoc
./gradlew javadoc

# Generate all documentation
./gradlew documentation
```

## Continuous Integration Commands 🔄

### GitHub Actions
```bash
# Run CI workflow locally
gh workflow run build.yml

# View CI logs
gh run view

# Download CI artifacts
gh run download
```

## Debug Commands 🐛
```bash
# Run with debug logging
./gradlew test --debug

# Remote debug
./gradlew test --debug-jvm

# Show test output
./gradlew test -i
```

## Performance Analysis 📈
```bash
# Run all performance tests
./gradlew jmh

# Profile CPU usage
./gradlew jmh -PprofilerType=CPU

# Profile memory
./gradlew jmh -PprofilerType=HEAP

# Generate flame graph
./gradlew jmh -PprofilerType=FLAME_GRAPH
```

## Common Issues & Solutions 🔧

### Build Issues
```bash
# Clear Gradle cache
rm -rf ~/.gradle/caches/

# Refresh Gradle
./gradlew --refresh-dependencies

# Reset Gradle wrapper
./gradlew wrapper --gradle-version 8.8
```

### Performance Issues
```bash
# Increase Gradle memory
export GRADLE_OPTS="-Xmx2g -XX:MaxMetaspaceSize=512m"

# Clean build cache
./gradlew cleanBuildCache
```

### AI Tool Issues
```bash
# Reset Copilot
Settings -> GitHub Copilot -> Reset

# Clear AI Assistant cache
Settings -> AI Assistant -> Clear Cache

# Reload AI plugins
File -> Invalidate Caches -> Restart
```

## Environment Setup Commands 🛠️
```bash
# Check Java version
java -version

# Check Gradle version
./gradlew --version

# Verify environment
./gradlew environmentCheck

# Setup development environment
./gradlew setupDev
```
