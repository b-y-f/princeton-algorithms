import edu.princeton.cs.algs4.Picture;

import java.awt.Color;

public class SeamCarver {
    private static final int DEFAULT_ENERGY = 1000;
    private Picture pic;
    private double[][] energy;


    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        pic = picture;
        initEnergy();

    }

    private void initEnergy() {
        energy = new double[height()][width()];
        for (int i = 0; i < height(); i++) {
            for (int j = 0; j < width(); j++) {
                setEnergyAt(i, j);
            }
        }
    }

    private void setEnergyAt(int i, int j) {
        if (i == 0)
            energy[i][j] = DEFAULT_ENERGY;
        else if (i == height() - 1)
            energy[i][j] = DEFAULT_ENERGY;
        else if (j == 0)
            energy[i][j] = DEFAULT_ENERGY;
        else if (j == width() - 1)
            energy[i][j] = DEFAULT_ENERGY;
        else {
            double deltaX = getDelta(pic.get(j - 1, i), pic.get(j + 1, i));
            double deltaY = getDelta(pic.get(j, i + 1), pic.get(j, i - 1));
            energy[i][j] = Math.sqrt(deltaX + deltaY);
        }
    }

    private double getDelta(Color a, Color b) {
        double delta = Math.pow(b.getRed() - a.getRed(), 2) + Math.pow(
                b.getGreen() - a.getGreen(), 2) + Math.pow(
                b.getBlue() - a.getBlue(), 2);
        return delta;
    }


    // current picture
    public Picture picture() {
        return pic;
    }

    // width of current picture
    public int width() {
        return pic.width();
    }

    // height of current picture
    public int height() {
        return pic.height();
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        return energy[y][x];
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        double[][] copyT = transposeMatrix(energy.clone());
        double[][] dp = minEnergyToBot(copyT);
        return getVerticalIndex(dp);
    }

    private double[][] transposeMatrix(double[][] matrix) {
        double[][] transposed = new double[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }
        return transposed;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        double[][] dp = minEnergyToBot(energy.clone());
        return getVerticalIndex(dp);
    }

    private int[] getVerticalIndex(double[][] dp) {
        int[] res = new int[dp.length];
        res[0] = findMinIndex(dp[1]);
        for (int i = 1; i < res.length; i++) {
            int last = res[i - 1];

            double left = Double.MAX_VALUE;
            double right = Double.MAX_VALUE;
            if (last - 1 >= 0) {
                left = dp[i][last - 1];
            }
            else if (last + 1 < dp[0].length) {
                right = dp[i][last + 1];
            }

            if (i == res.length - 1) {
                res[i] = last;
            }
            else {
                double[] nextEnergies = new double[] {
                        left, dp[i][last], right
                };
                int currIndex = last + getDir(nextEnergies);
                res[i] = currIndex;
            }

        }
        return res;
    }


    private double[][] minEnergyToBot(double[][] dp) {
        for (int row = 1; row < dp.length; row++) {
            for (int col = 0; col < dp[0].length; col++) {
                double curr = dp[row][col];
                double downEnergy = curr + dp[row - 1][col];
                double leftEnergy = curr;
                double rightEnergy = curr;
                if (col - 1 >= 0) {
                    leftEnergy += dp[row - 1][col - 1];
                }
                else if (col + 1 < dp[0].length) {
                    rightEnergy += dp[row - 1][col + 1];
                }
                dp[row][col] = Math.min(downEnergy, Math.min(leftEnergy, rightEnergy));
            }
        }
        return dp;
    }

    private int getDir(double[] threeDirEnergy) {
        int minIndex = findMinIndex(threeDirEnergy);
        if (minIndex == 0) {
            return -1;
        }
        else if (minIndex == 2) {
            return 1;
        }
        return 0;
    }

    private int findMinIndex(double[] arr) {
        double min = arr[0];
        int minIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
                minIndex = i;
            }
        }
        return minIndex;
    }


    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
    }

    //  unit testing (optional)
    public static void main(String[] args) {
    }

}