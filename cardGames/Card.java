import java.util.Arrays;

/**
 * Class to represent the concept of a playing card. To be used in conjunction with the Deck class
 * which represents a deck of playing cards; part of the project for the game Crazy Eights, done
 * while reading the book Think Java, 2nd Edition, by Allen B. Downey and Chris Mayfield.
 *
 * @author Pierre-Charles Dussault
 * @since 2021-07-24
 * @version 1.0
 */
public class Card {

  /*
    Card ranks encoding:
    - Jack => 10
    - Queen => 11
    - King => 12
    - Ace => 13

    Card symbol (suit) encoding:
    - Clubs => 0
    - Diamonds => 1
    - Hearts => 2
    - Spades => 3
  */

  // Instance attributes
  private final int rank;
  private final int suit;
  // Ranks start at index position 1, not 0, and its length is 1 more than the actual number of
  // significant values (because of the extra null at index 0). The Ace holds the greatest rank
  public static final String[] RANKS = {
    null, "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"
  };
  // Suits start at index position 0
  public static final String[] SUITS = {"Clubs", "Diamonds", "Hearts", "Spades"};

  // Can we see the Card's value, or only the back cover
  private boolean faceUp;

  /**
   * Value constructor; the default constructor is not very useful here; refer to the class
   * constants RANKS and SUITS for the values assigned to each real-life component of a playing
   * card.
   *
   * @param rank int the rank of the Card
   * @param suit int the suit of the Card
   */
  public Card(int rank, int suit) throws IllegalArgumentException {

    // Check if the given values for rank and suit are valid
    if (rank < 1 || rank > 13) {
      throw new IllegalArgumentException("Card rank must be from 1 to 13.");
    } else if (suit < 0 || suit > 3) {
      throw new IllegalArgumentException("Card suit must be from 0 to 3.");
    }

    // You can assign unassigned final keyworded attributes inside a constructor, but not anywhere
    // else
    this.rank = rank;
    this.suit = suit;
    // By default we should only see the back of the Card
    this.faceUp = false;
  }

  /* ----- Getters  ----- */
  public int getRank() {

    return this.rank;
  }

  public int getSuit() {

    return this.suit;
  }

  public boolean getCardFace() {

    return this.faceUp;
  }

  /* ----- Setters ----- */
  public void setFaceUp() {

    this.faceUp = true;
  }

  public void setFaceDown() {

    this.faceUp = false;
  }

  /**
   * Get a neatly formatted String for the current Card.
   *
   * @return String that is formatted to neatly represent the caller instance Card
   */
  public String toString() {

    return String.format("%s of %s", RANKS[this.rank], SUITS[this.suit]);
  }

  /**
   * Verify that two cards have the same rank and suit.
   *
   * @param other Card to which to compare the caller instance Card with
   * @return true or false, depending on if they are equivalent or not
   */
  public boolean equals(Card other) {

    return this.rank == other.rank && this.suit == other.suit;
  }

  /**
   * Compare the ranks and suits of two Cards to see which is greater than the other, or if they are
   * equivalent; suit is more important than rank.
   *
   * @param other Card to which to compare the caller instance Card with
   * @return -1 if this < other, 1 if this > other, and 0 if this == other
   */
  public int compareTo(Card other) {

    if (this.suit > other.suit) {

      return 1;

    } else if (this.suit < other.suit) {

      return -1;

    } else if (this.rank > other.rank) {

      return 1;

    } else if (this.rank < other.rank) {

      return -1;
    }

    // If no return statement was triggered, then they are equivalent
    return 0;
  }

  /**
   * Populate an ordered array of Cards.
   *
   * @return Card[] an ordered array of Cards
   */
  public static Card[] generateCards() {

    Card[] cards = new Card[52];
    int index = 0;

    // For each possible suit family
    for (int lSuit = 0; lSuit < SUITS.length; lSuit++) {

      // And for each possible rank. Remember, ranks use 1-based indexing, not 0-based indexing, and
      // has a length of 1-more than the actual number of significant values
      for (int lRank = 1; lRank < RANKS.length; lRank++) {

        // Create a Card holding each of those values and add it to the array of Cards
        cards[index] = new Card(lRank, lSuit);
        index++;
      }
    }

    return cards;
  }

