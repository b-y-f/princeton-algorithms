public class Solver {

    private class Node implements Comparable<Node> {
        private Board board;
        private int moves, score;
        private Node prev;

        Node(Board board, int moves, Node prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            this.score = board.manhattan() + moves;
        }

        public int compareTo(Node other) {
            if (this.score == other.score) {
                return Integer.compare(this.board.manhattan(), other.board.manhattan());
            }
            else {
                return Integer.compare(this.score, other.score);
            }
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }

    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return false;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!isSolvable()) {
            return -1;
        }
        return 0;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable()) {
            return null;
        }
        return null;
    }

    // test client (see below)
    public static void main(String[] args) {
    }

}
