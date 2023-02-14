/* *****************************************************************************
 *  Name: Yifan
 *  Date: 6/12/22
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Stack;

public class PointSET {
    private SET<Point2D> points;

    /**
     * construct an empty set of points
     */
    public PointSET() {
        points = new SET<>();
    }

    public boolean isEmpty() {
        return points.isEmpty();
    }

    public int size() {
        return points.size();
    }

    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        if (!points.contains(p)) {
            points.add(p);
        }
    }

    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        return points.contains(p);
    }

    public void draw() {
        for (Point2D p : points) {
            p.draw();
        }

    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }
        Stack<Point2D> insiders = new Stack<Point2D>();
        for (Point2D p : points) {
            if (rect.contains(p)) {
                insiders.push(p);
            }
        }
        return insiders;
    }

    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        Point2D nearest = null;
        double closetDist = Double.POSITIVE_INFINITY;
        for (Point2D point : points) {
            if (p.distanceSquaredTo(point) < closetDist) {
                nearest = point;
                closetDist = p.distanceSquaredTo(point);
            }
        }
        return nearest;
    }
}
