import java.util.ArrayList;
import java.util.List;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;


/**
 * Representation of a sample drawing to draw polygons; advanced inheritance.
 *
 * @author Pierre-Charles Dussault
 * @since 2021/10/13
 * */
public class Drawing extends Canvas {
  // CONSTANTS
  private static final int REFRESH_RATE = 10; // in Hz
  private static final int THREAD_SLEEP_TIME = (int) (1000 / REFRESH_RATE);
  
  // Attributes
  private List<Actor> list;

  /**
   * Value constructor.
   *
   * @param width the width of the total drawing
   * @param height the height of the total drawing
   * */
  public Drawing(int width, int height) {
    this.setSize(width, height);
    this.setBackground(Color.WHITE);
    this.list = new ArrayList<Actor>();
  }

  /**
   * Add a drawable polygon to the list of drawable polygons.
   *
   * @param dp the drawable polygon to add to the list
   * */
  public void add(Actor actor) {
    this.list.add(actor);
  }

  /**
   * Draw all the polygons from the list onto the drawing.
   *
   * @param g the graphics context for painting on the screen
   * */
  public void paint(Graphics g) {
    for (Actor actor : list) {
      actor.draw(g);
    }
  }
  
  /**
   * Create a sample drawing to display regular polygons.
   * 
   * @return the drawing object that was created
   * */
  private static Drawing sampleDrawing() {
    /* Declaring an object variable by the name of its interface is good practice, but it is
     * incumbant upon you to make sure the functionality needed is indeed present. */
    Actor a1 = new BlinkingPolygon(3, 50, Color.GREEN);
    Actor a2 = new BlinkingPolygon(6, 50, Color.BLUE);
    Actor a3 = new BlinkingPolygon(360, 50, Color.RED);
    // Move the polygons out of the top-left corner and space them out
    a1.translate(100, 80);
    a2.translate(250, 120);
    a3.translate(400, 160);
    // Create a drawing and add the polygons
    Drawing drawing = new Drawing(800, 600);
    drawing.add(a1);
    drawing.add(a2);
    drawing.add(a3);
    // Setup the window display frame
    JFrame frame = new JFrame("My Polygons");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(drawing);
    frame.pack();
    frame.setVisible(true);
    
    return drawing;
  }

  /**
   * Pause the program for a given duration.
   * 
   * @param duration time duration in milliseconds for which to pause
   * */
  private static void pause(int timer) {
    try {
      Thread.sleep(timer);
    } catch (InterruptedException e) {
      // do nothing
    }
  }
  
  /** Make the polygons blink on screen. */
  private void blink() {
    while (true) {
      this.step();
      pause(THREAD_SLEEP_TIME);
    }
  }
  
  /** Do some blinking stuff. */
  private void step() {
    for (Actor actor : list) {
      actor.step();
    }

    // This clears the drawing and then redraws
    this.repaint();
  }
  
  /** Method called in Main class to run the program. */
  public static void run() {
    Drawing drawing = sampleDrawing();
    drawing.blink();
  }
}
