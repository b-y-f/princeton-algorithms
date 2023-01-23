import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.HashMap;

public class WordNet {

    private HashMap<Integer, String> nouns;

    /**
     * constructor takes the name of the two input files
     *
     * @param synsets
     * @param hypernyms
     */
    public WordNet(String synsets, String hypernyms) {
        In inSynsets = new In(synsets);
        In inHypers = new In(hypernyms);
        nouns = new HashMap<>();
        for (String s : inSynsets.readAllLines()) {
            String[] splitted = s.split(",");
            int id = Integer.parseInt(splitted[0]);
            String noun = splitted[1];
            nouns.put(id, noun);
        }
        String[] allHypers = inHypers.readAllLines();
        Digraph G = new Digraph(allHypers.length);
        for (String line : allHypers) {
            String[] items = line.split(",");
            int length = items.length;
            int v = Integer.parseInt(items[0]);
            if (length == 1) {
                continue;
            }
            for (int i = 0; i < length - 1; i++) {
                int w = Integer.parseInt(items[1]);
                G.addEdge(v, w);
            }

        }
    }

    /**
     * returns all WordNet nouns
     *
     * @return
     */
    public Iterable<String> nouns() {
        return nouns.values();
    }

    /**
     * is the word a WordNet noun?
     *
     * @param word
     * @return if this is noun from synsets
     */
    public boolean isNoun(String word) {
        for (String w : nouns.values()) {
            if (word.equals(w)) {
                return true;
            }
        }
        return false;
    }

    /**
     * distance between nounA and nounB (defined below)
     *
     * @param nounA
     * @param nounB
     * @return
     */
    public int distance(String nounA, String nounB) {
        return 0;
    }

    /**
     * a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
     * in a shortest ancestral path (defined below)
     *
     * @param nounA
     * @param nounB
     * @return
     */
    public String sap(String nounA, String nounB) {
        return "";
    }

    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        System.out.println(wordnet);
    }
}