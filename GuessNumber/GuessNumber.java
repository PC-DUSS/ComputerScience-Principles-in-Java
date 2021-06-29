import java.lang.Math;
import java.util.Scanner;
import java.util.Random;


public class GuessNumber
{
  public static void main(String[] args)
  {
    Scanner in = new Scanner(System.in);
    Random random = new Random();
    int myNumber;
    int userGuess;
    int difference;
    int guessesRemaining = 3;

    // Generate a random integer from 1-100
    myNumber = random.nextInt(100);  // INCORRECT. Returns an int from 0-99
    myNumber = random.nextInt(100) + 1;  // Equivalent to an int from 1-100

    // Prompt for input and compare it with the generated number
    System.out.print("Try to guess my number from 1-100: ");
    while (guessesRemaining > 0)
    {
      userGuess = in.nextInt();
      if (userGuess == myNumber)
      {
        System.out.println("Congratulations, you guessed right!");
        return;
      }
      else if (userGuess > myNumber)
      {
        System.out.print("You guessed too high! Guess again: ");
        guessesRemaining--;
      }
      else
      {
        System.out.print("You guessed too low! Guess again: ");
        guessesRemaining--;
      }
    }

    System.out.println("Oh, but you're out of guesses :(" 
                       + "\nBetter luck next time!\n");
  }
}
