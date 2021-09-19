import javax.swing.JFrame;

/**
 * Represent the rules of GoL, and the overall state of the game.
 * 
 * @author Pierre-Charles Dussault
 * @since 2021/09/19
 * @version 1.0
 * */
public class Conway {
  private GridCanvas grid;
  
  /** Constructor. */
  public Conway() {
    grid = new GridCanvas(5, 10, 20);
    grid.turnOn(2, 1);
    grid.turnOn(2, 2);
    grid.turnOn(2, 3);
    grid.turnOn(1, 7);
    grid.turnOn(2, 7);
    grid.turnOn(3, 7);
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
    Conway game = new Conway();
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
