/**
 * Class to represent and use decks of cards, based on the CardCollection superclass.
 *
 * @author Pierre-Charles Dussault
 * @since 2021/09/09
 * @version 1.0
 */
public class Deck extends CardCollection {

  /**
   * Constructor, requires a label to indicate what type of CardCollection it is.
   *
   * @param label descriptive label to say what type of CardCollection this is going to be
   */
  public Deck(String label) {
    // The super() method invokes the constructor of the superclass.
    // This means that creating a new Deck object first and foremost instaciates a CardCollection
    // object within it before instanciating anything else.
    // This instanciates 'label' and 'cards' attributes of CardCollection inside this Deck object,
    // and passes the label parameter from the Deck constructor down to the CardCollection
    // constructor.
    super(label);
    // And now this step populates the 'cards' attribute with instances of Card objects
    for (int suit = 0; suit <= 3; suit++) {
      for (int rank = 1; rank <= 13; rank++) {
        // Remember how the 'this' keyword is optional in certain cases
        addCard(new Card(rank, suit));
      }
    }
  }

  /** Main Program */
  public static void main(String[] args) {
    Deck deck = new Deck("Deck");
  }
}
