public class MaximumSquareSubmatrix {

    // Returns the size of the largest contiguous square submatrix
    // of a[][] containing only 1s.
    public static int size(int[][] a) {
        if (a.length == 0) return 0;
        int m = a.length, n = a[0].length, result = 0;
        int[][] b = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (a[i - 1][j - 1] == 1) {
                    b[i][j] = Math.min(
                            Math.min(b[i][j - 1], b[i - 1][j - 1]),
                            b[i - 1][j]) + 1;
                    result = Math.max(b[i][j], result);
                }
            }
        }
        return result;
    }

    /**
     * Reads an n-by-n matrix of 0s and 1s from standard input
     * and prints the size of the largest contiguous square sub-matrix
     * containing only 1s.
     *
     * @param args
     */
    public static void main(String[] args) {
        int n = StdIn.readInt();
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = StdIn.readInt();
            }
        }
        System.out.println(size(a));
    }
}
