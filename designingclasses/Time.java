public class Time {

  // (hour:minute:second) in 24-hr format
  private int hour;
  private int minute;
  private double second;

  // Create a constructor used to instantiate new instances of the Time class. It's a public method
  // with the same name as the class, also using CapitalCase. Constructors do not explicit a return
  // value, and you do not specfy void type in the method declaration. They return an object
  // instance of the class implicitly

  /**
   * Default constructor: construct an instance of Time with a value equal to midnight (00:00:00.0)
   */
  public Time() {

    this.hour = 0;
    this.minute = 0;
    this.second = 0.0;
  }

  /**
   * Example value constructor: java figures out which constructor to use based on the arguments
   * passed to it when it is called. Partly by the number of arguments, and partly by the type of
   * the arguments.
   *
   * @param pHour int value for hours
   * @param pMinute int value for minutes
   * @param pSecond double value for seconds, including decimals to have access to milliseconds
   */
  public Time(int pHour, int pMinute, double pSecond) {

    this.hour = pHour;
    this.minute = pMinute;
    this.second = pSecond;
  }

  /* ----- Main Program ----- */
  public static void main(String[] args) {

    // Creates a Time at midnight
    Time midnight = new Time();
    // Creates a Time at a fraction of a second before noon
    Time almostNoon = new Time(11, 59, 59.999);
  }

  public int getHour() {

    return this.hour;
  }

  public int getMinute() {

    return this.minute;
  }

  public double getSecond() {

    return this.second;
  }

  public void setHour(int pHour) {

    this.hour = pHour;
  }

  public void setMinute(int pMinute) {

    this.minute = pMinute;
  }

  public void setSecond(int pSecond) {

    this.second = pSecond;
  }

  public static void printTime(Time t) {

    // Support displaying milliseconds
    System.out.printf("%02d:%02d:%06.3f\n", t.hour, t.minute, t.second);
  }

  // This is an instance method, it is invoked on an instance of a class. It is 'non-static', it
  // depends on the instance, not the class. Getters and Setter are also instance methods, or
  // 'non-static' methods
  public String toString() {

    // Overide the default toString method for this class
    return String.format("%02d:%02d:%06.3f", this.hour, this.minute, this.second);
  }

  /**
   * Override default equals method for more contextually appropriate behaviour.
   *
   * @param other Time object to compare the caller object with for equality of attributes
   */
  public boolean equals(Time other) {

    // Consider half of the smallest unit, 1 millisecond, as the maximum tolerable uncertainty
    final double DELTA = 0.0005;

    return this.hour == other.getHour()
        && this.minute == other.getMinute()
        // It is ill-advised to compare floating point numbers for equality, because rounding errors
        // are common. It is better to compare for near equality using a tolerable uncertainty
        && (this.second - other.getSecond()) <= DELTA;
  }

  /**
   * Static method to return a Time object that is the result of the sum of the attributes of the
   * two previous Time objects; format the time to make sure it makes sense.
   *
   * @param time1 First Time object to add
   * @param time2 Second Time object to add
   * @return the sum of the attributes of both Time objects inside a new Time object
   */
  public static Time addTimes(Time time1, Time time2) {

    Time sum =
        new Time(
            time1.getHour() + time2.getHour(),
            time1.getMinute() + time2.getMinute(),
            time1.getSecond() + time2.getSecond());

    // Adjust the attributes to make sure the time makes sense
    if (sum.second >= 60.0) {
      sum.second -= 60.0;
      sum.minute++;
    }
    if (sum.minute >= 60) {
      sum.minute -= 60;
      sum.hour++;
    }
    if (sum.hour >= 24) {
      sum.hour -= 24;
      // sum.day++ ??? Not yet implemented
    }

    return sum;
  }

  /**
   * Instance method to add a Time object's attributes to the caller Time object; format the time to
   * make sure it makes sense.
   *
   * @param other Time object from which to add attributes to the caller Time object
   * @return a new Time object whose attributes are the sum of the caller's and the argument's
   *     attributes
   */
  public Time addTime(Time other) {

    Time sum =
        new Time(
            this.hour + other.getHour(),
            this.minute + other.getMinute(),
            this.second + other.getSecond());

    // Adjust the attributes to make sure the time makes sense
    if (sum.second >= 60.0) {
      sum.second -= 60.0;
      sum.minute++;
    }
    if (sum.minute >= 60) {
      sum.minute -= 60;
      sum.hour++;
    }
    if (sum.hour >= 24) {
      sum.hour -= 24;
      // sum.day++ ??? Not yet implemented
    }

    return sum;
  }
}
