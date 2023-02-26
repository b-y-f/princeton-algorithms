/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import java.awt.Color;

public class KernelFilter {
    private static final double[][] IDENTITY_KERNEL = { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } };
    private static final double[][] GAUSSIAN_KERNEL = {
            { (1.0 / 16) * 1, (1.0 / 16) * 2, (1.0 / 16) * 1 },
            { (1.0 / 16) * 2, (1.0 / 16) * 4, (1.0 / 16) * 2 },
            { (1.0 / 16) * 1, (1.0 / 16) * 2, (1.0 / 16) * 1 }
    };
    private static final double[][] SHARPEN_KERNEL = { { 0, -1, 0 }, { -1, 5, -1 }, { 0, -1, 0 } };
    private static final double[][] LAPLACIAN_KERNEL = {
            { -1, -1, -1 }, { -1, 8, -1 }, { -1, -1, -1 }
    };
    private static final double[][] EMBOSS_KERNEL = { { -2, -1, 0 }, { -1, 1, 1 }, { 0, 1, 2 } };
    private static final double[][] MOTION_BLUR_KERNEL = {
            { 1.0 / 9, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 1.0 / 9, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 1.0 / 9, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 1.0 / 9, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 1.0 / 9, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 1.0 / 9, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 1.0 / 9, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 1.0 / 9, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 1.0 / 9 }
    };

    // Returns a new picture that applies the identity filter to the given picture.
    public static Picture identity(Picture picture) {
        return applyKernel(picture, IDENTITY_KERNEL);
    }

    // Returns a new picture that applies a Gaussian blur filter to the given picture.
    public static Picture gaussian(Picture picture) {
        return applyKernel(picture, GAUSSIAN_KERNEL);
    }


    // Returns a new picture that applies a sharpen filter to the given picture.
    public static Picture sharpen(Picture picture) {
        return applyKernel(picture, SHARPEN_KERNEL);
    }

    // Returns a new picture that applies an Laplacian filter to the given picture.
    public static Picture laplacian(Picture picture) {
        return applyKernel(picture, LAPLACIAN_KERNEL);
    }

    // Returns a new picture that applies an emboss filter to the given picture.
    public static Picture emboss(Picture picture) {
        return applyKernel(picture, EMBOSS_KERNEL);
    }

    // Returns a new picture that applies a motion blur filter to the given picture.
    public static Picture motionBlur(Picture picture) {
        return applyKernel(picture, MOTION_BLUR_KERNEL);
    }

    public static Picture applyKernel(Picture picture, double[][] kernel) {
        // Get the dimensions of the picture and the kernel.
        int width = picture.width();
        int height = picture.height();
        int kernelSize = kernel.length;

        // Create a new picture to hold the filtered image.
        Picture filteredPicture = new Picture(width, height);

        // Iterate over each pixel in the picture.
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // Apply the kernel to the current pixel and its neighbors.
                double redSum = 0;
                double greenSum = 0;
                double blueSum = 0;

                for (int i = 0; i < kernelSize; i++) {
                    for (int j = 0; j < kernelSize; j++) {
                        int pixelX = (x + i - (kernelSize / 2) + width) % width;
                        int pixelY = (y + j - (kernelSize / 2) + height) % height;

                        // Get the color of the current pixel and multiply it by the corresponding kernel value.
                        Color color = picture.get(pixelX, pixelY);
                        double kernelValue = kernel[i][j];
                        redSum += kernelValue * color.getRed();
                        greenSum += kernelValue * color.getGreen();
                        blueSum += kernelValue * color.getBlue();
                    }
                }

                // Compute the new color for the current pixel.
                int red = (int) Math.round(redSum);
                int green = (int) Math.round(greenSum);
                int blue = (int) Math.round(blueSum);
                // Ensure that the color components are in the range [0, 255].
                red = Math.min(255, Math.max(0, red));
                green = Math.min(255, Math.max(0, green));
                blue = Math.min(255, Math.max(0, blue));
                Color newColor = new Color(red, green, blue);

                // Set the new color for the current pixel in the filtered picture.
                filteredPicture.set(x, y, newColor);
            }
        }

        return filteredPicture;
    }

    public static void main(String[] args) {
        Picture pic = new Picture("baboon.png");
        System.out.println(motionBlur(pic).get(9, 9));
    }
}
