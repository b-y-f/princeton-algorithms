import edu.princeton.cs.algs4.In;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
class BoggleSolverTest {
    private In in = new In("dictionary-algs4.txt");
    private String[] dictionary = getIn().readAllStrings();
    private BoggleSolver solver = new BoggleSolver(dictionary);

    @Test
    public void testBoard4x4() {
        BoggleBoard boardQ = new BoggleBoard("board4x4.txt");
        int score = 0;
        for (String word : solver.getAllValidWords(boardQ)) {
            score += solver.scoreOf(word);
        }
        assertEquals(33, score);
    }

    @Test
    public void testBoardQ() {
        BoggleBoard boardQ = new BoggleBoard("board-q.txt");
        int score = 0;
        for (String word : solver.getAllValidWords(boardQ)) {
            score += solver.scoreOf(word);
        }
        assertEquals(84, score);
    }

    public In getIn() {
        return in;
    }

    public void setIn(In in) {
        this.in = in;
    }

    // @Test
    // public void testBoardCouscous() {
    //     BoggleBoard board = new BoggleBoard("board-couscous.txt");
    //     int cnt = 0;
    //     for (String word : solver.getAllValidWords(board)) {
    //         cnt++;
    //     }
    //     assertEquals(12, cnt);
    //
    // }
}