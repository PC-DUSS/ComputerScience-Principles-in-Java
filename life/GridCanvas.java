import java.awt.Canvas;
import java.awt.Graphics;

/**
 * 2D grid representation as the Canvas for GoL.
 * 
 * @author Pierre-Charles Dussault
 * @since 2021/09/17
 * @version 1.0
 * */
public class GridCanvas extends Canvas {
  // 2D array containing all the cells in the grid
  private Cell[][] array;
  
  /** Constructor. */
  public GridCanvas(int rows, int columns, int size) {
    array = new Cell[rows][columns];
    // Now populate the array
    // It is helpful to remember of the array as row-major, although it is not exactly the case
    for (int lRow = 0; lRow < rows; lRow++) {
      int y = lRow * size;
      for (int lColumn = 0; lColumn < columns; lColumn++) {
	int x = lColumn * size;
	array[lRow][lColumn] = new Cell(x, y, size);
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
    for (Cell[] row : array) {
      for (Cell cell : row) {
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
  public Cell getCell(int row, int column) {
    return array[row][column];
  }
  
  /**
   * Turn on a cell, convenience method for when starting the game.
   * 
   * @param row the row of the cell, starting from index 0
   * @param column the column of the cell, starting from index 0
   * */
  public void turnOn(int row, int column) {
    array[row][column].turnOn();
  }
  
  /**
   * Test to see if a cell is alive.
   * 
   * @param row the row index of the cell, starting from index 0
   * @param column the column index of the cell, starting from index 0
   * @return 1 if the cell is alive, or 0 if it is dead
   * */
  public int test(int row, int column) {
    try {
      if (array[row][column].isOn()) {
	return 1;
      }
      // If the cell doesn't exist
    } catch (ArrayIndexOutOfBoundsException e) {
      return spilloverTest(row, column);
    }
    
    return 0;
  }
  
  /**
   * Test to see if a cell is alive, according to toroidal grid style.
   * 
   * @param row the row index of the cell, starting from 0
   * @param column the column index of the cell, starting from 0
   * @return 1 if the cell is alive, or 0 if it is not alive
   * */
  private int spilloverTest(int row, int column) {
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
    if (array[row][column].isOn()) {
      return 1;
    }
    
    return 0;
  }
  
  /**
   * Count the total number of cells that are alive within the game grid.
   * 
   * @return the number of cells that are alive
   * */
  public int countOn() {
    int numberAlive = 0;
    // For each row of cells in the array
    for (Cell row[] : array) {
    // For each cell in the row of cells
      for (Cell cell : row) {
	if (cell.isOn()) {
	  numberAlive++;
	}
      }
    }
    
    return numberAlive;
  }
}
