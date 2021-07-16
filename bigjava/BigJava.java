import java.math.BigInteger;

/**
 * Class exploring BigIntegers by doing things I have done in the past, but redone using
 * BigIntegers; also applying basic test-driven development principles.
 *
 * @author Pierre-Charles Dussault
 * @since 07/13/2021
 * @version 1.0
 */
public class BigJava {

  public static void main(String[] args) {
    runTests();
    runFactorials();
  }

  /**
   * Produce tables of results for each factorial function in this class; showcase the limitations
   * of using ints in such a scenario, and how to overcome this limitation using BigIntegers.
   */
  public static void runFactorials() {

    factorialTable(8);
    factorialTable(30);
  }

  /**
   * Get the factorial of an integer n
   *
   * @param n: int for which to produce the factorial
   * @return factorial: int result of the factorial
   */
  public static int factorialIter(int n) throws IllegalArgumentException {

    if (n < 0) {
      throw new IllegalArgumentException("factorialIter(int n) does not accept negative values");
    } else if (n == 0 || n == 1) {
      // By definition, the factorials of 0 and 1 are both = 1
      return 1;
    }

    int factorial = 1;
    // Can start with i=2, since we know we are handling the factorial of a number greater than or
    // equal to 2
    for (int i = 2; i <= n; i++) {
      factorial *= i;
    }
    return factorial;
  }

  /**
   * Print a table of the factorials of all ints up to a max of nMax to std_out.
   *
   * @param nMax: int maximum value for which to produce a factorial in the table
   */
  public static void factorialTable(int nMax) {

    // If the numbers will fit in an int, use the int version of the function
    if (nMax < 14) {
      for (int i = 0; i <= nMax; i++) {
        int factorial = factorialIter(i);
        // Add newline at the end, since printf doesn't add it on its own
        System.out.printf("factorial %d = %,d\n", i, factorial);
      }
      // Otherwise, use the BigInteger version of the function
    } else {
      for (int i = 0; i <= nMax; i++) {
        BigInteger factorial = bigFactorialIter(i);
        // Add newline at the end, since printf doesn't add it on its own
        System.out.printf("factorial %d = %,d\n", i, factorial);
      }
    }
    System.out.println();
  }

  /**
   * Get the factorial of a BigInteger.
   *
   * @param n: BigInteger for which to produce the factorial
   * @return factorial: BigInteger result of the factorial
   */
  public static BigInteger bigFactorialIter(int n) throws IllegalArgumentException {

    // Convert int to BigInteger
    BigInteger x = new BigInteger(String.format("%d", n));

    if (isNegative(x)) {
      throw new IllegalArgumentException("bigFactorialIter(int n) does not accept negative values");
    } else if (x.equals(new BigInteger("0")) || x.equals(new BigInteger("1"))) {
      // By definition, the factorials of 0 and 1 are both = 1
      return new BigInteger("1");
    }

    BigInteger factorial = new BigInteger("1");
    // All this just means for(i=2; i<=x; i++) but with BigIntegers
    for (BigInteger i = new BigInteger("2"); i.compareTo(x) <= 0; i = increment(i)) {
      factorial = factorial.multiply(i);
    }
    return factorial;
  }

  /**
   * Increment a BigInteger.
   *
   * @param n: BigInteger value to increment
   * @return incremented_n: BigInteger result of incremented value
   */
  public static BigInteger increment(BigInteger n) {

    return n.add(new BigInteger("1"));
  }

  /**
   * Indicate, by true or false, if a BigInteger is negative.
   *
   * @param n: BigInteger to check if is negative
   * @return isNegative: boolean is n negative?
   */
  public static boolean isNegative(BigInteger n) {

    if (n.compareTo(new BigInteger("0")) == -1) {
      return true;
    }
    return false;
  }

  /* ----- TESTS ----- */

  /**
   * Test if factorialIter(int n) is working as inteded, and that is has proper error handling of
   * when n is negative.
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

  /**
   * Test if bigFactorialIter(int n) is working as intended, and that it has proper error handling
   * when n is negative.
   */
  public static void bigFactorialIterTest() {

    System.out.println("Testing function: bigFactorialIter()");
    if (bigFactorialIter(0).equals(new BigInteger("1"))) {
      System.out.println("\t-Case 0: pass");
    } else {
      System.err.println("\t-Case 0: FAILED!");
    }

    if (bigFactorialIter(1).equals(new BigInteger("1"))) {
      System.out.println("\t-Case 1: pass");
    } else {
      System.err.println("\t-Case 1: FAILED!");
    }

    if (bigFactorialIter(2).equals(new BigInteger("2"))) {
      System.out.println("\t-Case 2: pass");
    } else {
      System.err.println("\t-Case 2: FAILED!");
    }

    if (bigFactorialIter(3).equals(new BigInteger("6"))) {
      System.out.println("\t-Case 3: pass");
    } else {
      System.err.println("\t-Case 3: FAILED!");
    }

    if (bigFactorialIter(20).equals(new BigInteger("2432902008176640000"))) {
      System.out.println("\t-Case 20: pass");
    } else {
      System.err.println("\t-Case 20: FAILED!");
    }

    try {
      bigFactorialIter(-1);
    } catch (Exception e) {
      if (e == null) {
        System.err.println("\t-Case negative: FAILED!");
      } else {
        System.out.println("\t-Case negative: pass");
      }
    }
  }

  /** Run all tests. */
  public static void runTests() {

    System.out.println();
    factorialIterTest();
    System.out.println();
    bigFactorialIterTest();
    System.out.println();
  }
}
