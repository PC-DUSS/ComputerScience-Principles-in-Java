

public class SquareRoot
{
  public static void main(String[] args)
  {
    double sqrt;

    sqrt = squareRoot(34.0);
    System.out.println(sqrt);
  }


  public static double squareRoot(double a)
  {
    /* Takes a double value. 
     *
     * Returns -1.0 if input value is incorrect
     * Else, returns its square root.*/

    double currentGuess = -1.0;  // temporary value
    double newGuess = a / 2.0;

    if (a == 0)
    {
      System.out.println("Error square root of zero. Returning -1.0");
    }
    else if (a < 0)
    {
      System.out.println("Error square root of negative. Returning -1.0");
    }
    else
    {
      for ( ; currentGuess != newGuess;
           newGuess = (currentGuess + a / currentGuess) / 2.0)
      {
        currentGuess = newGuess;
      }
    }


    return currentGuess;
  }
}
