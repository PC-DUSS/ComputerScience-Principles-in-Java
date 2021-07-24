import java.util.Arrays;

/**
 * Big exercise summarizing class design from Chapter 11.
 *
 * @author Pierre-Charles Dussault
 * @since 2021-07-23
 * @version 1.0
 */
public class Rational {

  private int numerator;
  private int denominator;

  /* ----- Main Program ----- */
  public static void main(String[] args) {

    Rational myRational = new Rational(5, 12);
    myRational.printRational();
    System.out.println(myRational);
    myRational.negate();
    myRational.printRational();
    myRational.invert();
    myRational.printRational();
    runTests();
  }

  /** Default constructor; initializes defaults as numerator to 0 and denominator to 1. */
  public Rational() {

    this.numerator = 0;
    this.denominator = 1;
  }

  /**
   * Value constructor.
   *
   * @param numerator int value for the numerator of the rational number
   * @param denominator int value for the denominator of the rational number
   */
  public Rational(int numerator, int denominator) throws IllegalArgumentException {

    if (denominator == 0) {
      throw new IllegalArgumentException("Cannot use 0 as a denominator.");
    }

    this.numerator = numerator;
    this.denominator = denominator;
  }

  /* ----- Getters and Setters ----- */
  public int getNumerator() {

    return this.numerator;
  }

  public int getDenominator() {

    return this.denominator;
  }

  public void setNumerator(int newNumerator) {

    this.numerator = newNumerator;
  }

  public void setDenominator(int newDenominator) {

    this.denominator = newDenominator;
  }

  /**
   * Instance method to print the attributes of an instance of the Rational class in neatly
   * formatted output to std_out.
   */
  public void printRational() {

    System.out.printf("Numerator: %d\nDenominator: %d\n", this.numerator, this.denominator);
  }

  /** Default method override. */
  public String toString() {

    return String.format("Numerator: %d\nDenominator: %d", numerator, denominator);
  }

  /**
   * Instance method to reverse the sign of a rational number, instance of the Rational class, more
   * precisely by reversing the numerator's sign.
   */
  public void negate() {

    this.numerator *= -1;
  }

  /**
   * Instance method to invert (switch) the numerator and denominator of a rational number, instance
   * of the Rational class.
   */
  public void invert() throws ArithmeticException {

    if (this.numerator == 0) {
      throw new ArithmeticException("Cannot invert '0' numerator to the denominator position.");
    }

    int tmp = this.numerator;
    this.numerator = this.denominator;
    this.denominator = tmp;
  }

  /**
   * Instance method to convert a rational number instance of the Rational class to floating-point
   * number of double type.
   *
   * @return the value of the rational number expressed as floating-point number of double type
   */
  public double toDouble() {

    return ((double) this.getNumerator() / (double) this.getDenominator());
  }

  /**
   * Instance method to reduce a rational number to an equivalent rational number by calculating the
   * Greatest Common Divisor (GCD); it will apply the same calculation to the numerator in order to
   * obtain a number that is equivalent in value.
   *
   * @return a new rational number instance of the Rational class with equivalent value to the
   *     original caller instance.
   */
  public Rational reduce() {

    // Case the initial Rational is a version of '0' (zero)
    if (this.getNumerator() == 0) return new Rational();

    // Check which value is smaller and which is bigger
    int num = this.getNumerator();
    int denom = this.getDenominator();
    int bigger;
    int smaller;
    if (num > denom) {
      bigger = num;
      smaller = denom;
    } else {
      bigger = denom;
      smaller = num;
    }

    // Remember to call this function with the smaller value first
    int gcd = getGreatestCommonDivisor(smaller, bigger);

    // If a valid greatest common divisor exists
    if (gcd != 1) {
      // Reduce the numerator and denominator by that divisor
      int newNumerator = this.getNumerator() / gcd;
      int newDenominator = this.getDenominator() / gcd;
      // And return a new Rational with these new values
      return new Rational(newNumerator, newDenominator);
    }

    // If no GCD was found other than 1, return the initial caller instance with no modification
    return this;
  }

  /**
   * Instance method to reduce a rational number to an equivalent rational number by dividing its
   * numerator and denominator by a given divisor.
   *
   * @param divisor int number by which to divide the numerator and denominator
   * @return a new rational number instance of the Rational class with equivalent value to the
   *     original caller instance
   */
  public Rational reduce(int divisor) throws IllegalArgumentException {

    // Raise an exception if the divisor is invalid
    if (divisor == 0) {
      throw new IllegalArgumentException("Cannot divide by 0.");
    } else if (this.getNumerator() % divisor != 0 || this.getDenominator() % divisor != 0) {
      throw new IllegalArgumentException("Invalid divisor for reduce(int divisor).");
    }

    // The new values of the reduced Rational
    int newNumerator = this.getNumerator() / divisor;
    int newDenominator = this.getDenominator() / divisor;

    return new Rational(newNumerator, newDenominator);
  }

