/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class TrieNode {
    private static final int R = 26;
    private TrieNode[] children;
    private boolean isWord;
    private String word;

    public TrieNode() {
        children = new TrieNode[R];
        isWord = false;
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
        node.isWord = true;
        node.word = word;
    }

    public TrieNode getChild(char c) {
        int index = c - 'A';
        return children[index];
    }

    public boolean isWord() {
        return isWord;
    }

    public String getWord() {
        return word;
    }
}