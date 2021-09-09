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

  /**
   * Create a copy of a deck.
   *
   * @param another instance of a deck object
   * @return the copied in a separate instance
   */
  public Deck(Deck another) {

    this.cards = another.cards;
    this.length = another.length;
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

    // Swap the current i'th card with a randomly picked card
    for (int i = 0; i < this.length; i++) {

      int randomIndex = randomInt(i, this.length - 1);
      this.swapCards(i, randomIndex);
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

    Card tmp = this.cards[i];
    this.cards[i] = this.cards[j];
    this.cards[j] = tmp;
  }

  /**
   * Sort cards in a deck, by traversing the deck and placing the lowest card just after the
   * previously placed lowest card, or at the beginning of the deck if this is the first card being
   * placed.
   */
  public void selectionSort() {

    for (int i = 0; i < this.length; i++) {

      // Find the lowest card to the right of i inclusively
      int currentLowestIndex = indexLowest(i, this.length - 1);
      // Swap the i'th card with the lowest card found
      this.swapCards(i, currentLowestIndex);
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

  /**
   * Simplified version of mergeSort: sorts the cards from a deck.
   *
   * @return the resulting sorted deck of cards
   */
  public Deck almostMergeSort() {

    // Divide the deck into 2 subdecks
    Deck subdeck1 = this.subdeck(0, 25);
    Deck subdeck2 = this.subdeck(26, 51);

    // Sort the subdecks using selectionSort
    subdeck1.selectionSort();
    subdeck2.selectionSort();

    // Merge the 2 subdecks into a new deck and return the result deck
    Deck resultDeck = merge(subdeck1, subdeck2);

    return resultDeck;
  }

  /**
   * Sort cards by a divide and conquer strategy, breaking a deck into smaller and smaller decks
   * until it reaches decks of one card only; then incrementally merge all of the smaller decks in
   * sorted fashion, so that the final merged deck is automatically sorted.
   *
   * @return the sorted deck
   */
  public Deck mergeSort() {

    // If the deck has 0 or 1 cards, it is already sorted so return it
    if (this.length == 0 || this.length == 1) {

      return this;
    }

    // Otherwise, divide the deck into two subdecks
    int subdeckHalf = (this.length - 1) / 2;
    Deck subdeck1 = this.subdeck(0, subdeckHalf);
    Deck subdeck2 = this.subdeck(subdeckHalf + 1, this.length - 1);

    // Sort the subdecks using mergeSort
    Deck sorted1 = subdeck1.mergeSort();
    Deck sorted2 = subdeck2.mergeSort();

    // Merge the decks
    Deck merged = merge(sorted1, sorted2);

    return merged;
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

  /**
   * Sort a deck of cards using insertion sort algorithm.
   *
   * @return the resulting sorted deck
   */
  public void insertionSort() {

    // Go through all cards starting from the second (the first one is considered sorted)
    for (int i = 1; i < this.length; i++) {

      Card currentCard = this.cards[i];
      // Insert the currently evaluated card in the correct spot inside the sorted deck
      this.insert(currentCard, i);
    }
  }

  /**
   * Insert a card that is being evaluated in its correct position inside a deck, so that the deck
   * remains sorted in increasing order.
   *
   * @param deckToSort Deck reference deck to use to create the sorted deck
   * @param card Card object that is to be inserted in the correct position
   * @param index int the index of the card currently being inserted into the sorted deck
   */
  public void insert(Card card, int i) {

    while (true) {

      // If the card has reached the very beginning of the deck
      if (i == 0) {

        // Then the card is in its correct position, exit the loop
        break;

        // Else if the evaluated card is bigger or equal to the previous card
      } else if (card.compareTo(this.cards[i - 1]) > 0 || card.compareTo(this.cards[i - 1]) == 0) {

        // Then the card is in its correct position, exit the loop
        break;

        // Otherwise (meaning it is smaller than the previous card)
      } else {

        // Swap the card with the previous one
        this.swapCards(i, i - 1);
        // Decrement the index variable to make sure we are keeping track of the card, since it
        // moved back one position
        i--;
      }
    }
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
    System.out.println(deck.toString());
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
    mergedDeck.shuffle();
    System.out.println();
    // Testing almostMergeSort method
    System.out.println("Testing almostMergeSort():");
    mergedDeck = mergedDeck.almostMergeSort();
    mergedDeck.printDeck();
    mergedDeck.shuffle();
    System.out.println();
    // Testing mergeSort method
    System.out.println("Testing mergeSort():");
    mergedDeck = mergedDeck.mergeSort();
    mergedDeck.printDeck();
    mergedDeck.shuffle();
    System.out.println();
    // Testing insertionSort method
    System.out.println("Testing insertionSort():");
    mergedDeck.insertionSort();
    mergedDeck.printDeck();
    mergedDeck.shuffle();
    System.out.println();
  } // end of main program
}
