import java.util.Scanner;

/*
 * Demonstrates input validation using if statements.
 * */
public class ValidateInput
{
  public static void main(String[] args)
  {
    Scanner in = new Scanner(System.in);
    System.out.print("Enter a number: ");

    // Check the format of the input
    if (!in.hasNextDouble())
    {
      // in.next() returns only the next token of input, not the entire line
      String word = in.next();
      // Notice how we use System.err instead of System.out
      System.err.println(word + " is not a number");
      // Early exit from the program
      return;
    }

    double x = in.nextDouble();
    if (x > 0)
    {
      double y = Math.log(x);
      System.out.println("The logarithm base 'e' for " + x + " is: " + y);
    }
    else 
    {
      System.out.println("The logarithm for that number is undefined");
    }
  }
}
