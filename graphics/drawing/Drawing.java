import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JFrame;

public class Drawing extends Canvas {

  private static final long serialVersionUID = 123234245L;

  public static void main(String[] args) {

    JFrame frame = new JFrame("My Drawing");
    // Make sure you can close the frame by clicking on exit
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Drawing drawing = new Drawing();
    drawing.setSize(400, 400);
    // Place the drawing in the frame
    frame.add(drawing);
    // This resizes the the frame to make it fit to the canvas
    frame.pack();
    frame.setVisible(true);
  }

  /**
   * Method automatically called on the JFrame on each visual refresh when necessary.
   *
   * @param g Graphics object passed on to it automatically
   */
  public void paint(Graphics g) {

    // setColor defines the color of everything drawn AFTER it was called
    g.setColor(Color.RED);
    // You can create your own color
    Color purple = new Color(128, 0, 128);
    g.setColor(purple);
    // Make a Rectangle for the starting Oval (circle, actually, since the rectangle is a square)
    Rectangle drawingRect = new Rectangle(100, 100, 200, 200);
    // Draw an Oval in the Rectangle (actually, like mentioned, a circle in the square...)
    g.fillOval(drawingRect.x, drawingRect.y, drawingRect.width, drawingRect.height);
  }

  /**
   * Custom method to fill an oval represented inside a Rectangle object within a Graphics object.
   *
   * @param g Graphics object coming from the paint method
   * @param bb Rectangle from which to model the oval
   */
  public void boxOval(Graphics g, Rectangle bb) {

    g.fillOval(bb.x, bb.y, bb.width, bb.height);
  }
}
