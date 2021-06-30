import java.util.Scanner;


public class Triangle
{
  public static void main(String[] args)
  {
    beginTriangleChecker();
  }


  public static void beginTriangleChecker()
  {
    Scanner scanner = new Scanner(System.in);
    int a, b, c;

    while (true)
    {
      welcomeMessage();
      a = getInt(scanner, "a");
      b = getInt(scanner, "b");
      c = getInt(scanner, "c");
      
      if(isTriangle(a, b, c))
      {
        System.out.println("Yes, such a triangle exists!");
      }
      else
      {
        System.out.println("No, there is no such triangle.");
      }

      if (!isKeepPlaying(scanner))
        break;
    }

  }

  public static void welcomeMessage()
  {
    System.out.println("Welcome to Triangle Checker. Input 3 dimensions, and "
                       + "we shall reveal if such a triangle exists with "
                       + "those dimensions.");
    System.out.println("(You can press 'q' or 'Q' at any time to exit)\n");
  }

  public static int getInt(Scanner scanner, String label)
  {
    int myInt = 0;
    String answer;

    while (true)
    {
      System.out.printf("Enter an integer value for dimension '%s': ", label);

      if (scanner.hasNextInt())
      {
        myInt = scanner.nextInt();
        // Clear Scanner buffer
        scanner.nextLine();

        if (isDimensionValid(myInt))
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

  public static boolean isDimensionValid(int myInt)
  {
    if (myInt <= 0)
      return false;
    else
      return true;
  }

  public static boolean isTriangle(int a, int b, int c)
  {
    if (a > b && a > c)
      return b + c >= a;
    else if (b > a && b > c)
      return a + c >= b;
    else if (c > a && c > b)
      return a + b >= c;
    else
      // Isocele or equilateral triangle
      return true;

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
      {
        // Clear Scanner buffer
        scanner.nextLine();
        System.out.print("Invalid answer. Keep playing? (y/n): ");
      }
    }
  }

}
