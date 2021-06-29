import java.util.Scanner;


public class ScannerBug
{
  public static void main(String[] args)
  {
    String name;
    int age;
    Scanner in = new Scanner(System.in);

    // This works fine...
    System.out.print("What is your name?: ");
    name = in.nextLine();
    System.out.print("What is your age?: ");
    age = in.nextInt();
    System.out.printf("Hello %s, you are %d years old.\n", name, age);

    // ...but this does not!
    System.out.print("What is your age?: ");
    age = in.nextInt();
    System.out.print("What is your name?: ");
    name = in.nextLine();
    System.out.printf("Hello %s, you are %d years old.\n", name, age);

    /* Taking a nextLine() right after a nextInt() results in unintended
     * behaviour: nextLine() reads characters until it gets to a newline. But
     * since the next character in buffer is already a newline, nextLine()
     * returns the empty string "". To solve this, you need an extra newLine()
     * after each instance of nextInt() or nextDouble() and such.
     */
    System.out.print("What is your age?: ");
    age = in.nextInt();
    in.nextLine();  // A throwaway nextLine() just like this.
    System.out.print("What is your name?: ");
    name = in.nextLine();
    System.out.printf("Hello %s, you are %d years old.\n", name, age);
  }
}
