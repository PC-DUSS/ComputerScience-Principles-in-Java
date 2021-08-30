import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Klondike extends Canvas {

  // Extending class, give new unique serial version identifier
  private static final long serialVersionUID = 2390835L;

  // 2D table for the card images
  private Image[][] images;
  private int cardWidth, cardHeight;

  // Data to hold each Card's current placement on the game board
  // The main columns of the game table
  private int[] col1 = new int[1];
  private int[] col2 = new int[2];
  private int[] col3 = new int[3];
  private int[] col4 = new int[4];
  private int[] col5 = new int[5];
  private int[] col6 = new int[6];
  private int[] col7 = new int[7];
  // The stockpile and its flipstack, containing the remaining cards not placed in the game table
  private int[] stockpile = new int[24];
  private int[] flipStack = new int[24];
  // The stacks for progression of saved consecutive cards, one for each card suit
  private int[] clubsStack = new int[13];
  private int[] diamondsStack = new int[13];
  private int[] heartsStack = new int[13];
  private int[] spadesStack = new int[13];

  /** Create a game board for a game of Klondike, or Solitaire */
  public Klondike() {

    // Give background color for the game board
    setBackground(new Color(0x088A4B));
    // Create the 2D table to hold the images
    images = new Image[7][8];
    // filepath to the image files for the cards
    String imagesFolder = "/home/user/Documents/ThinkJavaCode2/ch12/cardset-oxymoron";
    // Notation for the different card suits, held in a traversable String
    String suitSymbols = "cdhs";

    // Create a new array of Cards for the Klondike game and shuffle them
    Card[] cards = Card.generateCards();
    Card.shuffleCards(cards);

    // Variables to keep track of current position to fill on the game board
    int cardNum = 1;
    int col = 0;
    int row = 0;

    // For each Card in the deck, get its image, and place it on the appropriate tile depending on
    // which tile is currently being filled by a Card.
    for (int i = 0; i < cards.length; i++) {

      // Get the filename for the current Card
      String fileName =
          String.format(
              "%s/%02d%c.gif",
              imagesFolder, cards[i].getRank(), suitSymbols.charAt(cards[i].getSuit()));

      System.out.println(fileName);

      // Determine on which tile of the game board the Card should go
      if (i == 0) {
        col = 0;
        row = i + 1;
      } else if (i <= 2) {
        col = 1;
        row = i;
      } else if (i <= 5) {
        col = 2;
        row = i - 2;
      } else if (i <= 9) {
        col = 3;
        row = i - 5;
      } else if (i <= 14) {
        col = 4;
        row = i - 9;
      } else if (i <= 20) {
        col = 5;
        row = i - 14;
      } else if (i <= 27) {
        col = 6;
        row = i - 20;
        // The rest of the Cards will go in the stockpile
      }

      // Load the image in the tile currently being filled
      images[col][row] = new ImageIcon(fileName).getImage();
    }

    // Load the stockpile Card bottom image
    String fileName = String.format("%s/back191.gif", imagesFolder);
    images[0][0] = new ImageIcon(fileName).getImage();

    // Load the images for the empty stack bottoms
    for (int suit = 0; suit < 4; suit++) {

      fileName = String.format("%s/bottom%02d.gif", imagesFolder, suit + 4);
      System.out.println(fileName);
      images[3 + suit][0] = new ImageIcon(fileName).getImage();
    }

    // Image for the empty flipstack next to the stockpile
    fileName = String.format("%s/bottom02.gif", imagesFolder);
    System.out.println(fileName);
    images[1][0] = new ImageIcon(fileName).getImage();

    // Save the dimensions for a Card image
    cardWidth = images[0][0].getWidth(null);
    cardHeight = images[0][0].getHeight(null);

    // Set the size of the Table to get the insets(I don't quite understand this one to be honest).
    // Give extra room on the horizontal axis (2 extra columns worth of space)
    int extraSpacing = 2;
    int columns = 7 + extraSpacing;
    int rows = 5;
    setTableSize(columns, rows);
  }

  /**
   * Set the table size for the GUI game board, allowing the use of a number of rows and columns to
   * determine size, without having to specify the exact number of pixels.
   *
   * @param x double the amount of times the width of a Card should fit the game board
   * @param y double the amount of times the height of a Card should fit the game board
   */
  public void setTableSize(double x, double y) {

    // Convert the size to an integer (to avoid using fractions of pixels)
    setSize((int) (x * cardWidth), (int) (y * cardHeight));
  }

  /**
   * Draw a main game card at the given coordinates on the GUI; x and y are in units of card
   * width/height.
   *
   * @param g Graphics objects that will come from the paint method
   * @param col column in which to draw the Card
   * @param row row in which to draw the Card
   * @param x horizontal coordinate in amount of cardWidths
   * @param y vertical coordinate in amount of cardHeights
   */
  public void drawGameCard(Graphics g, int col, int row, double x, double y) {

    Image currentImage = images[col][row];
    int xAxisOffset = (int) (cardWidth / 4.0);
    int yAxisOffset = cardHeight + (int) (cardHeight / 12.0) + (int) (cardHeight / 5.0);

    g.drawImage(
        currentImage,
        (int) (x * cardWidth) + xAxisOffset,
        (int) (y * cardHeight) + yAxisOffset,
        null);
  }

  /**
   * Draw card from the header row at the given coordinates: x and y are in units of card
   * width/height.
   *
   * @param g Graphics objects that will come from the paint method
   * @param col column in which to draw the Card
   * @param x horizontal coordinate in amount of cardWidths
   * @param y vertical coordinate in amount of cardHeights
   */
  public void drawHeaderCard(Graphics g, int col, double x, double y) {

    Image currentImage = images[col][0];
    int xAxisOffset = (int) (cardWidth / 4.0);
    int yAxisOffset = (int) (cardHeight / 12.0);

    g.drawImage(
        currentImage,
        (int) (x * cardWidth) + xAxisOffset,
        (int) (y * cardHeight) + yAxisOffset,
        null);
  }

  /**
   * Default method override; special method called when the Frame needs to be drawn/re-drawn
   *
   * @param g Graphics object passed to it automatically
   */
  public void paint(Graphics g) {

    // This will determine the visual outlay of the game board, how each Card is placed visually
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 7; col++) {

        double x = (double) col * 1.15;

        if (row == 0) {
          if (col == 2) {
            continue;
          }
          double y = 1.0 / 8.0;
          drawHeaderCard(g, col, x, y);
        } else {
          double y = row / 5.0;
          drawGameCard(g, col, row, x, y);
        }
      }
    }
  }

  public static void main(String[] args) {

    // Create new JFrame for the display
    JFrame frame = new JFrame("Klondike start board");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Create the game board
    Klondike gameBoard = new Klondike();

    // Place the created game board into the Frame, format it for display and then display it
    frame.add(gameBoard);
    frame.pack();
    frame.setVisible(true);
  }
}
