public class Time
{
  public static void main(String[] args)
  {
    int hours, minutes, seconds;
    int secondsSinceMidnight;
    int secondsInADay;
    double percentageOfDayPassed;

    hours = 18;
    minutes = 36;
    seconds = 39;

    secondsSinceMidnight = seconds + (60 * minutes) + (60 * 60 * hours);
    System.out.println(String.format("Seconds elapsed since midnight: %d",
                                     secondsSinceMidnight));

    secondsInADay = 60 * 60 * 24;
    percentageOfDayPassed = 100 * (double)secondsSinceMidnight
                            / (double)secondsInADay;
    System.out.println(String.format("Percentage of the current day that is " +
                                     "completed: %.2f%%", percentageOfDayPassed));
  }
}
