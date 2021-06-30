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
    String subMessage3 = message.substring(3);  // Starts on [3] Ends on end of String
    System.out.println(subMessage3);

    // Compare Strings for alphabetical order
    String name1 = "Alan Turing";
    String name2 = "Ada Lovelace";
    int diff = name1.compareTo(name2);
    System.out.println(diff);
    if (diff < 0)
    {
      System.out.println("name1 comes before name2.");
    }
    else if (diff > 0)
    {
      System.out.println("name2 comes before name1");
    }
    else
    {
      System.out.println("The names are the same.");
    }
    // The return value from compareTo is the difference between the first
    // characters in the strings that are not the same. In the preceding code,
    // compareTo returns positive 8, because the second letter of "Ada" comes
    // before the second letter of "Alan" by eight letters.
  }


  public static String reverseString(String str)
  {
    String reversed = "";
    for(int i = str.length() - 1; i >= 0; i--)
      reversed += str.charAt(i);

    return reversed;
  }
}
