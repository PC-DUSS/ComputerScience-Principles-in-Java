import java.awt.Graphics;

/**
 * General class to represent elements drawable on a Drawing; good representation of code
 * generalization: using interfaces.
 *
 * @author Pierre-Charles Dussault
 * @since 2021/10/16
 * */
public interface Actor {
// Interfaces are public by default, which makes sense

  // Provide the minimum methods needed by the elements that a Drawing wants to display

  /**
   * Draw this Actor on the Drawing; override this method in a subclass and implement the
   * aforementioned functionality.
   * */
  public void draw(Graphics g);

  /**
   * Increment step count since last time the visibility of the Actor changed; and change the
   * Actor's visibility if the count reaches the determined count threshold; override this method
   * in a subclass and implement the aforementioned functionality.
   * */
  public void step();

  /**
   * Handle collision between Actors; override this method in a subclass and implement
   * aforementioned functionality. */
  public void handleCollisions();
}
