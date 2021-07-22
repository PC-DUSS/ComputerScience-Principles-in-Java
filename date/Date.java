public class Date {

  private int year;
  private int month;
  private int day;

  public static void main(String[] args) {

    Date birthday = new Date(1997, 11, 12);
  }

  /** Default constructor; initializes an instance of Date with January 1st, 2000. */
  public Date() {

    this.year = 2000;
    this.month = 1;
    this.day = 1;
  }

  /**
   * Date constructor; creates an instance of Date in the format (YYYY, MM, DD).
   *
   * @param year int 4 digit value for the year
   * @param month int 2 digit value for the month
   * @param day int 2 digit value for the day
   */
  public Date(int year, int month, int day) {

    this.year = year;
    this.month = month;
    this.day = day;
  }

  // Getters and Setters for instance variables of the Date class
  public int getYear() {

    return this.year;
  }

  public int getMonth() {

    return this.month;
  }

  public int getDay() {

    return this.day;
  }
  // end of Getter and Setters.

}
