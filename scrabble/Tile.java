public class Tile {

  // Instance attributes
  private char letter;
  private int value;

  /* ----- Main Program ----- */
  public static void main(String[] args) {

    Tile tile = new Tile('Z', 10);
    printTile(tile);
  }

  /**
   * Constructor for instance of Tile class.
   *
   * @param letter char letter to be stored in this Tile
   * @param value int value to be stored in this Tile
   */
  public Tile(char letter, int value) {

    this.letter = letter;
    this.value = value;
  }

  // Getters and Setters for the instance attributes of the Tile class
  public char getLetter() {

    return this.letter;
  }

  public int getValue() {

    return this.value;
  }

  public void setLetter(char letter) {

    this.letter = letter;
  }

  public void setValue(int value) {

    this.value = value;
  }
  // end of Getters and Setters.

  /**
   * Print the attributes of an instance of the Tile class to std_out.
   *
   * @param tile Tile class instance for which to print the attributes
   */
  public static void printTile(Tile tile) {

    System.out.println("Letter: " + tile.getLetter());
    System.out.println("Value: " + tile.getValue());
  }

  /**
   * Default method override; neatly format the attributes of an instance of the Tile class.
   *
   * @return a String representing the attributes of the caller instance of the Tile class in pretty
   *     formatting
   */
  public String toString() {

    return String.format("Letter: %c\nValue: %d", letter, value);
  }

  /**
   * Default method override; check if a Tile instance is equivalent to another Tile instance.
   *
   * @param other Tile instance to compare with the caller Tile instance
   * @return boolean if the Tiles have attributes that are equal
   */
  public boolean equals(Tile other) {

    return this.letter == other.getLetter() && this.value == other.getValue();
  }
}
