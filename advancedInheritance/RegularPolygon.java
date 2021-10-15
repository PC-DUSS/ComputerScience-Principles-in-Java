import java.awt.Color;

/**
 * Representation of a regular polygon, extending DrawablePolygon; to explore more advanced features of inheritance.
 *
 * @author Pierre-Charles Dussault
 * @since 2021/10/13
 * */
public class RegularPolygon extends DrawablePolygon {
// Keep in mind this class uses radians as a unit for angles

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
    this.color = color;

    // The amount of rotation necessary, in RADIANS, to go from one corner to the next (each corner is a vertex)
    double baseTheta = 2.0 * Math.PI / numSides;
    // Compute x and y coordinates for all corners
    for (int i = 0; i < numSides; i++) {
      double theta = i * baseTheta;
      double x = radius * Math.cos(theta);
      double y = radius * Math.sin(theta);
      // Round to exact values for pixels
      xpoints[i] = (int) Math.round(x);
      ypoints[i] = (int) Math.round(y);
    }
  }

  /**
   * Value constructor with default color as gray.
   *
   * @param numSides number of sides for this polygon
   * @param radius the distance between the center and the vertex
   * */
  public RegularPolygon(int numSides, int radius) {
    /*
     * Make the color parameter optional, and sets its default value to the Color.GRAY field.
     * The 'this' keyword can be used in a constructor to call the class constructor with an equivalent parameter list.
     * In this case, it calls the constructor we defined just previously, but by deciding to pass the static value
     * Color.GRAY to the attribute 'color' of the object. This allows us to set default values to certain attributes,
     * and in the process make said attributes optional when creating an object from the class.
     * */
    this(numSides, radius, Color.GRAY);
  }

  /**
   * Value constructor with default radius as 50px and default color as Color.GRAY.
   *
   * @param numSides number of sides for this polygon
   * */
  public RegularPolygon(int numSides) {
    // This will call the constructor with 2 parameters, which will call the constructor with 3 parameters
    this(numSides, 50);
  }

  /** Default constructor with default number of sides as 4 (a square). */
  public RegularPolygon() {
    // This will call the constructor with 1 parameter, which will call the constructor with 2 parameters, which will
    // call the constructor with 3 paramaters
    this(4);
  }

  /** Main program. */
  public static void main(String[] args) {
    // Just shits and giggles in here
    RegularPolygon rp = new RegularPolygon(6);
    rp.translate(100, 100);
  }
}
