import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;

public class MovingPolygon extends RegularPolygon implements Actor, KeyListener {
  // Constants
  protected static final int MOVE_SPEED = 5; // In pixels

  // Atributes
  protected int xpos;
  protected int ypos;
  protected int dx;
  protected int dy;
  protected int[] topleft;
  protected int[] topright;
  protected int[] bottomleft;
  protected int[] bottomright;

  /**
   * Value constructor.
   *
   * @param numSides number of sides for this polygon
   * @param radius the distance from the center to the vertex
   * @param color the fill color of this polygon, based on the Color class
   * @param xpos initial x-axis position (topleft corner)
   * @param ypos initial y-axis position (topleft corner)
   * */
  public MovingPolygon(int numSides, int radius, Color color, int xpos, int ypos) {
    super(numSides, radius, color);
    this.xpos = xpos;
    this.ypos = ypos;
    this.topleft = new int[] {xpos, ypos};
    this.topright = new int[] {xpos + this.diameter, ypos};
    this.bottomleft = new int[] {xpos, ypos + this.diameter};
    this.bottomright = new int[] {xpos + this.diameter, ypos + this.diameter};
    this.translateToPoint(xpos, ypos);
  }

  /** Value constructor; default color as GRAY. */
  public MovingPolygon(int numSides, int radius, int xpos, int ypos) {
    this(numSides, radius, Color.GRAY, xpos, ypos);
  }

  /** Value constructor; default radius as 50px. */
  public MovingPolygon(int numSides, int xpos, int ypos) {
    this(numSides, 50, xpos, ypos);
  }

  /** Value constructor; default numbe of sides as 4 (a square).  */
  public MovingPolygon(int xpos, int ypos) {
    this(4, xpos, ypos);
  }

  /** Value constructor; default coordinates as (0, 0). */
  public MovingPolygon() {
    this(0, 0);
  }

  /** Update the position of the moving polygon according to its velocity attributes dx and dy. */
  public void step() {
    // Update corners and check if any corner is out of bounds
    if (updateCorners()) {
      xpos += dx;
      ypos += dy;
      translate(dx, dy);
    }
  }

  /**
   * Set velocity according to key presses.
   *
   * @param e the key event to analyze
   * */
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_UP:
        dy = -MOVE_SPEED;
        break;
      case KeyEvent.VK_DOWN:
        dy = MOVE_SPEED;
        break;
      case KeyEvent.VK_RIGHT:
        dx = MOVE_SPEED;
        break;
      case KeyEvent.VK_LEFT:
        dx = -MOVE_SPEED;
        break;
    }
  }

  /**
   * Remove velocity according to key releases.
   *
   * @param e the key event to analyze
   * */
  public void keyReleased(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_UP:
      case KeyEvent.VK_DOWN:
        dy = 0;
        break;
      case KeyEvent.VK_RIGHT:
      case KeyEvent.VK_LEFT:
        dx = 0;
        break;
    }
  }

  /** Do not implement. */
  public void keyTyped(KeyEvent e) {
    // Method must be defined, but do not implement
  }

  /** Convenience method for easier translation of the moving polygon to a coordinate point on the
   * Drawing.
   *
   *@param x coordinate for the topleft corner on the x-axis
   @param y coordinate for the topleft corner on the y-axis
   * */
  public void translateToPoint(int x, int y) {
    int deltaX = x - topleft[0];
    int deltaY = y - topleft[1];
    if (updateCorners(deltaX, deltaY)) {
      translate(deltaX, deltaY);
    } else {
      System.err.println("Cannot translate out of bounds");
    }
  }

  /**
   * Update the corners and check if they are within bounds; if they are not, revert them to
   * their prior position; returns the status of this attempt.
   *
   * @return true if it succeeded, or false if it did not
   * */
  private boolean updateCorners() {
    topleft[0] = xpos + dx;
    topleft[1] = ypos + dy;
    topright[0] = xpos + 2*radius + dx;
    topright[1] = ypos + dy;
    bottomleft[0] = xpos + dx;
    bottomleft[1] = ypos + 2*radius + dy;
    bottomright[0] = xpos + 2*radius + dx;
    bottomright[1] = ypos + 2*radius + dy;

    if (topleft[0] < 0 || topright[0] > VideoGame.WIDTH) {
      resetCorners();
      return false;
    } else if (topleft[1] < 0 || bottomleft[1] > VideoGame.HEIGHT) {
      resetCorners();
      return false;
    } else {
      this.bounds = initBounds();
      return true;
    }
  }

  /**
   * Update the corners and check if they are within bounds; if they are not, revert them to
   * their prior position; returns the status of this attempt.
   *
   * @param deltaX
   * @param deltaY
   * @return true if it succeeded, or false if it did not
   * */
  private boolean updateCorners(int deltaX, int deltaY) {
    topleft[0] = xpos + deltaX;
    topleft[1] = ypos + deltaY;
    topright[0] = xpos + 2*radius + deltaX;
    topright[1] = ypos + deltaY;
    bottomleft[0] = xpos + deltaX;
    bottomleft[1] = ypos + 2*radius + deltaY;
    bottomright[0] = xpos + 2*radius + deltaX;
    bottomright[1] = ypos + 2*radius + deltaY;

    if (topleft[0] < 0 || topright[0] > VideoGame.WIDTH) {
      resetCorners();
      return false;
    } else if (topleft[1] < 0 || bottomleft[1] > VideoGame.HEIGHT) {
      resetCorners();
      return false;
    } else {
      this.bounds = initBounds();
      return true;
    }
  }

  /** Reset corners from before the last movement input. */
  private void resetCorners() {
    topleft[0] -= dx;
    topleft[1] -= dy;
    topright[0] -= dx;
    topright[1] -= dy;
    bottomleft[0] -= dx;
    bottomleft[1] -= dy;
    bottomright[0] -= dx;
    bottomright[1] -= dy;
  }

  /** Handle what happens if a collision occurs with other Actors. */
  public void handleCollision() {
    // TODO
  }
}
