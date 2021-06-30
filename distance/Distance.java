public class Distance
{
  public static void main(String[] args)
  {
    // First point cordinates.
    double x1 = 1.0;
    double y1 = 2.0;

    // Second point coordinates.
    double x2 = 4.0;
    double y2 = 6.0;

    double distance = getDistance(x1, y1, x2, y2);
    System.out.println(distance);
  }

  public static double getDistance(double x1, double y1, double x2, double y2)
  {
    double xDistance = x2 - x1;
    double yDistance = y2 - y1;
    double distanceSquared = xDistance * xDistance + yDistance * yDistance;
    double distance = Math.sqrt(distanceSquared);

    return distance;
  }
}
