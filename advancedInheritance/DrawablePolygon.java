import java.awt.Polygon;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Representation of a polygon drawable on a Drawing object; explore more advanced topics with
 * inheritance.
 *
 * @author Pierre-Charles Dussault
 * @since 2021/10/13
 * */
public class DrawablePolygon extends Polygon implements Actor {
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
   * For each element in both arrays, combine each element with the respective element in the other
   * array into a new 2-element array, and then place it into a destination array that will be
   * returned.
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
  
  /** Built-in override: get a string representation for this polygon. */
  public String toString() {
    int[][] coords = associateArrays(xpoints, ypoints);
    // Print first part of the output, with an opening bracket for the 2D array to come
    StringBuilder sb = new StringBuilder(
        String.format("%d-sided polygon, color is %s, with coordinate points: [",
        this.npoints, this.color.toString()));
	
    // Print each element in the 2D array in single-line format
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
  
  /** Test out functionality. */
  public static void main(String[] args) {
    DrawablePolygon myPoly = new DrawablePolygon();
    myPoly.addPoint(25, 25);
    myPoly.addPoint(58, 106);
    myPoly.addPoint(90, 40);
    myPoly.addPoint(20, 150);
    System.out.println(myPoly);
  }
}
