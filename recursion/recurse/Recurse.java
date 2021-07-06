public class Recurse
{
  public static void main(String[] args)
  {
    System.out.println("Should return \'H\': " + first("Hello, World!"));
    System.out.println("Should return \"ello, World!\": "
                       + rest("Hello, World!"));
    System.out.println("Should return \"ello, World\": "
                       + middle("Hello, World!"));
    System.out.println("Should return 13: " + length("Hello, World!"));

    System.out.println();
    printString("Hello, World!");
    System.out.println();
    printBackwards("Hello, World!");
    System.out.println();
    System.out.println(reverseString("Hello, World!"));
    System.out.println();
    System.out.println(reverseString("coffee"));

    System.out.println();
    System.out.println("Should return true: "
                       + isPalindrome("palindromeemordnilap"));
  }


  /*
   * Returns the first char in the given String.
   * */
  public static char first(String s)
  {
    return s.charAt(0);
  }

  /*
   * Returns all but the first letter in the given String.
   * */
  public static String rest(String s)
  {
    return s.substring(1);
  }

  /*
   * Returns all but the first and last letter in the given String.
   * */
  public static String middle(String s)
  {
    return s.substring(1, s.length() - 1);
  }

  /*
   * Returns the length of the given String.
   * */
  public static int length(String s)
  {
    return s.length();
  }

  /*
   * Prints each char of a String to standard output, one on each line.
   * */
  public static void printString(String str)
  {
    if (length(str) == 1)
    {
      System.out.println(str);
    }
    else
    {
      System.out.println(first(str));
      printString(rest(str));
    }
  }

  public static void printBackwards(String str)
  {
    if (length(str) == 1)
    {
      System.out.println(str);
    }
    else
    {
      printBackwards(rest(str));
      System.out.println(first(str));
    }
  }

  public static String reverseString(String str)
  {
    if (length(str) == 1)
    {
      return str;
    }

    return reverseString(rest(str)) + first(str);
  }

  public static boolean isPalindrome(String str)
  {
    if (length(str) == 1)
    {
      return true;
    }
    else if (length(str) == 2)
    {
      // If the first char is equal to the last char
      if (first(str) == first(reverseString(str)))
      {
        return true;
      }
    }
    else if (first(str) == first(reverseString(str)))
    {
      return isPalindrome(middle(str));
    }

    return false;
  }
}
