import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class InfiniteSeries {

  public static void main(String[] args) {

    runTests();
    System.out.println(myexp(2, 30));
  }

  /**
   * Get the sum of the infinite series up to the n'th iteration, with a base of x; this
   * approximates e^x.
   *
   * @param x: int base for the infinite series
   * @param n: int iteration count up until which to calculate the series' sum
   * @return sum: BigInteger result of the sum
   */
  public static BigDecimal myexp(int x, int n) {

    // MathContext to only keep 10 decimal digits
    MathContext mc = new MathContext(10);
    // Set starting sum to 0
    BigDecimal sum = new BigDecimal("0.0", mc);

    for (int i = 0; i <= n; i++) {
      // Convert x^n and n! from BigIntegers to BigDecimals with a MathContext of 10 decimal digits
      BigDecimal power = new BigDecimal(bigPow(x, i), mc);
      BigDecimal factorial = new BigDecimal(bigFactorialIter(i), mc);

      // Add the result of the current iteration to the sum total
      sum = sum.add(power.divide(factorial, mc));
    }
    return sum;
  }

  /**
   * Get the n'th power of base x, in the form of a BigInteger; supports really large numbers.
   *
   * @param x: int base number to raise
   * @param n: int power to raise base x to
   * @return power: BigInteger the n'th power of x
   */
  public static BigInteger bigPow(int x, int n) {

    if (n == 0) return new BigInteger("1");

    BigInteger t = bigPow(x, n / 2);

    // If n is even
    if (n % 2 == 0) {
      return t.multiply(t); // return t * t;
    }
    // If n is odd
    return t.multiply(t.multiply(new BigInteger("" + x))); // return t * t * x;
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

  public static void isNegativeTest() throws Exception {

    System.out.println("\nRunning tests for: isNegative(BigInteger n)");

    if (isNegative(new BigInteger("-1"))) {
      System.out.println("\t-Case n < 0: pass");
    } else {
      throw new Exception("\t-Case n < 0: FAIL!\n");
    }

    if (!isNegative(new BigInteger("0"))) {
      System.out.println("\t-Case n == 0: pass");
    } else {
      throw new Exception("\t-Case n == 0: FAIL!\n");
    }

    if (!isNegative(new BigInteger("1"))) {
      System.out.println("\t-Case n > 0: pass");
    } else {
      throw new Exception("\t-Case n > 0: FAIL!\n");
    }
  }

  public static void incrementTest() throws Exception {

    System.out.println("\nRunning tests for: increment(BigInteger n)");

    if (increment(new BigInteger("-1")).equals(new BigInteger("0"))) {
      System.out.println("\t-Case n < 0: pass");
    } else {
      throw new Exception("\t-Case n < 0: FAIL!\n");
    }

    if (increment(new BigInteger("0")).equals(new BigInteger("1"))) {
      System.out.println("\t-Case n == 0: pass");
    } else {
      throw new Exception("\t-Case n == 0: FAIL!\n");
    }

    if (increment(new BigInteger("1")).equals(new BigInteger("2"))) {
      System.out.println("\t-Case n == 1: pass");
    } else {
      throw new Exception("\t-Case n == 1: FAIL!\n");
    }
  }

  public static void bigFactorialIterTest() throws Exception {

    System.out.println("\nRunning tests for: bigFactorialIter(int n)");

    if (bigFactorialIter(0).equals(new BigInteger("1"))) {
      System.out.println("\t-Case n == 0: pass");
    } else {
      throw new Exception("\t-Case n == 0: FAIL!\n");
    }

    if (bigFactorialIter(1).equals(new BigInteger("1"))) {
      System.out.println("\t-Case n == 1: pass");
    } else {
      throw new Exception("\t-Case n == 1: FAIL!\n");
    }

    if (bigFactorialIter(2).equals(new BigInteger("2"))) {
      System.out.println("\t-Case n == 2: pass");
    } else {
      throw new Exception("\t-Case n == 2: FAIL!\n");
    }

    if (bigFactorialIter(3).equals(new BigInteger("6"))) {
      System.out.println("\t-Case n == 3: pass");
    } else {
      throw new Exception("\t-Case n == 3: FAIL!\n");
    }

    if (bigFactorialIter(20).equals(new BigInteger("2432902008176640000"))) {
      System.out.println("\t-Case n == 20: pass");
    } else {
      throw new Exception("\t-Case n == 20: FAIL!\n");
    }
  }

  public static void bigPowTest() throws Exception {

    System.out.println("\nRunning tests for bigPow(int n)");

    if (bigPow(0, 0).equals(new BigInteger("1"))) {
      System.out.println("\t-Case 0^0: pass");
    } else {
      throw new Exception("\t-Case 0^0: FAIL!\n");
    }

    if (bigPow(0, 1).equals(new BigInteger("0"))) {
      System.out.println("\t-Case 0^1: pass");
    } else {
      throw new Exception("\t-Case 0^1: FAIL!\n");
    }

    if (bigPow(1, 0).equals(new BigInteger("1"))) {
      System.out.println("\t-Case 1^0: pass");
    } else {
      throw new Exception("\t-Case 1^0: FAIL!\n");
    }

    if (bigPow(1, 1).equals(new BigInteger("1"))) {
      System.out.println("\t-Case 1^1: pass");
    } else {
      throw new Exception("\t-Case 1^1: FAIL!\n");
    }

    if (bigPow(54, 0).equals(new BigInteger("1"))) {
      System.out.println("\t-Case x^0: pass");
    } else {
      throw new Exception("\t-Case x^0: FAIL!\n");
    }

    if (bigPow(54, 1).equals(new BigInteger("54"))) {
      System.out.println("\t-Case x^1: pass");
    } else {
      throw new Exception("\t-Case x^1: FAIL!\n");
    }

    // source: https://www.geogebra.org/scientific
    if (bigPow(54, 10).equals(new BigInteger("210832519264920576"))) {
      System.out.println("\t-Case x^n: pass");
    } else {
      System.out.println(bigPow(54, 10));
      throw new Exception("\t-Case x^n: FAIL!\n");
    }
  }

  public static void runTests() {

    try {
      isNegativeTest();
      incrementTest();
      bigFactorialIterTest();
      bigPowTest();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
