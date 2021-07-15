/**
 * Class to explore the manipulation of arrays.
 *
 * July 2, 2021
 *
 * @author Pierre-Charles Dussault
 * @version 1.0
 * */
import java.util.Arrays;
import java.util.Random;


public class ArrayOperations
{
  public static void main(String[] args)
  {
    // Evaluate occurrences of random ints
    System.out.println("\n----EVALUATING RANDOM INTS----\n");
    final int ARR_SIZE = 50000;
    final int RAND_RANGE = 101;
    int[] randomsTable = generateRandomsTable(RAND_RANGE, ARR_SIZE);
    int[] occurrencesTable = generateOccurrencesTable(RAND_RANGE, randomsTable);
    // Display occurrences for all possible values
    for (int i = 0; i < RAND_RANGE; i++)
    {
      System.out.printf("%d was achieved %d times\n", i, occurrencesTable[i]);
    }

    // Evaluate primes
    System.out.println("\n----EVALUATING PRIMES----\n");
    final int ALGO_ARR_SIZE = 100;
    boolean[] whosPrime = sieve(ALGO_ARR_SIZE + 1);
    // Get a clean array of all the prime numbers
    int[] primesArray;
    primesArray = getArrayFromWhosPrime(whosPrime);
    // Now print out all the primes
    for (int prime : primesArray)
    {
      System.out.println(prime);
    }

    // Evaluate indexOfMax
    System.out.println("\n----EVALUATING INDEXOFMAX----\n");
    System.out.print(indexOfMax(primesArray) + " should be the same as ");
    System.out.println(24);

    // Evaluate factors
    System.out.println("\n----EVALUATING FACTORS----\n");
    int[] factorsOf100 = {2, 5, 10, 20, 50};
    int[] notFactorsOf100 = {2, 5, 10, 22, 55};
    System.out.println("should be true: " + areFactors(100, factorsOf100));
    System.out.println("should be false: " + areFactors(100, notFactorsOf100));

    // Evaluate letter histogram
    System.out.println("\n----EVALUATING LETTER HISTOGRAM----\n");
    int[] letterHist;
    letterHist = letterHist("mombosa");
    System.out.println(Arrays.toString(letterHist) + "\n");
    displayFormattedLetterHist(letterHist);
    // Test it using multiple words, separated by spaces
    System.out.println("\n----EVALUATING LETTER HISTOGRAM FOR SPACED WORDS "
                       + "(output should be 3x the values of the previous "
                       + "histogram)----\n");
    int[] spacedLetterHist;
    spacedLetterHist = letterHist("mombosa mombosa mombosa");
    System.out.println(Arrays.toString(spacedLetterHist) + "\n");
    displayFormattedLetterHist(spacedLetterHist);

    // Evaluate anagram, using letter histogram
    System.out.println("\n----EVALUATING ANAGRAM----\n");
    String originString = "christopher mayfield";
    String candidateString = "hi prof the camel is dry";
    System.out.println("should be true: "
                       + isAnagram(originString, candidateString));
  }


  public static int countOccurrencesInRange(int[] arr, int low, int high)
  {
    /*Count occurrences of values in a given range inside of an array.
     * We end up not using this function after optimizing the code*/
    int count = 0;

    for (int i = 0; i < arr.length; i++)
    {
      if (arr[i] >= low && arr[i] < high)
      {
        count++;
      }
    }

    return count;
  }

  public static int[] generateOccurrencesTable(int rangeEnd, int[] arr)
  {
    /*Makes the assumption that the range of values begins at 0*/
    int[] occurrencesTable = new int[rangeEnd];

    // This is like the improved version, but with a 'for each' loop instead
    for (int value : arr)
    {
      occurrencesTable[value]++;
    }

    // This is much faster than the old code. Only goes through the loop once
    //for (int i = 0; i < arr.length; i++)
    //{
      //int value = arr[i];
      //occurrencesTable[value]++;
    //}

    // OLD CODE
    //for (int i = 0; i < rangeEnd; i++)
    //{
      //occurrencesTable[i] = countOccurrencesInRange(arr, i, i+1);
    //}

    return occurrencesTable;
  }

