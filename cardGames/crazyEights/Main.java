/**
 * Main Program for the game Crazy Eights from chapter 14 in ThinkJava, by Allen B Downey.
 *
 * @author Pierre-Charles Dussault
 * @since 2021/09/10
 * @version 1.0
 */
public class Main {

  public static void main(String args[]) {
    Deck deck = new Deck("Deck");
    deck.shuffle();

    Hand hand = new Hand("Hand");
    deck.deal(hand, 5);
    hand.display();

    Hand drawPile = new Hand("Draw Pile");
    deck.dealAll(drawPile);
    System.out.printf("Draw Pile has %d cards.\n", drawPile.size());
  }
}
