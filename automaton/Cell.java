import java.awt.Color;
import java.awt.Graphics;

/**
 * Representation of a cell in Conway's Game of Life.
 * 
 * @author Pierre-Charles Dussault
 * @since 2021/09/17
 * */
public class Cell {
  // Cells don't move, only their state can change
  protected final int x;
  protected final int y;
  protected final int size;
  // Cell is alive=1    cell is dead=0
  protected int state;
  // Possible colors for each cell, index=0 --> white (dead)    index=1 --> black (alive)
  public static final Color[] COLORS = {Color.WHITE, Color.BLACK};
  
  /** 
   * Constructor; sets state to a default value of dead/inactive.
   * 
   * @param x x-axis coordinate; increases towards the right
   * @param y y-axis coordinate; increases towards the bottom
   * @param size size in pixels
   * */
  public Cell(int x, int y, int size) {
    this.x = x;
    this.y = y;
    this.size = size;
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
   * Is this cell alive. 
   * 
   * @return true or false, depending on if this cell is alive or not
   * */
  public boolean isOn() {
    return state != 0;
  }
  
  /**
   * Is this cell dead.
   * 
   * @return true or false, depending on if this cell is dead or not
   * */
  public boolean isOff() {
    return state == 0;
  }
  
  /** Make this cell alive. */
  public void turnOn() {
    state = 1;
  }
  
  /** Make this cell dead. */
  public void turnOff() {
    state = 0;
  }
}
