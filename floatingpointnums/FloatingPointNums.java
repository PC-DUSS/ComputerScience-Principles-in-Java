public class FloatingPointNums
{
  public static void main(String[] args)
  {
    // double is the default floating-point number used in Java, not float.
    double x, y;

    // Is not really correct, but Java converts it to 1.0 for us
    x = 1; 

    // ERROR. Result will return an integer division, rounding the result down. y will be equal to 0.
    y = 1 / 3;
    System.out.println(String.format("x: %f\ny: %f", x, y));

    // CORRECT.
    y = 1.0 / 3.0;
    System.out.println(String.format("y: %f", y));

    // Rounding-Errors. Example.
    System.out.println(0.1 * 10);
    System.out.println(0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1
                       + 0.1);

    // Because of rounding-errors, use integers to represent money (in cents) and such.
    double bankBalanceBad = 123.45; // NO!
    int bankBalanceGood = 12345; // In cents.

  }
}
