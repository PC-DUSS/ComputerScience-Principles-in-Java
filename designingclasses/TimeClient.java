public class TimeClient {

  public static void main(String[] args) {

    Time time = new Time(11, 59, 9.984);
    System.out.println(time.getHour());
    Time.printTime(time);
    String timeString = time.toString();
    System.out.println(timeString);
    // This will print the same thing, as print functions automatically call the toString method
    System.out.println(time);

    Time time1 = new Time(9, 30, 0.0);
    Time time2 = time1;
    Time time3 = new Time(9, 30, 0.0);
    System.out.println("time1==time2: " + (time1 == time2));
    System.out.println("time1.equals(time2): " + time1.equals(time2));
    System.out.println("time1==time3: " + (time1 == time3));
    System.out.println("time1.equals(time3): " + time1.equals(time3));

    Time movieStartTime = new Time(18, 50, 0.0);
    Time movieRunningTime = new Time(2, 16, 0.0);
    // Now we can add these two times in two ways: static method with both as arguments, or instance
    // method that is called upon one, and takes the other as argument
    System.out.println(Time.addTimes(movieStartTime, movieRunningTime));
    System.out.println(movieStartTime.addTime(movieRunningTime));
  }
}
