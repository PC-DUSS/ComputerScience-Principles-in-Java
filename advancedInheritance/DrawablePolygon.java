import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;

/**
 * Representation of a polygon drawable on a Drawing object; explore more advanced topics with
 * inheritance.
 *
 * @author Pierre-Charles Dussault
 * @since 2021/10/13
 * */
public class DrawablePolygon extends Polygon implements Actor {
// This class is an example of specialization

  // Attributes
  protected Color color;
  protected Rectangle bounds;

  /** Default constructor. */
  public DrawablePolygon() {
    super();
    this.color = Color.GRAY;
    // Initially, this is null. You need to call it again when you add more points
    this.bounds = initBounds();
  }

  /**
   * Draw this polygon based on its color attribute.
   *
   * @param g the graphics context from the Drawing object to draw elements to it; will be passed
   * 	from the 'paint' method in Drawing class when it is called by the Event Dispatch Thread
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
    // Empty, override this in a subclass.
  }

  /**
   * For each element in both arrays, combine each element with its counterpart at the same index
   * in the other array into a new 2-element array, and put each of those pairs into a destination
   * array that will be returned; both arrays must be of the same size.
   *
   * @param arr1 the first array to use for the merge
   * @param arr2 the second array to use for the merge
   * @return the destination array
   * */
  private static int[][] associateArrays(int[] arr1, int[] arr2) {
    // Parameter validation
    if (arr1.length != arr2.length) {
      throw new IllegalArgumentException("associateArrays(): Arrays must be of the same length.");
    }

    // Remember row-major ordering
    int[][] destinationArray = new int[arr1.length][2];
    for (int i = 0; i < arr1.length; i++) {
      int[] currentPair = {arr1[i], arr2[i]};
      destinationArray[i] = currentPair;
    }

    return destinationArray;
  }

  /** Built-in override: get a string representation for this drawable polygon. */
  public String toString() {
    int[][] coords = associateArrays(xpoints, ypoints);
    // First part of the output, with an opening bracket for the 2D array that is coming
    StringBuilder sb = new StringBuilder(
        String.format("%d-sided polygon, color is %s, with coordinate points: [",
        this.npoints, this.color.toString()));

    // Each element in the 2D array, in single-line format
    for (int i = 0; i < coords.length; i++) {
      // Open the inner-bracket for the beginning of each pair
      sb.append("[");
      for (int j = 0; j < 2; j++) {
        sb.append(coords[i][j]);
        if (j == 0) {
          // Separate the second item from the first with a comma + space
          sb.append(", ");
        }
      }

      // Close the inner-bracket for the end of each pair, and separate each pair by a comma + space
      sb.append("], ");
    }

    // Remember to remove the final comma and space, and to close the final array bracket
    sb.delete(sb.length() - 2, sb.length() - 1);
    sb.append("]");
    // Remove a space that gets in there who-knows-how
    sb.deleteCharAt(sb.length() - 2);
    return sb.toString();
  }

  /** Get the leftmost and topmost coordinates for this polygon according to its set of points. */
  public int[] getTopLeftCorner() {
    // Input validation
    if (npoints < 1) {
      throw new ArrayIndexOutOfBoundsException(
          "getTopLeftCorner(): there are no coordinates from which to search");
    }

    int xMin = getSmallest(xpoints, npoints);
    int yMin = getSmallest(ypoints, npoints);
    int[] topleftCorner = {xMin, yMin};
    return topleftCorner;
  }

  /** Get the smallest element from a sample of an array of positive integers.
   *
   * @param arr the array to examine
   * @param upperBound number of elements to examine inside the array
   * @return the smallest element from inside the examined sample from the array
   * */
  private static int getSmallest(int[] arr, int upperBound) {
    int smallest = VideoGame.MAX_DIMENSION;
    for (int i = 0; i < upperBound; i++)
      if (arr[i] < smallest)
        smallest = arr[i];

    return smallest;
  }

  /** Get the greatest element from a sample of an array of positive integers.
   *
   * @param arr the array to examine
   * @param upperBound number of elements to examine inside the array
   * @return the greatest element from inside the examined sample from the array
   * */
  private static int getGreatest(int[] arr, int upperBound) {
    int greatest = 0;
    for (int i = 0; i < upperBound; i++)
      if (arr[i] > greatest)
        greatest = arr[i];

    return greatest;
  }

  /**
   * Initialize the bounding Rectangle for this polygon.
   *
   * @return the bounding Rectangle for this polygon
   * */
  public Rectangle initBounds() {
    // If this is a new and empty polygon, return empty bounds
    if (npoints == 0)
      return null;

    int[] topleft = getTopLeftCorner();
    int width = getWidth();
    int height = getHeight();
    return new Rectangle(topleft[0], topleft[1], width, height);
  }

  /** Get the width of this polygon.
   *
   * @return the width of this polygon
   * */
  private int getWidth() {
    int smallestX = getSmallest(xpoints, npoints);
    int greatestX = getGreatest(xpoints, npoints);
    return greatestX - smallestX;
  }

  /** Get the height of this polygon.
   *
   * @return the height of this polygon
   * */
  private int getHeight() {
    int smallestY = getSmallest(ypoints, npoints);
    int greatestY = getGreatest(ypoints, npoints);
    return greatestY - smallestY;
  }

  /** Do not implement. */
  public void handleCollisions() {
    // Do nothing here, not implemented
  }
}
