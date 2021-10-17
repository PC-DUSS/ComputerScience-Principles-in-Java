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
  // Velocity of the sprite in x-axis and y-axis
  private int dx;
  private int dy;
  // Image to display
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
  }
  
  /**
   * Draw the Image at its current coordinates at this point in time; interface method override.
   * 
   * @param g Graphics context to draw on the Drawing
   * */
  public void draw(Graphics g) {
    g.drawImage(this.image, this.xpos, this.ypos, null);
  }
  
  /** Update the position of the Sprite according to its velocity attributes dx and dy. */
  public void step() {
    this.xpos += this.dx;
    this.ypos += this.dy;
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
}
