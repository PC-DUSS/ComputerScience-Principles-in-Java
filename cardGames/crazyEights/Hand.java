/**
 * @author Pierre-Charles Dussault
 * @since 2021/09/10
 * @version 1.0
 */
public class Hand extends CardCollection {

  /**
   * Constructor
   *
   * @param label descriptive word for the type of CardCollection this is (should be "Hand" or
   *     "Pile")
   */
  public Hand(String label) {
    super(label);
  }

  public void display() {
    System.out.println(getLabel() + ":");
    for (int i = 0; i < size(); i++) {
      System.out.println(getCard(i));
    }

    System.out.println();
  }
}
