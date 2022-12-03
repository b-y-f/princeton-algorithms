/* *****************************************************************************
 *  Name: Yifan
 *  Date: 2022-Dec-04
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;

import java.util.Arrays;

public class Board {

    private int[][] board, goal;
    private int hamming = 0, manhattan = 0;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        board = tiles.clone();
        genGoal();
    }

    private void genGoal() {
        goal = new int[dimension()][dimension()];
        int tmp = 1;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                goal[i][j] = tmp;
                tmp++;
            }
        }
        goal[dimension() - 1][dimension() - 1] = 0;
    }

    // string representation of this board
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(dimension()) + '\n');
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < board[0].length; j++) {
                sb.append(board[i][j]).append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // board dimension n
    public int dimension() {
        return board.length;
    }

    // number of tiles out of place
    public int hamming() {
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (board[i][j] != 0 && board[i][j] != goal[i][j]) {
                    hamming++;
                    int[] goalIdxs = getGoalIndex(board[i][j]);
                    int gi = goalIdxs[0];
                    int gj = goalIdxs[1];
                    manhattan += Math.abs(i - gi) + Math.abs(j - gj);
                }
            }
        }
        return hamming;
    }

    private int[] getGoalIndex(int value) {
        int[] tmp = new int[2];
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (value == goal[i][j]) {
                    tmp[0] = i;
                    tmp[1] = j;
                }
            }
        }
        return tmp;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return Arrays.deepEquals(board, goal);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        int[][] that = (int[][]) y;
        return Arrays.deepEquals(board, that);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return null;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {

        return null;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        for (String filename : args) {

            // read in the board specified in the filename
            In in = new In(filename);
            int n = in.readInt();
            int[][] tiles = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    tiles[i][j] = in.readInt();
                }
            }

            // solve the slider puzzle
            Board initial = new Board(tiles);
            // System.out.println(initial.toString());
            System.out.println(initial.isGoal());
            System.out.println(initial.hamming());
            System.out.println(initial.manhattan());
        }
    }

}