  /**
   * Add two Rational numbers together.
   *
   * @param other Rational the Rational number to add to the caller instance of Rational
   * @return a Rational number which is the sum of the caller and the one passed as argument; the
   *     resulting Rational number is reduced such that there exists no common divisor between the
   *     numerator and denominator other than 1
   */
  public Rational add(Rational other) {

    if (this.getNumerator() == 0 && other.getNumerator() == 0) {
      return new Rational();
    } else if (this.getNumerator() == 0) {
      return other.reduce();
    } else if (other.getNumerator() == 0) {
      return this.reduce();
    }

    // Get the least common multiple for the two denominators
    int leastCommonMultiple = getLeastCommonMultiple(this.getDenominator(), other.getDenominator());

    // Augment each Rational to match denominators with the least common multiple
    Rational newThis = this.augment(leastCommonMultiple / this.getDenominator());
    Rational newOther = other.augment(leastCommonMultiple / other.getDenominator());

    // The 2 Rational numbers now have the same denominator. Get the sum of their numerators
    int numeratorSum = newThis.getNumerator() + newOther.getNumerator();

    // Return the summed Rationals as a new Rational in reduced form
    Rational sum = new Rational(numeratorSum, leastCommonMultiple);
    return sum.reduce();
  }

  /**
   * Augment a Rational number by multiplying its numerator and denominator by a given number.
   *
   * @param n int number by which to multiply the numerator and denominator
   * @return a new Rational instance representing the augmented initial Rational number
   */
  public Rational augment(int n) {

    // Throw exception if n is 0
    if (n == 0) {
      throw new IllegalArgumentException("Cannot make a denominator equal to 0.");
    }

    return new Rational(this.getNumerator() * n, this.getDenominator() * n);
  }

  /* ----- Helper methods ----- */

  /**
   * Get the greatest common divisor for two ints with the smaller number as first argument, and the
   * bigger number as second argument.
   *
   * @param smaller int the first number which must be the smallest of the two
   * @param bigger int the second number which must be the biggest of the two
   * @return an int representing the greatest common divisor for both inital numbers
   */
  public static int getGreatestCommonDivisor(int smaller, int bigger)
      throws IllegalArgumentException {

    // If either or both of smaller and bigger are equal to 0
    if (smaller == 0 && bigger == 0) {
      throw new IllegalArgumentException(
          "Cannot find greatest common divisor between both 0 and 0.");
    } else if (smaller == 0) {
      return bigger;
    } else if (bigger == 0) {
      return -smaller;
    }

    for (int i = smaller; i > 1; i--) {
      if (bigger % i == 0 && smaller % i == 0) {
        return i;
      }
    }

    // In case no common divisor was found, return 1
    return 1;
  }

  /**
   * Get the least common multiple for two integers.
   *
   * @param a int the first number
   * @param b int the second number
   * @return the least common multiple as an integer
   */
  public static int getLeastCommonMultiple(int a, int b) {

    // If any of the numbers are equal to 0, then their LCM is 0
    if (a == 0 || b == 0) return 0;

    // If either number is prime, return the product of both numbers
    if (isPrime(a) || isPrime(b)) return a * b;

    // Otherwise go on with the algorithm
    int smallest;
    int biggest;

    // Determine the smallest and biggest absolute value out of the two parameters
    if (Math.abs(a) < Math.abs(b)) {
      smallest = Math.abs(a);
      biggest = Math.abs(b);
    } else {
      smallest = Math.abs(b);
      biggest = Math.abs(a);
    }

    // Traverse all the possible least common multiples for the two numbers
    for (int i = 1; i < smallest; i++) {
      if ((smallest * i) % biggest == 0) {
        return smallest * i;
      } else if ((biggest * i) % smallest == 0) {
        return biggest * i;
      }
    }

    // If nothing triggered a return statement, return smallest * biggest
    return smallest * biggest;
  }

