public class PracticeRecursion {

  /**
   * Remove all 'x' characters from a string recursively.
   *
   * @param str the string to clean up
   * @return the cleaned up string without any 'x' in it
   * */
  private static String noX(String str) {
    // Base case
    if (str.length() == 0) {
      return "";
    } else {
      // - Break down all the chars in the string to blocks of individual chars
      // - If a block is a 'x' char, don't return it, only return the rest. If a block is NOT a x char, return it with
      // the rest.

      // This block of code will recurse until it hits the base case
      char first = str.charAt(0);
      String rest = str.substring(1);
      String cleanString = noX(rest);
      // until here

      // Then this piece of code will execute for each instance of this function that was called
      if (first == 'x') {
	// Return the rest of the String which has already been cleaned
	return cleanString;
      } else {
	// Return this first char along with the cleaned String
	// Important to place the first before the rest, or the String will be reversed!
	return first + cleanString;
      }
    }
  }


  /**
   * Iterative solution to fibonacci.
   *
   * @param n the number for which to calculate the fibonacci
   * @return the result of the fibonacci for the given number
   * */
  private static long fibIter(int n) {
    long prevPrev = 0;
    long prev = 1;
    if (n == 0) {
      return prevPrev;
    } else if (n == 1) {
      return prev;
    }

    long total = 0;
    for (int i = 2; i <= n; i++) {
      total = prev + prevPrev;
      prevPrev = prev;
      prev = total;
    }

    return total;
  }

  private static long fibRecur(int n) {
    // Put the condition that will be triggered the most up top to evaluate less conditionals -> more performance
    if (n > 2) {
      return fibRecur(n - 1) + fibRecur(n - 2);
    } else if (n == 1) {
      return 1;
    } else {
      return 0;
    }
  }

  /**
   * Compare the performance of iterative and recursive fibonacci functions.
   * */
  private static void timeFibonaccis() {
    long start1 = System.currentTimeMillis();
    System.out.printf("Iterative fibonacci result: %d\n", fibIter(45));
    long end1 = System.currentTimeMillis();
    long timeTaken1 = end1 - start1;

    long start2 = System.currentTimeMillis();
    System.out.printf("Recursive fibonacci result: %d\n", fibRecur(45));
    long end2 = System.currentTimeMillis();
    long timeTaken2 = end2 - start2;

    System.out.printf("Iterative: %dms\nRecursive: %dms\n", timeTaken1, timeTaken2);
    // Big difference!

    // Recursion in Java needs you to manually implement memoization in order to not suck
  }

  /** Main program. */
  public static void main(String[] args) {
    String cleanedString = noX("Hxxelxlox xWxorlxxdx!xx");
    System.out.println(cleanedString);
    // substring() method can begin after the last character in the String, returning an empty String
    System.out.println("a".substring(1));
    timeFibonaccis();
  }
}
