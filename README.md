# AIHUB
Website [https://www.aihub.capital](https://www.aihub.capital)

## AI Courses
Basic AI developer - series for software developers

### Course One
- AI plugin experience - code generation, explanation, optimization, testing

- This course demonstrates various Java concepts and practices using a simple fruit filtering example.

#### Getting Started

1. Clone this repository to your local machine from `git clone https://github.com/cexbit/aihub-website.git`
2. Open the project in your preferred Java IDE.
3. Navigate to the `Course1DemoFruits.java` file in the `capital.aihub.courses` package.

## Project Structure

The main class `Course1DemoFruits` contains:

- A `main` method demonstrating the usage of the fruit filtering methods.
- Two implementations of a fruit filtering method:
    - `filterLongFruitsUnoptimized`: A basic implementation using a for-loop.
    - `filterLongFruits`: An optimized version using Java Stream API.

## Use Cases

### 1. Filtering Fruits

The `filterLongFruits` method demonstrates how to filter a list of fruits based on their length. It returns a new list containing only fruits with names longer than 5 characters.

Try running the `main` method to see the results of filtering the initial fruit list.

Use Case 1 
- Design method in AI assistant for filtering the initial fruit list
- AI Prompt > Write a method to filter fruits longer than 5 characters
- Test method

### 2. Code Optimization

Compare the `filterLongFruitsUnoptimized` and `filterLongFruits` methods. Notice how the optimized version uses the Stream API to achieve the same result more concisely.

Use case 2
- Code optimalisation
- AI Prompt > How can I optimize this method?
- Compare output with not optimized method


### 3. Understanding Stream API

The `filterLongFruits` method uses Java's Stream API. To understand how it works:

1. Start with the `fruits` list.
2. Call `stream()` to create a stream of fruits.
3. Use `filter()` to keep only fruits with names longer than 5 characters.
4. Finally, `collect()` the filtered fruits back into a list.

Use case 3
- Code explanation
- AI Prompt > Explain how Stream API works in Java
- Is explanation understandable ? Compare with others.

### 4. Unit Testing

Practice writing unit tests for the `filterLongFruits` method. Consider the following scenarios:

- Test with a list containing fruits of various lengths.
- Test with an empty list.
- Test with a list where all fruits are longer than 5 characters.
- Test with a list where no fruits are longer than 5 characters.

Use case 4
- Generate unit tests
- AI Prompt > Create a unit test for the filterLongFruits method


## Additional Resources

- [Java Stream API Documentation](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
