import java.util.ArrayList;

/**
 * Class to represent the behaviour of a card pile: for use in the card game "War".
 *
 * @author Pierre-Charles Dussault
 * @since 2021/09/07
 * @version 1.0
 */
public class Pile {
  // Declare the attribute for the pile of cards
  private ArrayList<Card> cards;
  // Java knows about Card objects, since this class is in the same classpath as the other classes
  // in this folder, including the Card class

  /** Default constructor */
  public Pile() {
    this.cards = new ArrayList<Card>();
  }

  /* ----- Getter(s) ----- */
  public ArrayList<Card> getCards() {
    return this.cards;
  }

  /**
   * Return the top card from a pile and remove it from the pile (wrapper method).
   *
   * @return the top card from the pile, simultaneously removing it from the pile
   */
  public Card popCard() {
    // Index 0 is treated as the top of the pile
    return this.cards.remove(0);
    // ArrayList.remove() automatically shifts all the remaining items to fill the gap at index 0
  }

  /**
   * Add a card to the bottom of a players pile (wrapper method).
   *
   * @param card Card to add to the bottom of a players pile
   */
  public void addCard(Card card) {
    // The last index position is treated as the bottom of the pile
    this.cards.add(card);
  }

  /**
   * Check if a card pile is empty (wrapper method).
   *
   * @return true of false, depending on if the card pile is empty
   */
  public boolean isEmpty() {
    return this.cards.isEmpty();
  }

  /**
   * Adds all the cards from a deck to this pile of cards.
   *
   * @param deck Deck a deck of cards to add to the pile of cards
   */
  public void addDeck(Deck deck) {
    // Notice how the pile and the deck point to the same card (aliasing), but that isn't a problem
    // since cards are immutable
    for (Card card : deck.getCards()) {
      this.addCard(card);
    }
  }

  /**
   * Add cards from another pile to this pile.
   *
   * @param otherPile Pile from which to take the cards to add to this pile
   */
  public void addPile(Pile otherPile) {
    for (Card card : otherPile.getCards()) {
      this.addCard(card);
    }
  }
}
