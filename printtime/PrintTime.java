

public class PrintTime
{
  public static void main(String[] args)
  {
    int hours = 10;
    int minutes = 55;

    printTime(hours, minutes);
  }

  public static void printTime(int hours, int minutes)
  {
    System.out.print(hours);
    System.out.print(":");
    System.out.print(minutes);
  }
}
