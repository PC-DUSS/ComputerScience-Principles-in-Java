import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 * Represent the rules of GoL, and the overall state of the game; this version follows the exercise using an ArrayList
 * or parsing the text file.
 * 
 * @author Pierre-Charles Dussault
 * @since 2021/09/19
 * @version 1.0
 * */
public class Conway {
  private GridCanvas grid;
  // Constant for the size of the grid squares
  private static final int size = 20;
  
  /** Constructor. */
  public Conway() {
    grid = new GridCanvas(5, 10, size);
    // Oscillator
    grid.turnOn(2, 1);
    grid.turnOn(2, 2);
    grid.turnOn(2, 3);
    grid.turnOn(1, 7);
    grid.turnOn(2, 7);
    grid.turnOn(3, 7);
    
    // Glider
    // grid.turnOn(2, 0);
    // grid.turnOn(2, 1);
    // grid.turnOn(2, 2);
    // grid.turnOn(1, 2);
    // grid.turnOn(0, 1);
  }
  
  /**
   * Constructor based on an input file. 
   * 
   * @param filename the name of the file, including the file extension
   * */
  public Conway(String fileName) {
    int rows = 0;
    int columns = 0;
    ArrayList<String> listOfLines = new ArrayList<String>();
    try {
      File fileObject = new File(fileName);
      Scanner reader = new Scanner(fileObject);
      while (reader.hasNextLine()) {
	String line = reader.nextLine();
	// Add all relevant lines to the list of lines
	if (line.startsWith(".") || line.startsWith("O")) {
	  listOfLines.add(line);
	  // Handle acquisition of the grid dimensions as you build up the list
	  rows++;
	  if (columns == 0) {
	    // This will hold the number of columns in the grid
	    columns = line.length();
	  }
	}
      }
      
      // Use the discovered dimensions to instanciate the grid
      GridCanvas grid = new GridCanvas(rows, columns, size);
      // Now parse the list to determine the grid and cell arrangement
      Iterator<String> lineIterator = listOfLines.iterator();
      int row = 0;
      while (lineIterator.hasNext()) {
	String line = lineIterator.next();
	for (int column = 0; column < line.length(); column++) {
	  if (line.charAt(column) == '0') {
	    grid.turnOn(row, column);
	  }
	}
	
	row++;
      }
      
      reader.close();
    } catch (FileNotFoundException e) {
      System.err.println("Error.");
      e.printStackTrace();
      // Exiting with status 1 means error, whereas status of 0 means success
      System.exit(1);
    }
  }
  
  /** Game loop. */
  private void mainLoop() {
    while (true) {
      update();
      // repaint() comes from Canvas class
      grid.repaint();
      // Pause for 500ms
      try {
	Thread.sleep(500);
      } catch (InterruptedException e) {
	// do nothing
      }
      
      // System.out.println(grid.countOn());
    }
  }

  /**
   * For a given cell, count the number of neighbor cells that are alive.
   * 
   * @param row row index of the cell, starting from index 0
   * @param column column index of the cell, starting from index 0
   * @return the number of neighbor cells that are alive
   * */
  public int countAlive(int row, int column) {
    int count = 0;
    count += grid.test(row - 1, column - 1);
    count += grid.test(row - 1, column);
    count += grid.test(row, column - 1);
    count += grid.test(row + 1, column + 1);
    count += grid.test(row + 1, column);
    count += grid.test(row, column + 1);
    count += grid.test(row - 1, column + 1);
    count += grid.test(row + 1, column - 1);
    return count;
  }
  
  /**
   * Get the number of alive neighbors for each cell on the grid.
   * 
   * @return a 2D array containing each cell's amount of live neighbors
   * */
  private int[][] countNeighbors() {
    int rows = grid.numRows();
    int columns = grid.numColumns();
    int[][] neighborCounts = new int[rows][columns];
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < columns; c++) {
	neighborCounts[r][c] = countAlive(r, c);
      }
    }
    
    return neighborCounts;
  }
  
  /** Update the grid of cells for each iteration through the main loop. */
  private void updateGrid(int[][] neighborCounts) {
    int rows = grid.numRows();
    int columns = grid.numColumns();
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < columns; c++) {
	Cell cell = grid.getCell(r, c);
	updateCell(cell, neighborCounts[r][c]);
      }
    }
  }
  
  /**
   * Update the status of a cell, depending on its neighbors; this method is static, since it does not depend on the
   * grid object that is instanciated with this class' constructor.
   * 
   * @param cell the cell in question
   * @param neighbors the number of alive neighbors this cell has
   * */
  private static void updateCell(Cell cell, int neighbors) {
    if (cell.isOn()) {
      if (neighbors < 2 || neighbors > 3) {
	cell.turnOff();
      }
    } else {
      if (neighbors == 3) {
	cell.turnOn();
      }
    }
  }
  
  /** Update the game state. */
  public void update() {
    int[][] neighborCounts = countNeighbors();
    updateGrid(neighborCounts);
  }
  
  /** Main program. */
  public static void main(String[] args) {
    String title = "Conway's game of life";
    // Create an instance of the game using a .cells file
    Conway game = new Conway("glider.cells");
    JFrame frame = new JFrame(title);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    // This adds the canvas (GridCanvas, which is subclass of Canvas) to the frame
    frame.add(game.grid);
    frame.pack(); // This resizes the frame to fit the canvas
    frame.setVisible(true);
    game.mainLoop();
  }
}
