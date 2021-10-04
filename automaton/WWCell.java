import java.awt.Color;

/**
 * Extension of the Cell class to be adapted to the Wireworld automaton; not actually inherited because they have
 * different functionality.
 * 
 * @author Pierre-Charles Dussault
 * @since 2021/10/03
 * */
public class WWCell extends Cell {
  // For this implementation, the state values are as follows: 
  // 0=empty(black) 1=electron_head(blue) 2=electron_tail(red) 3=conductor(yellow)
  
  public static final Color[] COLORS = {Color.BLACK, Color.BLUE, Color.RED, Color.YELLOW};
  
  /** 
   * Constructor; sets state to a default value of empty. 
   * 
   * @param x x-axis coordinate; increases towards the right
   * @param y y-axis coordinate; increases towards the bottom
   * @param size size in pixels
   * */
  public WWCell(int x, int y, int size) {
    super(x, y, size);
  }
  
  /** 
   * Getter for the cell's state. 
   * 
   * @return the integer representing the state of the cell
   * */
  public int getState() {
    return state;
  }
  
  /** Setter for the cell's state. */
  public void setState(int newState) {
    state = newState;
  }
  
  /**
   * Check if a cell's state is equal to a given state.
   * 
   * @param stateToCheck the state with which to compare the cell's actual state
   * @return true or false, depending on if they match or not
   * */
  public boolean isState(int stateToCheck) {
    return state == stateToCheck;
  }
  
  /* Legacy methods from Cell class; override to display error message. */
  
  public boolean isOn() {
    System.err.println("method isOn() is deprecated in class WWCell.");
    System.exit(1);
    return state != 0;
  }

  public boolean isOff() {
    System.err.println("method isOn() is deprecated in class WWCell.");
    System.exit(1);
    return state == 0;
  }

  public void turnOn() {
    System.err.println("method isOn() is deprecated in class WWCell.");
    System.exit(1);
  }

  public void turnOff() {
    System.err.println("method isOn() is deprecated in class WWCell.");
    System.exit(1);
  }
  
  /* End of legacy method error overrides. */
}
