public class Trie {
    public TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        root.insert(word);
    }

    public TrieNode find(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node = node.getChild(c);
            if (node == null) {
                return null;
            }
        }
        return node;
    }
}
