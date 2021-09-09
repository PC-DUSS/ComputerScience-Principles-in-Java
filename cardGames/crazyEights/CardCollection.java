import java.util.ArrayList;
import java.util.Random;

/**
 * Generalizing behaviour for different possible collections of cards by using inheritance.
 *
 * @author Pierre-Charles Dussault
 * @since 2021/09/09
 * @version 1.0
 */
public class CardCollection {

  private String label;
  private ArrayList<Card> cards;
  public static Random random = new Random();

  /**
   * Constructor, sets a label to distinguish what type of CardCollection this is.
   *
   * @param label single-word to describe what kind of CardCollection that this is to become
   */
  public CardCollection(String label) {

    this.label = label;
    this.cards = new ArrayList<Card>();
  }

  /**
   * Append a card to the end of this CardCollection.
   *
   * @param card the card to append to this CardCollection
   */
  public void addCard(Card card) {

    // Previously, we had been using 'this' keyword in front of instance attributes all the time to
    // make them explicit. It's use is optional WHEN INSIDE INSTANCE METHODS. So now we will stop
    // doing it.
    // this.cards.add(card);
    cards.add(card);
  }

  /**
   * Remove the top card from a CardCollection and return it.
   *
   * @param i index position from which to remove the card
   * @return the popped card
   */
  public Card popCard(int i) {

    return cards.remove(i);
  }

  /**
   * Pop and return the last card from a CardCollection.
   *
   * @return the popped card
   */
  public Card popCard() {

    // Do you see what we're doing here? Clever huh.
    return popCard(cards.size() - 1);
  }

  /**
   * Wrapper method: check if a CardCollection is empty.
   *
   * @return true or false depending on if the CardCollection is empty or not
   */
  public boolean isEmpty() {

    return cards.isEmpty();
  }

  /**
   * Wrapper method: check the size of a CardCollection.
   *
   * @return an int representing the number of cards present in the CardCollection
   */
  public int size() {

    return cards.size();
  }

  /* ----- Getters ----- */
  public Card getCard(int i) {

    // With ArrayList, you can't use the [] accessor. You have to use the get() and set() methods
    // from ArrayList API.
    return cards.get(i);
  }

  /**
   * Set a card's value for a specific index position inside this CardCollection.
   *
   * @param index the index position for which to specify the new card
   * @param card the new card to be held in the given index position for this CardCollection
   */
  public void setCard(int index, Card card) {

    cards.set(index, card);
  }

  /**
   * Get the last card from a CardCollection without removing it.
   *
   * @return the last card from this CardCollection
   */
  public Card lastCard() {

    return cards.get(cards.size() - 1);
  }

  /**
   * Swap the position of two cards in this CardCollection, using their index positions.
   *
   * @param i index position of the first card to swap
   * @param j index position of the second card to swap
   */
  public void swapCards(int i, int j) {

    Card tmp = cards.get(i);
    cards.set(i, cards.get(j));
    cards.set(j, tmp);
  }

  /** Shuffle the cards inside a CardCollection. */
  public void shuffle() {

    for (int i = cards.size() - 1; i > 0; i--) {

      int randomNum = random.nextInt(i + 1);
      swapCards(i, randomNum);
    }
  }

  /**
   * Return a String representation of all the cards inside this CardCollection.
   *
   * @return the String representing all the cards
   */
  public String toString() {

    StringBuilder text = new StringBuilder();
    for (Card card : cards) {

      text.append(card.toString());
      text.append("\n");
    }

    return text.toString();
  }

  /** Print to std_out all the cards inside this CardCollection. */
  public void printCardCollection() {

    for (Card card : cards) {

      System.out.println(card.toString());
    }
  }
} // End of CardCollection class
