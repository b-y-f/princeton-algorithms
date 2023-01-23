import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

import java.util.HashMap;
import java.util.Map;

public class WordNet {

    private final SAP sap;
    private final Map<Integer, String[]> nouns;

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
        nouns = createNouns(inSynsets);
    }

    private Map<Integer, String[]> createNouns(In inSynsets) {
        Map<Integer, String[]> nounsDict = new HashMap<>();
        for (String s : inSynsets.readAllLines()) {
            String[] splitted = s.split(",");
            int id = Integer.parseInt(splitted[0]);
            String[] splittedNouns = splitted[1].split(" ");
            nounsDict.put(id, splittedNouns);
        }
        return nounsDict;
    }

    private Digraph createDigraph(In inHypers) {
        String[] allHypers = inHypers.readAllLines();
        Digraph g = new Digraph(allHypers.length);
        for (String line : allHypers) {
            String[] items = line.split(",");
            int length = items.length;
            int v = Integer.parseInt(items[0]);
            if (length == 1) {
                continue;
            }
            for (int i = 0; i < length - 1; i++) {
                int w = Integer.parseInt(items[1]);
                g.addEdge(v, w);
            }
        }
        return g;
    }

    /**
     * returns all WordNet nouns
     *
     * @return
     */
    public Iterable<String> nouns() {
        Stack<String> res = new Stack<>();
        for (String[] words : nouns.values()) {
            for (String n : words) {
                res.push(n);
            }
        }
        return res;
    }

    /**
     * is the word a WordNet noun?
     *
     * @param word
     * @return if this is noun from synsets
     */
    public boolean isNoun(String word) {

        return false;
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

        Stack<Integer> vs = new Stack<>();
        Stack<Integer> ws = new Stack<>();
        for (Map.Entry<Integer, String[]> entry : nouns.entrySet()) {
            for (String word : entry.getValue()) {
                if (word.equals(nounA)) {
                    vs.push(entry.getKey());
                }
                if (word.equals(nounB)) {
                    ws.push(entry.getKey());
                }
            }
        }
        for (int v : vs) {
            for (int w : ws) {
                System.out.print(v + "\t" + w + "\t" + sap.length(v, w) + "\t");
                System.out.println(sap.ancestor(v, w));
            }
        }
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
        wordnet.distance("punk", "waif");
    }
}