  /**
   * Get an array of all the prime factors for a number; not used, but left behind because this is a
   * learning exercise.
   *
   * @param num int number for which to find all prime factors
   * @return an array of all the priem factors for the given number
   */
  public static int[] getPrimeFactors(int num) throws IllegalArgumentException {

    if (num == 1) throw new IllegalArgumentException("1 has no factors other than itself.");
    else if (num == 0) throw new IllegalArgumentException("0 has infinite factors.");

    final int arrSize = 20;
    int[] primeFactors = new int[arrSize];
    int index = 0;
    int largestFactor = num;
    // Initialize tmp to arbitrary value
    int tmp = 1;

    // Find the duo of factors containing the second largest value after num itself
    while (true) {
      // If the new largest factor is prime
      if (isPrime(largestFactor)) {
        // Add it to the list of prime factors and exit the loop
        primeFactors[index] = largestFactor;
        break;
      }

      // Go through all possible factors of the new largest factor
      for (int i = 2; i < largestFactor; i++) {
        // If the current number is a factor of the new largest factor
        if (largestFactor % i == 0) {
          // Save it as contender for the new largest factor
          tmp = i;
        }
      }

      // Add the new largest factor's corresponding factor to the list of prime factors for the
      // original number
      primeFactors[index] = largestFactor / tmp;
      // Save the last saved contender as the new largest factor
      largestFactor = tmp;
      // Increment the index to which to add a factor in the array primeFactors for the next
      // iteration
      index++;
    }

    return primeFactors;
  }

  /**
   * Check if a number is prime or not.
   *
   * @param num int number to check if is prime
   * @return true of false, depending on if the number is prime or not
   */
  public static boolean isPrime(int num) {

    num = Math.abs(num);
    for (int i = 2; i < num; i++) {
      if (num % i == 0) return false;
    }

    return true;
  }

  /**
   * Check if an array contains only zeroes; not used but kept since this is a learning exercise.
   *
   * @param arr int[] array of ints
   * @return true or false, depending on if the array contains only zeroes or not
   */
  public static boolean isOnlyZeroes(int[] arr) {

    for (int i = 0; i < arr.length; i++) {
      if (arr[i] != 0) return false;
    }

    return true;
  }

  /* ----- TESTS ----- */

  public static void constructorTest() {

    System.out.println("Testing constructors:");

    Rational defaultConstructed = new Rational();
    if (defaultConstructed.getNumerator() == 0 && defaultConstructed.getDenominator() == 1) {
      System.out.println("\tCase default constructor: pass");
    } else {
      System.out.println("\tCase default constructor: FAIL!!!");
    }

    Rational valueConstructed = new Rational(5, 12);
    if (valueConstructed.getNumerator() == 5 && valueConstructed.getDenominator() == 12) {
      System.out.println("\tCase value constructor: pass");
    } else {
      System.out.println("\tCase value constructor: FAIL!!!");
    }

    Exception exc = new Exception();
    try {
      Rational illegalRational = new Rational(1, 0);
    } catch (Exception e) {
      exc = e;
    }

    if (exc.getMessage() == "Cannot use 0 as a denominator.") {
      System.out.println("\tCase value constructor zero denominator: pass");
    } else {
      System.out.println("\tCase value constructor zero denominator: FAIL!!!");
    }
  }

  public static void negateTest() {

    System.out.println("Testing negate:");

    Rational toNegate = new Rational(5, 1);
    Rational negated = new Rational(-5, 1);
    Rational zero = new Rational();
    Rational zeroNegated = new Rational();
    toNegate.negate();
    zeroNegated.negate();

    if (toNegate.getNumerator() == negated.getNumerator()
        && toNegate.getDenominator() == negated.getDenominator()) {
      System.out.println("\tCase non-zero: pass");
    } else {
      System.out.println("\tCase non-zero: FAIL!!!");
    }

    if (zero.getNumerator() == zeroNegated.getNumerator()
        && zero.getDenominator() == zeroNegated.getDenominator()) {
      System.out.println("\tCase zero: pass");
    } else {
      System.out.println("\tCase zero: FAIL!!!");
    }
  }

  public static void invertTest() {

    System.out.println("Testing invert:");

    Rational toInvert = new Rational(5, 12);
    Rational inverted = new Rational(12, 5);
    Rational zero = new Rational();
    toInvert.invert();

    if (toInvert.getNumerator() == inverted.getNumerator()
        && toInvert.getDenominator() == inverted.getDenominator()) {
      System.out.println("\tCase non-zero: pass");
    } else {
      System.out.println("\tCase non-zero: FAIL!!!");
    }

    // Prepare in case calling invert on 'case zero' raises an Exception, as it should
    Exception exc = new Exception();
    try {
      zero.invert();
    } catch (Exception e) {
      exc = e; // Store exception
    }

    if (exc.getMessage() == "Cannot invert '0' numerator to the denominator position.") {
      System.out.println("\tCase zero: pass");
    } else {
      System.out.println("\tCase zero: FAIL!!!");
    }
  }

