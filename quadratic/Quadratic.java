import java.util.Scanner;


/* Program to calculate the roots of a quadratic function, according to
 * parameters given by the user.
 *
 * June 29, 2021
 *
 * @author Pierre-Charles Dussault
 * @version 1.0 
 * */
public class Quadratic
{
  public static void main(String[] args)
  {
    beginQuadraticCalculator();
  }


  public static void beginQuadraticCalculator()
  {
    Scanner scanner = new Scanner(System.in);
    int a, b, c;
    double roots[];
    double x1, x2;

    while (true)
    {
      welcomeMessage();
      while (true)
      {
        a = getInt(scanner, "a");
        b = getInt(scanner, "b");
        c = getInt(scanner, "c");

        if (!isDivideByZero(a) && !isSquareRootOfNegative(a, b, c))
        {
          break;
        }
        else
        {
          System.out.println("Error. Reminder: 'a' must not be equal to zero, "
                             + "and the discriminant (b^2 - 4ac) must be "
                             + "greater or equal to zero.");
        }
      }

      roots = calculateRoots(a, b, c);
      x1 = roots[0];
      x2 = roots[1];

      System.out.printf("The roots for this quadratic function are:\nx1: %f\n"
                        + "x2: %f\n", x1, x2);

      if (!isKeepPlaying(scanner))
        break;
    }
  }

  public static void welcomeMessage()
  {
    System.out.println("Welcome to Quadratic Root Calculator.\n\nPlease enter"
                       + " parameters to calculate the roots of their"
                       + " corresponding quadratic function.");
    System.out.println("(You can press 'q' or 'Q' at any time to exit)\n");
  }

  public static int getInt(Scanner scanner, String label)
  {
    int myInt = 0;
    String answer;

    while (true)
    {
      System.out.printf("Enter an integer value for '%s': ", label);

      if (scanner.hasNextInt())
      {
        myInt = scanner.nextInt();
        // Clear Scanner buffer
        scanner.nextLine();
        break;
      }
      else if (scanner.hasNext("q") || scanner.hasNext("Q"))
      {
        // Clear Scanner buffer
        scanner.nextLine();
        System.exit(0);
      }
      else
      {
        // Clear Scanner buffer
        scanner.nextLine();
        System.out.println("Invalid integer!");
      }
    }

    return myInt;
  }

  public static boolean isDivideByZero(int a)
  {
    if (a == 0)
      return true;
    else
      return false;
  }

  public static boolean isSquareRootOfNegative(int a, int b, int c)
  {
    if ((4 * a * c) > Math.pow(b, 2))
      return true;
    else
      return false;
  }

  public static double[] calculateRoots(int a, int b, int c)
  {
    double root1 = (-b + Math.sqrt(calculateDiscriminant(a, b, c)) ) / (2*a);
    double root2 = (-b - Math.sqrt(calculateDiscriminant(a, b, c)) ) / (2*a);
    double roots[] = {root1, root2};
    return roots;
  }

  public static int calculateDiscriminant(int a, int b, int c)
  {
    return (int) Math.pow(b, 2) - 4 * a * c;
  }

  public static boolean isKeepPlaying(Scanner scanner)
  {
    System.out.print("Keep playing? (y/n): ");

    while (true)
    {
      if (scanner.hasNext("y") || scanner.hasNext("Y"))
      {
        // Clear Scanner buffer
        scanner.nextLine();
        return true;
      }
      else if (scanner.hasNext("n") || scanner.hasNext("N")
               || scanner.hasNext("q") || scanner.hasNext("Q"))
      {
        // Clear Scanner buffer
        scanner.nextLine();
        return false;
      }
      else
        // Clear Scanner buffer
        scanner.nextLine();
        System.out.print("Invalid answer. Keep playing? (y/n): ");
    }
  }

}
