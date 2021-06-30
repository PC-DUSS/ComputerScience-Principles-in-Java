import java.util.Scanner;

/*
 * Converts centimeters to feet and inches.
 */
public class Convert
{
  public static void main(String[] args)
  {
    final int INCHES_PER_FOOT = 12;
    final double CM_PER_INCH = 2.54;
    double cm;
    int feet, inches, remainder;
    Scanner in = new Scanner(System.in);

    // Prompt for a measure in centimeters.
    System.out.print("How many cm?: ");
    cm = in.nextDouble();

    // Convert and output result.
    inches = (int) (cm / CM_PER_INCH);
    feet = inches / INCHES_PER_FOOT;
    remainder = inches % INCHES_PER_FOOT;
    System.out.printf("\n%.2fcm = %dft, %din\n",
                      cm, feet, remainder);    
  }
}
