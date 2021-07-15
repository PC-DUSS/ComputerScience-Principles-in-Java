import java.math.BigInteger;

/**
 * Exploring operations on big integers, and wrapper classes. Switched to emacs with Google's
 * auto-formatting, so the coding style will be different.
 *
 * @author Pierre-Charles Dussault
 * @since 07/13/2021
 * @version 1.0
 */
public class Main {

  public static void main(String[] args) {

    System.out.println("Introduction to Big Integer operations.");
    // This would be the equivalent of normal ints, but using the wrapper class and its available
    // methods.
    Integer x = Integer.valueOf(286);
    Integer y = Integer.valueOf("286");
    System.out.println(x.equals(y));
    // Now enter the 'BigInteger'. It allows to store values greater than Long.MAX_VALUE, but must
    // be imported.
    long myLong = 9835;
    BigInteger bigX = BigInteger.valueOf(myLong);
    System.out.println("value of bigX: " + bigX);
    BigInteger bigY = new BigInteger("23948758973495734857");
    System.out.println("Value of bigY: " + bigY);
    BigInteger sum = bigX.add(bigY);
    System.out.println("Result of their sum: " + sum);
  }
}
