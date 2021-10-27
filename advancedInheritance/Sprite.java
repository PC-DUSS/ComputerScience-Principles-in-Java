import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * Class to represent a moving image on a Drawing; using event listeners.
 *
 * @author Pierre-Charles Dussault
 * @since 2021/10/16
 * */
public class Sprite implements Actor, KeyListener {
  private int xpos;
  private int ypos;
  private int width;
  private int height;
  private int dx;
  private int dy;
  protected int[] topleft;
  protected int[] topright;
  protected int[] bottomleft;
  protected int[] bottomright;
  private Rectangle bounds;
  private Image image;

  /** Creates a new Sprite. */
  public Sprite(String path, int xpos, int ypos) {
    this.xpos = xpos;
    this.ypos = ypos;
    try {
      // Load the image from a File object
      File fileObject = new File(path);
      this.image = ImageIO.read(fileObject);
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Define the bounds Rectangle for this sprite
    this.width = this.image.getWidth(null);
    this.height = this.image.getHeight(null);
    this.bounds = new Rectangle(this.xpos, this.ypos, this.width, this.height);
  }

  /**
   * Draw the Image at its current coordinates (the topleft corner) at this point in time;
   * interface method override.
   *
   * @param g Graphics context to draw on the Drawing
   * */
  public void draw(Graphics g) {
    g.drawImage(this.image, this.xpos, this.ypos, null);
  }

  /** Update the position of the Sprite according to its velocity attributes dx and dy. */
  public void step() {
    // Update corners and check if any corner is out of bounds
    if (updateCorners()) {
      xpos += dx;
      ypos += dy;
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
        // Remember, going upwards decreases the y-coordinate
        dy = -5;
        break;
      case KeyEvent.VK_DOWN:
        dy = 5;
        break;
      case KeyEvent.VK_RIGHT:
        dx = 5;
        break;
      case KeyEvent.VK_LEFT:
        dx = -5;
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

  /** Not implemented. */
  public void keyTyped(KeyEvent e) {
    // do nothing
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
    topright[0] = xpos + width + dx;
    topright[1] = ypos + dy;
    bottomleft[0] = xpos + dx;
    bottomleft[1] = ypos + height + dy;
    bottomright[0] = xpos + width + dx;
    bottomright[1] = ypos + height + dy;

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
    topright[0] = xpos + width + deltaX;
    topright[1] = ypos + deltaY;
    bottomleft[0] = xpos + deltaX;
    bottomleft[1] = ypos + height + deltaY;
    bottomright[0] = xpos + width + deltaX;
    bottomright[1] = ypos + height + deltaY;

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

  /** Handle collision with other Actors. */
  public void handleCollisions() {
    // TODO
  }
}
