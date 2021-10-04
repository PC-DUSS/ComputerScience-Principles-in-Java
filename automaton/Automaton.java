import javax.swing.JFrame;

/**
 * Superclass to define behaviour of automated zero-player games that run on cells within a 2D grid.
 * 
 * @author Pierre-Charles Dussault
 * @since 2021/10/02
 * */
public abstract class Automaton {
// Abstract class cannot be directly instantiated, only its subclasses can

  // Protected attributes can only be accessed by subclasses
  protected GridCanvas grid;
  
  // Abstract method must be overriden in the subclasses
  public abstract void update();
  
  /**
   * Run the automaton.
   * 
   * @param title the title to give to this automaton
   * @param rate the speed at which the automaton plays
   * */
  public void run(String title, int rate) {
    JFrame frame = new JFrame(title);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    // This adds the canvas (GridCanvas, which is subclass of Canvas) to the frame
    frame.add(grid);
    frame.pack(); // This resizes the frame to fit the canvas
    frame.setVisible(true);
    mainLoop(rate);
  }
  
  /**
   * Main game loop.
   * 
   * @param rate the speed at which the automaton plays
   * */
  private void mainLoop(int rate) {
    while (true) {
      update();
      grid.repaint();
      pause(1000 / rate);
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
}
