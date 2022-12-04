/* *****************************************************************************
 *  Name: Yifan
 *  Date: 2022-Dec-04
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class Board {

    private int[][] board;
    private int dimension;
    private int hamming = 0, manhattan = 0;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        board = tiles.clone();
        dimension = board.length;
    }

    // string representation of this board
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(dimension()) + '\n');
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < board[0].length; j++) {
                sb.append(" ").append(board[i][j]).append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // board dimension n
    public int dimension() {
        return dimension;
    }

    // number of tiles out of place
    public int hamming() {
        int tmp = 1;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (board[i][j] != 0 && board[i][j] != tmp) {
                    hamming++;
                }
                tmp++;
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
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        if (dimension() != that.dimension()) return false;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (board[i][j] != that.board[i][j]) return false;
            }
        }
        return true;
    }

    private int[][] deepCopy(int[][] board) {
        int n = board.length;
        int[][] cp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cp[i][j] = board[i][j];
            }
        }
        return cp;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        int zeroI = 0, zeroJ = 0;
        outter:
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (board[i][j] == 0) {
                    zeroI = i;
                    zeroJ = j;
                    break outter;
                }
            }
        }
        return checkNeighbor(zeroI, zeroJ);
    }

    private Stack<Board> checkNeighbor(int i, int j) {
        Stack<Board> deque = new Stack<>();
        if (i - 1 >= 0) {
            int[][] cp = deepCopy(board);
            cp[i][j] = cp[i - 1][j];
            cp[i - 1][j] = 0;
            deque.push(new Board(cp));
        }
        if (i + 1 < dimension()) {
            int[][] cp = deepCopy(board);
            cp[i][j] = cp[i + 1][j];
            cp[i + 1][j] = 0;
            deque.push(new Board(cp));
        }
        if (j - 1 >= 0) {
            int[][] cp = deepCopy(board);
            cp[i][j] = cp[i][j - 1];
            cp[i][j - 1] = 0;
            deque.push(new Board(cp));
        }
        if (j + 1 < dimension()) {
            int[][] cp = deepCopy(board);
            cp[i][j] = cp[i][j + 1];
            cp[i][j + 1] = 0;
            deque.push(new Board(cp));
        }
        return deque;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        // switch any two except empty tile
        int[][] twin = deepCopy(board);
        if (board[0][0] != 0 && board[0][1] != 0) {
            twin[0][0] = board[0][1];
            twin[0][1] = board[0][0];
        }
        else {
            twin[1][0] = board[1][1];
            twin[1][1] = board[1][0];
        }
        return new Board(twin);
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

            for (Board nei : initial.neighbors()) {
                StdOut.println(nei.toString());
            }
        }
    }

}
