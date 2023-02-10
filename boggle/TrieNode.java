/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class TrieNode {
    private static final int R = 26;
    private TrieNode[] children;
    private boolean isEndOfWord;
    private String word;

    public TrieNode() {
        children = new TrieNode[R];
        isEndOfWord = false;
    }

    public void insert(String word) {
        TrieNode node = this;
        for (char c : word.toCharArray()) {
            int index = c - 'A';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true;
        node.word = word;
    }

    public TrieNode getChild(char c) {
        int index = c - 'A';
        return children[index];
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public String getWord() {
        return word;
    }
}