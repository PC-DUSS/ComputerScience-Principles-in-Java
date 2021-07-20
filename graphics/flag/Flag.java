import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JFrame;

public class Flag extends Canvas {

  private static final long serialVersionUID = 23987234L;

  // How many Mickeys to draw
  public static int numOfMickeys = 4;

  // Frame dimensions (also used for the main Rectangle)
  private static int totalWidth = 400;
  private static int totalHeight = 300;

  // Rectangle dimensions, in pixels
  private static int mainRectX = 0;
  private static int mainRectY = 0;
  private static int circleRadius = totalHeight / 2;
  private static int centerRectWidth = circleRadius;
  private static int centerRectHeight = circleRadius;
  // Center the inner Rectangle's coordinates, and then move them up and left by half of each of its
  // dimensions so that its center matches the center of the larger Rectangle
  private static int centerRectX = (totalWidth + mainRectX) / 2 - (centerRectWidth / 2);
  private static int centerRectY = (totalHeight + mainRectY) / 2 - (centerRectHeight / 2);
  // Use total width and height dimensions for the main Rectangle's dimensions
  public static Rectangle mainRect = new Rectangle(mainRectX, mainRectY, totalWidth, totalHeight);
  public static Rectangle centerRect =
      new Rectangle(centerRectX, centerRectY, centerRectWidth, centerRectHeight);

  /* ----- Main Program ----- */
  public static void main(String[] args) {

    // Initialize the Frame on which we'll put the Canvas
    JFrame frame = new JFrame("Flag of Japan");
    // Make it exit when the window is closed
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // New Canvas for the Flag. It will make use of extended methods from Canvas class
    Flag flag = new Flag();
    flag.setSize(totalWidth, totalHeight);

    // Add Flag to the Frame
    frame.add(flag);
    frame.pack();
    frame.setVisible(true);
  }

  /** Extends a default method of Canvas class to use our custom methods */
  public void paint(Graphics g) {

    drawFlagJapan(g);
  }

  /**
   * Draw a red circle in the center of a white (default color) JFrame.
   *
   * @param g Graphics object coming from the paint method
   */
  public void drawFlagJapan(Graphics g) {

    g.setColor(Color.RED);
    g.fillOval(centerRectX, centerRectY, centerRect.width, centerRect.height);
  }
}
