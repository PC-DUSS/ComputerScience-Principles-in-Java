/**
 * Class exploring BigIntegers by doing things I have done in the past, but redone using
 * BigIntegers. Also applying basic test-driven development principles.
 *
 * @author Pierre-Charles Dussault
 * @since 07/13/2021
 * @version 1.0
 */
public class BigJava {

  public static void main(String[] args) {

    runTests();
  }

  public static int factorialIter(int n) throws IllegalArgumentException {

    if (n < 0) {
      throw new IllegalArgumentException("factorialIter(int n) does not accept negative values");
    }

    int factorial = 1;
    if (n == 0 || n == 1) {
      return factorial;
    }

    for (int i = 1; i <= n; i++) {
      factorial *= i;
    }

    return factorial;
  }

  /**
   * Test method for factorialIter(int n). Checks for: factorial of 0; factorial of 1; factorial of
   * 2; factorial of 3; and error handling of when n is negative.
   *
   * @return void
   */
  public static void factorialIterTest() {

    System.out.println("Testing function: factorialIter()");
    if (factorialIter(0) == 1) {
      System.out.println("\t-Case 0: pass");
    } else {
      System.err.println("\t-Case 0: FAILED!");
    }

    if (factorialIter(1) == 1) {
      System.out.println("\t-Case 1: pass");
    } else {
      System.err.println("\t-Case 1: FAILED!");
    }

    if (factorialIter(2) == 2) {
      System.out.println("\t-Case 2: pass");
    } else {
      System.err.println("\t-Case 2: FAILED!");
    }

    if (factorialIter(3) == 6) {
      System.out.println("\t-Case 3: pass");
    } else {
      System.err.println("\t-Case 3: FAILED!");
    }

    try {
      factorialIter(-1);
    } catch (Exception e) {
      if (e == null) {
        System.err.println("\t-Case negative: FAILED!");
      } else {
        System.out.println("\t-Case negative: pass");
      }
    }
  }

  public static void runTests() {

    factorialIterTest();
  }
}
