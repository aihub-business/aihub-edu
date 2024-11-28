# JaCoCo (Java Code Coverage) Guide

## Overview
JaCoCo is a code coverage library for Java that measures how much of your code is covered by tests.

## Basic Setup

### Gradle Configuration
```groovy
plugins {
    id 'jacoco'
}

jacoco {
    toolVersion = "0.8.11"  // JaCoCo version
}

test {
    finalizedBy jacocoTestReport  // Generate report after tests
}

jacocoTestReport {
    dependsOn test    // Ensure tests are run before generating report
    
    reports {
        xml.required = true    // For CI tools like SonarQube
        html.required = true   // For human-readable reports
        csv.required = false
    }
}

// Verification rules
jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = 0.80  // 80% coverage required
            }
        }
        
        rule {
            element = 'CLASS'
            excludes = ['*Test']
            limit {
                counter = 'LINE'
                value = 'COVEREDRATIO'
                minimum = 0.80
            }
        }
        
        rule {
            element = 'METHOD'
            limit {
                counter = 'LINE'
                value = 'COVEREDRATIO'
                minimum = 0.80
            }
        }
    }
}
```

## Coverage Types

### 1. Line Coverage
```groovy
rule {
    element = 'CLASS'
    limit {
        counter = 'LINE'
        value = 'COVEREDRATIO'
        minimum = 0.80
    }
}
```

### 2. Branch Coverage
```groovy
rule {
    element = 'CLASS'
    limit {
        counter = 'BRANCH'
        value = 'COVEREDRATIO'
        minimum = 0.70
    }
}
```

### 3. Method Coverage
```groovy
rule {
    element = 'METHOD'
    limit {
        counter = 'METHOD'
        value = 'COVEREDRATIO'
        minimum = 0.90
    }
}
```

### 4. Class Coverage
```groovy
rule {
    element = 'CLASS'
    limit {
        counter = 'CLASS'
        value = 'MISSEDCOUNT'
        maximum = 0
    }
}
```

## Execution Commands

### Basic Usage
```bash
# Generate coverage report
./gradlew test jacocoTestReport

# Verify coverage meets rules
./gradlew jacocoTestCoverageVerification

# Both report and verification
./gradlew test jacocoTestReport jacocoTestCoverageVerification

# Clean and regenerate
./gradlew clean test jacocoTestReport
```

### Report Locations
```
build/reports/jacoco/test/html/index.html     # HTML report
build/reports/jacoco/test/jacocoTestReport.xml # XML report
build/reports/jacoco/test/jacocoTestReport.csv # CSV report
```

## Advanced Configuration

### 1. Excluding Classes/Packages
```groovy
jacocoTestReport {
    afterEvaluate {
        classDirectories.setFrom(files(classDirectories.files.collect {
            fileTree(dir: it, exclude: [
                '**/model/**',
                '**/config/**',
                '**/dto/**',
                '**/*Constants*',
                '**/*Exception*'
            ])
        }))
    }
}
```

### 2. Custom Rules
```groovy
jacocoTestCoverageVerification {
    violationRules {
        rule {
            element = 'CLASS'
            includes = ['com.example.service.*']
            
            limit {
                counter = 'LINE'
                value = 'COVEREDRATIO'
                minimum = 0.90
            }
            
            limit {
                counter = 'BRANCH'
                value = 'COVEREDRATIO'
                minimum = 0.80
            }
        }
    }
}
```

### 3. Multiple Report Formats
```groovy
jacocoTestReport {
    reports {
        xml.required = true
        csv.required = true
        html.required = true
        html.outputLocation = layout.buildDirectory.dir('jacocoHtml')
    }
}
```

## Integration with CI/CD

### GitHub Actions
```yaml
name: Code Coverage

on: [push, pull_request]

jobs:
  coverage:
    runs-on: ubuntu-latest
    
    steps:
    - uses: actions/checkout@v3
    
    - name: Set up JDK
      uses: actions/setup-java@v3
      with:
        java-version: '21'
        distribution: 'temurin'
        
    - name: Generate Coverage Report
      run: ./gradlew test jacocoTestReport
      
    - name: Upload Coverage Report
      uses: actions/upload-artifact@v3
      with:
        name: coverage-report
        path: build/reports/jacoco/
```

### SonarQube Integration
```groovy
plugins {
    id "org.sonarqube" version "4.4.1.3373"
}

sonar {
    properties {
        property "sonar.coverage.jacoco.xmlReportPaths", 
                 "${buildDir}/reports/jacoco/test/jacocoTestReport.xml"
    }
}
```

## Best Practices

### 1. Coverage Thresholds
```groovy
jacocoTestCoverageVerification {
    violationRules {
        rule {
            // Business Logic
            includes = ['com.example.service.*']
            limit {
                minimum = 0.90
            }
        }
        rule {
            // Data Models
            includes = ['com.example.model.*']
            limit {
                minimum = 0.70
            }
        }
    }
}
```

### 2. Incremental Coverage
```groovy
// Ensure new code has high coverage while allowing legacy code exceptions
rule {
    element = 'CLASS'
    includes = ['com.example.newfeature.*']
    limit {
        counter = 'LINE'
        value = 'COVEREDRATIO'
        minimum = 0.95
    }
}
```

### 3. Documentation
```groovy
// Adding coverage info to reports
jacocoTestReport {
    reports {
        html.required = true
        html.outputLocation = layout.buildDirectory.dir('jacocoHtml')
    }
    
    doLast {
        println "JaCoCo coverage report generated at: ${reports.html.outputLocation}"
    }
}
```

## Coverage Analysis

### Report Interpretation
```
Element     Missed Instructions  Cov.    Missed Branches  Cov.
========================================================
Total             100           85%          20          75%
com.example        80           90%          15          80%
  Class1           20           95%           5          90%
  Class2           60           85%          10          70%
```

### Common Metrics
1. **Instructions (C0 Coverage)**
    - Basic blocks of code
    - Shows execution path

2. **Branches (C1 Coverage)**
    - Decision points
    - Conditional execution

3. **Cyclomatic Complexity**
    - Code complexity measure
    - Decision point count

4. **Method Coverage**
    - Method execution status
    - Entry point coverage

## Troubleshooting

### Common Issues
```bash
# No coverage data
./gradlew clean test jacocoTestReport --debug

# Missing classes
./gradlew test jacocoTestReport --info

# Verification failures
./gradlew jacocoTestCoverageVerification --stacktrace
```

### Debugging Tips
```groovy
test {
    testLogging {
        events "passed", "skipped", "failed"
        exceptionFormat = 'full'
        showStandardStreams = true
    }
}

jacocoTestReport {
    doFirst {
        println "Classes to be analyzed: "
        classDirectories.files.each { println it }
    }
}
```
