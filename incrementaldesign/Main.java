/**
 * Explore the process of incremental design. Separating code chunks into methods to provide better
 * modularity to the program and make it easier to understand.
 *
 * @author Pierre-Charles Dussault
 * @since 07/13/2021
 * @version 1.0
 */
public class Main {

  public static void main(String[] args) {

    System.out.println("Program to display a multiplication table.");
    // Let's test displaying multiples of 2
    for (int i = 1; i <= 6; i++) {
      System.out.printf("%4d", 2 * i);
    }
    // Now let's repeat the same behaviour, but with the encapsulation
    System.out.println();
    printRow(2, 6);
    System.out.println();
    printRow(3, 6);
    System.out.println();

    // Now let's create a multiplication table
    for (int i = 1; i <= 6; i++) {
      printRow(i, 6);
    }

    // Same as before, reimplement it using encapsulatization and generalization
    System.out.println();
    printTable(6);
  }

  public static void printRow(int n, int columns) {
    // Encapsulate the previous behaviour, and generalize to allow multiples of n
    // Also generalize for number of columns
    for (int i = 1; i <= columns; i++) {
      System.out.printf("%4d", n * i);
    }
    System.out.println(); // newline after end of row
  }

  public static void printTable(int rows) {
    // Encapsulate once again, with generalization
    for (int i = 1; i <= rows; i++) {
      printRow(i, i); // Same number of rows as columns
    }
  }
}