  public static void toDoubleTest() {

    System.out.println("Testing toDouble:");

    // Tolerable uncertainty
    final double DELTA = 0.0005;
    Rational willBeToDouble = new Rational(1, 4);
    Rational zero = new Rational();

    if (willBeToDouble.toDouble() - 0.25 < DELTA) {
      System.out.println("\tCase non-zero: pass");
    } else {
      System.out.println("\tCase non-zero: FAIL!!!");
    }

    if (zero.toDouble() - 0.0 < DELTA) {
      System.out.println("\tCase zero: pass");
    } else {
      System.out.println("\tCase zero: FAIL!!!");
    }
  }

  public static void reduceTest() {

    System.out.println("Testing reduce:");

    Rational toBeReduced = new Rational(8, 12);
    Rational expected = new Rational(2, 3);
    Rational reduced = toBeReduced.reduce();
    Rational zero = new Rational(0, 10);
    Rational zeroReduced = zero.reduce();
    Rational zeroExpected = new Rational();
    Rational toBeReducedWithDivisor = new Rational(8, 12);
    int divisor = -2;
    Rational expectedWithDivisor = new Rational(-4, -6);
    Rational reducedWithDivisor = toBeReducedWithDivisor.reduce(divisor);
    int invalidDivisor = 5;

    if (reduced.getNumerator() == expected.getNumerator()
        && reduced.getDenominator() == expected.getDenominator()) {
      System.out.println("\tCase non-zero: pass");
    } else {
      System.out.println("\tCase non-zero: FAIL!!!");
    }

    if (zeroReduced.getNumerator() == zeroExpected.getNumerator()
        && zeroReduced.getDenominator() == zeroExpected.getDenominator()) {
      System.out.println("\tCase zero: pass");
    } else {
      System.out.println("\tCase zero: FAIL!!!");
    }

    if (reducedWithDivisor.getNumerator() == expectedWithDivisor.getNumerator()
        && reducedWithDivisor.getDenominator() == expectedWithDivisor.getDenominator()) {
      System.out.println("\tCase with argument divisor: pass");
    } else {
      System.out.println("\tCase with argument divisor: pass");
    }

    Exception invalidDivisorExc = new Exception();
    try {
      Rational invalidReduced = toBeReducedWithDivisor.reduce(invalidDivisor);
    } catch (Exception e) {
      invalidDivisorExc = e;
    }

    if (invalidDivisorExc.getMessage() == "Invalid divisor for reduce(int divisor).") {
      System.out.println("\tCase with invalid argument divisor: pass");
    } else {
      System.out.println("\tCase with invalid argument divisor: FAIL!!!");
    }

    Exception zeroDivisorExc = new Exception();
    try {
      Rational reducedByZero = toBeReducedWithDivisor.reduce(0);
    } catch (Exception e) {
      zeroDivisorExc = e;
    }

    if (zeroDivisorExc.getMessage() == "Cannot divide by 0.") {
      System.out.println("\tCase with argument divisor = 0: pass");
    } else {
      System.out.println("\tCase with argument divisor = 0: FAIL!!!");
    }
  }

  public static void getGreatestCommonDivisorTest() {

    System.out.println("Testing getGreatestCommonDivisor:");

    int smaller = 8;
    int bigger = 12;
    int expected = 4;
    int gcd = getGreatestCommonDivisor(8, 12);
    int zeroAndPositive = getGreatestCommonDivisor(0, 15);
    int zeroAndPositiveExpected = 15;
    int negativeAndZero = getGreatestCommonDivisor(-9, 0);
    int negativeAndZeroExpected = 9;

    if (gcd == expected) {
      System.out.println("\tCase two positives: pass");
    } else {
      System.out.println("\tCase two positives: FAIL!!!");
    }

    if (zeroAndPositive == zeroAndPositiveExpected) {
      System.out.println("\tCase zero and a positive: pass");
    } else {
      System.out.println("\tCase zero and a positive: FAIL!!!");
    }

    if (negativeAndZero == negativeAndZeroExpected) {
      System.out.println("\tCase a negative and zero: pass");
    } else {
      System.out.println("\tCase a negative and zero: FAIL!!!");
    }

    // Finally, the case where, for some reason, two zeroes are given to the function
    Exception exc = new Exception();
    try {
      int twoZeroes = getGreatestCommonDivisor(0, 0);
    } catch (Exception e) {
      exc = e;
    }

    if (exc.getMessage() == "Cannot find greatest common divisor between both 0 and 0.") {
      System.out.println("\tCase two zeroes: pass");
    } else {
      System.out.println("\tCase two zeroes: FAIL!!!");
    }
  }

