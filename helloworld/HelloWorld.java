public class HelloWorld
{
  public static void main(String[] args)
  {
    String message;
    int hour, minute;

    hour = 11;
    minute = 59;
    message = String.format("Hello, World!\nIt is %d:%d", hour, minute);

    System.out.println(message);
  }
}
