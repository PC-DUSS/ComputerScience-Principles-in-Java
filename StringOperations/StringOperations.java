public class StringOperations
{
  public static void main(String[] args)
  {
    // This is a good way to introduce bugs if you are not careful.
    // The operations are executed from left-to-right, except for mathematical
    // conventions.
    System.out.println(1 + 2 + "Hello");
    System.out.println("Hello" + 1 + 2);
  }
}