  public static void addTest() {

    System.out.println("Testing add:");

    Rational thisRational = new Rational(9, 12);
    Rational otherRational = new Rational(-17, 7);
    Rational sumOfThisAndOther = thisRational.add(otherRational);
    Rational zero1 = new Rational(0, 5);
    Rational zero2 = new Rational(0, 13);
    Rational sumWithZero = thisRational.add(zero1);
    Rational sumOfTwoZeroes = zero1.add(zero2);
    Rational expected = new Rational(-141, 84);
    Rational expectedWithZero = new Rational(3, 4);
    Rational expectedWithTwoZeroes = new Rational();

    if (sumOfThisAndOther.getNumerator() == expected.getNumerator()
        && sumOfThisAndOther.getDenominator() == expected.getDenominator()) {
      System.out.println("\tCase non-zero: pass");
    } else {
      System.out.println("\tCase non-zero: FAIL!!!");
    }

    if (sumWithZero.getNumerator() == expectedWithZero.getNumerator()
        && sumWithZero.getDenominator() == expectedWithZero.getDenominator()) {
      System.out.println("\tCase zero: pass");
    } else {
      System.out.println("\tCase zero: FAIL!!!");
    }

    if (sumOfTwoZeroes.getNumerator() == expectedWithTwoZeroes.getNumerator()
        && sumOfTwoZeroes.getDenominator() == expectedWithTwoZeroes.getDenominator()) {
      System.out.println("\tCase two zeroes: pass");
    } else {
      System.out.println("\tCase two zeroes: FAIL!!!");
    }
  }

  public static void getLeastCommonMultipleTest() {

    System.out.println("Testing getLeastCommonMultiple:");

    int comp1 = 12;
    int comp2 = 8;
    int prime = 7;
    int lcmComposites = getLeastCommonMultiple(comp1, comp2);
    int lcmWithPrime = getLeastCommonMultiple(comp1, prime);
    int lcmWithZero = getLeastCommonMultiple(0, 5);
    // the least common multiple should always be a positive number
    int lcmNegatives = getLeastCommonMultiple(comp1, -comp2);
    int expectedComposites = 24;
    int expectedWithPrime = 84;
    int expectedWithZero = 0;

    if (lcmComposites == expectedComposites) {
      System.out.println("\tCase composites: pass");
    } else {
      System.out.println("\tCase composites: FAIL!!!");
    }

    if (lcmWithPrime == expectedWithPrime) {
      System.out.println("\tCase with prime: pass");
    } else {
      System.out.println("\tCase with prime; FAIL!!!");
    }

    if (lcmWithZero == expectedWithZero) {
      System.out.println("\tCase with zero: pass");
    } else {
      System.out.println("\tCase with zero: FAIL!!!");
    }
  }

  public static void isPrimeTest() {

    System.out.println("Testing isPrime:");

    int two = 2;
    int prime = 67;
    int nonPrime = 63;
    int negativePrime = -67;
    int negativeNonPrime = -63;

    if (isPrime(prime)) {
      System.out.println("\tCase prime: pass");
    } else {
      System.out.println("\tCase prime: FAIL!!!");
    }

    if (!(isPrime(nonPrime))) {
      System.out.println("\tCase non-prime: pass");
    } else {
      System.out.println("\tCase non-prime: FAIL!!!");
    }

    if (isPrime(negativePrime)) {
      System.out.println("\tCase negative prime: pass");
    } else {
      System.out.println("\tCase negative prime: FAIL!!!");
    }

    if (!(isPrime(negativeNonPrime))) {
      System.out.println("\tCase negative non-prime: pass");
    } else {
      System.out.println("\tCase negative non-prime: FAIL!!!");
    }

    if (isPrime(two)) {
      System.out.println("\tCase two: pass");
    } else {
      System.out.println("\tCase two: FAIL!!!");
    }
  }

