import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

import java.util.HashMap;

public class WordNet {

    private final SAP sap;
    private ST<String, Bag<Integer>> nouns;

    private HashMap<Integer, String> idToSynetString;

    /**
     * constructor takes the name of the two input files
     *
     * @param synsets
     * @param hypernyms
     */
    public WordNet(String synsets, String hypernyms) {
        In inSynsets = new In(synsets);
        In inHypers = new In(hypernyms);

        sap = new SAP(createDigraph(inHypers));
        createNouns(inSynsets);
    }

    private void createNouns(In inSynsets) {
        nouns = new ST<>();
        idToSynetString = new HashMap<>();

        for (String s : inSynsets.readAllLines()) {
            String[] splitted = s.split(",");
            int id = Integer.parseInt(splitted[0]);
            String synets = splitted[1];
            String[] splittedNouns = splitted[1].split(" ");
            idToSynetString.put(id, synets);
            for (String n : splittedNouns) {
                if (!nouns.contains(n)) {
                    Bag<Integer> ids = new Bag<>();
                    ids.add(id);
                    nouns.put(n, ids);
                }
                else {
                    nouns.get(n).add(id);
                }
            }
        }
    }

    private Digraph createDigraph(In inHypers) {
        String[] allHypers = inHypers.readAllLines();
        Digraph g = new Digraph(allHypers.length);
        for (String line : allHypers) {
            String[] items = line.split(",");
            int v = Integer.parseInt(items[0]);
            for (int i = 0; i < items.length - 1; i++) {
                int w = Integer.parseInt(items[1]);
                g.addEdge(v, w);
            }
        }
        DirectedCycle circle = new DirectedCycle(g);
        if (circle.hasCycle()) {
            throw new IllegalArgumentException();
        }
        return g;
    }

    /**
     * returns all WordNet nouns without repeat
     *
     * @return
     */
    public Iterable<String> nouns() {
        return nouns;
    }

    /**
     * is the word a WordNet noun?
     *
     * @param word
     * @return if this is noun from synsets
     */
    public boolean isNoun(String word) {
        if (word == null) {
            throw new IllegalArgumentException();
        }
        return nouns.contains(word);
    }

    /**
     * distance between nounA and nounB (defined below), use nounA's id and noun B's
     * ID to find the length by class SAP.
     *
     * @param nounA
     * @param nounB
     * @return shortest distance with common ancestor
     */
    public int distance(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        return getDistance(nounA, nounB);
    }

    /**
     * this function help to find ids from nounA and nounB
     *
     * @param nounA
     * @param nounB
     * @return tuple of the shortest length to common ancestor and ancestor's id
     */
    private int getDistance(String nounA, String nounB) {
        Bag<Integer> vs = nouns.get(nounA);
        Bag<Integer> ws = nouns.get(nounB);
        return sap.length(vs, ws);
    }

    private int getAncestor(String nounA, String nounB) {
        Bag<Integer> vs = nouns.get(nounA);
        Bag<Integer> ws = nouns.get(nounB);
        return sap.ancestor(vs, ws);
    }

    /**
     * a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
     * in a shortest ancestral path (defined below)
     *
     * @param nounA
     * @param nounB
     * @return ancestor's name
     */
    public String sap(String nounA, String nounB) {
        int ancester = getAncestor(nounA, nounB);
        return String.join(" ", idToSynetString.get(ancester));
    }

    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        System.out.println(wordnet.sap("punk", "waif"));
    }
}