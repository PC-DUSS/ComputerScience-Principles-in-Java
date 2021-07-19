import java.math.BigInteger;

public class Main {

  public static void main(String[] args) {

    System.out.println(parenCount("\n\n\n\n\n\n((\\)\\)\\("));
  }

  /**
   * Get the net count for non-matching parentheses; a positive count means extra opening
   * parentheses; a negative count means extra closing parentheses.
   *
   * @param myString: String for which to evaluate the parentheses count
   * @return the net count for parentheses that don't have a matching partner
   * */
  public static BigInteger parenCount(String myString) {

    BigInteger count = new BigInteger("0");

    for (int i = 0; i < myString.length(); i++) {
      char c = myString.charAt(i);
      // If the char at the current index is an openning parenthese
      if (c == '(') {
        count = increment(count);
        // If the char at the current index is a closing parenthese
      } else if (c == ')') {
        count = decrement(count);
      }
    }
    return count;
  }

  /**
   * Increment a BigInteger number.
   *
   * @param n: BigInteger value to increment
   * @return the incremented BigInteger value
   * */
  public static BigInteger increment(BigInteger n) {

    return n.add(new BigInteger("1"));
  }

  /**
   * Decrement a BigInteger number.
   *
   * @param n: BigIntger value to decrement
   * @return the decremented BigInteger value
   * */
  public static BigInteger decrement(BigInteger n) {

    return n.subtract(new BigInteger("1"));
  }
}
