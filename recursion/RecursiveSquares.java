/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

public class RecursiveSquares {

    /**
     * Draws a square centered on (x, y) of the given side length
     * with a light gray background and a black border.
     */
    public static void drawSquare(double x, double y, double length) {
        double half = length / 2.0;
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.filledSquare(x, y, half);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.square(x, y, half);
    }

    /**
     * Draws a recursive square pattern of order n, centered on (x, y)
     * of the given side length.
     */
    public static void draw(int n, double x, double y, double length) {
        if (n == 0) return;

        double half = length / 2.0;
        draw(n - 1, x - half, y + half, half);   // upper left
        draw(n - 1, x + half, y + half, half);   // upper right
        drawSquare(x, y, length);
        draw(n - 1, x - half, y - half, half);   // lower left
        draw(n - 1, x + half, y - half, half);   // lower right
    }

    /**
     * Takes an integer command-line argument n and draws a recursive
     * square pattern of order n, centered on (0.5, 0.5) with side length 0.5.
     */
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        draw(n, 0.5, 0.5, 0.5);
    }
}
