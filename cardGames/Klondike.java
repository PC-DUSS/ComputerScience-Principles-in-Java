import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Klondike extends Canvas {

  // Extending class, give new unique serial version identifier
  private static final long serialVersionUID = 2390835L;

  // File path for images
  public static final String imagesFolder =
      "/home/user/Documents/ThinkJavaCode2/ch12/cardset-oxymoron";
  // Notation for the different card suits, held in a traversable String. Will be used later for
  // finding image files for each card
  public static final String suitSymbols = "cdhs";

  // 2D grid for the card images
  private Image[][] images;
  private int cardWidth, cardHeight;

  // Data to hold each Card's current placement on the game board
  // - - - - -
  // The main columns of the game table, named the "tableau" area
  private Card[] col1 = new Card[7];
  private Card[] col2 = new Card[7];
  private Card[] col3 = new Card[7];
  private Card[] col4 = new Card[7];
  private Card[] col5 = new Card[7];
  private Card[] col6 = new Card[7];
  private Card[] col7 = new Card[7];
  // The stockpile and its flipstack with the remaining cards not placed in the "tableau" area
  private Card[] stockpile = new Card[24];
  private Card[] flipStack = new Card[24];
  // The stacks for progression of saved consecutive cards, one for each card suit
  private Card[] clubsStack = new Card[13];
  private Card[] diamondsStack = new Card[13];
  private Card[] heartsStack = new Card[13];
  private Card[] spadesStack = new Card[13];

  /** Create a game board for a game of Klondike, or Solitaire */
  public Klondike() {

    // Give background color for the game board
    setBackground(new Color(0x088A4B));
    // Create the 2D table to hold the images
    images = new Image[7][8];

    // Create a new array of Cards
    Card[] cards = Card.generateCards();
    // Now shuffle the Cards
    Card.shuffleCards(cards);

    // Variables to keep track of current position to fill with an image on the global game board
    int col = 0;
    int row = 0;

    // For each Card in the deck, get its image, and place it on the appropriate tile depending on
    // which tile is currently being filled by a Card
    for (int i = 0; i < cards.length; i++) {

      // Cards shall start by being placed on the "tableau" area of the board (the columns with rows
      // of cards to pick from when playing)
      // - - - - -
      // Determine on which tile of the "tableau" the Card should be placed
      if (i == 0) {

        col = 0;
        row = i + 1;
        // Now save the Card's position for each column
        col1[i] = cards[i];
        // Load the image of the current Card in the tile currently being filled in the image grid
        String cardImageFileName = getCardImageFileName(cards, i);
        images[col][row] = new ImageIcon(cardImageFileName).getImage();

      } else if (i <= 2) {

        col = 1;
        row = i;
        col2[i - 1] = cards[i];
        String cardImageFileName = getCardImageFileName(cards, i);
        images[col][row] = new ImageIcon(cardImageFileName).getImage();

      } else if (i <= 5) {

        col = 2;
        row = i - 2;
        col3[i - 3] = cards[i];
        String cardImageFileName = getCardImageFileName(cards, i);
        images[col][row] = new ImageIcon(cardImageFileName).getImage();

      } else if (i <= 9) {

        col = 3;
        row = i - 5;
        col4[i - 6] = cards[i];
        String cardImageFileName = getCardImageFileName(cards, i);
        images[col][row] = new ImageIcon(cardImageFileName).getImage();

      } else if (i <= 14) {

        col = 4;
        row = i - 9;
        col5[i - 10] = cards[i];
        String cardImageFileName = getCardImageFileName(cards, i);
        images[col][row] = new ImageIcon(cardImageFileName).getImage();

      } else if (i <= 20) {

        col = 5;
        row = i - 14;
        col6[i - 15] = cards[i];
        String cardImageFileName = getCardImageFileName(cards, i);
        images[col][row] = new ImageIcon(cardImageFileName).getImage();

      } else if (i <= 27) {

        col = 6;
        row = i - 20;
        col7[i - 21] = cards[i];
        String cardImageFileName = getCardImageFileName(cards, i);
        images[col][row] = new ImageIcon(cardImageFileName).getImage();

      } else {

        // Remaining cards go in the stockpile
        stockpile[i - 28] = cards[i];
        // The image for the stockpile will be handled outside the loop, only once (it is simply the
        // back cover of a Card, as long as the stockpile is not empty)
      }
    }

    // Load the Card bottom image for the stockpile
    String cardBottomImageFileName = String.format("%s/back191.gif", imagesFolder);
    images[0][0] = new ImageIcon(cardBottomImageFileName).getImage();

    // Load the respective images for each suit's empty stack in the header row
    for (int suit = 0; suit < 4; suit++) {

      String emptyStackImageFileName = String.format("%s/bottom%02d.gif", imagesFolder, suit + 4);
      // Card suit stacks in the header row start at the 4th column in the image grid
      images[3 + suit][0] = new ImageIcon(emptyStackImageFileName).getImage();
    }

    // Load the image for the empty flipstack next to the stockpile
    String flipstackImageFileName = String.format("%s/bottom02.gif", imagesFolder);
    // The flipstack is in the 2nd column in the image grid
    images[1][0] = new ImageIcon(flipstackImageFileName).getImage();

    // Save the dimensions for a Card image
    cardWidth = images[0][0].getWidth(null);
    cardHeight = images[0][0].getHeight(null);

    // Set the size of the Table to get the insets(I don't quite understand this one to be honest)
    // Give extra room on the horizontal axis (2 extra columns worth of space in total)
    int extraSpacing = 2;
    int columns = 7 + extraSpacing;
    int rows = 5;
    setTableSize(columns, rows);
  } // end of constructor

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
   * Draw a card for the main "tableau" area at the given coordinates on the GUI; x and y are in
   * units of card width/height.
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
   * Draw card from the header row at the given coordinates on the GUI; x and y are in units of card
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

          // The 3rd column is an empty space in the header row. Ignore and continue to next column
          if (col == 2) continue;

          // Initial spacing between the header row and the top of the game board
          double y = 1.0 / 8.0;
          drawHeaderCard(g, col, x, y);

        } else {

          // Spacing increments for the rest of the cards (which will be placed on the "tableau"
          // area of the game board)
          double y = row / 5.0;
          drawGameCard(g, col, row, x, y);
        }
      }
    }
  }

  /**
   * Get the filename for the current Card. Example format of a filename:
   * "../carset-oxymoron/09h.gif" --> image for the 9 of Hearts
   */
  public String getCardImageFileName(Card[] cards, int index) {

    return String.format(
        "%s/%02d%c.gif",
        imagesFolder, cards[index].getRank(), suitSymbols.charAt(cards[index].getSuit()));
  }

  /** ----- Main Program ----- */
  public static void main(String[] args) {

    // Create new JFrame for the display
    JFrame frame = new JFrame("Klondike Game Board");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Create the game board
    Klondike gameBoard = new Klondike();

    // Place the created game board into the Frame, pack the Frame for display and then make the
    // Frame visible
    frame.add(gameBoard);
    frame.pack();
    frame.setVisible(true);
  }
}