  public static int[] generateRandomsTable(int maxRand, int arrSize)
  {
    Random rand = new Random();
    int[] randomsTable = new int[arrSize];

    for (int i = 0; i < randomsTable.length; i++)
    {
      randomsTable[i] = rand.nextInt(maxRand);
    }

    return randomsTable;
  }

  public static int indexOfMax(int[] arr)
  {
    int lastBiggestIndex = arr[0];

    for (int i = 1; i < arr.length; i++)
    {
      if (arr[i] > lastBiggestIndex)
      {
        lastBiggestIndex = i;
      }
    }

    return lastBiggestIndex;
  }

  public static boolean[] sieve(int n)
  {
    /* Accepts an int, and returns an array of booleans indicating which
     * numbers from 0 to n-1 are primes
     * */
    boolean[] whosComposite = new boolean[n];

    for (int i = 2; i <= Math.sqrt(n - 1); i++)
    {
      if (whosComposite[i] == false)
      {
        // Mark all multiples of current prime inside the array
        for (int j = i; j <= (n - 1) / i; j++)
          {
            whosComposite[i * j] = true;
          }
      }
    }

    return invertBoolArray(whosComposite);
  }

  public static boolean[] invertBoolArray(boolean[] array)
  {
    for (int i = 2; i < array.length; i++)
    {
      array[i] = !array[i];
    }

    return array;
  }

  public static int[] getArrayFromWhosPrime(boolean[] whosPrime)
  {
    int[] crudeArray;
    crudeArray = getCrudeArrayOfPrimes(whosPrime);
    // Remove trailing zeros
    int[] primesArray;
    primesArray = cleanArray(crudeArray);

    return primesArray;
  }

  public static boolean areFactors(int n, int[] factors)
  {
    for (int factor : factors)
    {
      if (n % factor != 0)
      {
        return false;
      }
    }

    return true;
  }

  public static boolean arePrimeFactors(int n, int[] factors)
  {
    boolean areAllFactors;
    boolean areAllPrimes;
    areAllFactors = areFactors(n, factors);
    areAllPrimes = false;

    return true;
  }

  public static int[] getCrudeArrayOfPrimes(boolean[] whosPrime)
  {
    int[] crudeArray = new int[whosPrime.length - 2];
    int index = 0;

    for (int i = 2; i < whosPrime.length; i++)
    {
      if (whosPrime[i])
      {
        crudeArray[index] = i;
        index++;
      }
    }

    return crudeArray;
  }

  public static int[] cleanArray(int[] crudeArray)
  {
    /* Remove trailing zeros after the last non-zero int inside the array
     * */
    int zeroCount;
    zeroCount = countTrailingZeroes(crudeArray);
    int actualNumOfPrimes = crudeArray.length - zeroCount;
    int[] cleanArray = new int[actualNumOfPrimes];

    for (int i = 0; i < actualNumOfPrimes; i++)
    {
      cleanArray[i] = crudeArray[i];
    }

    return cleanArray;
  }

  public static int countTrailingZeroes(int[] crudeArray)
  {
    int count = 0;
    for (int i = crudeArray.length - 1; i >= 0; i--)
    {
      if (crudeArray[i] == 0)
      {
        count++;
      }
      else
      {
        break;
      }
    }

    return count;
  }

  public static int[] letterHist(String str)
  {
    int index;
    int[] letterHist = new int[26];
    String lowerStr = str.toLowerCase();

    for (int i = 0; i < lowerStr.length(); i++)
    {
      // Check if it is a space
      if (lowerStr.charAt(i) == ' ')
      {
        continue;
      }

      index = lowerStr.charAt(i) - 'a';
      letterHist[index]++;
    }

    return letterHist;
  }

  public static void displayFormattedLetterHist(int[] letterHist)
  {
    for (int i = 0; i < letterHist.length; i++)
    {
      System.out.printf("letter \'%c\' appears %d times\n", i + 'a',
                                                           letterHist[i]);
    }
  }

  public static boolean isAnagram(String originString, String candidateString)
  {
    int[] originLetterHist;
    originLetterHist = letterHist(originString);
    int[] candidateLetterHist;
    candidateLetterHist = letterHist(candidateString);

    if (Arrays.equals(originLetterHist, candidateLetterHist))
    {
      return true;
    }
    else
    {
      return false;
    }
  }
}
