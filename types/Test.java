/**
 * Test out operators on various types of data, and explore what can and can't be done with each
 * data type.
 *
 * @author Pierre-Charles Dussault
 * @since 07/13/2021
 * @version 1.0
 */
public class Test {

  public static void main(String[] args) {

    String myStr = new String("Hello Worl");
    char myChar =
        'd'; // The Unicode value of 'd' is 100, so it will be easy to see what is going on
    System.out.println(myStr + myChar);
    System.out.println((myStr + myChar).getClass()); // String + char = String
    System.out.println((myChar + myStr).getClass()); // Regardless of order

    int myInt = 5;
    System.out.println(myChar + myInt); // Well, now we know char + int = int
    System.out.println(myInt + myChar); // Regardless of order
    System.out.println(5 + "abc");

    // Look at this oddity
    myChar++; // This works
    System.out.println(myChar);
    // myChar = myChar + 1; // Compiler error, huh?
    // char + int = int  ...so when you give an int value to a char type variable, it warns of
    // incompatible type
    myChar = (char) (myChar + 1); // You can type-cast the int value of the result as a char instead

    // Tip: transform any type into a String using concatenation to an empty String
    System.out.println(true + "");
  }
}
