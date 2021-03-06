import java.util.ArrayList;
import java.util.List;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JFrame;


/**
 * Representation of a sample drawing to draw polygons; advanced inheritance.
 *
 * @author Pierre-Charles Dussault
 * @since 2021/10/13
 * */
public class Drawing extends Canvas {
  // CONSTANTS
  private static final int REFRESH_RATE = 30; // in Hz
  private static final int THREAD_SLEEP_TIME = (int) (1000 / REFRESH_RATE);

  // Attributes
  protected List<Actor> list;
  protected Rectangle bounds;

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
    this.bounds = new Rectangle(0, 0, width, height);
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

  /** Main loop for the program. */
  private void mainLoop() {
    while (true) {
      this.step();
      pause(THREAD_SLEEP_TIME);
    }
  }

  /** Method that determines what happens for each step in the main loop. */
  public void step() {
    for (Actor actor : list) {
      // The behaviour of this method depends on its implementation inside the caller object's class
      actor.step();
    }

    // This clears the drawing and then redraws
    this.repaint();
  }

  /**
   * Create a sample drawing of polygons.
   *
   * @return the drawing object that was created
   * */
  private static void samplePolygons() {
    /* Declaring an object variable by the name of its interface is good practice, but it is
     * incumbant upon you to make sure the functionality needed is indeed present. */
    DrawablePolygon p1 = new BlinkingPolygon(3, 50, Color.GREEN);
    DrawablePolygon p2 = new BlinkingPolygon(6, 50, Color.BLUE);
    DrawablePolygon p3 = new BlinkingPolygon(360, 50, Color.RED);

    // Move the polygons out of the top-left corner and space them out
    p1.translate(100, 80);
    p2.translate(250, 120);
    p3.translate(400, 160);

    // Create a drawing and add the polygons
    Drawing drawing = new Drawing(800, 600);
    drawing.add(p1);
    drawing.add(p2);
    drawing.add(p3);

    // Setup the window display frame
    JFrame frame = new JFrame("My Polygons");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(drawing);
    frame.pack();
    frame.setVisible(true);

    drawing.mainLoop();
  }

  /**
   * Create a sample drawing with a moving Sprite.
   *
   * @return the drawing object that was created
   * */
  private static void sampleSprite() {
    Sprite sprite = new Sprite("face-smile.png", 25, 150);
    Drawing drawing = new Drawing(800, 600);
    drawing.add(sprite);
    drawing.addKeyListener(sprite);
    // Make the drawing focusable so that it can register keyboard input
    drawing.setFocusable(true);

    JFrame frame = new JFrame("My Sprite");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(drawing);
    frame.pack();
    frame.setVisible(true);

    drawing.mainLoop();
  }

  /** Method called in Main class to run the program. */
  public static void run() {
    sampleSprite();
  }

  /**
   * Detect collisions between actors in the Drawing.
   *
   * @return true or false, depending on if any actors collided or not
   * */
  public boolean detectCollisions() {
    // TODO

    // stub
    return false;
  }
}