  /**
   * Generate an array of Cards, with one specified Card missing from the array.
   *
   * @param missingCard Card that should be missing from the returned array
   * @return Card[] Card array that is missing a specified Card from its contents
   */
  public static Card[] generateCardsMissing(Card missingCard) {

    Card[] incompleteCards = new Card[51];
    int index = 0;

    for (int lSuit = 0; lSuit < SUITS.length; lSuit++) {
      for (int lRank = 1; lRank < RANKS.length; lRank++) {

        Card lCard = new Card(lRank, lSuit);
        if (!(lCard.equals(missingCard))) {

          incompleteCards[index] = lCard;
          index++;
        }
      }
    }

    return incompleteCards;
  }

  /**
   * Display all the Cards in an array of Cards to std_out by their respective rank and suit.
   *
   * @param cards Card[] array of Cards
   */
  public static void printCardArray(Card[] cards) {

    for (Card card : cards) {

      System.out.println(card);
    }
  }

  /**
   * Get the index position of a desired target card from inside a Card array; returns -1 if the
   * target isn't found; use sequential search algorithm; if the Cards are NOT ordered sequential
   * search is the fastest algorithm, as we have no other choice than to check each Card; if the
   * Cards are ordered however, then there are faster algorithms that do not have to check every
   * single Card.
   *
   * @param cards : Card[]. Card array to search in
   * @param target : Card. Target Card to search for
   * @return the index position of the target if it is found, or return -1 if it isn't found
   */
  public static int sequentialSearch(Card[] cards, Card target) {

    // Search all Cards in the Card array
    for (int i = 0; i < cards.length; i++) {

      // If the currently searched card is equivalent to the target
      if (cards[i].equals(target)) {

        // Return the index position of the currently search card
        return i;
      }
    }

    // If no equivalent card is found in the Card array, return -1
    return -1;
  }

  /**
   * Get the index position of a desired target card from inside a Card array; returns -1 if the
   * target isn't found; use binary search algorithm; this algorithm is very efficient if we can pay
   * the price to keep the array sorted at all times; this method can be applied to ANY object that
   * provides a compareTo method.
   *
   * @param cards : Card[]. Card array to search in
   * @param target : Card. Target Card to search for
   * @return the index position of the target if it is found, or return -1 if it isn't found
   */
  public static int binarySearch(Card[] cards, Card target) {

    /* Go to middle.
    If the card found is lesser than the target, go higher.
    If the card found is greater than the target, go lower.
    Repeat until either:
    - You hit the target, or
    - You see two adjacent cards in between which the target should be, but isn't, therefore it is
      not in the Card array
    - The context-appropriate adjacent (either the previous, or the next one, depending on context)
      Card to the one found is equivalent to the target

    NOTES:
    - When the Card at the current index is greater than the target, update the lower bound,
      excluding the current Card in the next iteration
    - When the Card at the current index is greater than the target, update the lower bound,
      excluding the current Card in the next iteration
     */

    int high = cards.length - 1;
    int low = 0;
    int index;

    // While there is an existing domain in which we can conduct a search
    while (low <= high) {

      // Print the current domain of search
      System.out.println("[" + low + ", " + high + "]");

      // Find the middle of the domain and save its index
      index = (low + high) / 2;
      // Compare the card at that index position with the target
      int comparison = cards[index].compareTo(target);

      // If it is equivalent to the target
      if (comparison == 0) {

        // Return its index
        return index;

        // If it is lesser than the target
      } else if (comparison == -1) {

        // The new domain becomes restricted to the upper half of the previous domain, EXCLUDING the
        // index of the just found middle point
        low = index + 1;

        // If it is greater than the target
      } else {

        // The new domain becomes restricted to the lower half of the previous domain, EXCLUDING the
        // index of the just found middle point
        high = index - 1;
      }
    }

    // If the return statement in the loop was never triggered, then the Card is absent from the
    // array
    return -1;
  }

  /**
   * Get a histogram for the frequency of Cards belonging to each suit in a given array of Cards.
   *
   * @param cards Card[] array of Cards
   * @return an int[] array histogram for the frequency of Cards in each suit. [0] is Clubs, [1] is
   *     Diamonds, [2] is Hearts and [3] is Spades
   */
  public static int[] suitHist(Card[] cards) {

    // Array to represent Card suits (0: Clubs, 1: Diamonds, 2: Hearts, 3: Spades)
    int[] hist = new int[4];

    // For each Card in the array of Cards
    for (int i = 0; i < cards.length; i++) {

      // Get its suit
      int currentSuit = cards[i].suit;
      // Increment the histogram amount for this suit
      hist[currentSuit]++;
    }

    return hist;
  }

