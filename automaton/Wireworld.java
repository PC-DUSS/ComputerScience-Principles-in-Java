import java.io.File;

/**
 * Automaton which models eletrical current in a conductive wire; can be used to model logic gates.
 * 
 * @author Pierre-Charles Dussault
 * @since 2021/10/03
 * */
public class Wireworld extends WWAutomaton {
  // CONSTANTS
  private static final int gridRows = 15;
  private static final int gridColumns = 15;
  private static final int cellSize = 20;
  
  // Global Scanner object
  private final static Scanner = new Scanner(System.in);
  
  /** Default constructor with a basic circuit. */
  public Wireworld() {
    grid = new WWGridCanvas(gridRows, gridColumns, cellSize);
    // First branch in conduction direction
    grid.setCellState(3, 0, 2);
    grid.setCellState(3, 1, 1);
    grid.setCellState(3, 2, 3);
    grid.setCellState(3, 3, 3);
    grid.setCellState(3, 4, 3);
    grid.setCellState(3, 5, 3);
    grid.setCellState(2, 5, 3);
    grid.setCellState(4, 5,3 );
    grid.setCellState(2, 6, 3);
    grid.setCellState(4, 6, 3);
    grid.setCellState(3, 7, 3);
    grid.setCellState(3, 8, 3);
    grid.setCellState(3, 9, 3);
    grid.setCellState(3, 10, 3);
    grid.setCellState(3, 11, 3);
    grid.setCellState(3, 12, 3);
    grid.setCellState(3, 13, 3);
    grid.setCellState(3, 14, 3);
    
    // Second branch in reverse conduction direction
    grid.setCellState(7, 0, 2);
    grid.setCellState(7, 1, 1);
    grid.setCellState(7, 2, 3);
    grid.setCellState(7, 3, 3);
    grid.setCellState(7, 4, 3);
    grid.setCellState(6, 5, 3);
    grid.setCellState(8, 5, 3);
    grid.setCellState(6, 6, 3);
    grid.setCellState(7, 6, 3);
    grid.setCellState(8, 6, 3);
    grid.setCellState(7, 7, 3);
    grid.setCellState(7, 8, 3);
    grid.setCellState(7, 9, 3);
    grid.setCellState(7, 10, 3);
    grid.setCellState(7, 11, 3);
    grid.setCellState(7, 12, 3);
    grid.setCellState(7, 13, 3);
    grid.setCellState(7, 14, 3);
  }
  
  /**
   * Value constructor to allow building a circuit from a file.
   * 
   * @param fileName the name of the file to load for the circuit
   * */
  public Wireworld(String fileName) {
    grid = new WWGridCanvas(gridRows, gridColumns, cellSize);
    if (fileName.endsWith(".cells")) {
      parseFileAsCells(fileName);
    } else if (fileName.endsWith(".rle")) {
      parseFileAsRLE(fileName);
    }
  }
  
  /**
   * Parse a file as a cells format to create an instance of a Wireworld automaton.
   * 
   * @param fileName the name of the file to parse as a cells file
   * */
  private void parseFileAsCells(String fileName) {
    // TODO
  }
  
  /**
   * Parse a file as an RLE format to create an instance of a Wireworld automaton.
   * 
   * @param fileName the name of the file to parse as an RLE file
   * */
  private void parseFileAsRLE(String fileName) {
    // TODO
  }
  
  /** Update all the cells in the wire circuit. */
  private void updateGrid() {
    int numRows = grid.numRows();
    int numColumns = grid.numColumns();
    int gridSize = numRows * numColumns;
    
    // Arrays to hold cells for each category actionable by this method
    WWCell[] toBecomeElectronHead = new WWCell[gridSize];
    int electronHeadIndex = 0;
    WWCell[] toBecomeElectronTail = new WWCell[gridSize];
    int electronTailIndex = 0;
    WWCell[] toBecomeConductor = new WWCell[gridSize];
    int conductorIndex = 0;
    
    // Conductor cell should be checked for neighbors who are electron heads, and those with 1-2 electron head neighbors
    // should be changed to electron heads themselves
    for (int row = 0; row < numRows; row++) {
      for (int column = 0; column < numColumns; column++) {
	WWCell cell = grid.getCell(row, column);
	int cellState = cell.getState();
	if (cellState == 3) {
	  // If it is conductor, check for incoming currents of 1 or 2 electron heads
	  if (checkIncomingCurrents(row, column)) {
	    // If it passes the incoming currents test, set to electron head
	    toBecomeElectronHead[electronHeadIndex] = cell;
	    electronHeadIndex++;
	  }
	} else if (cellState == 1) {
	  // If it is electron head, set to electron tail
	  toBecomeElectronTail[electronTailIndex] = cell;
	  electronTailIndex++;
	} else if (cellState == 2) {
	  // If it is electron tail, set to conductor
	  toBecomeConductor[conductorIndex] = cell;
	  conductorIndex++;
	}
      }
    }

    setElectronHeads(toBecomeElectronHead);
    setElectronTails(toBecomeElectronTail);
    setConductors(toBecomeConductor);
  }
  
  /**
   * Apply the determined changes for cells to become electron heads.
   * 
   * @param cells the array of cells that should become electron heads
   * */
  private void setElectronHeads(WWCell[] cells) {
    for (WWCell cell : cells) {
      if (cell != null)
	cell.setState(1);
    }
  }

  /**
   * Apply the determined changes for cells to become electron tails.
   * 
   * @param cells the array of cells that should become electron tails
   * */
  private void setElectronTails(WWCell[] cells) {
    for (WWCell cell : cells) {
      if (cell != null)
	cell.setState(2);
    }
  }

  /**
   * Apply the determined changes for cells to become conductors.
   * 
   * @param cells the array of cells that should become conductors
   * */
  private void setConductors(WWCell[] cells) {
    for (WWCell cell : cells) {
      if (cell != null)
	cell.setState(3);
    }
  }
  
  /**
   * Check the total number of electron heads that are neighbors to the given cell; change the current cell to an
   * electron head if the total number of neighboring electron heads is equal to either 1 or 2, otherwise do nothing.
   * 
   * @param row the row of the given cell in the grid
   * @param column the column of the given cell in the grid
   * */
  private boolean checkIncomingCurrents(int row, int column) {
    int numOfIncomingCurrents = 0;
    // Check all neighbor cells
    if (grid.test(row - 1, column - 1)) {
      numOfIncomingCurrents++;
    }
    
    if (grid.test(row - 1, column)) {
      numOfIncomingCurrents++;
    }

    if (grid.test(row - 1, column + 1)) {
      numOfIncomingCurrents++;
    }

    if (grid.test(row, column - 1)) {
      numOfIncomingCurrents++;
    }

    if (grid.test(row, column + 1)) {
      numOfIncomingCurrents++;
    }

    if (grid.test(row + 1, column - 1)) {
      numOfIncomingCurrents++;
    }
    
    if (grid.test(row + 1, column)) {
      numOfIncomingCurrents++;
    }
    
    if (grid.test(row + 1, column + 1)) {
      numOfIncomingCurrents++;
    }

    // If 1 or 2 electron heads were found
    if (numOfIncomingCurrents == 1 || numOfIncomingCurrents == 2) {
      return true;
    }

    return false;
  }
  
  /** Override abstract method; update the state of the automaton. */
  public void update() {
    updateGrid();
  }

  /** Main program. */
  public static void main(String[] args) {
    String title = "My Circuit";
    int rate = 2;
    Wireworld myCircuit = new Wireworld();
    myCircuit.run(title, rate);
  }
}
