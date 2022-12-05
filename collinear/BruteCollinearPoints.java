/* *****************************************************************************
 *  Name: Yifan
 *  Date: 2022-Dec-01
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> lines;

    public BruteCollinearPoints(Point[] points) // finds all line segments containing 4 points
    {
        edgeCases(points);
        
        lines = new ArrayList<LineSegment>();
        Point[] cpPoints = points.clone();

        Arrays.sort(cpPoints);


        for (int i = 0; i < cpPoints.length; i++) {
            for (int j = i + 1; j < cpPoints.length; j++) {
                for (int k = j + 1; k < cpPoints.length; k++) {
                    for (int m = k + 1; m < cpPoints.length; m++) {
                        Point p = cpPoints[i];
                        Point q = cpPoints[j];
                        Point r = cpPoints[k];
                        Point s = cpPoints[m];
                        if (p.slopeTo(q) == q.slopeTo(r) && q.slopeTo(r) == r.slopeTo(s)) {
                            assert lines != null;
                            lines.add(new LineSegment(p, s));
                        }
                    }
                }
            }
        }

    }

    private void edgeCases(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].equals(points[j])) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    public int numberOfSegments()     // the number of line segments
    {
        return lines.size();
    }

    public LineSegment[] segments()       // the line segments
    {
        return lines.toArray(new LineSegment[lines.size()]);
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
