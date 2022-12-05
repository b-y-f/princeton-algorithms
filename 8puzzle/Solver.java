import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

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


    private MinPQ<Node> pq;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }

        pq = new MinPQ<Node>();
        pq.insert(new Node(initial, 0, null));

        while (!pq.min().board.isGoal()) {
            Node node = pq.delMin();
            for (Board nei : node.board.neighbors()) {
                if (node.moves == 0 || !nei.equals(node.prev.board)) {
                    pq.insert(new Node(nei, node.moves + 1, node));
                }
            }
        }

    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return pq.min().board.isGoal();
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!isSolvable()) {
            return -1;
        }
        return pq.min().moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable()) {
            return null;
        }
        Stack<Board> boards = new Stack<>();
        Node node = pq.min();
        while (node != null) {
            boards.push(node.board);
            node = node.prev;
        }
        return boards;
    }

    // test client (see below)
    public static void main(String[] args) {
    }

}
