import java.awt.Polygon;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Explore more advanced topics with inheritance.
 *
 * @author Pierre-Charles Dussault
 * @since 2021/10/13
 * */
public class DrawablePolygon extends Polygon {
// This class is an example of specialization

  protected Color color;

  /** Default constructor. */
  public DrawablePolygon() {
    super();
    this.color = Color.GRAY;
  }

  /**
   * Draw this polygon based on its color attribute.
   *
   * @param g the graphics context used to draw the polygon on the screen
   * */
  public void draw(Graphics g) {
    g.setColor(color);
    g.fillPolygon(this);
  }

  /**
   * Set the current color to a new color.
   * 
   * @param newColor the desired new color
   * */
  public void setColor(Color newColor) {
    this.color = newColor;
  }

  public void step() {
    // do nothing
  }

  /** Main program. */
  public static void main(String[] args) {
    // Just shits and giggles in here
    DrawablePolygon myPolygon = new DrawablePolygon();
    myPolygon.addPoint(57, 110);
    myPolygon.addPoint(100, 35);
    myPolygon.addPoint(143, 110);
    myPolygon.setColor(Color.GREEN);
  }
}
