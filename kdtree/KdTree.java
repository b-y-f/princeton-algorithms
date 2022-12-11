/* *****************************************************************************
 *  Name: Yifan
 *  Date: 6/12/22
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {

    private KDNode root;
    private int size;

    public KdTree() {
    }

    public boolean contains(Point2D p) {
        return contains(root, p);
    }

    private boolean contains(KDNode t, Point2D p) {
        if (t == null) {
            return false;
        }
        if (t.point.x() == p.x() && t.point.y() == p.y()) {
            return true;
        }
        return contains(t.left, p) || contains(t.right, p);

    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        root = insertRecur(p, root, 1, 1, 0, 0, 1);
    }

    private KDNode insertRecur(Point2D p, KDNode node, int level, double top,
                               double bot, double left,
                               double right) {
        if (node == null) {
            size++;
            if (isVertical(level)) {
                node = new KDNode(p, level, p.x(), bot, p.x(), top);
            }
            else {
                node = new KDNode(p, level, left, p.y(), right, p.y());
            }
        }
        else if (getCutdim(p, level) < getCutdim(node.point, level)) {
            if (isVertical(level)) {
                node.left = insertRecur(p, node.left, level + 1, top, bot, left, node.point.x()
                );
            }
            else {
                node.left = insertRecur(p, node.left, level + 1, node.point.y(), bot, left, right
                );
            }
        }
        else {
            if (isVertical(level)) {
                node.right = insertRecur(p, node.right, level + 1, top, bot, node.point.x(), right
                );
            }
            else {
                node.right = insertRecur(p, node.right, level + 1, top, node.point.y(), left, right
                );
            }
        }
        return node;
    }

    private double getCutdim(Point2D p, int level) {
        return isVertical(level) ? p.x() : p.y();
    }

    private boolean isVertical(int level) {
        return level % 2 == 1;
    }

    public void draw() {
        inorderRecur(root);
    }

    private void inorderRecur(KDNode node) {
        if (node != null) {
            drawPointAndLine(node.point, node.x0, node.y0, node.x1, node.y1,
                             isVertical(node.level));
            inorderRecur(node.left);
            inorderRecur(node.right);
        }
    }

    private void drawPointAndLine(Point2D p, double x0, double y0, double x1, double y1,
                                  boolean isRed) {
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLACK);
        p.draw();
        StdDraw.setPenRadius();
        if (isRed) {
            StdDraw.setPenColor(StdDraw.RED);
        }
        else {
            StdDraw.setPenColor(StdDraw.BLUE);
        }
        StdDraw.line(x0, y0, x1, y1);
    }


    public Point2D nearest(Point2D query) {
        if (query == null) {
            throw new IllegalArgumentException();
        }
        return nearestRecur(root, query, null, 1);
    }

    private Point2D nearestRecur(KDNode t, Point2D queryPt, Point2D bestPt, double bestDist) {
        if (t == null) return bestPt;
        double dist = t.point.distanceTo(queryPt);
        if (dist < bestDist) {
            bestPt = t.point;
            bestDist = dist;
        }
        if (t.left != null && (t.point.x() - queryPt.x()) < bestDist) {
            bestPt = nearestRecur(t.left, queryPt, bestPt, bestDist);
        }
        if (t.right != null && (queryPt.x() - t.point.x()) < bestDist) {
            bestPt = nearestRecur(t.right, queryPt, bestPt, bestDist);
        }

        return bestPt;
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }
        SET<Point2D> set = new SET<>();
        rangeQueryRecur(rect, root, set);
        return set;
    }

    private void rangeQueryRecur(RectHV rect, KDNode node, SET<Point2D> s) {
        if (node != null) {
            if (isLineIntersect(rect, node)) {
                if (rect.contains(node.point)) {
                    s.add(node.point);
                }
            }
            rangeQueryRecur(rect, node.left, s);
            rangeQueryRecur(rect, node.right, s);
        }
    }

    private boolean isLineIntersect(RectHV r, KDNode node) {
        if (isVertical(node.level)) {
            return node.x0 <= r.xmax() || node.x0 >= r.xmin();
        }
        else {
            return node.y0 <= r.ymax() || node.y0 >= r.ymin();
        }
    }

    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
            StdDraw.clear();
            kdtree.draw();
            StdDraw.show();
        }

        System.out.println(kdtree.size());

        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.setPenRadius(0.05);
        Point2D testPoint = new Point2D(0.2, 0.2);
        testPoint.draw();
        // should be 0.144 0.179
        System.out.println(kdtree.nearest(testPoint));

        Point2D testContain = new Point2D(0.417, 0.362);
        System.out.println(kdtree.contains(testContain));
        Point2D testContain2 = new Point2D(0.417, 0.9);
        System.out.println(kdtree.contains(testContain2));
    }

    private static class KDNode {
        private KDNode left, right;
        private Point2D point;
        private int level;
        private double x0, y0, x1, y1;

        private KDNode(Point2D p, int lvl, double x0, double y0, double x1, double y1) {
            point = p;
            level = lvl;
            this.x0 = x0;
            this.y0 = y0;
            this.x1 = x1;
            this.y1 = y1;
        }
    }
}
