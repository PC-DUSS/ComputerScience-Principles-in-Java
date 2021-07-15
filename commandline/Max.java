/**
 * Practice validating user input, but using Exceptions and try...catch blocks
 * this time.
 *
 * @author Pierre-Charles Dussault
 * @version 1.0
 * @since 07/08/2021
 * */
import java.util.Arrays;


public class Max
{
  public static void main(String[] args) throws IllegalArgumentException
  {
    // Initialize max to the lowest (negative) possible value for an int
    int max = Integer.MIN_VALUE;

    // Validate user input
    if (args.length == 0)
    {
      System.err.println("Incorrect usage of Max\nUsage:\njava Max "
                         + "<integer numbers>\n");
      return;
    }

    try
    {
      for (String arg : args)
      {
        int value = Integer.parseInt(arg);
        if (value > max)
        {
          max = value;
        }
      }
    }
    catch (Exception e)
    {
      if (NumberFormatException.class.isInstance(e))
      {
        System.err.println("Wrong input for integer numbers.\nCorrect usage:"
                           + "\njava Max <integer numbers>");
        return;
      }
      else
      {
        System.err.println("Incorrect usage of Max\nUsage:\njava Max "
                           + "<integer numbers>\n");
        return;
      }
    }

    System.out.println(Arrays.toString(args));
    System.out.println("The max is: " + max);
  }
}
