public class Percolation {

    private boolean[] grid;
    private int size;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 0) throw new IllegalArgumentException();
        size = n;
        grid = new boolean[n * n];
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        grid[getPos(row, col)] = true;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return grid[getPos(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return false;
    }

    private int getPos(int row, int col) {
        return (row - 1) * size + (col - 1);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return 0;
    }

    // does the system percolate?
    public boolean percolates() {
        // when upper and lower connected
        return false;
    }


    // test client (optional)
    public static void main(String[] args) {
    }
}
