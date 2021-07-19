import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class InfiniteSeries {

  public static void main(String[] args) {

    runTests();

    System.out.println("\n" + myexp(2, 30));
    System.out.println("\n" + myexpReworked(2, 30) + "\n");

    // Observe what happens to the accuracy differences as we increase the power to which we raise e
    for (double i = 0.1; i <= 100; i *= 10) {
      check(i, 500);
    }

    // Or make it exponentially smaller...
    for (double i = -0.1; i >= -100; i *= 10) {
      check(i, 500);
    }

    // Our functions can't keep up! :( And they are also much slower
  }

  /**
   * Compare the values returned by Math.exp with those returned by myexp and myexpReworked.
   *
   * @param x: int power to raise e to (e^x)
   * @param n: int number of iterations to use in the infinite series sequence for the precision of
   *     the returned value
   */
  public static void check(double x, int n) {

    System.out.printf(
        "value of x:\n\t%.1f\nmyexp:\n\t%.10f\nmyexpReworked:\n\t%.10f\nMath.exp:\n\t%.10f\n",
        x, myexp(x, n), myexpReworked(x, n), Math.exp(x));
  }

  /**
   * Get the sum of the infinite series up to the n'th iteration, with a base of x; this
   * approximates e^x.
   *
   * @param x: int base for the infinite series
   * @param n: int iteration count up until which to calculate the series' sum
   * @return sum: BigInteger result of the sum
   */
  public static BigDecimal myexp(double x, int n) {

    // MathContext to only keep 10 decimal digits
    MathContext mc = new MathContext(10);
    // Set starting sum to 0
    BigDecimal sum = new BigDecimal("0.0", mc);

    for (int i = 0; i <= n; i++) {
      // Convert x^n and n! from BigIntegers to BigDecimals with a MathContext of 10 decimal digits
      BigDecimal power = bigPow(x, i);
      BigDecimal factorial = new BigDecimal(bigFactorialIter(i), mc);

      // Add the result of the current iteration to the sum total
      sum = sum.add(power.divide(factorial, mc));
    }
    return sum;
  }

  /**
   * rework of myexp: Get the sum of the infinite series up to the n'th iteration, with a base of x;
   * this approximates e^x.
   *
   * @param x: int base for the infinite series
   * @param n: int iteration count up until which to calculate the series' sum
   * @return sum: BigInteger result of the sum
   */
  public static BigDecimal myexpReworked(double x, int n) {

    // Number of significant decimal digits
    MathContext mc = new MathContext(10);
    // Starting sum to 1.0
    BigDecimal sum = new BigDecimal("1.0", mc);
    // Starting numerator to 1.0
    BigDecimal numerator = new BigDecimal("1.0", mc);
    // Starting denominator to 1.0
    BigDecimal denominator = new BigDecimal("1.0", mc);
    // BigDecimal version of x for ease of reading of the algorithm
    BigDecimal bigX = new BigDecimal("" + x, mc);

    if (n == 0) return sum;

    for (int i = 1; i <= n; i++) {
      BigDecimal currentIter = new BigDecimal("" + (0.0 + i), mc);
      numerator = numerator.multiply(bigX, mc);
      denominator = denominator.multiply(currentIter, mc);
      BigDecimal currentValue = numerator.divide(denominator, mc);
      sum = sum.add(currentValue);
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
  public static BigDecimal bigPow(double x, int n) {

    if (n == 0) return new BigDecimal("1.0");

    BigDecimal t = bigPow(x, n / 2);

    // If n is even
    if (n % 2 == 0) {
      return t.multiply(t); // return t * t;
    }
    // If n is odd
    return t.multiply(t.multiply(new BigDecimal("" + (0.0 + x)))); // return t * t * x;
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

    if (bigPow(0, 0).equals(new BigDecimal("1.0"))) {
      System.out.println("\t-Case 0^0: pass");
    } else {
      throw new Exception("\t-Case 0^0: FAIL!\n");
    }

    if (bigPow(0, 1).equals(new BigDecimal("0.0"))) {
      System.out.println("\t-Case 0^1: pass");
    } else {
      throw new Exception("\t-Case 0^1: FAIL!\n");
    }

    if (bigPow(1, 0).equals(new BigDecimal("1.0"))) {
      System.out.println("\t-Case 1^0: pass");
    } else {
      throw new Exception("\t-Case 1^0: FAIL!\n");
    }

    if (bigPow(1, 1).equals(new BigDecimal("1.0"))) {
      System.out.println("\t-Case 1^1: pass");
    } else {
      throw new Exception("\t-Case 1^1: FAIL!\n");
    }

    if (bigPow(54, 0).equals(new BigDecimal("1.0"))) {
      System.out.println("\t-Case x^0: pass");
    } else {
      throw new Exception("\t-Case x^0: FAIL!\n");
    }

    if (bigPow(54, 1).equals(new BigDecimal("54.0"))) {
      System.out.println("\t-Case x^1: pass");
    } else {
      throw new Exception("\t-Case x^1: FAIL!\n");
    }

    // source: https://www.geogebra.org/scientific
    if (bigPow(54, 10).equals(new BigDecimal("210832519264920576.0"))) {
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
