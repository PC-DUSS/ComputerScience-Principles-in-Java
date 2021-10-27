import java.awt.Color;
import java.awt.Rectangle;

/**
 * Representation of a regular polygon, extending DrawablePolygon; to explore more advanced
 * features of inheritance.
 *
 * @author Pierre-Charles Dussault
 * @since 2021/10/13
 * */
public class RegularPolygon extends DrawablePolygon {
// Keep in mind this class uses radians as a unit for angles
  protected int radius;
  protected int diameter;

  /**
   * Value constructor.
   *
   * @param numSides number of sides for this polygon
   * @param radius the distance from the center to the vertex
   * @param color the fill color of this polygon, based on the Color class
   * */
  public RegularPolygon(int numSides, int radius, Color color) {
    // Validate the arguments
    if (numSides < 3) {
      throw new IllegalArgumentException("invalid numSides for RegularPolygon");
    }

    if (radius <= 0) {
      throw new IllegalArgumentException("invalid radius for RegularPolygon");
    }

    if (color == null) {
      throw new IllegalArgumentException("invalid color for RegularPolygon");
    } // end of argument validation

    this.npoints = numSides;
    this.xpoints = new int[numSides];
    this.ypoints = new int[numSides];
    this.radius = radius;
    this.diameter = 2 * radius;
    this.color = color;

    // The amount of rotation necessary, in RADIANS, to go from one vertex to the next
    double baseTheta = 2.0 * Math.PI / numSides;
    // Compute x and y coordinates for all corners
    for (int i = 0; i < numSides; i++) {
      double theta = i * baseTheta;
      double x = radius * Math.cos(theta);
      double y = radius * Math.sin(theta);
      // Round to exact values for pixels
      this.xpoints[i] = (int) Math.round(x);
      this.ypoints[i] = (int) Math.round(y);
    }

    this.bounds = initBounds();
  }

  /**
   * Value constructor with default color as Color.GRAY.
   *
   * @param numSides number of sides for this polygon
   * @param radius the distance between the center and the vertex
   * */
  public RegularPolygon(int numSides, int radius) {
    /*
     * Makes the color parameter optional, and sets its default value to the Color.GRAY field.
     * The 'this' keyword can be used in a constructor to call the class constructor with a
     * different parameter list. In this case, it calls the constructor we defined just previously,
     * but it passes the static value 'Color.GRAY' as the color parameter for the constructor.
     * This allows us to give a default value to certain attributes and make it possible to create
     * an object without explicitely specifiying a value for that attribute.
     * */
    this(numSides, radius, Color.GRAY);
  }

  /**
   * Value constructor with default radius as 50px.
   *
   * @param numSides number of sides for this polygon
   * */
  public RegularPolygon(int numSides) {
    // This will call the 2-parameter constructor, which will call the 3-parameter constructor
    this(numSides, 50);
  }

  /** Default constructor with default number of sides as 4 (a square). */
  public RegularPolygon() {
    this(4);
  }
}
