/* *****************************************************************************
 *  Name: Yifan
 *  Date: 6/12/22
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Stack;

// TODO You must use either SET or java.util.TreeSet; do not implement your own red–black BST.
public class PointSET {
    private SET<Point2D> points;

    public PointSET()
    // construct an empty set of points
    {
        points = new SET<>();
    }

    public boolean isEmpty()
    // is the set empty?
    {
        return points.isEmpty();
    }

    public int size()
    // number of points in the set
    {
        return points.size();
    }

    public void insert(Point2D p)
    // add the point to the set (if it is not already in the set)
    {
        if (!points.contains(p)) {
            points.add(p);
        }
    }

    public boolean contains(Point2D p)
    // does the set contain point p?
    {
        return points.contains(p);
    }

    public void draw()
    // draw all points to standard draw
    {
        for (Point2D p : points) {
            p.draw();
        }

    }

    public Iterable<Point2D> range(RectHV rect)
    // all points that are inside the rectangle (or on the boundary)
    {
        Stack<Point2D> insiders = new Stack<Point2D>();
        for (Point2D p : points) {
            if (rect.contains(p)) {
                insiders.push(p);
            }
        }
        return insiders;
    }

    public Point2D nearest(Point2D p)
    // a nearest neighbor in the set to point p; null if the set is empty
    {
        Point2D nearest = null;
        double closetDist = Double.POSITIVE_INFINITY;
        for (Point2D point : points) {
            if (p.distanceTo(point) < closetDist)
                nearest = point;
        }
        return nearest;
    }

    public static void main(String[] args) {
    }
}
