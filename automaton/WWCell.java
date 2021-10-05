import java.awt.Color;

/**
 * Extension of the Cell class to be adapted to the Wireworld automaton; not actually inherited because they have
 * different functionality.
 * 
 * @author Pierre-Charles Dussault
 * @since 2021/10/03
 * */
public class WWCell extends Cell {
   // Cells don't move, only their state can change
  private final int x;
  private final int y;
  private final int size;
  // Possible states    dead=0    electron_head=1    electron_tail=2    conductor=3
  private int state;
  // Possible colors for each cell, depending on its state
  //    dead=BLACK    electron_tail=BLUE    electron_tail=RED    conductor=YELLOW
  public static final Color[] COLORS = {Color.BLACK, Color.BLUE, Color.RED, Color.YELLOW};
  
  /** 
   * Constructor; sets state to a default value of dead/inactive.
   * 
   * @param x x-axis coordinate; increases towards the right
   * @param y y-axis coordinate; increases towards the bottom
   * @param size size in pixels
   * */
  public WWCell(int x, int y, int size) {
    this.x = x;
    this.y = y;
    this.size = size;
    // Default the cell's state to dead when creating the grid
    this.state = 0;
  }
  
  /**
   * Draw this cell.
   * 
   * @param g Graphics object passed automatically to the method
   * */
  public void draw(Graphics g) {
    // Set the color according to the state of the this cell
    g.setColor(COLORS[state]);
    // Leave a thin border of space between the cell's rect's bounds and the color filling
    g.fillRect(x + 1, y + 1, size - 1, size - 1);
    g.setColor(Color.LIGHT_GRAY);
    // Now draw a lightgray rect around the inner colored rect, to create a grid across the game board
    g.drawRect(x, y, size, size);
  }
  
  /**
   * Get the state for this cell.
   * 
   * @return the state of this cell
   * */
  public int getState() {
    return state;
  }
  
  /**
   * Determine if the state of this cell is equal to a given state.
   * 
   * @param comparisonState state with which to compare the state of this cell
   * @return true or false, depending on if the the two states are equal
   * */
  public boolean isState(int comparisonState) {
    return state == comparisonState;
  }
  
  /** 
   * Make this cell alive. 
   * 
   * @param newState the state to give to this cell: dead=0, electron_head=1, electron_tail=2, conductor=3
   * */
  public void setState(int newState) {
    state = newState;
  }
}
