public class WordNet {

    /**
     * constructor takes the name of the two input files
     *
     * @param synsets
     * @param hypernyms
     */
    public WordNet(String synsets, String hypernyms) {
    }

    /**
     * returns all WordNet nouns
     *
     * @return
     */
    public Iterable<String> nouns() {
        return null;
    }

    /**
     * is the word a WordNet noun?
     *
     * @param word
     * @return
     */
    public boolean isNoun(String word) {
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
        return nounA;
    }

    public static void main(String[] args) {
    }
}