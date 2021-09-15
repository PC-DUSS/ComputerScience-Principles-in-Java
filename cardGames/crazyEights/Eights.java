import java.util.Scanner;

/**
 * Class to represent the state of the game in Crazy Eights.
 *
 * @author Pierre-Charles Dussault
 * @since 2021/09/10
 * @version 1.0
 */
public class Eights {

  /*
   1) Create the deck, the players, and the discard and draw piles. Deal the
   cards and set up the game. (Eights constructor)
   2) Check whether the game is over. (isDone)
   3) If the draw pile is empty, shuffle the discard pile and move the cards into
   the draw pile. (reshuffle)
   4) Draw a card, reshuffling the discard pile if necessary. (drawCard)
   5) Keep track of whose turn it is, and switch from one player to the next.
   (nextPlayer)
   6) Display the state of the game, and wait for the user before running the
   next turn. (displayState)
  */

  private Player one;
  private Player two;
  private Hand drawPile;
  private Hand discardPile;
  private Scanner in;

  /** Constructor: initialize instance variables and deal cards to players. */
  public Eights() {
    in = new Scanner(System.in);
    Deck deck = new Deck("Deck");
    one = new Player("Player 1");
    two = new Player("Player 2");
    drawPile = new Hand("Draw Pile");
    discardPile = new Hand("Discard Pile");

    deck.shuffle();
    one.dealHand(deck);
    two.dealHand(deck);
    deck.deal(discardPile, 1);
    deck.dealAll(drawPile);
  }

  /**
   * Check whether the game is over: if any player's hand is empty, the game is done.
   *
   * @return true or false, depending on if the hand of any player is empty
   */
  public boolean isDone() {
    return one.getHand().isEmpty() || two.getHand().isEmpty();
  }

  /** Reshuffle the discard pile and turn it into a draw pile, except for the card on top. */
  public void reshuffle() {
    Card topCard = discardPile.popCard();
    discardPile.dealAll(drawPile);
    discardPile.addCard(topCard);
    drawPile.shuffle();
  }

  /**
   * Draw a card and return it, reshuffling the discard pile into the draw pile if it ever becomes
   * empty.
   *
   * @return the drawn card
   */
  public Card drawCard() {
    if (drawPile.isEmpty()) {
      reshuffle();
    }

    return drawPile.popCard();
  }

  /**
   * Handle player turns.
   *
   * @param current player whose turn it currently is
   * @return player whose turn it is about to be
   */
  public Player nextPlayer(Player current) {
    if (current == one) {
      return two;
    }

    return one;
  }

  /**
   * Display each player's hand, the contents of the discard pile and the number of cards in the
   * draw pile.
   */
  public void displayState() {
    one.display();
    two.display();
    discardPile.display();
    System.out.printf("Draw Pile:\n%d cards\n", drawPile.size());
    in.nextLine();
  }

  /**
   * A player takes his turn.
   *
   * @param player the player whose turn it is to play
   */
  public void takeTurn(Player player) {
    // This gets the top card without removing it from the discard pile, as popCard() would
    Card topCard = discardPile.lastCard();
    Card nextCard = player.play(this, topCard); // 'this' is this instance of Eights
    discardPile.addCard(nextCard);
    System.out.printf("%s plays %s\n", player.getName(), nextCard);
  }
  
  /**
   * A player takes his turn without notification to std_out.
   *
   * @param player the player whose turn it is to play
   */
  public void takeTurnSilently(Player player) {
    // This gets the top card without removing it from the discard pile, as popCard() would
    Card topCard = discardPile.lastCard();
    Card nextCard = player.play(this, topCard); // 'this' is this instance of Eights
    discardPile.addCard(nextCard);
  }

  /**
  * Play the game Crazy Eights. 
  * 
  * @return which player won that game
  */
  public Player playGame() {
    // Player one starts
    Player player = one;
    // Keep playing until someone wins
    while (!isDone()) {
      displayState();
      takeTurn(player);
      player = nextPlayer(player);
    }

    one.displayScore();
    two.displayScore();
    return whoWon();
  }
  
  /**
  * Play the game Crazy Eights without waiting for input on every turn, and without notification to
  * std_out. 
  * 
  * @return which player won that game
  */
  public Player playGameFast() {
    Player player = one;
    while(!isDone()) {
      takeTurnSilently(player);
      player = nextPlayer(player);
    }
    
    Player winner = whoWon();
    System.out.printf("%s wins\n", winner.getName());
    return winner;
  }
  
  private Player whoWon() {
    if (one.getHand().isEmpty()) {
      return one;
    } else if (two.getHand().isEmpty()) {
      return two;
    } else {
      System.err.println("Error in whoWon()");
      return null;
    }
  }
  
  /**
  * Return the Player 1 object.
  * 
  * @return the Player 1 object
  */
  public Player getPlayerOne() {
    return one;
  }
  
  /**
  * Return the Player 2 object.
  * 
  * @return the Player 2 object
  */
  public Player getPlayerTwo() {
    return two;
  }
}
