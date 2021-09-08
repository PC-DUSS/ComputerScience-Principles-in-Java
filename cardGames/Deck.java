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
  // Convenience attribute
  private int length;
  // Classwide Random Object for having access to pseudorandom number generation
  private static Random deckRand = new Random();

  /**
   * Constructs and empty Deck of an integer n number of Cards.
   *
   * @param n int number of Cards to have in the Deck
   */
  public Deck(int n) {

    this.cards = new Card[n];
    this.length = this.cards.length;
  }

  /** Constructs an unshuffled standard 52 Card Deck. */
  public Deck() {

    this.cards = new Card[52];
    this.length = this.cards.length;
    int index = 0;

    for (int suit = 0; suit <= 3; suit++) {
      for (int rank = 1; rank <= 13; rank++) {

        this.cards[index] = new Card(rank, suit);
        index++;
      }
    }
  }

  /* ----- Getter(s) ----- */
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
    for (int i = 0; i < this.length; i++) {

      this.swapCards(i, this.length - 1);
    }
  }

  /**
   * Helper method: return a random int between a low and high value, both inclusively.
   *
   * @param low int minimum acceptable value
   * @param high int maximum acceptable value
   * @return the random int inside the given range
   */
  private static int randomInt(int low, int high) {

    return deckRand.nextInt(high - low + 1) + low;
  }

  /**
   * Helper method: swap two cards positions from inside the deck.
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

  /**
   * Sort cards in a deck, by traversing the deck and placing the lowest card behind the previously
   * placed lowest card, or at the beginning of the deck if this is the first card being placed.
   */
  public void selectionSort() {

    int cardArrLength = this.length;
    for (int i = 0; i < cardArrLength; i++) {

      // Find the lowest card to the right of i inclusively
      int indexOfLowest = indexLowest(i, cardArrLength - 1);
      // Swap the i'th card with the lowest card found
      this.swapCards(i, indexOfLowest);
    }
  }

  /**
   * Helper method: find the lowest card between low and high both inclusively, and return its
   * index.
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

    for (int i = 0; i < sub.length; i++) {

      sub.cards[i] = this.cards[low + i];
    }

    return sub;
  }

  public static Deck merge(Deck d1, Deck d2) {

    // Create a new empty deck d3 big enough for all the cards
    int deckSize = d1.length + d2.length;
    Deck d3 = new Deck(deckSize);

    // Use the index i to keep track of where we are at in the first deck, and index j for the
    // second deck.
    int i = 0;
    int j = 0;

    // The index k traverses the result deck
    for (int k = 0; k < d3.length; k++) {

      // If d1 is empty or already passed through, use the current card from d2
      if (i >= d1.length || d1.length == 0) {

        d3.cards[k] = d2.cards[j];
        // Increment the next card to check in d2
        j++;

        // If d2 is either empty or already passed through, use the current card from d1
      } else if (j >= d2.length || d2.length == 0) {

        d3.cards[k] = d1.cards[i];
        // Increment the next card to check in d1
        i++;

        // Otherwise, compare the two top cards
      } else {

        // If the current card from d1 is smaller than the current card from d2 add the card from d1
        // to the result deck's current index
        if (d1.cards[i].compareTo(d2.cards[j]) < 0) {

          d3.cards[k] = d1.cards[i];
          // Increment the next card to check in d1
          i++;

          // If it's d2's card that is smaller, add it to the result deck
        } else if (d2.cards[j].compareTo(d1.cards[i]) < 0) {

          d3.cards[k] = d2.cards[j];
          // Increment the next card to check in d2
          j++;

          // If the cards are equal in value, add them both one after the other to the result deck
        } else {

          // Add the card from d1 (the order doesn't really matter)
          d3.cards[k] = d1.cards[i];
          // Increment the next card to check in d1
          i++;
          // Increment next empty spot to fill in result deck
          k++;
          // Add the card from d2
          d3.cards[k] = d2.cards[j];
          // Increment the next card to check in d2
          j++;
        }
      }
    } // end of for loop

    // Return the result deck
    return d3;
  }

  public Deck almostMergeSort() {

    // Divide the deck into 2 subdecks
    // Sort the subdecks using selectionSort
    // Merge the 2 subdecks into a new deck and return the result deck

    // Stub
    return new Deck();
  }

  public Deck mergeSort() {

    // If the deck has 0 or 1 cards, it is already sorted so return it
    // Otherwise, divide the deck into two subdecks
    // Sort the subdecks using mergeSort
    // Merge the decks
    // Return the result deck

    // Stub
    return new Deck();
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
    // Testing printDeck method
    System.out.println("Testing printDeck():");
    deck.printDeck();
    System.out.println();
    // Testing toString method
    System.out.println("Testing toString():");
    System.out.println("\n" + deck.toString());
    System.out.println();
    // Testing shuffle method
    System.out.println("Testing shuffle();");
    deck.shuffle();
    deck.printDeck();
    System.out.println();
    // Testing subdeck method
    System.out.println("Testing subdeck(int low, int high):");
    Deck subdeck1 = deck.subdeck(0, 25);
    Deck subdeck2 = deck.subdeck(26, 51);
    System.out.println("Subdeck 1:");
    subdeck1.printDeck();
    System.out.println("\nSubdeck 2:");
    subdeck2.printDeck();
    System.out.println();
    // Testing merge method
    System.out.println("Testing merge(Deck d1, Deck d2):");
    Deck mergedDeck = merge(subdeck1, subdeck2);
    mergedDeck.printDeck();
    System.out.println();
    // Testing selectionSort method
    System.out.println("Testing selectionSort():");
    mergedDeck.selectionSort();
    mergedDeck.printDeck();
    System.out.println();
  } // end of main program
}
