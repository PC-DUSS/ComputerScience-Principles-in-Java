import java.awt.Canvas;
import java.awt.Graphics;

/**
 * Extension of the GridCanvas class to be adapted to the Wireworld automaton; not actually inherited because they have
 * different functionality.
 * 
 * @author Pierre-Charles Dussault
 * @since 2021/10/03
 * */
public class WWGridCanvas extends Canvas {
  private WWCell[][] array;
  
  /** Constructor. */
  public WWGridCanvas(int rows, int columns, int size) {
    // Create the array of cells
    array = new WWCell[rows][columns];
    // It is helpful to remember of the array as row-major, although it is not exactly the case
    for (int lRow = 0; lRow < rows; lRow++) {
      int y = lRow * size;
      for (int lColumn = 0; lColumn < columns; lColumn++) {
	int x = lColumn * size;
	// Populate each spot in the array with a cell
	array[lRow][lColumn] = new WWCell(x, y, size);
      }
    }
    
    // Canvas size in format (width, height)
    this.setSize(columns * size, rows * size);
  }

  /** 
   * Draw the grid of cells
   * 
   * @param g graphics object for handling the display
   * */
  public void draw(Graphics g) {
    // For each cell in each row
    for (WWCell[] row : array) {
      for (WWCell cell : row) {
	// Draw the cell
	// ... the graphics object must be passed as a parameter
	cell.draw(g);
      }
    }
  }

  /**
   * Get the cell at a given location.
   * 
   * @param row the row number from the grid, starting from index 0
   * @param column the column number from the grid, starting from index 0
   * @return the cell at the given coordinates
   * */
  public WWCell getCell(int row, int column) {
    return array[row][column];
  }

  /**
   * Set the state of a cell, convenience method for when starting the game.
   * 
   * @param row the row of the cell, starting from index 0
   * @param column the column of the cell, starting from index 0
   * */
  public void setCellState(int row, int column, int state) {
    array[row][column].setState(state);
  }

  /* Legacy methods from Cell class; override to display error message. */
  
  public void turnOn(int row, int column) {
    System.err.println("method turnOn() is deprecated in class WWGridCanvas.");
    System.exit(1);
  }

  public int test(int row, int column) {
    System.err.println("method test() is deprecated in class WWGridCanvas.");
    System.exit(1);
    return 0;
  }

  private int spilloverTest(int row, int column) {
    System.err.println("method spilloverTest() is deprecated in class WWGridCanvas.");
    System.exit(1);
    return 0;
  }

  public int countOn() {
    System.err.println("method countOn() is deprecated in class WWGridCanvas.");
    System.exit(1);
    return 0;
  }

  /* End of legacy method error overrides. */
}
