/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.HashSet;
import java.util.Set;

public class BoggleSolver {
    /**
     * Initializes the data structure using the given array of strings as the dictionary.
     * (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
     *
     * @param dictionary
     */
    private Trie trie;

    public BoggleSolver(String[] dictionary) {
        trie = new Trie();
        for (String word : dictionary) {
            trie.insert(word);
        }
    }

    /**
     * Returns the set of all valid words in the given Boggle board, as an Iterable.
     *
     * @param board
     * @return
     */
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        int rows = board.rows();
        int cols = board.cols();
        boolean[][] visited = new boolean[rows][cols];
        Set<String> words = new HashSet<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dfs(board, visited, i, j, trie.root, words);
            }
        }

        return words;
    }

    private void dfs(BoggleBoard board, boolean[][] visited, int row, int col, TrieNode node,
                     Set<String> words) {
        if (visited[row][col]) {
            return;
        }

        char letter = board.getLetter(row, col);
        TrieNode nextNode = node.getChild(letter);
        if (nextNode == null) {
            return;
        }

        visited[row][col] = true;
        if (nextNode.isWord()) {
            words.add(nextNode.getWord());
        }

        int[][] dirs = {
                { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }
        };
        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (nextRow >= 0 && nextRow < board.rows() && nextCol >= 0 && nextCol < board.cols()) {
                dfs(board, visited, nextRow, nextCol, nextNode, words);
            }
        }

        visited[row][col] = false;
    }

    /**
     * Returns the score of the given word if it is in the dictionary, zero otherwise.
     * (You can assume the word contains only the uppercase letters A through Z.)
     *
     * @param word
     * @return
     */
    public int scoreOf(String word) {

        TrieNode node = trie.find(word);
        if (node == null || !node.isWord()) {
            return 0;
        }

        int length = word.length();
        if (length <= 2) {
            return 0;
        }
        else if (length <= 4) {
            return 1;
        }
        else if (length == 5) {
            return 2;
        }
        else if (length == 6) {
            return 3;
        }
        else if (length == 7) {
            return 5;
        }
        else {
            return 11;
        }
    }

    public static void main(String[] args) {

    }
}