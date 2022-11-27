/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private double[] scores;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        scores = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            int opened = 0;
            while (!perc.percolates()) {
                int row = StdRandom.uniformInt(1, n + 1);
                int col = StdRandom.uniformInt(1, n + 1);
                if (!perc.isOpen(row, col)) {
                    perc.open(row, col);
                    opened++;
                }
            }
            scores[i] = (double) opened / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(scores);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(scores);
    }


    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - CONFIDENCE_95 * stddev() / Math.sqrt(scores.length);
    }


    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + CONFIDENCE_95 * stddev() / Math.sqrt(scores.length);
    }


    // test client (see below)
    public static void main(String[] args) {
        PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]),
                                                      Integer.parseInt(args[1]));
        StdOut.printf("mean                    = %s\n", stats.mean());
        StdOut.printf("stddev                  = %s\n", stats.stddev());
        StdOut.printf("95%% confidence interval = [%s, %s]", stats.confidenceLo(),
                      stats.confidenceHi());
    }


}
