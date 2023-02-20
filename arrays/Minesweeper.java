/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

public class Minesweeper {
    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);
        int k = Integer.parseInt(args[2]);

        boolean[][] mines = new boolean[m][n];
        int[][] counts = new int[m][n];

        // Generate k mines uniformly at random
        int count = 0;
        while (count < k) {
            int i = (int) (Math.random() * m);
            int j = (int) (Math.random() * n);
            if (!mines[i][j]) {
                mines[i][j] = true;
                count++;
            }
        }

        // Count neighboring mines for each cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mines[i][j]) {
                    continue;
                }

                for (int r = -1; r <= 1; r++) {
                    for (int c = -1; c <= 1; c++) {
                        int row = i + r;
                        int col = j + c;
                        if (row >= 0 && row < m && col >= 0 && col < n && mines[row][col]) {
                            counts[i][j]++;
                        }
                    }
                }
            }
        }

        // Print the grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mines[i][j]) {
                    System.out.print("*  ");
                }
                else {
                    System.out.print(counts[i][j] + "  ");
                }
            }
            System.out.println();
        }
    }
}
