public class Main
{
  public static void main(String[] args)
  {
    System.out.println("Hello, World!");
  }


  /*
   * We need to make sure that this method handles runtime errors.
   * It needs to gracefully handle wrong or missing input.
   * */
  public static boolean isCapitalized(String str)
  {
    // IMPORTANT: Always check for the null case before anything else, as if
    // it is null, java will throw a NullPointerException if you try to invoke
    // a method call on it
    if (str == null || str.isEmpty())  // This is in the right order
    {
      return false;
    }

    //if (str.isEmpty() || str == null)  // Wrong order; this would throw a
                                         // NullPointerException error

    return Character.isUpperCase(str.charAt(0));
  }
}
