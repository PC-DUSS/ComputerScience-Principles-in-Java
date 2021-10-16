import java.awt.Color;
import java.awt.Graphics;

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
  
  /**
   * Override from DrawablePolygon class; draw the polygon to the Drawing object.
   * 
   * @param g graphics context with which to draw the polygon to the Drawing
   * */
  public void draw(Graphics g) {
    if (this.visible) {
      super.draw(g);
    }
  }
  
  /**
   * Count steps since the last time the polygon changed visibility, and change its visibility if
   * it has been 10 steps; this, combined with the REFRESH_RATE, determines the speed at which the
   * polygon will blink.
   * */
  public void step() {
    this.stepCount++;
    if (this.stepCount == 10) {
      this.visible = !(this.visible);
      this.stepCount = 0;
    }
  }
}
