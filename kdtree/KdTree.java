/* *****************************************************************************
 *  Name: Yifan
 *  Date: 6/12/22
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;

public class KdTree {

    private KDNode root;
    private Stack<Point2D> list;


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
        list = new Stack<>();
        inorderRecur(root);
        for (Point2D p : list) {
            p.draw();
        }
    }

    private void inorderRecur(KDNode node) {
        if (node != null) {
            list.push(node.point);
            inorderRecur(node.left);
            inorderRecur(node.right);
        }
    }

    public Point2D nearest(Point2D query) {
        return query;
    }

    public Point2D[] range(RectHV rect) {
        return new Point2D[0];
    }

    public static void main(String[] args) {
    }

    private class KDNode {
        private KDNode left, right;
        private Point2D point;

        private KDNode(Point2D p) {
            point = p;
        }
    }
}
