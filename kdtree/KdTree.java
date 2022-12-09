/* *****************************************************************************
 *  Name: Yifan
 *  Date: 6/12/22
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTree {

    private KDNode root;

    public static void main(String[] args) {
        KdTree t = new KdTree();
        for (int i = 0; i < 100; i++) {
            double x = Math.random();
            double y = Math.random();
            t.insert(new Point2D(x, y));
        }
        System.out.println("done");
    }

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
            node = new KDNode(p);
        }
        else if (getCutdim(p, level) < getCutdim(node.point, level)) {
            node.left = insertRecur(p, node.left, level + 1);
        }
        else {
            node.right = insertRecur(p, node.right, level + 1);
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
    }

    public Point2D nearest(Point2D query) {
        return query;
    }

    public Point2D[] range(RectHV rect) {
        return new Point2D[0];
    }

    public static void main(String[] args) {
        KdTree t = new KdTree();
        for (int i = 0; i < 100; i++) {
            double x = Math.random();
            double y = Math.random();
            t.insert(new Point2D(x, y));
        }
        System.out.println("done");
    }

    private class KDNode {
        private KDNode left, right;
        private Point2D point;

        private KDNode(Point2D p) {
            point = p;
        }

    }
}
