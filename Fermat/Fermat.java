import java.util.Scanner;


/*Test if Fermat Law holds true*/
public class Fermat
{
  public static void main(String[] args)
  {
    introMessage();
    playGame();
  }


  public static void introMessage()
  {
    System.out.println("Fermat's Last Theorem says that there are no integers"
                       + " a, b, c and n such that a^n + b^n = c^n, except "
                       + "when n <= 2. Test it to see if it holds true.");
  }

  public static void playGame()
  {
    Scanner scanner = new Scanner(System.in);
    boolean activeFlag = true;
    int a, b, c, n;

    while (activeFlag)
    {
      a = getA(scanner);
      b = getB(scanner);
      c = getC(scanner);
      n = getN(scanner);
      if (!isFermatTrue(a, b, c, n))
      {
        System.out.println("Holy smokes, Fermat was wrong!");
        return;
      }
      else
      {
        System.out.println("No, that doesn't work.");
        activeFlag = askTryAgain(scanner);
      }
    }
  }

  public static int getA(Scanner scanner)
  {
    boolean askingForInteger = true;
    int a = 0;

    while (askingForInteger)
    {
      System.out.print("Enter an integer value for 'a': ");
      if (scanner.hasNextInt())
      {
        a = scanner.nextInt();
        askingForInteger = false;
      }
      else
      {
        System.out.println("Invalid integer!");
      }
    }

    return a;
  }

  public static int getB(Scanner scanner)
  {
    boolean askingForInteger = true;
    int b = 0;

    while (askingForInteger)
    {
      System.out.print("Enter an integer value for 'b': ");
      if (scanner.hasNextInt())
      {
        b = scanner.nextInt();
        askingForInteger = false;
      }
      else
      {
        System.out.println("Invalid integer!");
      }
    }

    return b;
  }

  public static int getC(Scanner scanner)
  {
    boolean askingForInteger = true;
    int c = 0;

    while (askingForInteger)
    {
      System.out.print("Enter an integer value for 'c': ");
      if (scanner.hasNextInt())
      {
        c = scanner.nextInt();
        askingForInteger = false;
      }
      else
      {
        System.out.println("Invalid integer!");
      }
    }

    return c;
  }

  public static int getN(Scanner scanner)
  {
    boolean askingForInteger = true;
    int n = 0;

    while (askingForInteger)
    {
      System.out.print("Enter an integer value for 'n': ");
      if (scanner.hasNextInt())
      {
        n = scanner.nextInt();
        askingForInteger = false;
      }
      else
      {
        System.out.println("Invalid integer!");
      }
    }

    return n;
  }

  public static boolean isFermatTrue(int a, int b, int c, int n)
  {
    if (Math.pow(a, n) + Math.pow(b, n) == Math.pow(c, n))
    {
      if (n <= 2)
      {
        return true;
      }
      else
      {
        return false;
      }
    }

    return true;
  }

  public static boolean askTryAgain(Scanner scanner)
  {
    boolean activeFlag;
    String userAnswer;

    System.out.print("Do you want to try again? (y/n): ");

    while (true)
    {
      userAnswer = scanner.next();
      if (userAnswer.equalsIgnoreCase("y"))
      {
        return true;
      }
      else if (userAnswer.equalsIgnoreCase("n"))
      {
        return false;
      }
      else
      {
        System.out.print("Invalid answer. Do you want to try again? "
                           + "(y/n): ");
      }
    }
  }
}
