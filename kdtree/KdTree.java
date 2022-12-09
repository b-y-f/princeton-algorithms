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
        inorderRecur(root, null, false);
    }

    private void inorderRecur(KDNode node, KDNode parent, boolean isSmall) {
        if (node != null) {
            drawPointAndLine(node, parent, isSmall);
            inorderRecur(node.right, node, false);
            inorderRecur(node.left, node, true);
        }
    }

    private void drawPointAndLine(KDNode node, KDNode parent, boolean isSmall) {
        int level = node.level;
        boolean isX = level % 2 == 1;
        Point2D p = node.point;
        p.draw();

        if (parent == null) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(p.x(), 0, p.x(), 1);
            return;
        }

        if (isX) {
            StdDraw.setPenColor(StdDraw.RED);
            if (isSmall) {
                StdDraw.line(p.x(), 0, p.x(), parent.point.y());
            }
            else {
                StdDraw.line(p.x(), parent.point.y(), p.x(), 1);
            }
        }
        else {
            StdDraw.setPenColor(StdDraw.BLUE);
            if (isSmall) {
                StdDraw.line(0, p.y(), parent.point.x(), p.y());
            }
            else {
                StdDraw.line(parent.point.x(), p.y(), 1, p.y());
            }
        }
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
        System.out.println();
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
