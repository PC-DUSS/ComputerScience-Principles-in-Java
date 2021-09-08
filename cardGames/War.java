/**
 * Implementation of a classic card game.
 *
 * @author Pierre-Charles Dussault
 * @since 2021/09/07
 * @version 1.0
 */
public class War {

  public static void main(String[] args) {

    Deck deck = new Deck();
    deck.shuffle();

    // Add half of the deck to player 1's pile
    Pile p1 = new Pile();
    p1.addDeck(deck.subdeck(0, 25));

    // Now the other half to player 2's pile
    Pile p2 = new Pile();
    p2.addDeck(deck.subdeck(26, 51));

    // Game loop
    while (!p1.isEmpty() && !p2.isEmpty()) {

      // Pop a card from each pile for upcoming battle
      Card c1 = p1.popCard();
      Card c2 = p2.popCard();

      // Battle and compare the two cards
      int diff = c1.getRank() - c2.getRank();

      if (diff > 0) {

        // Player 1 wins this round
        p1.addCard(c1);
        p1.addCard(c2);

      } else if (diff < 0) {

        // Player 2 wins this round
        p2.addCard(c1);
        p2.addCard(c2);

      } else {

        // Tie
      }
    } // end of game loop

    // Display win message depending on which pile of cards is not empty
    if (p2.isEmpty()) {

      System.out.println("Player 1 wins!");

    } else {

      // Then it is player 1's pile that is empty, so player 2 wins
      System.out.println("Player 2 wins!");
    }
  } // end of main program
}
