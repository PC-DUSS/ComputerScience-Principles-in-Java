import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JFrame;

public class ManyMickeys extends Canvas {

  private static final long serialVersionUID = 228347824L;

  // Number of iterations for which to draw mickeys
  public static int numOfMickeys = 4;
  // Frame settings
  private static int totalWidth = 400;
  private static int totalHeight = 400;
  // Main outer Rectangle settings
  private static int mainRectWidth = totalWidth;
  private static int mainRectHeight = totalHeight;
  private static int mainRectX = 0;
  private static int mainRectY = 0;
  // Inner Rectangle used for circles settings
  private static int circleRectWidth = totalHeight / 4; // This is intentional, yes
  private static int circleRectHeight = totalHeight / 4;
  private static int circleRectX = (mainRectWidth + mainRectX) / 2 - circleRectWidth / 2;
  private static int circleRectY = (mainRectHeight + mainRectY) / 2 - circleRectHeight / 2;
  // Now instanciate the Rectangles
  public static Rectangle mainRect =
      new Rectangle(mainRectX, mainRectY, mainRectWidth, mainRectHeight);
  public static Rectangle circleRect =
      new Rectangle(circleRectX, circleRectY, circleRectWidth, circleRectHeight);

  /* ----- Main Program ----- */
  public static void main(String[] args) {

    // Initialize a new JFrame, and give it a title
    JFrame frame = new JFrame("Many Mickeys");
    // Make sure theJFrame exits when we close the program
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // Create new Canvas, based on this class, which extends the Canvas class
    ManyMickeys allTheMickeys = new ManyMickeys();
    // Set the size for this Canvas to 400px by 400px
    allTheMickeys.setSize(400, 400);
    // Add this Canvas to the JFrame
    frame.add(allTheMickeys);
    // pack the JFrame and its contents so that they fit appropriately
    frame.pack();
    // Display the JFrame
    frame.setVisible(true);
  }

  /** Extends a default method of Canvas class to use our custom methods */
  public void paint(Graphics graphics) {

    graphics.setColor(Color.BLACK);
    // Create a Rectangle for the first mickey
    Rectangle startRect =
        new Rectangle(circleRect.x, circleRect.y, circleRect.width, circleRect.height);
    drawManyMickeys(graphics, startRect);
  }

  /**
   * Extends a default method of Graphics class, to use the properties of a Rectangle object
   *
   * @param graphics Graphics object for which to update the display
   * @param rect Rectangle object to use for the bounds of the oval shape to draw
   */
  public void boxOval(Graphics graphics, Rectangle rect) {

    graphics.fillOval(rect.x, rect.y, rect.width, rect.height);
  }

  /**
   * Draw all mickeys on the todo list, and return the next todo list of mickeys to draw next time,
   * if there is such a next time.
   *
   * @param graphics Graphics object handled by the paint method and passed to it automatically
   * @param rects Rectangles[] array of Rectangles, a todo list of mickeys to draw
   * @param numOfRects int number of mickeys present in the array of Rectangles, as to avoid
   *     NullPointerException when the number of Rectangle objects present in the rects array is
   *     smaller than the size of the array (the rest are a bunch of nulls)
   */
  public Rectangle[] drawMickeysAndReturnNextMickeys(
      Graphics graphics, Rectangle[] rects, int numOfRects) {

    // Prep the list of Rectangles for the new mickeys
    Rectangle[] newMickeyRects = new Rectangle[numOfRects * 2];
    int index = 0;

    // If this is the first mickey, draw the initial head
    if (numOfRects == 1) boxOval(graphics, rects[0]);

    for (int i = 0; i < numOfRects; i++) {
      // Even index numbers will always be for left ears, and odd index numbers will always be for
      // right ears

      int hx = rects[i].width / 2;
      int hy = rects[i].height / 2;
      Rectangle half = new Rectangle(rects[i].x, rects[i].y, hx, hy);

      // Left ear operation
      half.translate(-hx / 2, -hy / 2);
      boxOval(graphics, half);
      // Save the attributes of the Rectangle for the left ear
      newMickeyRects[index] = new Rectangle(half);
      index++;

      // Right ear operation
      half.translate(2 * hx, 0);
      boxOval(graphics, half);
      // Save the attributes of the Rectangle for the right ear
      newMickeyRects[index] = new Rectangle(half);
      index++;
    }
    return newMickeyRects;
  }

  /**
   * Draw a fractal-like tree of mickeys one on top of each other, each subsequent mickey attaching
   * itself to the previous one's ear.
   *
   * @param graphics Graphics object, automatically passed to paint method from which this method
   *     will be called
   * @param startingRect initial Rectangle object used for painting the first mickey in the chain of
   *     mickeys
   */
  public void drawManyMickeys(Graphics graphics, Rectangle startingRect) {

    Rectangle[] mickeysToDo = new Rectangle[power(2, numOfMickeys)];
    // Rectangle for the initial mickey
    mickeysToDo[0] = startingRect;

    for (int i = 0; i < numOfMickeys; i++) {
      mickeysToDo = drawMickeysAndReturnNextMickeys(graphics, mickeysToDo, power(2, i));
    }
  }

  /*Custom method for raising base x to a power n.

  @param x int base number
  @param n int power to which to raise the base number
  */
  public static int power(int x, int n) {

    int power = 1;
    for (int i = 1; i <= n; i++) {
      power *= x;
    }
    return power;
  }
}
