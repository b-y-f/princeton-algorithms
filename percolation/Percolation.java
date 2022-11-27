import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[] grid;
    private int size, numberOfOpenSites;
    private WeightedQuickUnionUF full, prec;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 1) throw new IllegalArgumentException();
        size = n;
        grid = new boolean[n * n];
        full = new WeightedQuickUnionUF(n * n);
        prec = new WeightedQuickUnionUF(n * n);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validation(row, col);
        if (isOpen(row, col)) {
            return;
        }
        grid[posToIdx(row, col)] = true;
        numberOfOpenSites++;
        checkAroundAndConnect(row, col);
    }

    private void checkAroundAndConnect(int row, int col) {

        if (row == 1) {
            prec.union(0, posToIdx(row, col));
            full.union(0, posToIdx(row, col));
        }

        if (row == size) {
            prec.union(size * size - 1, posToIdx(row, col));
        }

        if (isValidBlk(row - 1, col)) {
            full.union(posToIdx(row - 1, col), posToIdx(row, col));
            prec.union(posToIdx(row - 1, col), posToIdx(row, col));
        }

        if (isValidBlk(row + 1, col)) {
            full.union(posToIdx(row + 1, col), posToIdx(row, col));
            prec.union(posToIdx(row + 1, col), posToIdx(row, col));
        }

        if (isValidBlk(row, col - 1)) {
            full.union(posToIdx(row, col - 1), posToIdx(row, col));
            prec.union(posToIdx(row, col - 1), posToIdx(row, col));
        }

        if (isValidBlk(row, col + 1)) {
            full.union(posToIdx(row, col + 1), posToIdx(row, col));
            prec.union(posToIdx(row, col + 1), posToIdx(row, col));
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return grid[posToIdx(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return full.find(posToIdx(row, col)) == full.find(1);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        // when upper and lower connected
        return full.find(0) == prec.find(size * size - 1);
    }

    private boolean isValidRowCol(int row, int col) {
        return row > 0 && row <= size && col > 0 && col <= size;
    }

    private boolean isValidBlk(int row, int col) {
        return isValidRowCol(row, col) && isOpen(row, col);
    }

    private int posToIdx(int row, int col) {
        return (row - 1) * size + (col - 1);
    }

    private void validation(int row, int col) {
        if (!isValidRowCol(row, col)) {
            throw new IllegalArgumentException();
        }
    }


    // test client (optional)
    public static void main(String[] args) {
    }
}
