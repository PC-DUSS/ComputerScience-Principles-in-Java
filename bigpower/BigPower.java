import java.math.BigInteger;

/**
 * Optimize a recursive pow() function to handle big numbers.
 *
 * @author Pierre-Charles Dussault
 * @since 07/16/2021
 * @version 1.0
 */
public class BigPower {

  public static void main(String[] args) {

    // The original pow
    System.out.println(pow(4, 4));
    // The new bigPow
    System.out.println(bigPow(4, 4));
    // Now the new bigPow, but with huge numbers
    System.out.printf("%,d\n", bigPow(20, 20));
  }

  /**
   * Base pow function; optimize it to handle very big numbers!
   *
   * @param x: int the base number to raise
   * @param n: int the exponent
   */
  public static int pow(int x, int n) {

    if (n == 0) return 1;

    // Find x to the n/2 recursively
    int t = pow(x, n / 2);

    // If n is even
    if (n % 2 == 0) {
      return t * t;
    }
    // If n is odd
    return t * t * x;
  }

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
}
