/**
 * Class to represent each player in the Crazy Eights card game.
 *
 * @author Pierre-Charles Dussault
 * @since 2021/09/10
 * @version 1.0
 */
public class Player {

  private String name;
  private Hand hand;

  /**
   * Constructor; assigns the player's name to his card hand.
   *
   * @param name the name that will be used to refer to this player
   */
  public Player(String name) {
    this.name = name;
    this.hand = new Hand(name);
  }

  /**
   * This player plays a card to complete his turn; if he cannot play a card from his hand, he draws
   * a card until he can.
   *
   * @param eights object that encapsulates the state of the game
   * @param previousCard the card on top of the discard pile
   * @return the card played by the player for this turn, and remove it from his hand
   */
  public Card play(Eights eights, Card previousCard) {
    Hand playableCards = getPlayableCards(eights, previousCard);
    if (playableCards.isEmpty()) {
      Card card = drawForMatch(eights, previousCard);
      return card;
    }
    
    Card card = getHighestCard(playableCards);
    int cardIndex = hand.getCards().indexOf(card);
    return hand.popCard(cardIndex);
  }
  
  /**
  * Return the Card with the highest point value according to the rules of Crazy Eights.
  *
  * @param playableCards hand of cards from which to select the card with the highest point value
  * @return the card with the highest point value
  */
  private Card getHighestCard(Hand playableCards) {
    int highestValue = 0;
    Card highestCard = null;
    for (Card card : playableCards.getCards()) {
      int value = getCardValue(card);
      if (value > highestValue) {
        highestValue = value;
        highestCard = card;
      }
    }
    
    return highestCard;
  }
  
  /**
  * Get the point value for a card, according to the rules of Crazy Eights.
  *
  * @param card the card for which to calculate the point value
  * @return the number of points that the card is worth
  */
  private int getCardValue(Card card) {
    int cardRank = card.getRank();
    // If t is an 8
    if (cardRank == 7) {
      return 20;
      // If it is a number card
    } else if (cardRank < 10) {
      return cardRank + 1;
      // If it is an Ace
    } else if (cardRank == 13) {
      return 1;
      // If it is a face card
    } else if (cardRank >= 10 && cardRank <= 12) {
      return 10;
    }
    
    // Otherwise return 0 to indicate error
    System.err.println("Invalid card rank");
    return 0;
  }
  
  /**
  * Return a hand of playable cards from the player's hand for the current turn.
  * 
  * @param eights Instance of the game state
  * @param previousCard the card on top of the discard pile which has to be matched for this turn
  * @return a hand of playable cards from the player's hand
  */
  private Hand getPlayableCards(Eights eights, Card previousCard) {
    Hand playableCards = new Hand("Playable Cards");
    for (Card card : hand.getCards()) {
      if (cardMatches(card, previousCard)) {
        playableCards.addCard(card);
      }
    }
    
    return playableCards;
  }

  /**
   * Search inside the player's hand for a card that can be matched to the previous played card.
   *
   * @param eights variable to hold the state of the game
   * @param previousCard the card on top of the discard pile
   * @return a matching card from the player's hand, if he has one, or null if he does not
   */
  private Card searchForMatch(Eights eights, Card previousCard) {
    for (int i = 0; i < hand.size(); i++) {
      Card card = hand.getCard(i);
      if (cardMatches(card, previousCard)) {
        return hand.popCard(i);
      }
    }

    // If no card was found to match, return null
    return null;
  }

  /**
   * Draw a card until it matches with the previously played card and can be played.
   *
   * @param eights variable to hold the state of the game
   * @param previousCard the card on top of the discard pile
   * @return the first drawn Card that can be played
   */
  private Card drawForMatch(Eights eights, Card previousCard) {
    while (true) {
      Card card = eights.drawCard();
      System.out.println(name + " draws " + card);
      if (cardMatches(card, previousCard)) {
        return card;
      }

      // Add the card to the player's hand if it could not be played
      hand.addCard(card);
    }
  }

  /**
   * Check if two cards match according to the Crazy Eights game rules.
   *
   * @param c1 first card to check for a match
   * @param c2 second card to check for a match
   * @return true or false, depending on if the cards match or not
   */
  private static boolean cardMatches(Card c1, Card c2) {
    // Remember the RANK table in Card.java, number ranks are off by -1
    return c1.getSuit() == c2.getSuit() || c1.getRank() == c2.getRank() || c1.getRank() == 7;
  }

  /**
   * Calculate the penalty points a player is attributed at the end of the game, based on the cards
   * in his hand.
   *
   * @return the number of penalty points the player has
   */
  public int score() {
    // Through all of this, remember that card ranks are off by -1
    int total = 0;
    for (Card card : hand.getCards()) {
      int cardRank = card.getRank();
      // If the card is an 8, add 20 to the total
      if (cardRank == 7) {
        total += 20;
        // If the card is a 10 or below (excluding Aces), add its rank+1 to the total
      } else if (cardRank < 10) {
        total += card.getRank() + 1;
        // If it's an Ace, add 1 to the total
      } else if (cardRank == 13) {
        total += 1;
        // If it's a face card, add 10 to the total
      } else {
        total += 10;
      }
    }

    return total;
  }

  /** Get the player's card hand. */
  public Hand getHand() {
    return hand;
  }

  /** Get the player's name. */
  public String getName() {
    return name;
  }

  /** Display the cards in a player's hand */
  public void display() {
    System.out.printf("%s:\n", name);
    for (Card card : hand.getCards()) {
      System.out.println(card);
    }

    System.out.println();
  }

  /**
   * Display the penalty score for this player, based on the calculated penalty from the cards
   * remaining in his hand.
   */
  public void displayScore() {
    System.out.printf("Player %s penalty score: %d\n", getName(), score());
  }

  /**
   * Deal this player a new hand (use at the start of the game).
   *
   * @param deck deck to use to deal the cards to the player's hand
   */
  public void dealHand(Deck deck) {
    deck.deal(this.hand, 5);
  }
}
