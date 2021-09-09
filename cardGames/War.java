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

      // If player 1 wins this round
      if (diff > 0) {

        // Add both cards to player 1's pile
        p1.addCard(c1);
        p1.addCard(c2);

        // If player 2 wins this round
      } else if (diff < 0) {

        // Add both cards to player 2's pile
        p2.addCard(c1);
        p2.addCard(c2);

        // If it's a tie
      } else {

        // Draw 3 cards from each player's pile, and store them in a collection, along with the
        // original two.
        // Then draw one more card from each pile and make them battle (compare them).
        // If it's another tie, then repeat the process.
        // If a player cannot draw the required 3 extra cards, the game ends immediately and he
        // loses.
        Pile pot = new Pile();
        boolean itsATie = true;
        boolean gameHasEnded = false;
        while (itsATie) {

          // Add the two tied cards to the pot
          pot.addCard(c1);
          pot.addCard(c2);

          // Add another 3 cards from each player's deck to the pot
          for (int i = 0; i < 3; i++) {

            pot.addCard(p1.popCard());
            pot.addCard(p2.popCard());

            // If a player has no more cards, the game ends
            if (p1.isEmpty() || p2.isEmpty()) {

              gameHasEnded = true;
              break;
            }
          }

          // First, check that the game has not ended (that a player has no more cards)
          if (gameHasEnded) {

            break;
          } // If each player has cards remaining, continue on

          // Make each player battle their top card
          c1 = p1.popCard();
          c2 = p2.popCard();
          diff = c1.getRank() - c2.getRank();

          // If player 1 wins
          if (diff > 0) {

            // Player 1 takes all
            pot.addCard(c1);
            pot.addCard(c2);
            p1.addPile(pot);
            // Tie is broken
            itsATie = false;

            // If player 2 wins
          } else if (diff < 0) {

            // Player 2 takes all
            pot.addCard(c1);
            pot.addCard(c2);
            p2.addPile(pot);
            // Tie is broken
            itsATie = false;
          }
          // If it's another tie, continue the process until there is tie-break, or until a player
          // has no more cards. In that case he loses automatically.
        } // end of tie loop
      } // end of tie condition
    } // end of game loop

    // Display win message depending on which pile of cards is empty
    if (p2.isEmpty()) {

      System.out.println("Player 1 wins!");

    } else {

      System.out.println("Player 2 wins!");
    }
  } // end of main program
}
