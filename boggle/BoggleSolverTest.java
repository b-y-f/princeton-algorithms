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
    private String[] dictionary = in.readAllStrings();
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
    public void testBoardCouscous() {
        BoggleBoard board = new BoggleBoard("board-couscous.txt");
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            System.out.println(word);
            score += solver.scoreOf(word);
        }
        assertEquals(24, score);
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
}