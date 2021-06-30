import java.util.Scanner;


public class ImperialMetric
{
  public static void main(String[] args)
  {
    // Constants
    final double CM_PER_INCH = 2.54;

    // Variables used
    Scanner in = new Scanner(System.in);
    int inch;
    double cm;

    System.out.print("How many inches?: ");
    inch = in.nextInt();

    cm = inch * CM_PER_INCH;
    System.out.print(inch + " inch = ");
    System.out.println(cm + " cm");
    
    // You can also use 'printf()' from System.out to output formatted content.
    System.out.printf("Four thirds = %f\n", 4.0 / 3.0);
    
    // Now with the same block of code as previously...
    cm = inch * CM_PER_INCH;
    System.out.printf("%d inch = %.1f cm\n", inch, cm);
    
    // What if you want to find out the number of inch based on cm?
    // Use type casting in order to make the expression work.
    System.out.println("How many cm: ");
    cm = in.nextDouble();
    inch = (int) (cm / CM_PER_INCH);
    System.out.printf("%.1f cm = %d inch\n", cm, inch);
  }
}
