public class AlternateDoubloonSolution
{
  public static void main(String[] args)
  {
    String myWord = "intestines";
    System.out.println(isDoubloon(myWord));
  }


  public static boolean isDoubloon(String word)
  {
    // Get a table of letter occurrences in the word
    int[] letterTable = getLetterTable(word);

    // If any letter appears an amount of times that neither:
    // --> 2 times, or
    // --> 0 times (the letter does not appear)
    // ...then the word is not a doubloon
    for (int letterOccurrence : letterTable)
    {
      if (letterOccurrence != 2 && letterOccurrence != 0)
        return false;
    }

    // If the loop did not trigger the disqualifying condition, then the word
    // is a doubloon
    return true;
  }

  public static int[] getLetterTable(String word)
  {
    int letterIndex;
    int[] letterTable = new int[26];

    word = word.toLowerCase();

    // Count number of occurrences of each letter in the word
    for (char letter : word.toCharArray())
    {
      letterIndex = letter - 'a';
      letterTable[letterIndex]++;
    }

    return letterTable;
  }
}
