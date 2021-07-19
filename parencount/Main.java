import java.math.BigInteger;

public class Main {

  public static void main(String[] args) {

    System.out.println(parenCount("\n\n\n\n\n\n((\\)\\)\\("));
  }

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

  public static BigInteger increment(BigInteger n) {

    return n.add(new BigInteger("1"));
  }

  public static BigInteger decrement(BigInteger n) {

    return n.subtract(new BigInteger("1"));
  }
}
