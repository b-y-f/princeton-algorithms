public class Trie {
    private TrieNode root;

    public Trie() {
        setRoot(new TrieNode());
    }

    public void insert(String word) {
        getRoot().insert(word);
    }

    public TrieNode find(String word) {
        TrieNode node = getRoot();
        for (char c : word.toCharArray()) {
            node = node.getChild(c);
            if (node == null) {
                return null;
            }
        }
        return node;
    }

    public TrieNode getRoot() {
        return root;
    }

    public void setRoot(TrieNode root) {
        this.root = root;
    }
}
