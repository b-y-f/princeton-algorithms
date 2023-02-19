/* *****************************************************************************
 *  Name: Yifan
 *  Date: 6/12/22
 **************************************************************************** */

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
        if (t.getPoint().x() == p.x() && t.getPoint().y() == p.y()) {
            return true;
        }
        return contains(t.getLeft(), p) || contains(t.getRight(), p);

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
        if (!contains(p)) {
            if (isVertical(level)) {
                node = new KDNode(p, level, p.x(), bot, p.x(), top);
            }
            else {
                node = new KDNode(p, level, left, p.y(), right, p.y());
            }
            size++;
        }
        else if (getCutdim(p, level) < getCutdim(node.getPoint(), level)) {
            if (isVertical(level)) {
                node.setLeft(
                        insertRecur(p, node.getLeft(), level + 1, top, bot, left,
                                    node.getPoint().x()
                        ));
            }
            else {
                node.setLeft(
                        insertRecur(p, node.getLeft(), level + 1, node.getPoint().y(), bot, left,
                                    right
                        ));
            }
        }
        else {
            if (isVertical(level)) {
                node.setRight(
                        insertRecur(p, node.getRight(), level + 1, top, bot, node.getPoint().x(),
                                    right
                        ));
            }
            else {
                node.setRight(
                        insertRecur(p, node.getRight(), level + 1, top, node.getPoint().y(), left,
                                    right
                        ));
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
            drawPointAndLine(node.getPoint(), node.getX0(), node.getY0(), node.getX1(),
                             node.getY1(),
                             isVertical(node.getLevel()));
            inorderRecur(node.getLeft());
            inorderRecur(node.getRight());
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
        double dist = t.getPoint().distanceTo(queryPt);
        if (dist < bestDist) {
            bestPt = t.getPoint();
            bestDist = dist;
        }
        if (t.getLeft() != null && (t.getPoint().x() - queryPt.x()) < bestDist) {
            bestPt = nearestRecur(t.getLeft(), queryPt, bestPt, bestDist);
        }
        if (t.getRight() != null && (queryPt.x() - t.getPoint().x()) < bestDist) {
            bestPt = nearestRecur(t.getRight(), queryPt, bestPt, bestDist);
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
                if (rect.contains(node.getPoint())) {
                    s.add(node.getPoint());
                }
            }
            rangeQueryRecur(rect, node.getLeft(), s);
            rangeQueryRecur(rect, node.getRight(), s);
        }
    }

    private boolean isLineIntersect(RectHV r, KDNode node) {
        if (isVertical(node.getLevel())) {
            return node.getX0() <= r.xmax() || node.getX0() >= r.xmin();
        }
        else {
            return node.getY0() <= r.ymax() || node.getY0() >= r.ymin();
        }
    }

    public static void main(String[] args) {
    }
}
