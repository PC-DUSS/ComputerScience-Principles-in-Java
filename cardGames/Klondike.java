import java.awt.Canvas;
import java.awt.Color;
import java.awt.Image;

public class Klondike extends Canvas {

  // Extending class, give new unique serial version identifier
  private static final long serialVersionUID = 2390835L;

  // 2D table for the card images
  private Image[][] images;
  private int cardWidth, cardHeight;

  /** Create a game board for a game of Klondike, or Solitaire */
  public Klondike() {

    // Give background color for the game board
    setBackground(new Color(60, 235, 60, 125));
    // Create the 2D table to hold the images
    images = new Image[7][8];
    // filepath to the image files for the cards
    String imagesFolder = "/home/user/Documents/ThinkJavaCode2/ch12/cardset-oxymoron";
    // Notation for the different card suits, held in a traversable String
    String suitSymbols = "cdhs";
  }

  public static void main(String[] args) {

    // TODO
  }
}