  public static void augmentTest() {

    System.out.println("Testing augment:");

    Rational valueConstructed = new Rational(5, -7);
    Rational defaultConstructed = new Rational();
    int positive = 6;
    int negative = -6;
    Rational augmentedPositive = valueConstructed.augment(positive);
    Rational augmentedNegative = valueConstructed.augment(negative);
    // This is arbitrary, could have chosen positive instead
    Rational zeroAugmented = defaultConstructed.augment(negative);
    Rational expectedPositive = new Rational(30, -42);
    Rational expectedNegative = new Rational(-30, 42);
    Rational zeroExpected = new Rational(0, -6);

    if (augmentedPositive.getNumerator() == expectedPositive.getNumerator()
        && augmentedPositive.getDenominator() == expectedPositive.getDenominator()) {
      System.out.println("\tCase positive: pass");
    } else {
      System.out.println("\tCase positive: FAIL!!!");
    }

    if (augmentedNegative.getNumerator() == expectedNegative.getNumerator()
        && augmentedNegative.getDenominator() == expectedNegative.getDenominator()) {
      System.out.println("\tCase negative: pass");
    } else {
      System.out.println("\tCase negative: FAIL!!!");
    }

    if (zeroAugmented.getNumerator() == zeroExpected.getNumerator()
        && zeroAugmented.getDenominator() == zeroExpected.getDenominator()) {
      System.out.println("\tCase zero augmented: pass");
    } else {
      System.out.println("\tCase zero augmented: FAIL!!!");
    }

    Exception exc = new Exception();
    try {
      valueConstructed.augment(0);
    } catch (Exception e) {
      exc = e;
    }

    if (exc.getMessage() == "Cannot make a denominator equal to 0.") {
      System.out.println("\tCase augmented by zero: pass");
    } else {
      System.out.println("\tCase augmented by zero: FAIL!!!");
    }
  }

  public static void isOnlyZeroesTest() {

    System.out.println("Testing isOnlyZeroes:");

    int[] arrEmpty = new int[20];
    int[] arrFull = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
    int[] arrNotFull = {0, 0, 0, 0, 0, 0, 7, 8, 9, 10, 11, 0, 13, 14, 0, 16, 17, 18, 19, 20};

    if (isOnlyZeroes(arrEmpty)) {
      System.out.println("\tCase empty: pass");
    } else {
      System.out.println("\tCase empty: FAIL!!!");
    }

    if (!(isOnlyZeroes(arrFull))) {
      System.out.println("\tCase full: pass");
    } else {
      System.out.println("\tCase full: FAIL!!!");
    }

    if (!(isOnlyZeroes(arrNotFull))) {
      System.out.println("\tCase not full: pass");
    } else {
      System.out.println("\tCase not full: FAIL!!!");
    }
  }

  public static void getPrimeFactorsTest() {

    System.out.println("Testing getPrimeFactors:");

    int composite = 64;
    int prime = 67;
    int[] expectedComposite = {2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    int[] expectedPrime = {prime, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    if (Arrays.equals(getPrimeFactors(composite), expectedComposite)) {
      System.out.println("\tCase composite: pass");
    } else {
      System.out.println("\tCase composite: FAIL!!!");
    }

    if (Arrays.equals(getPrimeFactors(prime), expectedPrime)) {
      System.out.println("\tCase prime: pass");
    } else {
      System.out.println("\tCase prime: FAIL!!!");
    }

    Exception zeroExc = new Exception();
    try {
      getPrimeFactors(0);
    } catch (Exception e) {
      zeroExc = e;
    }

    if (zeroExc.getMessage() == "0 has infinite factors.") {
      System.out.println("\tCase zero: pass");
    } else {
      System.out.println("\tCase zero: FAIL!!!");
    }

    Exception oneExc = new Exception();
    try {
      getPrimeFactors(1);
    } catch (Exception e) {
      oneExc = e;
    }

    if (oneExc.getMessage() == "1 has no factors other than itself.") {
      System.out.println("\tCase one: pass");
    } else {
      System.out.println("\tCase one: FAIL!!!");
    }
  }

  public static void runTests() {

    System.out.println("Running tests.");

    constructorTest();
    System.out.println();

    negateTest();
    System.out.println();

    invertTest();
    System.out.println();

    toDoubleTest();
    System.out.println();

    reduceTest();
    System.out.println();

    getGreatestCommonDivisorTest();
    System.out.println();

    addTest();
    System.out.println();

    getLeastCommonMultipleTest();
    System.out.println();

    isPrimeTest();
    System.out.println();

    augmentTest();
    System.out.println();

    isOnlyZeroesTest();
    System.out.println();

    getPrimeFactorsTest();
    System.out.println();
  }
}
