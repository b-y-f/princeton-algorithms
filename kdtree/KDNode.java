/* *****************************************************************************
 *  Name: Yifan
 *  Date: 14/02/23
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;

public class KDNode {
    private KDNode left;
    private KDNode right;
    private Point2D point;
    private int level;
    private double x0;
    private double y0;
    private double x1;
    private double y1;

    KDNode(Point2D p, int lvl, double x0, double y0, double x1, double y1) {
        setPoint(p);
        setLevel(lvl);
        this.setX0(x0);
        this.setY0(y0);
        this.setX1(x1);
        this.setY1(y1);
    }

    public KDNode getLeft() {
        return left;
    }

    public void setLeft(KDNode left) {
        this.left = left;
    }

    public KDNode getRight() {
        return right;
    }

    public void setRight(KDNode right) {
        this.right = right;
    }

    public Point2D getPoint() {
        return point;
    }

    public void setPoint(Point2D point) {
        this.point = point;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getX0() {
        return x0;
    }

    public void setX0(double x0) {
        this.x0 = x0;
    }

    public double getY0() {
        return y0;
    }

    public void setY0(double y0) {
        this.y0 = y0;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }
}
