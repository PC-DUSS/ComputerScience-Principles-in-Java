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
    
    for (int i = 0; i < 99; i++) {
      Eights eights = new Eights();
      Player winner = eights.playGameFast();
      if (winner == eights.getPlayerOne()) {
        oneWins++;
      } else if (winner == eights.getPlayerTwo()) {
        twoWins++;
      } else {
        System.err.println("Error in playTournament()");
      }
    }
    
    if (oneWins > twoWins) {
      return getPlayerOne();
    } else if (twoWins > oneWins) {
      return getPlayerTwo();
      // Return null if it's a draw
    } else {
      return null;
    }
  }
}