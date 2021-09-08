import java.util.Random;

/**
 * Class to represent and use decks of cards.
 *
 * @author Pierre-Charles Dussault
 * @since 2021/08/31
 * @version 1.0
 */
public class Deck {

  private Card[] cards;
  // Classwide Random Object for having access to pseudorandom number generation
  private static Random deckRand = new Random();

  /**
   * Constructs and empty Deck of an integer n number of Cards.
   *
   * @param n int number of Cards to have in the Deck
   */
  public Deck(int n) {

    this.cards = new Card[n];
  }

  /** Constructs an unshuffled standard 52 Card Deck. */
  public Deck() {

    this.cards = new Card[52];
    int index = 0;

    for (int suit = 0; suit <= 3; suit++) {
      for (int rank = 1; rank <= 13; rank++) {

        this.cards[index] = new Card(rank, suit);
        index++;
      }
    }
  }

  /* ----- Getters ----- */
  public Card[] getCards() {

    return this.cards;
  }

  /** Print the all the Cards from a Deck to std_out. */
  public void printDeck() {

    for (Card card : this.cards) {

      System.out.println(card);
    }
  }

  /** Shuffle all the cards in a deck. */
  public void shuffle() {

    // Swap the current i'th card with the randomly picked card
    for (int i = 0; i < this.cards.length; i++) {

      this.swapCards(i, this.cards.length - 1);
    }
  }

  /**
   * Return a random int between a low and high value, both inclusively.
   *
   * @param low int minimum acceptable value
   * @param high int maximum acceptable value
   * @return the random int inside the given range
   */
  private static int randomInt(int low, int high) {

    return deckRand.nextInt(high - low + 1) + low;
  }

  /**
   * Swap two cards positions from inside the deck.
   *
   * @param i int index of the first card
   * @param j int index of the second card
   */
  private void swapCards(int i, int j) {

    int randomIndex = randomInt(i, j);
    Card tmp = this.cards[i];
    this.cards[i] = this.cards[randomIndex];
    this.cards[randomIndex] = tmp;
  }

  public void selectionSort() {

    for (int i = 0; i < this.cards.length; i++) {

      // Find the lowest card to the right of i
      // Swap the i'th card with the lowest card found
    }
  }

  /**
   * Find the lowest card between low and high, and return its index.
   *
   * @param low int index of the start of search
   * @param high int index of the end of search
   * @return the index of the lowest value card found
   */
  private int indexLowest(int low, int high) {

    // Set baseline card as value of an Ace of Spades (Ace of Spades is highest value card)
    Card lowest = new Card(13, 3);
    // If no other card is found to be smaller than the first card, then we return the index of the
    // first card (which then has to be an Ace of Spades alone inside the given range to search)
    int index = low;
    for (int i = low; i <= high; i++) {

      if (this.cards[i].compareTo(lowest) < 0) {

        lowest = this.cards[i];
        index = i;
      }
    }

    return index;
  }

  /**
   * Create a subdeck from a current deck, using cards from index low to high: uses aliasing and
   * therefore each card in the subdeck refers to the same card from the original deck.
   *
   * @param low int the starting index inclusively
   * @param high int the ending index inclusively
   * @return a new Deck that is a subdeck of the original
   */
  public Deck subdeck(int low, int high) {

    Deck sub = new Deck(high - low + 1);

    for (int i = 0; i < sub.cards.length; i++) {

      sub.cards[i] = this.cards[low + i];
    }

    return sub;
  }

  public static Deck mergeDecks(Deck d1, Deck d2) {

    // Create a new deck d3 big enough for all the cards

    // Use the index i to keep track of where we are at in the first deck, and index j for the
    // second deck.
    int i = 0;
    int j = 0;

    // The index k traverses the result deck
    for (int k = 0; k < d3.length; k++) {

      // If d1 is empty, use top card from d2
      // If d2 is empty, use top card from d1
      // Otherwise, compare the two top cards

      // Add lowest card to the new deck at k index
      // Increment i or j depending on which deck's card was placed in d3
    }
    // Return the result deck
  }

  public Deck almostMergeSort() {

    // Divide the deck into 2 subdecks
    // Sort the subdecks using selectionSort
    // Merge the 2 subdecks into a new deck and return the result deck
  }

  public Deck mergeSort() {

    // If the deck has 0 or 1 cards, it is already sorted so return it
    // Otherwise, divide the deck into two subdecks
    // Sort the subdecks using mergeSort
    // Merge the decks
    // Return the result deck
  }

  /**
   * Return a String representing all the cards in the deck, one card on each newline.
   *
   * @return the String representing all the cards in the deck
   */
  public String toString() {

    StringBuilder text = new StringBuilder();
    for (Card card : this.cards) {

      text.append(card.toString());
      text.append('\n');
    }

    return text.toString();
  }

  /* ----- Main Program ----- */
  public static void main(String[] args) {

    Deck deck = new Deck();
    deck.printDeck();
  }
}
