import java.awt.Color;

/**
 * Extension of RegularPolygon to allow blinking animation on a Drawing; advanced inheritance.
 * 
 * @author Pierre-Charles Dussault
 * @since 2021/10/15
 * */
public class BlinkingPolygon extends RegularPolygon {
  // Attributes
  protected boolean visible;
  protected int stepCount; // num of time steps since last blink
  
  /** Value constructor; create a new instance of BlinkingPolygon.
   * 
   * @param int numSides the number of sides for this blinking polygon
   * @param int radius distance from the center to the vertex
   * @param color fill color for this blinking polygon
   * */
  public BlinkingPolygon(int numSides, int radius, Color color) {
    super(numSides, radius, color);
    this.visible = true;
    this.stepCount = 0;
  }
}
