import javax.swing.JFrame;

/**
 * Langton's Ant, a zero-player game where an ant explores a grid using set of rules that govern its behaviour
 * depending on what color of cell it is standing on, with the possibilities being white or black.
 * 
 * @author Pierre-Charles Dussault
 * @since 2021/10/01
 * */
public class Langton extends Automaton {
  // Attributes for the ant
  private int xpos;
  private int ypos;
  private int head; // 0=North 1=East 2=South 3=West (clock-wise alternation)

  /** Constructor. */
  public Langton(int rows, int columns) {
    grid = new GridCanvas(rows, columns, 10);
    xpos = rows / 2;
    ypos = columns / 2;
    head = 0;
  }

  /** Update the ant's positioning; override from parent abstract class. */
  public void update() {
    flipCell();
    moveAnt();
  }
  
  /** Find out which way to turn the ant, and change the state of the cell. */
  private void flipCell() {
    Cell cell = grid.getCell(xpos, ypos);
    if (cell.isOff()) {
      // Turn right
      head = (head + 1) % 4;
      cell.turnOn();
    } else {
      // Turn left
      head = (head + 3) % 4;
      cell.turnOff();
    }
  }
  
  /** Move the ant forward one square, using its head to determine in which direction. */
  private void moveAnt() {
    // Remember y value increases as we go down on the screen, and x value increases as we go right.
    if (head == 0) {
      ypos -= 1;
    } else if (head == 1) {
      xpos += 1;
    } else if (head == 2) {
      ypos += 1;
    } else {
      xpos -= 1;
    }
  }
  
  /**
   * Pause the game for a given number of milliseconds.
   * 
   * @param msCount number of milliseconds for which to pause the game
   * */
  private static void pause(int msCount) {
    try {
      Thread.sleep(msCount);
    } catch (InterruptedException e) {
      // do nothing
    }
  }
  
  /** Play Langton's Ant. */
  private static void playLangton() {
    String title = "Langton's Ant";
    Langton game = new Langton(61, 61);
    game.run(title, 2);
  }
  
  /** Main program. */
  public static void main(String[] args) {
    playLangton();
  }
}
