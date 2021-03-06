import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 * Represent the rules of GoL, and the overall state of the game; this version uses my personal solution to parsing a
 * text file, using a HashMap, as opposed to following the exercise which prescribes using an ArrayList.
 * 
 * @author Pierre-Charles Dussault
 * @since 2021/09/19
 * @version 1.0
 * */
public class MyConway {
  private GridCanvas grid;
  // Constant for the size of the grid squares
  private static final int size = 20;
  
  /** Constructor. */
  public MyConway() {
    grid = new GridCanvas(5, 10, size);
    // Oscillator
    //grid.turnOn(2, 1);
    //grid.turnOn(2, 2);
    //grid.turnOn(2, 3);
    //grid.turnOn(1, 7);
    //grid.turnOn(2, 7);
    //grid.turnOn(3, 7);
    
    // Glider
    grid.turnOn(2, 0);
    grid.turnOn(2, 1);
    grid.turnOn(2, 2);
    grid.turnOn(1, 2);
    grid.turnOn(0, 1);
  }
  
  /**
   * Constructor based on an input file. 
   * 
   * @param filename the name of the file, including the file extension
   * */
  public MyConway(String fileName) {
    int rows = 0;
    int columns = 0;
    HashMap<String, int[]> cellsToActivate = new HashMap<>();
    try {
      File fileObject = new File(fileName);
      Scanner reader = new Scanner(fileObject);
      while (reader.hasNextLine()) {
	String line = reader.nextLine();
	// Ignore commented lines (that start with '!')
	if (line.startsWith("!")) {
	  continue;
	} else if (line.startsWith(".") || line.startsWith("O")) {
	  // Go through the row only once, acquiring the dimensions of the grid at the same time as you determine the
	  // positioning of the active cells
	  rows++;
	  if (columns == 0) {
	    // This will hold the number of columns in the grid
	    columns = line.length();
	  }
	  
	  for (int c = 0; c < columns; c++) {
	    // If the cell should be activated
	    if (line.charAt(c) == 'O') {
	      // Get the number of the current cell
	      String cellName = String.format("cell%d", (rows - 1) * columns + c);
	      // Store the coordinates of the cell in the hashmap as (K=cellName, V={row, column})
	      int[] coordinates = {rows - 1, c};
	      cellsToActivate.put(cellName, coordinates);
	    }
	  }
	}
      } // end of reader loop
    
      reader.close();
    } catch (FileNotFoundException e) {
      System.err.println("Error.");
      e.printStackTrace();
      // Exiting with status 1 means error, whereas status of 0 means success
      System.exit(1);
    }
    
    // Tests START
    // System.out.println("Num of rows: " + rows);
    // System.out.println("Num of columns: " + columns);
    // System.out.println("Num of cells to activate: " + cellsToActivate.keySet().size());
    // System.out.println(cellsToActivate.toString());
    // System.out.println();
    // displayCellsToActivate(cellsToActivate);
    // Tests END
    
    // Instanciate the game grid and activate the cells according to the file
    grid = new GridCanvas(rows, columns, size);
    for (String cellName : cellsToActivate.keySet()) {
      int[] coordinates = cellsToActivate.get(cellName);
      int row = coordinates[0];
      int column = coordinates[1];
      grid.turnOn(row, column);
      System.out.printf("Activated %s at %s\n", cellName, Arrays.toString(coordinates));
    }
  }
  
  /**
   * Display all the cells that should be activated on game start, and their respective coordinates.
   * 
   * @param cells the cells that will be activated
   * */
  private void displayCellsToActivate(HashMap<String, int[]> cells) {
    for (String key : cells.keySet()) {
      int[] rawCoordinates = cells.get(key);
      String prettyCoordinates = Arrays.toString(rawCoordinates);
      System.out.printf("%s: %s\n", key, prettyCoordinates);
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
    MyConway game = new MyConway("glider.cells");
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
