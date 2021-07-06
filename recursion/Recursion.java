import java.util.Arrays;


public class Recursion
{
  public static void main(String[] args)
  {
    exploringRecursion();
    recursionExercises();
  }


  public static void recursionExercises()
  {
    bottlesOfBeer(99);
    System.out.println("\n" + oddSum(10));
    System.out.println("\n" + ack(3, 3));
    System.out.println("\n" + power(5, 4));
    int[] myArr = {1, 4, 2, 5, 10, 12, 1203, 121, 0, 1};
    System.out.println("\nShould equal 12: " + maxInRange(myArr, 2, 5));
    System.out.println("Should equal 1203: " + max(myArr));
  }

  public static void exploringRecursion()
  {
    System.out.println(factorial(8));
    System.out.println();
    countdown(5);
    System.out.println();
    countup(5);
    System.out.println();
    System.out.print("456 in binary is: ");
    displayBinary(456);
    System.out.println("\n");

    System.out.println(noX("xHexlxlox, Wxxorxldx!x"));
  }

  public static int factorial(int n)
  {
    if (n == 0)
    {
      return 1;
    }
    else
    {
      return n * factorial(n - 1);
    }
  }

  public static void countdown(int n)
  {
    if (n == 0)
    {
      System.out.println("Blastoff!");
    }
    else
    {
      /* If you place the recursive call AFTER the 'action', the action will
       * go from top to bottom.*/
      System.out.println(n);
      countdown(n - 1);
    }
  }

  public static void countup(int n)
  {
    if (n == 0)
    {
      System.out.println("Blastoff!");
    }
    else
    {
      /* If you place the recursive call BEFORE the 'action', the action will
       * go from bottom to top.*/
      countup(n - 1);
      System.out.println(n);
    }
  }

  public static void displayBinary(int n)
  {
    if (n > 0)
    {
      // Pass on the next value to be evaluated, which is the result of n/2
      // floored
      displayBinary(n / 2);
      // When all the recursive calls return, display each value from bottom
      // to top
      System.out.print(n % 2);
    }
  }

  public static String noX(String str)
  {
    if (str.length() == 0)
    {
      return "";
    }
    else
    {
      char first;
      String rest;
      String recurse;
      first = str.charAt(0);
      rest = str.substring(1);
      recurse = noX(rest);

      if (first == 'x')
      {
        // Return the previous result without adding the currently held char
        return recurse;
      }
      else
      {
        // Concatenate the current char with the previously returned result,
        // and return their sum
        return first + recurse;
      }
    }
  }

  public static void bottlesOfBeer(int bottles)
  {
    if (bottles == 0)
    {
      String songEnding = String.format("\nNo bottles of beer on the wall,\nno"
                                      + " bottles of beer,\nya\' can\'t take"
                                      + " one down, ya\' can\'t pass it "
                                      + "around,\n\'cause there are no more "
                                      + "bottles of beer on the wall!",
                                      bottles, bottles, bottles - 1);
      System.out.println(songEnding);
    }
    else
    {
      singBeerSong(bottles);
      bottlesOfBeer(bottles - 1);
    }
  }

  public static void singBeerSong(int bottles)
  {
    if (bottles == 2)
    {
      String songBody = String.format("\n%d bottles of beer on the wall,\n%d "
                                      + "bottles of beer,\nya\' take one down,"
                                      + " ya\' pass it around,\n%d bottle of "
                                      + "beer on the wall.", bottles, bottles,
                                      bottles - 1);
      System.out.println(songBody);
    }
    else if (bottles == 1)
    {
      String songBody = String.format("\n%d bottle of beer on the wall,\n%d "
                                      + "bottle of beer,\nya\' take it down,"
                                      + " ya\' pass it around,\nno more "
                                      + "bottles of beer on the wall.",
                                      bottles, bottles, bottles - 1);
      System.out.println(songBody);
    }
    else
    {
      String songBody = String.format("\n%d bottles of beer on the wall,\n%d "
                                      + "bottles of beer,\nya\' take one down,"
                                      + " ya\' pass it around,\n%d bottles of "
                                      + "beer on the wall.", bottles, bottles,
                                      bottles - 1);
      System.out.println(songBody);
    }
  }

  public static int oddSum(int n)
  {
    if (n == 1)
    {
      return 1;
    }
    else if (n % 2 != 0)
    {
      return n + oddSum(n - 1);
    }
    else
    {
      return oddSum(n - 1);
    }
  }

  public static int ack(int m, int n) throws IllegalArgumentException
  {
    /* Ackermann Function*/
    // First, check that both m and n are non-negative
    if (m < 0)
    {
      throw new IllegalArgumentException();
    }
    else if (n < 0)
    {
      throw new IllegalArgumentException();
    }

    if (m == 0)
    {
      return n + 1;
    }
    else if (n == 0)
    {
      return ack(m - 1, 1);
    }

    return ack(m - 1, ack(m, n - 1));
  }

  public static double power(double x, int n)
  {
    if (n == 0)
    {
      return 1.0;
    }
    else if (n % 2 == 0)
    {
      return power(x, n / 2) * power(x, n / 2);
    }

    return x * power(x, n - 1);
  }

  public static int maxInRange(int[] arr, int start, int end)
  {
    // Return the biggest value from an array, but only inside the given
    // range, using recursion
    if (start == end)
    {
      return arr[start];
    }

    // Split the array in two pieces, find the max for each one
    int halfpoint = (start + end) / 2;
    int[] part1 = slice(arr, start, halfpoint);
    int[] part2 = slice(arr, halfpoint + 1, end);
    int part1max = maxInRange(part1, 0, part1.length - 1);
    int part2max = maxInRange(part2, 0, part2.length - 1);

    if (part1max > part2max)
    {
      return part1max;
    }
    else
    {
      return part2max;
    }
  }

  public static int max(int[] arr)
  {
    /* Return the biggest value in an array of ints*/
    return maxInRange(arr, 0, arr.length - 1);
  }

  public static int[] slice(int[] arr, int start, int end)
  {
    int[] slice = new int[end - start + 1];
    int index = 0;

    for (int i = start; i <= end; i++)
    {
      slice[index] = arr[i];
      index++;
    }

    return slice;
  }
}
