public class Tournament extends Eights {
  public Tournament() {
    super();
  }
  
  /**
  * Play a tournament of 100 games, and see which player wins the most out of these 100 games.
  *
  * @return the player who won the most games, or null if it is a draw
  */
  public Player playTournament() {
    int oneWins = 0;
    int twoWins = 0;
    int threeWins = 0;
    int fourWins = 0;
    
    for (int i = 0; i < 99; i++) {
      Eights eights = new Eights();
      Player winner = eights.playGameFast();
      if (winner == eights.getPlayer(1)) {
        oneWins++;
      } else if (winner == eights.getPlayer(2)) {
        twoWins++;
      } else if (winner == eights.getPlayer(3)) {
        threeWins++;
      } else if (winner == eights.getPlayer(4)) {
        fourWins++;
      } else {
        System.err.println("Error in playTournament()");
      }
    }
    
    int[] winsArr = {oneWins, twoWins, threeWins, fourWins};
    int maxWins = getArrPositiveMax(winsArr);
    Player winner = whoWonTournament(winsArr, maxWins);
    System.out.printf("The grand winner of the tournament is %s!\n", winner.getName());
    return winner;
    
    
    /*
    if (oneWins > twoWins) {
      return getPlayerOne();
    } else if (twoWins > oneWins) {
      return getPlayerTwo();
      // Return null if it's a draw
    } else {
      return null;
    }
    */
  }
  
  /**
  * Get the maximum positive value from an array, or 0 if there are no positive values.
  *
  * @param arr an array of ints
  * @return the max positive value from the array, or 0
  */
  private int getArrPositiveMax(int[] arr) {
    int max = 0;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] > max) {
        max = arr[i];
      }
    }
    
    return max;
  }
  
  /**
  * Determine who won the tournament of Crazy Eights, by comparing which player's amount of wins
  * corresponds to the greatest amount of wins earned in this tournament.
  *
  * @param eights game instance to store the game state
  * @param winsArr array containing the amount of wins each player has
  * @param maxWins the greatest amount of wins any player has had in this tournament
  */
  private Player whoWonTournament(int[] winsArr, int maxWins) {
    // The return statement in the loop should always trigger at least once
    for (int i = 0; i < winsArr.length; i++) {
      if (winsArr[i] == maxWins) {
        return getPlayer(i + 1);
      }
    }
    
    // Return null if it did not trigger: there is an error
    System.err.println("Error in whoWonTournament(int[], int)");
    return null;
  }
}