  /**
   * Check to see if a hand contains a flush (five or more cards of the same suit).
   *
   * @param cards Card[] array of Cards, the hand you want to examine
   * @return true of false, depending on whether the hand contains a flush or not
   */
  public static boolean hasFlush(Card[] cards) {

    // Get a histogram for the suits of all the Cards present in the hand
    int[] hist = suitHist(cards);

    // For each suit
    for (int i = 0; i < hist.length; i++) {

      // If the number of Cards from that suit in the given hand equals or exceeds 5
      if (hist[i] >= 5) {

        // Then the hand contains a flush
        return true;
      }
    }

    // If no return statement was triggered, then there is no flush
    return false;
  }

  /**
   * Get a histogram of frequencies for each Card in an array of Cards.
   *
   * @param cards Card[] given array of Cards to examine
   * @return a histogram of frequencies for each possible standard Card in a deck, meaning
   *     exlcluding Wildcards
   */
  public static int[] cardHist(Card[] cards) {

    // Histogram for all possible standard Cards
    int hist[] = new int[52];

    // For each Card inside the hand
    for (int i = 0; i < cards.length; i++) {

      // Determine the index of the current Card in the histogram
      int cardIndex = (cards[i].suit * 13) + cards[i].rank - 1;
      hist[cardIndex]++;
    }

    return hist;
  }

  /**
   * Check if a hand contains a royal flush, or the sequence: 10, jack, queen, king, ace for a same
   * Card suit.
   *
   * @param cards Card[] given array of Cards
   * @return true of false, depending on if the hand contains a royal flush or not
   */
  public static boolean hasRoyal(Card[] cards) {

    // Get frequencies for all cards in the examined hand
    int[] hist = cardHist(cards);

    // Test print the Card histogram to see whats up
    System.out.println(Arrays.toString(hist));

    // If any type of royal flush is present:
    // Clubs royal flush
    if (hist[8] >= 1 && hist[9] >= 1 && hist[10] >= 1 && hist[11] >= 1 && hist[12] >= 1) {

      return true;

      // Diamonds royal flush
    } else if (hist[21] >= 1 && hist[22] >= 1 && hist[23] >= 1 && hist[24] >= 1 && hist[25] >= 1) {

      return true;

      // Hearts royal flush
    } else if (hist[34] >= 1 && hist[35] >= 1 && hist[36] >= 1 && hist[37] >= 1 && hist[38] >= 1) {

      return true;

      // Spades royal flush
    } else if (hist[47] >= 1 && hist[48] >= 1 && hist[49] >= 1 && hist[50] >= 1 && hist[51] >= 1) {

      return true;
    }

    // If no royal flush condition has triggered a return statement, then return false
    return false;
  }

  /**
   * Fisher-Yates shuffle to randomize an array of Cards.
   *
   * @param cards Card[] array of Cards
   */
  public static void shuffleCards(Card[] cards) {

    // The shuffle starts from the back and moves towards the front. lengthToShuffle also represents
    // the current Card being shuffled
    int lengthToShuffle = cards.length;
    // Temporary variable to hold the current Card being shuffled
    Card tmp;
    // Card to swap with the shuffled Card
    int randomSwap;

    // While there remains cards that need to be shuffled
    while (lengthToShuffle > 0) {

      // Pick a random index for the card to shuffle from [0 - (lengthToShuffle - 1)]
      randomSwap = (int) Math.floor(Math.random() * (double) (lengthToShuffle - 1));
      // Hold the swap card in temporary variable
      tmp = cards[lengthToShuffle - 1];
      // Swap the randomly selected card to the back of the array of Cards, in the place of the
      // shuffled Card
      cards[lengthToShuffle - 1] = cards[randomSwap];
      // Place the Card currently being shuffled in place of the randomly selected swap Card
      cards[randomSwap] = tmp;
      // Decrement the length of cards to shuffle (also points to the current Card being shuffled)
      lengthToShuffle--;
    }
  }

