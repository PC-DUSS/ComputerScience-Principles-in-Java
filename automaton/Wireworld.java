
/**
 * Automaton which models eletrical current in a conductive wire; can be used to model logic gates.
 * 
 * @author Pierre-Charles Dussault
 * @since 2021/10/03
 * */
public class Wireworld extends WWAutomaton {
  
  public Wireworld() {
    System.out.println("Init Wireworld object.");
    // TODO
  }
  
  /** Update all the cells in the wire circuit. */
  private void updateGrid() {
    int numRows = grid.getRows();
    int numColumns = grid.getColumns();
    // TODO finish this function: iterate through all the cells in the grid, and update each cell accordingly.
    // Conductor cell should be checked for neighbors who are electron heads, and those with 1-2 electron head neighbors
    // should be changed to electron heads themselves
  }
  
  /** Override abstract method; update the state of the automaton. */
  public void update() {
    // TODO
  }

  /** Main program. */
  public static void main(String[] args) {
    // TODO
  }
}
