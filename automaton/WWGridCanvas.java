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
  // 2D array containing all the cells in the grid
  private WWCell[][] array;
  
  /** Constructor. */
  public WWGridCanvas(int rows, int columns, int size) {
    array = new WWCell[rows][columns];
    // Now populate the array
    // It is helpful to remember of the array as row-major, although it is not exactly the case
    for (int lRow = 0; lRow < rows; lRow++) {
      int y = lRow * size;
      for (int lColumn = 0; lColumn < columns; lColumn++) {
	int x = lColumn * size;
	array[lRow][lColumn] = new WWCell(x, y, size);
      }
    }
    
    // Set the canvas size in format (width, height)
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
  
  /** Default method override. */
  public void paint(Graphics g) {
    draw(g);
  }
  
  /**
   * Get the number of rows in the grid.
   * 
   * @return the number of rows in the grid
   * */
  public int numRows() {
    return array.length;
  }
  
  /**
   * Get the number of columns in the grid.
   * 
   * @return the number of columns in the grid
   * */
  public int numColumns() {
    return array[0].length;
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
   * Set the state of a cell, convenience method for when creating an instance of the automaton.
   * 
   * @param row the row of the cell, starting from index 0
   * @param column the column of the cell, starting from index 0
   * */
  public void setCellState(int row, int column, int state) {
    array[row][column].setState(state);
  }
  
  /**
   * Test to see if a cell is an electron head; used when checking for neigboring currents to a conductor cell.
   * 
   * @param row the row index of the cell, starting from index 0
   * @param column the column index of the cell, starting from index 0
   * @return true if the cell has 1 or 2 neighbor electron heads, or 0 if it has anything else
   * */
  public boolean test(int row, int column) {
    try {
      if (array[row][column].getState() == 1) {
	// If cell is an electron head
	return true;
      }
      // If the cell doesn't exist
    } catch (ArrayIndexOutOfBoundsException e) {
      return spilloverTest(row, column);
    }
    
    return false;
  }
  
  /**
   * Test to see if a cell is an electron head, according to toroidal grid style; used when checking for neigboring
   * currents to a conductor cell.
   * 
   * @param row the row index of the cell, starting from 0
   * @param column the column index of the cell, starting from 0
   * @return true if the cell has 1 or 2 neighbor electron heads, or 0 if it has anything else
   * */
  private boolean spilloverTest(int row, int column) {
    // Handle rows spilling over borders
    if (row == -1) {
      row = array.length - 1;
    } else if (row == array.length) {
      row = 0;
    }
    
    // Handle columns spilling over borders
    if (column == -1) {
      column = array[0].length - 1;
    } else if (column == array[0].length) {
      column = 0;
    }
    
    // Test a cell according to the corrected cell coordinates
    if (array[row][column].getState() == 1) {
      // If cell is an electron head
      return true;
    }
    
    return false;
  }
  
  /**
   * Count the total number of cells that are not dead within the game grid.
   * 
   * @return the number of cells that are not dead
   * */
  public int countOn() {
    int numberAlive = 0;
    // For each row of cells in the array
    for (WWCell row[] : array) {
    // For each cell in the row of cells
      for (WWCell cell : row) {
	if (cell.getState() != 0) {
	  numberAlive++;
	}
      }
    }
    
    return numberAlive;
  }
}
