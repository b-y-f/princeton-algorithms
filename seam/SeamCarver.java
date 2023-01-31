import edu.princeton.cs.algs4.Picture;

import java.awt.Color;

public class SeamCarver {
    private Picture pic;
    private double[][] energy;

    private final int defaultEnergy = 1000;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        pic = picture;
        energy = new double[height()][width()];
        for (int i = 0; i < height(); i++) {
            for (int j = 0; j < width(); j++) {
                initEnergy(i, j);
            }
        }

    }

    private void initEnergy(int i, int j) {
        if (i == 0)
            energy[i][j] = defaultEnergy;
        else if (i == height() - 1)
            energy[i][j] = defaultEnergy;
        else if (j == 0)
            energy[i][j] = defaultEnergy;
        else if (j == width() - 1)
            energy[i][j] = defaultEnergy;
        else {
            Color left = pic.get(j - 1, i);
            Color right = pic.get(j + 1, i);
            Color up = pic.get(j, i - 1);
            Color down = pic.get(j, i + 1);
            double deltaX = Math.pow(right.getRed() - left.getRed(), 2) + Math.pow(
                    right.getGreen() - left.getGreen(), 2) + Math.pow(
                    right.getBlue() - left.getBlue(), 2);
            double deltaY = Math.pow(up.getRed() - down.getRed(), 2) + Math.pow(
                    up.getGreen() - down.getGreen(), 2) + Math.pow(
                    up.getBlue() - down.getBlue(), 2);

            energy[i][j] = Math.sqrt(deltaX + deltaY);
        }
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
        return new int[0];
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        return new int[0];
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