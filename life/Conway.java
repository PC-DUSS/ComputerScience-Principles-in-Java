import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 * Represent the rules of GoL, and the overall state of the game; this version follows the exercise using an ArrayList
 * for parsing the text file.
 * 
 * @author Pierre-Charles Dussault
 * @since 2021/09/19
 * @version 1.0
 * */
public class Conway {
  private GridCanvas grid;
  // Constant for the size of the grid squares
  
  /** Constructor. */
  public Conway() {
    grid = new GridCanvas(5, 10, 20);
    
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
   * Constructor based on an input file; check filename extension to check if rle file, otherwise assums cells file.
   * 
   * @param filename the name of the file, including the file extension
   * */
  public Conway(String fileName) throws Exception {
    // Extract all the lines from the file which are relevant
    try {
      // Create a file reader on a file object
      File fileObject = new File(fileName);
      Scanner reader = new Scanner(fileObject);
      // Check if file is an RLE file
      if (isRLE(fileName)) {
	decodeRLEFile(reader);
      } else {
	// Otherwise assume the file is a cells file
	decodeCellsFile(reader);
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
      pause(500);
      // System.out.println(grid.countOn());
    }
  }

  /**
   * For a given cell, count the number of ne      // System.out.println(grid.countOn());ighbor cells that are alive.
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
  
  /**
   * Helper method for inside the constructor; decode the game setup from an RLE file.
   * 
   * @param reader a Scanner to read the file
   * */
  private void decodeRLEFile(Scanner reader) throws Exception {
    while (reader.hasNextLine()) {
      String line = reader.nextLine();
      if (line.startsWith("x")) {
	// Handle the grid dimensions
	int[] dimensions = readDimensions(line);
	int rows = dimensions[0];
	int columns = dimensions[1];
	grid = new GridCanvas(rows, columns, 20);
      } else if (line.startsWith("$")) {
	// Handle the placement of cells
	determineCellsForRLE(line);
      }
    }
  }
  
  /**
   * Helper method for inside the constructor; decode the game setup from a cells file.
   * 
   * @param reader a Scanner to read the file
   * */
  private void decodeCellsFile(Scanner reader) {
    int rows = 0;
    int columns = 0;
    ArrayList<String> listOfLines = new ArrayList<String>();
    
    while (reader.hasNextLine()) {
      String line = reader.nextLine(); // The current line being read
      // Add all relevant lines to the list of lines
      if (line.startsWith(".") || line.startsWith("O")) {
	listOfLines.add(line);
	rows++;
	// If we haven't counted the columns already, count the columns 
	if (columns == 0) {
	  columns = line.length(); // Equivalent to the number of chars on a valid line
	}
      }
    }
    
    // Use the stored dimensions to instanciate the grid
    grid = new GridCanvas(rows, columns, 20);
    // Parse the saved lines to determine the active cells
    parseLines(listOfLines);
  }
  
  /**
   * Determine the arrangement of active and inactive cells in the grid for an RLE file.
   * 
   * @param line the line to parse
   * */
  private void determineCellsForRLE(String line) throws Exception {
    int row = 0;
    int column = 0;
    int multiplier = 1;
    for (int c = 1; c < line.length(); c++) {
      int[] gridPosition = {row, column};
      char currentChar = line.charAt(c);
      if (Character.isDigit(currentChar)) {
	multiplier = parseDigits(line, c);
      } else if (currentChar == 'b') {
	// Move column tracker to next unused cell in this row and reset the multiplier
	column += multiplier;
	multiplier = 1;
      } else if (currentChar == 'o') {
	activateConsecutiveCells(multiplier, gridPosition);
	column += multiplier;
	multiplier = 1;
      } else if (currentChar == '$') {
	// Change grid position to the start of the next line
	row++;
	column = 0;
      } else if (currentChar == '!') {
	// End of grid arrangement instructions
	break;
      }
    }
  }
  
  /**
   * Activate cells from a row in the grid in a consecutive streak, given an initial grid position and a multiplier.
   * 
   * @param multiplier the number of cells in this streak to fill
   * @param gridPosition the initial position for the row in which to fill the consecutive streak of cells in the format
   * 	[row, column]
   * */
  private void activateConsecutiveCells(int multiplier, int[] gridPosition) {
    int row = gridPosition[0];
    int column = gridPosition[1];
    for (int i = 0; i < multiplier; i++) {
      grid.turnOn(row, column);
      column++;
    }
  }
  
  /**
   * Read x (columns) and y (rows) dimensions from a line of text inside an RLE file.
   * 
   * @param line the line of text to parse
   * @return an array of the dimensions in format [rows, columns]
   * */
  private static int[] readDimensions(String line) throws Exception {
    int c = 0;
    int rows = 0;
    int columns = 0;
    while (c < line.length()) {
      if (line.charAt(c) == 'x') {
	c = findEqualSign(line, c);
	c = findDigit(line , c + 1);
	columns = parseDigits(line, c);
	// set c to the index of the first char following the last digit of the chain of consecutive digits
	c = c + String.valueOf(columns).length();
      } else if (line.charAt(c) == 'y') {
	c = findEqualSign(line, c);
	c = findDigit(line, c);
	rows = parseDigits(line, c);
      }
      
      c++;
    }
    
    if (columns == 0 || rows == 0) {
      throw new Exception("Error, dimensions not correctly set.");
    }
    
    int[] dimensions = {rows, columns};
    return dimensions;
  }
  
  /**
   * Parse the current chain of consecutive digits and return the number formed by the digits.
   * 
   * @param line the line that contains the digits
   * @param index the index of the initial chain of digits 
   * @return the number formed by the digits
   * */
  private static int parseDigits(String line, int index) throws Exception {
    StringBuilder numberSB = new StringBuilder();
    while (index < line.length()) {
      // Exit loop if current char is not a digit anymore
      if (!Character.isDigit(line.charAt(index))) {
	break;
      }
      
      numberSB.append(line.charAt(index));
      index++;
    }
    
    // If the number is empty
    String numberString = numberSB.toString();
    if (numberString.equals("")) {
      throw new Exception("Error in parseDigits(). Empty number.");
    }
    
    try {
      return Integer.parseInt(numberString);
    } catch (Exception e) {
      throw new Exception("Error in parseDigits(). Number contains non-digits.");
    }
  }
  
  /**
   * Find the index of the first encountered digits while parsing a line.
   * 
   * @param line the line to parse
   * @param index the index from which to start when trying to find a digit
   * @return the index of the first encountered digit
   * */
  private static int findDigit(String line , int index) throws Exception {
    try {
      while (!Character.isDigit(line.charAt(index))) {
	index ++;
      }
    } catch (Exception e) {
      throw new Exception("Error out of bounds in findDigit()");
    }

    return index;
  }
  
  /**
   * Get the index position of the first equal sign encountered while parsing a String from an initial index position.
   * 
   * @param line the String to parse
   * @param index the starting index
   * @return the index of the first encountered equal sign
   * */
  private static int findEqualSign(String line, int index) throws Exception {
    try {
      while (line.charAt(index) != '=') {
	index++;
      }
    } catch (Exception e) {
      throw new Exception("Error, out of bounds in findEqualSign()");
    }
    
    return index;
  }
  
  /**
   * Check if a file is of RLE filetype.
   * 
   * @param fileName the name of the file
   * @return true or false, depending on if the file is an RLE file or not
   * */
  private static boolean isRLE(String fileName) {
    int nameLength = fileName.length();
    int index = nameLength - 1;
    StringBuilder fileNameExtension = new StringBuilder();
    while (fileName.charAt(index) != '.') {
      fileNameExtension.append(fileName.charAt(index));
      index--;
    }
    
    fileNameExtension.reverse();
    if (fileNameExtension.toString().equals("rle")) {
      return true;
    }
    
    return false;
  }
  
  /**
   * Parse the list of lines to determine the grid and cell arrangement.
   * 
   * @param listOfLines ArrayList containing all relevant lines to parse
   * */
  private void parseLines(ArrayList<String> listOfLines) {
    Iterator<String> lineIterator = listOfLines.iterator();
    int row = 0; // Store the number of the current row for the grid
    while (lineIterator.hasNext()) {
      String line = lineIterator.next(); // The current line being checked
      for (int column = 0; column < line.length(); column++) {
	if (line.charAt(column) == 'O') {
	  grid.turnOn(row, column); // Using current row and current column
	}
      }

      row++;
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
  
  /**
   * Play Conway's Game of Life with current settings.
   * 
   * @param args command line arguments passed along from main method
   * */
  public static void playConway(String[] args) throws Exception {
    String title = "Conway's game of life";
    // Create an instance of the game using a .cells file
    Conway game = new Conway("oscillator.rle");
    JFrame frame = new JFrame(title);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    // This adds the canvas (GridCanvas, which is subclass of Canvas) to the frame
    frame.add(game.grid);
    frame.pack(); // This resizes the frame to fit the canvas
    frame.setVisible(true);
    game.mainLoop();
  }
  
  /** Main program. */
  public static void main(String[] args) throws Exception {
    playConway(args);
  }
}
