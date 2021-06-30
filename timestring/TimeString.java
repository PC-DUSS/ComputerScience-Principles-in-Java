

public class TimeString
{
  public static void main(String[] args)
  {
    System.out.print(timeString(19, 5));
    testTimeStringInADay();
  }


  public static String timeString(int hours, int minutes)
  {
    String ampm;

    if (hours > 12)
    {
      ampm = "PM";
      hours -= 12;
    }
    else
    {
      ampm = "AM";
      // If it is midnight
      if (hours == 0)
        hours = 12;
    }

    return String.format("%02d:%02d %s", hours, minutes, ampm);
  }

  private static void testTimeStringInADay()
  {
    String currentTime;
    for (int hourInDay = 0; hourInDay < 24; hourInDay++)
    {
      for (int minuteInHour = 0; minuteInHour < 60; minuteInHour++)
      {
        currentTime = timeString(hourInDay, minuteInHour);
        System.out.println(currentTime);
      }
    }
  }
}
