import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Closest pair of points Using the divide and conquer idea Achieve O(nlogn)
 * 
 * @author lianlu
 *
 */
public class CloestSet {
  class Point {
    double x;
    double y;
  }

  private double UPPER_BOUND;

  public double findClosestSet(Point[] points) {
    if (points.length <= 1)
      return -1;
    UPPER_BOUND = distance(points[0], points[1]);
    Arrays.sort(points, new Comparator<Point>() {// sorting is come with free
      @Override
      public int compare(Point o1, Point o2) {
        return (int) (o1.x - o2.x);
      }
    });
    Point[] qpoint = new Point[points.length];
    for (int i = 0; i < points.length; i++) {
      qpoint[i] = points[i];
    }
    Arrays.sort(qpoint, new Comparator<Point>() {// sorting is come with free
      @Override
      public int compare(Point o1, Point o2) {
        return (int) (o2.y - o1.y);
      }
    });
    return findCloset(points, qpoint);
  }

  private double distance(Point p1, Point p2) {
    return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
  }

  private double findCloset(Point[] points, Point[] qpoints) {
    if (points.length <= 1)
      return UPPER_BOUND;
    if (points.length == 2)
      return distance(points[0], points[1]);
    int mid = points.length / 2;
    Point[] leftPoints = splitleft(points); // the left half of points
    Point[] rightPoints = splitright(points); // the right half of points
    Point[] qleftPoints = qsplitleft(qpoints); // the left half of qpoints
    Point[] qrightPoints = qsplitright(qpoints); // the right half of qpoints
    double leftMin = findCloset(leftPoints, qleftPoints);
    double rightMin = findCloset(rightPoints, qrightPoints);
    double min = Math.min(leftMin, rightMin);
    List<Point> midList = new ArrayList<Point>();
    double midx = (rightPoints[0].x + leftPoints[leftPoints.length - 1].x) / 2;
    for (Point p : qpoints) {
      if (Math.abs(p.x - midx) < min)
        midList.add(p);
    }
    Point[] midPoint = (Point[]) midList.toArray();
    return quickCombine(midPoint, min);
  }

  private double quickCombine(Point[] points, double min) {
    int constant = 15;
    double res = min;
    for (int i = 0; i < points.length; i++) {
      for (int j = 1; j <= constant && i + j < points.length; j++)
        res = Math.min(res, distance(points[i], points[j + i]));
    }
    return res;
  }
}
