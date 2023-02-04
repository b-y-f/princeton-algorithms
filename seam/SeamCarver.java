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
        for (int row = 0; row < height(); row++) {
            for (int col = 0; col < width(); col++) {
                setEnergyAt(row, col);
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
        if (x < 0 || x > width() - 1 || y < 0 || y > height() - 1) {
            throw new IllegalArgumentException("invalid x or y!");
        }

        return energy[y][x];
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        double[][] energyWithoutEdge = removeFirstAndLastColumns(energy);
        double[][] copyT = transposeMatrix(energyWithoutEdge);
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
        double[][] energyWithoutEdge = removeFirstAndLastColumns(energy);
        double[][] dp = minEnergyToBot(energyWithoutEdge);
        return getVerticalIndex(dp);
    }

    private double[][] removeFirstAndLastColumns(double[][] originalMatrix) {
        int rows = originalMatrix.length;
        int columns = originalMatrix[0].length;
        double[][] newMatrix = new double[rows - 2][columns - 2];
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 0; j < columns - 2; j++) {
                newMatrix[i - 1][j] = originalMatrix[i][j + 1];
            }
        }
        return newMatrix;
    }

    private int[] getVerticalIndex(double[][] energyNoEdge) {
        int dpCols = energyNoEdge[0].length;
        int dpRows = energyNoEdge.length;

        int[] res = new int[energyNoEdge.length + 2];
        initFirstRow(energyNoEdge, dpRows, res);
        processingRestRows(energyNoEdge, dpCols, res);

        int[] postRes = postProcessDP(res, res.length);
        return postRes;
    }

    private void processingRestRows(double[][] energyNoEdge, int dpCols, int[] res) {
        for (int i = res.length - 4; i >= 0; i--) {
            int prevCol = res[i + 2];
            int leftCol = prevCol;
            int rightCol = prevCol;
            if (prevCol - 1 >= 0) {
                leftCol--;
            }
            if (prevCol + 1 < dpCols) {
                rightCol++;
            }
            double energyLeft = energyNoEdge[i][leftCol];
            double energyMid = energyNoEdge[i][prevCol];
            double energyRight = energyNoEdge[i][rightCol];
            double[] dirs = { energyLeft, energyMid, energyRight };
            int minEnergyIndex = findMinIndex(dirs);
            int newCol = prevCol + findDir(minEnergyIndex);
            res[i + 1] = filterEdge(dpCols, newCol);
        }
    }

    private void initFirstRow(double[][] energyNoEdge, int dpRows, int[] res) {
        res[res.length - 2] = findMinIndex(energyNoEdge[dpRows - 1]);
    }

    private int filterEdge(int dpCols, int newCol) {
        if (newCol < 0) {
            newCol = 0;
        }
        if (newCol > dpCols - 1) {
            newCol = dpCols - 1;
        }
        return newCol;
    }

    private int[] postProcessDP(int[] res, int length) {
        for (int i = 0; i < length; i++) {
            res[i] += 1;
        }
        res[0] = res[1];
        res[length - 1] = res[length - 2];
        return res;
    }


    private double[][] minEnergyToBot(double[][] dp) {
        for (int row = 1; row < dp.length; row++) {
            for (int col = 0; col < dp[0].length; col++) {
                double minEnergyAddAllDir = dp[row][col] + getMinEnergyAboveIt(dp, row, col);
                dp[row][col] = minEnergyAddAllDir;
            }
        }
        return dp;
    }

    private double getMinEnergyAboveIt(double[][] dp, int row, int col) {
        double upper = dp[row - 1][col];
        double rightEnergy = upper;
        double leftEnergy = upper;

        if (col + 1 < dp[0].length) {
            rightEnergy = dp[row - 1][col + 1];
        }
        if (col - 1 >= 0) {
            leftEnergy = dp[row - 1][col - 1];

        }
        return getMinFromThree(upper, leftEnergy, rightEnergy);
    }

    private double getMinFromThree(double a, double b, double c) {
        return Math.min(a, Math.min(b, c));
    }


    private int findMinIndex(double[] arr) {
        double min = Double.MAX_VALUE;
        int minIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    private int findDir(int idx) {
        if (idx == 0) {
            return -1;
        }
        else if (idx == 2) {
            return 1;
        }
        else {
            return 0;
        }
    }


    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null || seam.length != pic.width()) {
            throw new IllegalArgumentException("Seam is invalid!");
        }

        Picture p = new Picture(width(), height() - 1);
        for (int col = 0; col < p.width(); col++) {
            int removeRow = seam[col];
            for (int row = 0; row < p.height(); row++) {
                if (row <= removeRow) {
                    p.set(col, row, pic.get(col, row));
                }
                else {
                    p.set(col, row, pic.get(col, row + 1));
                }
            }
        }

        pic = p;
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (seam == null || seam.length != pic.height()) {
            throw new IllegalArgumentException("Seam is invalid!");
        }

        Picture p = new Picture(width() - 1, height());
        for (int row = 0; row < p.height(); row++) {
            int removeCol = seam[row];
            for (int col = 0; col < p.width(); col++) {
                if (col <= removeCol) {
                    p.set(col, row, pic.get(col, row));
                }
                else {
                    p.set(col, row, pic.get(col + 1, row));
                }

            }
        }
        pic = p;
    }

    //  unit testing (optional)
    public static void main(String[] args) {
    }

}