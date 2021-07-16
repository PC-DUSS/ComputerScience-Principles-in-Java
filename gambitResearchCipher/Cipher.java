/**
 * Program to decipher the quiz at https://gambitresearch.com/quiz/ .
 *
 * @author Pierre-Charles Dussault
 * @version 1.0
 * @since 07/07/2021
 */
package gambitResearchCipher;

import java.util.Arrays;

public class Cipher {

  public static void main(String[] args) {

    // Cleaned-up String for the cipher at https://gambitresearch.com/quiz/
    String cipher =
        "16 140 12 52 150 204 232 106 15 54 142 18 41 155 21 52 136 20 49 "
            + "150 14 59 71 6 55 153 192 59 150 12 62 144 14 47 71 20 48 140 "
            + "192 15 136 13 42 144 20 232 138 8 41 147 12 45 149 7 45 85 192 "
            + "24 147 5 41 154 5 232 154 5 54 139 192 65 150 21 58 71 19 55 "
            + "147 21 60 144 15 54 71 1 54 139 192 11 125 192 60 150 192 49 138 "
            + "1 54 138 15 44 140 224 47 136 13 42 144 20 58 140 19 45 136 18 "
            + "43 143 206 43 150 13 232 152 21 55 155 9 54 142 192 58 140 6 45 "
            + "153 5 54 138 5 2 71 213 248 88 2 248 96 5 1 92 5";

    int[][] secretParams = getSecretParametersImproved(cipher, "gambit");

    // This mimics the output of getSecretParametersImproved if you
    // want to save some time and not have to compute everything
    // int[][] secretParams = {{-56, 39, -96}, {0,0,0}, {0,0,0}, {0,0,0},
    // {0,0,0}, {0,0,0}, {0,0,0}, {0,0,0}, {0,0,0},
    // {0,0,0}};

    System.out.println("Plausible sets of secret keys:\n");
    display2DArray(secretParams);
    System.out.println("\nPossible secret messages:\n");
    String decodedMessage = descramble(cipher, secretParams);
    System.out.println(decodedMessage + "\n");
  }

  /**
   * Takes a message and 3 codifying parameters (secret keys), returns a cipher message.
   *
   * @param message String that you want to scramble.
   * @param a int, first secret key.
   * @param b int, second secret key.
   * @param c int, third secret key.
   * @return String, encrypted message.
   */
  public static String scramble(String message, int a, int b, int c) {

    String[] splitMessage = message.split("");
    String[] moddedMessage = codify(splitMessage, a, b, c);
    // Must be an array of Strings to work, not chars
    String cipher = String.join(" ", moddedMessage);

    return cipher;
  }

  /**
   * Takes an array of one-char sized Strings, and 3 codifying parameters (secret keys).
   *
   * @param message String[], an array of one-char Strings representing a message to codify.
   * @param a int, first secret key.
   * @param b int, second secret key.
   * @param c int, third secret key.
   * @return String[], an array of numbers representing the codified message.
   */
  public static String[] codify(String[] message, int a, int b, int c) {

    for (int i = 0; i < message.length; i++) {
      char currentChar = message[i].charAt(0);
      // Remember here, we need to return the codified char in String format,
      // not in char or int format
      message[i] = theSecretCode(currentChar, i, a, b, c);
    }
    return message;
  }

  /**
   * Takes a char, its attributed index and 3 codifying parameters (secret keys), returns the
   * codified int value of the char in single-char String format, modifying the codifying algorithm
   * based on its attributed index position.
   *
   * @param currentChar char, the current char to evaluate.
   * @param index int, the index to attribute to the codified char; will influence the algorithm
   *     used to codify the char.
   * @param a int, the first secret key.
   * @param b int, the second secret key.
   * @param c int, the third secret key.
   * @return The codified value of the char, as a String.
   */
  public static String theSecretCode(char currentChar, int index, int a, int b, int c) {

    // This is from the algorithm found in F12 mode inside the web browser on the page of the cipher

    long code = toUnicode(currentChar);

    if (index % 3 == 0) {
      return "" + ((code + a) % 256);
    } else if (index % 3 == 1) {
      return "" + ((code + b) % 256);
    }
    // Then, if (index % 3 == 2)
    return "" + ((code + c) % 256);
  }

  /**
   * Takes a char, returns the long unicode value of the char.
   *
   * @param ch char, the char for whom to get the unicode value from.
   * @return the unicode value of the char in long type format.
   */
  public static long toUnicode(char ch) {

    String unicodeHex = String.format("%04x", (int) ch);
    long unicodeLong = Long.parseLong(unicodeHex, 16);

    return unicodeLong;
  }