  /* ----- Main Program ----- */
  public static void main(String[] args) {

    Card threeOfClubs = new Card(2, 0);
    System.out.println(threeOfClubs);
    Card jackOfDiamonds = new Card(10, 1);
    System.out.println(jackOfDiamonds);

    // Let's create an empty array of 52 Cards, kind of like a deck of cards. Remember, it actually
    // contains REFERENCES to Cards, since variables that are objects (here, Cards) are references
    // to their object, and are not the object itself
    Card[] cards = new Card[52];
    if (cards[0] == null) {
      System.out.println("No card yet!");
    }

    cards = generateCards();
    printCardArray(cards);
    // This also works, but not as pretty
    System.out.println(Arrays.toString(cards));

    // Testing out algorithms
    System.out.println();
    System.out.println(
        "Sequential search for Ace of Spades: " + sequentialSearch(cards, new Card(13, 3)));
    System.out.println("Binary search for Ace of Spades: " + binarySearch(cards, new Card(13, 3)));

    System.out.println();
    System.out.println(
        "Sequential search for King of Spades: " + sequentialSearch(cards, new Card(12, 3)));
    System.out.println("Binary search for King of Spades: " + binarySearch(cards, new Card(12, 3)));

    System.out.println();
    System.out.println(
        "Sequential search for Ace of Clubs: " + sequentialSearch(cards, new Card(13, 0)));
    System.out.println("Binary search for Ace of Clubs: " + binarySearch(cards, new Card(13, 0)));

    System.out.println();
    Card aceOfClubs = new Card(13, 0);
    Card[] cardsMissingAceOfClubs = generateCardsMissing(aceOfClubs);
    System.out.println(
        "Sequential search for Ace of Clubs when absent: "
            + sequentialSearch(cardsMissingAceOfClubs, aceOfClubs));
    System.out.println(
        "Binary search for Ace of Clubs when absent: "
            + binarySearch(cardsMissingAceOfClubs, aceOfClubs));

    // Testing for Card suit frequency histogram, flush and royal flush
    System.out.println();
    System.out.println(
        "Suit histogram with full array of Cards: " + Arrays.toString(suitHist(cards)));
    System.out.println(
        "Suit histogram with incomplete array of Cards (missing Ace of Clubs): "
            + Arrays.toString(suitHist(cardsMissingAceOfClubs)));
    Card kingOfClubs = new Card(12, 0);
    Card queenOfClubs = new Card(11, 0);
    Card jackOfClubs = new Card(10, 0);
    Card tenOfClubs = new Card(9, 0);
    Card[] royalFlush = {tenOfClubs, jackOfClubs, queenOfClubs, kingOfClubs, aceOfClubs};
    Card twoOfClubs = new Card(1, 0);
    Card fourOfClubs = new Card(3, 0);
    Card fiveOfClubs = new Card(4, 0);
    Card[] standardFlush = {aceOfClubs, twoOfClubs, threeOfClubs, fourOfClubs, fiveOfClubs};
    // Notice the imposter jackOfDiamonds
    Card[] incompleteFlush = {tenOfClubs, jackOfDiamonds, queenOfClubs, kingOfClubs, aceOfClubs};
    System.out.println(
        "\nCheck if a valid hand contains a standard flush: " + hasFlush(standardFlush));
    System.out.println(
        "Check if an invalid hand contains a standard flush: " + hasFlush(incompleteFlush));

    System.out.println(
        "\nCheck complete histogram for complete array of Cards: "
            + Arrays.toString(cardHist(cards)));
    System.out.println(
        "\nCheck complete histogram for an incomplete array of Cards: "
            + Arrays.toString(cardHist(cardsMissingAceOfClubs)));
    System.out.println("\nCheck for royal flush in valid hand: " + hasRoyal(royalFlush));
    System.out.println("\nCheck for royal flush in invalid hand: " + hasRoyal(incompleteFlush));
    System.out.println(
        "\nCheck for royal flush in standard flush hand: " + hasRoyal(standardFlush));

    // Test Fisher-Yates shuffle
    System.out.println("\nTesting Fisher-Yates shuffle on array of Cards:");
    Card[] cardsToShuffle = generateCards();
    shuffleCards(cardsToShuffle);
    printCardArray(cardsToShuffle);
  } // end of Main Program
}
