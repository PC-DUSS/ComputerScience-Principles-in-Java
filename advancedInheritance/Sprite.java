import java.awt.Rectangle;
import java.awt.Image;
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
  // Attributes
  // The location of the Sprite in (x, y) coordinates
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
      File fileObject = new File(path);
      this.image = ImageIO.read(fileObject);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void translate(int deltaX, int deltaY) {
    this.xpos += deltaX;
    this.ypos += deltaY;
  }
}
