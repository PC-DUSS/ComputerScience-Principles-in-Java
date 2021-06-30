public class Multadd
{
  public static void main(String[] args)
  {
    double a1 = 1.0;
    double b1 = 2.0;
    double c1 = 3.0;
    System.out.println("1.0 x 2.0 + 3.0 = " + multadd(a1, b1, c1));

    double a2 = 1.0 / 2.0;
    double b2 = Math.cos(Math.PI / 4.0);
    double c2 = Math.sin(Math.PI / 4.0);
    System.out.println("cos(pi / 4) / 2 + sin(pi / 4) = " + multadd(a2, b2, c2));

    double a3 = 1.0;
    double b3 = Math.log(10);
    double c3 = Math.log(20);
    System.out.println("log(10) + log(20) = " + multadd(a3, b3, c3));

    System.out.println(expSum(10));
  }

  public static double multadd(double a, double b, double c)
  {
    return a * b + c;
  }

  public static double expSum(double x)
  {
    double a = x;
    double b = Math.exp(-x);
    double c = Math.sqrt(1 - b);

    return multadd(a, b, c);
  }
}
