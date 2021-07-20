import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JFrame;

/** Class to extend java_awt_Canvas; create a fractal of a hidden Mickey. */
public class Mickey extends Canvas {

  private static final long serialVersionUID = 234892374L;

  public static void main(String[] args) {

    // Create a JFrame, and give it a title
    JFrame frame = new JFrame("Hidden Mickey");
    // Enable closing of the JFrame on process exit
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // Create Canvas to insert in the JFrame
    Mickey myMickey = new Mickey();
    // Set the size of the Canvas to 400px wide by 400px high
    myMickey.setSize(400, 400);
    // Add the Canvas to the JFramce
    frame.add(myMickey);
    // Format the Canvas to fit precisely into the JFrame
    frame.pack();
    // ... and make it visible
    frame.setVisible(true);
  }

  /**
   * Paint onto the Canvas.
   *
   * @param g Graphics object
   */
  public void paint(Graphics g) {

    g.setColor(Color.BLACK);
    // Create Rectangle for the base head of the mickey
    // Coordinates: (x: 100px, y: 100px)    Dimensions: (width: 200px, height: 200px)
    Rectangle headRectangle = new Rectangle(100, 100, 200, 200);
    drawMickey(g, headRectangle);
  }

  /**
   * Draw an Oval inside a Rectangle.
   *
   * @param g Graphics object
   * @param rect Rectangle object
   */
  public void boxOval(Graphics g, Rectangle rect) {

    g.fillOval(rect.x, rect.y, rect.width, rect.height);
  }

  /**
   * Draw the Mickey.
   *
   * @param g Graphics object
   * @param rect Rectangle object
   */
  public void drawMickey(Graphics g, Rectangle rect) {

    // Draw the initial circle for the head
    boxOval(g, rect);
    // Set the new values for the ears
    int hx = rect.width / 2;
    int hy = rect.height / 2;
    // Create new smaller Rectangle for the ears, but positionned at the same coordinates
    Rectangle earRectangle = new Rectangle(rect.x, rect.y, hx, hy);
    // Now translate the ear Rectangle to left side first
    earRectangle.translate(-hx / 2, -hy / 2);
    // Draw the left ear
    boxOval(g, earRectangle);
    // Now place the Rectangle on the right side
    earRectangle.translate(hx * 2, 0);
    // And draw the right ear
    boxOval(g, earRectangle);
  }
}
