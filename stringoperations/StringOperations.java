


public class StringOperations
{
  public static void main(String[] args)
  {
    // This is a good way to introduce bugs if you are not careful.
    // The operations are executed from left-to-right, except for mathematical
    // conventions.
    System.out.println(1 + 2 + "Hello");
    System.out.println("Hello" + 1 + 2);

    String message = "Hello, World!";
    message = reverseString(message);
    System.out.println(message);

    // Find the index of the first appearance of the letter 'o'
    int indexOf_o = message.indexOf('o');
    System.out.println(indexOf_o);

    // Find the index of 'o', but for its second appearance
    int indexOf_o_2 = message.indexOf('o', 5);  // Begin search at index: 5
    System.out.println(indexOf_o_2);
    // Note: indexOf will return -1 if no such char is found within the String
    
    // You can also search for Strings inside a String
    int indexOf_String = message.indexOf("lro");
    System.out.println(indexOf_String);
    // This return the index of the start of the String where found
  
    // substring() may also be useful. It is an 'overloaded' method
    String subMessage1 = message.substring(0, 3);  // Ends on [2]
    System.out.println(subMessage1);
    String subMessage2 = message.substring(2, 5);  // Starts on [2] Ends on [4]
    System.out.println(subMessage2);
    // See how its usage is different here
    String subMessage3 = message.substring(3);  // Starts on [3] Ends on end
                                                // of String
    System.out.println(subMessage3);

    // Compare Strings for alphabetical order
    String name1 = "Alan Turing";
    String name2 = "Ada Lovelace";
    int diff = name1.compareTo(name2);
    System.out.println(diff);
    if (diff < 0)
    {
      System.out.println("name1 comes before name2\n");
    }
    else if (diff > 0)
    {
      System.out.println("name2 comes before name1\n");
    }
    else
    {
      System.out.println("The names are the same\n");
    }
    // The return value from compareTo is the difference between the first
    // characters in the strings that are not the same. In the preceding code,
    // compareTo returns positive 8, because the second letter of "Ada" comes
    // before the second letter of "Alan" by eight letters.
    
    String sampleAbecedarian = "acknow";
    String sampleNotAbecedarian = "diggity";
    String sampleCapitalizedAbecedarian = "AcKNoW";
    String sampleNumberedAbecedarian = "123AcKNoW";

    System.out.println(isAbecedarian(sampleAbecedarian));
    System.out.println(isAbecedarian(sampleNotAbecedarian));
    System.out.println(isAbecedarian(sampleCapitalizedAbecedarian));
    // Should not be acceptedas input, should throw error
    //System.out.println(isAbecedarian(sampleNumberedAbecedarian));

    // Notice the initialization of the String, how it differs from usual
    // Strings are objects. Java just makes it easier for us to create them
    // by providing a shortcut as if it were a Primitive, but it is not. It is
    // an Object
    String sampleDoubloon = new String("intestines");
    String wrongDoubloon = new String("HelloWorld");
    String numberedDoubloon = new String("int2estin2es");

    System.out.println(isDoubloon(sampleDoubloon));
    System.out.println(isDoubloon(wrongDoubloon));
    // Should not be acceptedas input, should throw error
    //System.out.println(isDoubloon(numberedDoubloon));
    
    String sampleLetterBank = "quijibo";
    String sampleMadeWord = "jibi";

    System.out.println(canSpell(sampleLetterBank, sampleMadeWord));
  }


  public static String reverseString(String str)
  {
    String reversed = "";
    for(int i = str.length() - 1; i >= 0; i--)
      reversed += str.charAt(i);

    return reversed;
  }

  public static boolean isAbecedarian(String str) 
      throws IllegalArgumentException
  {
    // Initial char to get the loop running
    char lastChar = 'A';

    for (int x = 0; x < str.length(); x++)
    {
      // Lowercase all chars to avoid comparing uppercase with lowercase chars
      char currentChar = Character.toLowerCase(str.charAt(x));

      // Check that all chars are latin alphabet letters
      if (currentChar < 'a' || currentChar > 'z')
      {
        String errorMessage = String.format(
            "Invalid word \"%s\". String must only contain latin alphabet "
            + "letters.", str);
        throw new IllegalArgumentException(errorMessage);
      }

      // If currentChar comes before lastChar in alphabetical order
      if (Character.compare(currentChar, lastChar) < 0)
      {
        return false;
      }

      // Prepare to evaluate the next letter
      lastChar = currentChar;
    }

    // If the loop completed without returning, then the word is an abecedarian
    return true;
  }

  public static boolean isDoubloon(String str) throws IllegalArgumentException
  {
    char currentChar;
    char comparedChar;
    char checkedChars[] = new char[26];
    int count;

    for (int currentCharIndex = 0;
         currentCharIndex < str.length();
         currentCharIndex++)
    {
      currentChar = str.toLowerCase().charAt(currentCharIndex);

      // Check if currentChar has already been checked
      if (isPartOfArray(currentChar, checkedChars))
        continue;

      // Initial check so that all chars are latin alphabet letters
      if (currentChar < 'a' || currentChar > 'z')
      {
        String errorMessage = String.format(
            "Invalid word \"%s\". String must only contain latin alphabet "
            + "letters.", str);
        throw new IllegalArgumentException(errorMessage);
      }

      count = countCharOccurence(currentChar, str);

      if (count != 2)
        return false;

      checkedChars[currentCharIndex] = currentChar;
    }
    
    // If the loop completed without returning, then the word is a doubloon
    return true;
  }

  public static boolean isPartOfArray(char theChar, char[] array)
  {
    /*
    Other shorter ways exist to do this with preexisting methods in Java,
    namely converting to List then using contains(), but the point here is
    to practice problem solving using basic tools of the language. Data
    structures will come later
    */
    for (int i = 0; i < array.length; i++)
    {
      if (theChar == array[i])
        return true;
    }

    return false;
  }

  public static boolean canSpell(String letterBank, String madeWord)
  {
    char currentChar;
    char checkedChars[] = new char[26];
    int countInWord;
    int countInBank;

    // For each letter inside the formed word
    for (int wordIndex = 0; wordIndex < madeWord.length(); wordIndex++)
    {
      currentChar = madeWord.charAt(wordIndex);

      // Check if it has already been checked
      if (isPartOfArray(currentChar, checkedChars))
        continue;

      countInWord = countCharOccurence(currentChar, madeWord);
      countInBank = countCharOccurence(currentChar, letterBank);

      if (countInWord > countInBank)
        return false;

      checkedChars[wordIndex] = currentChar;
    }

    return true;
  }

  public static int countCharOccurence(char myChar, String myString)
  {
    int count = 0;

    for (int i = 0; i < myString.length(); i++)
    {
      if (myString.charAt(i) == myChar)
        count++;
    }

    return count;
  }
}
