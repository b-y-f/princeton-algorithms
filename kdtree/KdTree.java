/* *****************************************************************************
 *  Name: Yifan
 *  Date: 6/12/22
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {

    private KDNode root;

    public KdTree() {
    }

    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        root = insertRecur(p, root, 1);
    }

    private KDNode insertRecur(Point2D p, KDNode node, int level) {
        if (node == null) {
            node = new KDNode(p, 1);
        }
        else if (getCutdim(p, level) < getCutdim(node.point, level)) {
            node.left = insertRecur(p, node.left, level + 1);
            node.left.level = level + 1;
        }
        else {
            node.right = insertRecur(p, node.right, level + 1);
            node.right.level = level + 1;
        }
        return node;
    }

    private double getCutdim(Point2D p, int level) {
        boolean isX = level % 2 == 1;
        if (isX) {
            return p.x();
        }
        else {
            return p.y();
        }
    }

    public void draw() {
        inorderRecur(root, 1, 0, 0, 1);
    }

    private void inorderRecur(KDNode node, double top, double bot, double left,
                              double right) {
        if (node != null) {
            boolean isVertical = node.level % 2 == 1;
            Point2D p = node.point;
            if (isVertical) {
                drawPointAndLine(p, p.x(), bot, p.x(), top, true);
                inorderRecur(node.left, top, bot, left, p.x());
                inorderRecur(node.right, top, bot, p.x(), right);
            }
            else {
                drawPointAndLine(p, left, p.y(), right, p.y(), false);
                inorderRecur(node.left, p.y(), bot, left, right);
                inorderRecur(node.right, top, p.y(), left, right);
            }
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
        return query;
    }

    public Point2D[] range(RectHV rect) {
        return new Point2D[0];
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
    }

    private class KDNode {
        private KDNode left, right;
        private Point2D point;
        private int level;

        private KDNode(Point2D p, int lvl) {
            point = p;
            level = lvl;
        }
    }
}
