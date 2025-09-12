# Tests

```java
package calculator;

import org.junit.jupiter.api.Test; // @Test annotation
import org.junit.jupiter.api.BeforeEach; // Setup method annotation
import org.junit.jupiter.api.DisplayName; // Custom test names
import org.junit.jupiter.api.Nested;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
  private Calculator calc;

  /**
   * Setup method - runs before each individual test method
   * Creates a fresh Calculator instance to esnure test isolaition
   */
  @BeforeEach
  void setup() {
    calc = new Calculator();
  }

  /*
   * Nested class to group addition tests together
   * Improves test organization and readability
   */
  @Nested
  @DisplayName("Addition Tests")
  class AdditionTests {

    @Test
    @DisplayName("Add two positive numbers")
    void testAddPositiveNumbers() {
      double summand1 = 5.0;
      double summand2 = 3.0;
      double expected = 8.0;

      double actual = calc.add(summand1, summand2);

      assertEquals(expected, actual, "5.0 + 3.0 should equal 8.0");
    }

    @Test
    @DisplayName("Add two negative numbers")
    void testAddNegativeNumbers() {
      double actual = calc.add(-2.5, -1.5);
      assertEquals(-4.0, actual, 0.001, "(-2.5) + (-1.5) should equal -4.0");
    }

    @Test
    @DisplayName("Add positive and negative numbers")
    void testAddMixedSigns() {
      assertEquals(2.0, calc.add(5.0, -3.0), "5.0 + (-3.0) should equal 2.0");
      assertEquals(-2.0, calc.add(-5.0, 3.0), "(-5.0) + 3.0 should equal -2.0");
    }

    @Test
    @DisplayName("Add with zero")
    void testAddWithZero() {
      assertEquals(5.0, calc.add(5.0, 0.0), "5.0 + 0.0 should equal 5.0");
      assertEquals(0.0, calc.add(0.0, 0.0), "0.0 + 0.0 should equal 0.0");
      assertEquals(-3.0, calc.add(0.0, -3.0), "0.0 + (-3.0) should equal -3.0");
    }

    @Test
    @DisplayName("Add decimal numbers")
    void testAddDecimals() {
      double actual = calc.add(1.5, 2.7);
      assertEquals(4.2, actual, 0.001, "1.5 + 2.7 should equal 4.2");
    }
  }

  /**
   * Nested class for subtraction tests
   */
  @Nested
  @DisplayName("Subtraction Tests")
  class SubtractionTests {

    @Test
    @DisplayName("Subtract positive numbers")
    void testSubtractPositiveNumbers() {
      assertEquals(2.0, calc.subtract(5.0, 3.0), "5.0 - 3.0 should equal 2.0");
    }

    @Test
    @DisplayName("Subtract resulting in negative")
    void testSubtractNegativeResult() {
      assertEquals(-2.0, calc.subtract(3.0, 5.0), "3.0 - 5.0 should equal -2.0");
    }

    @Test
    @DisplayName("Subtract with zero")
    void testSubtractWithZero() {
      assertEquals(5.0, calc.subtract(5.0, 0.0), "5.0 - 0.0 should equal 5.0");
      assertEquals(-3.0, calc.subtract(0.0, 3.0), "0.0 - 3.0 should equal -3.0");
    }

    @Test
    @DisplayName("Subtract negative numbers")
    void testSubtractNegativeNumbers() {
      assertEquals(1.0, calc.subtract(-2.0, -3.0), "(-2.0) - (-3.0) should equal 1.0");
    }
  }

  /**
   * Nested class for multiplication tests
   */
  @Nested
  @DisplayName("Multiplication Tests")
  class MultiplicationTests {

    @Test
    @DisplayName("Multiply positive numbers")
    void testMultiplyPositiveNumbers() {
      assertEquals(15.0, calc.multiply(3.0, 5.0), "3.0 * 5.0 should equal 15.0");
    }

    @Test
    @DisplayName("Multiply by zero")
    void testMultiplyByZero() {
      assertEquals(0.0, calc.multiply(5.0, 0.0), "5.0 * 0.0 should equal 0.0");
      assertEquals(0.0, calc.multiply(0.0, 3.0), "0.0 * 3.0 should equal 0.0");
    }

    @Test
    @DisplayName("Multiply by one")
    void testMultiplyByOne() {
      assertEquals(7.0, calc.multiply(7.0, 1.0), "7.0 * 1.0 should equal 7.0");
      assertEquals(7.0, calc.multiply(1.0, 7.0), "1.0 * 7.0 should equal 7.0");
    }

    @Test
    @DisplayName("Multiply negative numbers")
    void testMultiplyNegativeNumbers() {
      assertEquals(6.0, calc.multiply(-2.0, -3.0), "(-2.0) * (-3.0) should equal 6.0");
      assertEquals(-6.0, calc.multiply(-2.0, 3.0), "(-2.0) * 3.0 should equal -6.0");
      assertEquals(-6.0, calc.multiply(2.0, -3.0), "2.0 * (-3.0) should equal -6.0");
    }

    @Test
    @DisplayName("Multiply decimal numbers")
    void testMultiplyDecimals() {
      double actual = calc.multiply(2.5, 1.2);
      assertEquals(3.0, actual, 0.001, "2.5 * 1.2 should equal 3.0");
    }
  }

  /**
   * Nested class for division tests
   */
  @Nested
  @DisplayName("Division Tests")
  class DivisionTests {

    @Test
    @DisplayName("Divide positive numbers")
    void testDividePositiveNumbers() {
      assertEquals(2.5, calc.divide(5.0, 2.0), 0.001, "5.0 / 2.0 should equal 2.5");
    }

    @Test
    @DisplayName("Divide by one")
    void testDivideByOne() {
      assertEquals(7.0, calc.divide(7.0, 1.0), "7.0 / 1.0 should equal 7.0");
    }

    @Test
    @DisplayName("Divide zero by number")
    void testDivideZeroByNumber() {
      assertEquals(0.0, calc.divide(0.0, 5.0), "0.0 / 5.0 should equal 0.0");
    }

    @Test
    @DisplayName("Divide negative numbers")
    void testDivideNegativeNumbers() {
      assertEquals(2.0, calc.divide(-6.0, -3.0), 0.001, "(-6.0) / (-3.0) should equal 2.0");
      assertEquals(-2.0, calc.divide(-6.0, 3.0), 0.001, "(-6.0) / 3.0 should equal -2.0");
      assertEquals(-2.0, calc.divide(6.0, -3.0), 0.001, "6.0 / (-3.0) should equal -2.0");
    }

    /**
     * Test that division by zero throws IllegalArgumentException
     * Uses assertThrows to verify exception is thrown with correct type and message
     */
    @Test
    @DisplayName("Division by zero should throw IllegalArgumentException")
    void testDivideByZeroThrowsException() {
      // Test division by exact zero
      IllegalArgumentException exception = assertThrows(
          IllegalArgumentException.class,
          () -> calc.divide(10.0, 0.0),
          "Expected divide() to throw IllegalArgumentException when divisor is 0.0");

      // Verify the exception message is correct
      assertEquals("Division by zero is not allowed", exception.getMessage());
    }

    @Test
    @DisplayName("Division by negative zero should also throw exception")
    void testDivideByNegativeZeroThrowsException() {
      // Test division by negative zero (-0.0)
      assertThrows(
          IllegalArgumentException.class,
          () -> calc.divide(5.0, -0.0),
          "Expected divide() to throw IllegalArgumentException when divisor is -0.0");
    }
  }

  /**
   * Additional edge case tests that don't fit into specific operation categories
   */
  @Nested
  @DisplayName("Edge Cases")
  class EdgeCaseTests {

    @Test
    @DisplayName("Test with very large numbers")
    void testLargeNumbers() {
      double large1 = 1000000.0;
      double large2 = 2000000.0;

      assertEquals(3000000.0, calc.add(large1, large2), 0.001);
      assertEquals(2000000000000.0, calc.multiply(large1, large2), 0.001);
    }

    @Test
    @DisplayName("Test with very small decimal numbers")
    void testSmallDecimals() {
      double small1 = 0.001;
      double small2 = 0.002;

      assertEquals(0.003, calc.add(small1, small2), 0.0001);
      assertEquals(0.000002, calc.multiply(small1, small2), 0.0000001);
    }
  }

}

```
