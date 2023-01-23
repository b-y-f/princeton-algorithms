import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {
    private final WordNet wn;

    /**
     * constructor takes a WordNet object
     */
    public Outcast(WordNet wordnet) {
        wn = wordnet;
    }

    /**
     * given an array of WordNet nouns, return an outcast
     *
     * @param nouns
     * @return
     */
    public String outcast(String[] nouns) {
        int currMax = -1;
        String outter = "";
        int[] dist = new int[nouns.length];

        // a,b,c,d,e
        for (int i = 0; i < nouns.length; i++) {
            String noun = nouns[i];
            int currScore = 0;
            for (String other : nouns) {
                if (!noun.equals(other) && wn.isNoun(noun)) {
                    currScore += wn.distance(noun, other);
                }
            }
            if (currScore > currMax) {
                currMax = currScore;
                outter = nouns[i];
            }
        }
        return outter;
    }

    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}
