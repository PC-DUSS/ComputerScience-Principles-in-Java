import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;


/**
 * Re-create the Sprite mini-animation, but using a Timer instead of Thread.sleep() for the pausing.
 * 
 * @author Pierre-Charles Dussault
 * @since 2021/10/17
 * */
public class VideoGame implements ActionListener {
  private Drawing drawing;
  
  /** Creates a new video game. */
  public VideoGame() {
    Sprite sprite = new Sprite("face-smile.png", 50, 50);
    this.drawing = new Drawing(800, 600);
    this.drawing.add(sprite);
    this.drawing.addKeyListener(sprite);
    this.drawing.setFocusable(true);
    
    JFrame frame = new JFrame("My Video Game");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(this.drawing);
    frame.pack();
    frame.setVisible(true);
  }
  
  /** Action to perform at specified intervals for the Timer. */
  public void actionPerformed(ActionEvent e) {
    // Remember this is ther method that was used for each step inside the main program loop
    // Now it will be used inside a Timer instead of a main loop
    this.drawing.step();
  }
  
  /** Run the videogame. */
  public static void run() {
    VideoGame game = new VideoGame();
    Timer timer = new Timer(33, game); // call drawing.step() every 33ms (see actionPerformed)
    timer.start();
  }
}