  /**
   * Takes a String cipher and a String keyword; test possible values for codifying parameters
   * (secret keys) a, b, c in the secret code, so that a matching keyword is found within the
   * cipher; returns an int array representing the secret keys in the following format: {a, b, c};
   * not actually used: see getSecretParametersImproved() just below.
   *
   * @param cipher String, the cipher to search.
   * @param keyword String, the keyword to search for in the cipher.
   * @return The secret keys that matched for the given keyword in the cipher, or an empty array if
   *     no matching keys were found.
   */
  public static int[] getSecretParameters(String cipher, String keyword) {

    for (int a = -100; a <= 100; a++) {
      for (int b = -100; b <= 100; b++) {
        for (int c = -100; c <= 100; c++) {
          if (cipher.contains(scramble(keyword, a, b, c))) {
            int[] secretParams = {a, b, c};
            return secretParams;
          }
        }
      }
    }
    // If no match is found, return an empty array
    int[] emptyParams = new int[0];
    return emptyParams;
  }

  /**
   * Takes a String cipher and a String keyword, test possible values for codifying parameters
   * (secret keys) a, b, c in the secret code, so that a matching keyword is found within the
   * cipher; Like getSecretParameters, but in case multiple arrays of parameter values match for the
   * given keyword (multiple plausible arrays of secret keys exist): this is the one I will be
   * using.
   *
   * @param cipher String, the cipher to search.
   * @param keyword String, the keyword to search for in the cipher.
   * @return an int[3] array representing plausible arrays of secret keys in the following format:
   *     {{a, b, c}, {a, b, c}, {a, b, c}, ...}; it is likely to only return one set of plausible
   *     keys, the other sub-arrays being only empty ones.
   */
  public static int[][] getSecretParametersImproved(String cipher, String keyword) {

    // Let's say a max of 10 possible sets of matching parameters...
    int[][] possibleParams = new int[10][3];
    int index = 0;

    for (int a = -100; a <= 100; a++) {
      for (int b = -100; b <= 100; b++) {
        for (int c = -100; c <= 100; c++) {
          if (cipher.contains(scramble(keyword, a, b, c))) {
            int[] secretParams = {a, b, c};
            possibleParams[index] = secretParams;
            index++;
          }
        }
      }
    }
    return possibleParams;
  }

  /**
   * Display a 2D array.
   *
   * @param arr int[][], 2D array to print to std.out.
   */
  public static void display2DArray(int[][] arr) {

    for (int i = 0; i < arr.length; i++) {
      System.out.println(Arrays.toString(arr[i]));
    }
  }

  /**
   * Descramble an encrypted message.
   *
   * @param cipher String, the encrypted message to decrypt.
   * @param paramArr int[][], 2D Array storing the plausible secret keys, each set of plausible keys
   *     being represented by each sub-array; it is likely to only contain one valid set of keys.
   * @return String, the descrambled message
   */
  public static String descramble(String cipher, int[][] paramArr) {

    String decodedMessage = "empty_string";
    int[] emptyArr = new int[paramArr[0].length];

    // For each set of matching parameters
    for (int i = 0; i < paramArr.length; i++) {
      // If the set is a valid set, and not just an empty set (... yes we
      // assume that a=0, b=0, c=0 or {0, 0, 0} is not an intentionally
      // valid set)
      if (!(Arrays.equals(paramArr[i], emptyArr))) {
        String[] hiddenMessageArr = cipher.split(" "); // Checked working
        String[] decodedMessageArr =
            decodeMessageArr(hiddenMessageArr, paramArr[i][0], paramArr[i][1], paramArr[i][2]);
        decodedMessage = String.join("", decodedMessageArr);
      }
    }
    return decodedMessage;
  }

  /**
   * Decode each char (single-char String to be more precise) within an Array of codified chars
   * (single-char Strings).
   *
   * @param hiddenMessageArr String[], an array of codified char values.
   * @param a int, the first secret key.
   * @param b int, the second secret key.
   * @param c int, the third secret key.
   * @return an array of one-char Strings, each String representing a Unicode char that was held in
   *     the array of coded chars.
   */
  public static String[] decodeMessageArr(String[] hiddenMessageArr, int a, int b, int c) {

    String[] decodedMessageArr = new String[hiddenMessageArr.length];

    for (int i = 0; i < hiddenMessageArr.length; i++) {
      int encryptedValue = Integer.parseInt(hiddenMessageArr[i]);

      if (i % 3 == 0) {
        // We can ignore the % 256 from the scramble function since all
        // characters used here have values under 256 in the UTF-16 table
        // This simplifies the algorithm to (unicodeValue + a) = encryptedValue
        int unicodeValue = encryptedValue - a;
        decodedMessageArr[i] = Character.toString((char) unicodeValue);
      } else if (i % 3 == 1) {
        int unicodeValue = encryptedValue - b;
        decodedMessageArr[i] = Character.toString((char) unicodeValue);
      }
      // Then, if (i % 3 == 2)
      else {
        int unicodeValue = encryptedValue - c;
        decodedMessageArr[i] = Character.toString((char) unicodeValue);
      }
    }
    return decodedMessageArr;
  }
}
