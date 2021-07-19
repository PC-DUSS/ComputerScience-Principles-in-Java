import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        double[] a = {1.0, 1.5, 2.0, 2.8, 3.3, 4.1, 4.9, 5.4, 6.2, 7.7};
        double[] b = powArray(a, 3);
        System.out.println(Arrays.toString(b));
    }

    /**
     * Get a histogram for the frequencies of each possible value in an array of scores.
     *
     * @param scores: int[] array of scores
     * @return int[] array of frequencies for each possible score
     * */
    public static int[] histogram(int[] scores) {

        int[] hist = new int[scores.length];

        for (int i = 0; i < scores.length; i++) {
            hist[scores[i]]++;
        }
        return hist;
    }

    /**
     * Get an array where all values have been raised to a given power.
     *
     * @param a: double[] array of base values
     * @param pow: int power to which you wish to raise each value
     * @return double[] array of values raised to the given power
     * */
    public static double[] powArray(double[] a, int pow) {

        for (int i = 0; i < a.length; i++) {
            a[i] = power(a[i], pow);
        }
        return a;
    }

    public static double power(double x, int n) {

        if (n == 0) {
            return 1.0;
        } else if (n % 2 == 0) {
            return power(x, n / 2) * power(x, n / 2);
        }
        return x * power(x, n - 1);
    }
}
