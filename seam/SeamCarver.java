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