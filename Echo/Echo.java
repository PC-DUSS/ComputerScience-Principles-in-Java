import java.util.Scanner;


public class Echo
{
  public static void main(String[] args)
  {
    Scanner in = new Scanner(System.in);
    String line;

    System.out.println("Type something:\n");
    line = in.nextLine();
    System.out.println(String.format("\nYou said > %s", line));
  }
}
