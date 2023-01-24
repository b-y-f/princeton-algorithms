import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WordNetTest {
    @Test
    public void pairsOfNounsAreVeryFarApart() {
        WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");
        int sca = wordnet.distance("white_marlin", "mileage");
        assertEquals(23, sca);

        int sca1 = wordnet.distance("Black_Plague", "black_marlin");
        assertEquals(33, sca1);

        int sca2 = wordnet.distance("Brown_Swiss", "barrel_roll");
        assertEquals(29, sca2);
    }

    @Test
    public void testTotalNumberOfNouns() {
        WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");
        int cnt = 0;
        for (String ignored : wordnet.nouns()) {
            cnt++;
        }
        assertEquals(119188, cnt);
    }

    @Test
    public void distanceWithRandomNounPairs() {
        WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");
        String nounA = "categorization";
        String nounB = "Evenk";

        assertEquals(12, wordnet.distance(nounA, nounB));
    }

    @Test
    public void distanceWithAllNounPairsA() {
        WordNet wordnet = new WordNet("synsets11.txt", "hypernyms11AmbiguousAncestor.txt");
        String nounA = "a";
        String nounB = "g";

        assertEquals(4, wordnet.distance(nounA, nounB));
    }

    @Test
    public void distanceWithAllNounPairsB() {
        WordNet wordnet = new WordNet("synsets8.txt", "hypernyms8ModTree.txt");
        String nounA = "f";
        String nounB = "h";

        assertEquals(2, wordnet.distance(nounA, nounB));
    }

    @Test
    public void distanceWithAllNounPairsC() {
        WordNet wordnet = new WordNet("synsets11.txt", "hypernyms11ManyPathsOneAncestor.txt");
        String nounA = "a";
        String nounB = "c";

        assertEquals(1, wordnet.distance(nounA, nounB));
    }

    @Test
    public void distanceWithAllNounPairsD() {
        WordNet wordnet = new WordNet("synsets8.txt", "hypernyms8WrongBFS.txt");
        String nounA = "a";
        String nounB = "d";

        assertEquals(2, wordnet.distance(nounA, nounB));
    }


    @Test
    public void distanceWithRandPairsA() {
        WordNet wordnet = new WordNet("synsets100-subgraph.txt", "hypernyms100-subgraph.txt");
        String nounA = "IgG";
        String nounB = "ricin_toxin";
        assertEquals(6, wordnet.distance(nounA, nounB));
    }

    @Test
    public void distanceWithRandPairsB() {
        WordNet wordnet = new WordNet("synsets500-subgraph..txt", "hypernyms500-subgraph.txt");
        String nounA = "rennin";
        String nounB = "linalool";
        assertEquals(7, wordnet.distance(nounA, nounB));
    }

    @Test
    public void distanceWithRandPairsC() {
        WordNet wordnet = new WordNet("synsets1000-subgraph.txt", "hypernyms1000-subgraph.txt");
        String nounA = "trigeminal";
        String nounB = "chymosin";
        assertEquals(15, wordnet.distance(nounA, nounB));
    }

    @Test
    public void sapWithRandomNounPairs() {
        WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");
        String nounA = "filter_paper";
        String nounB = "bioclimatology";
        assertEquals(17, wordnet.distance(nounA, nounB));
        assertEquals("abstraction abstract_entity", wordnet.sap(nounA, nounB));
    }

    @Test
    public void constructorWhenInputIsNotRootedDAG() {
        assertThrows(IllegalArgumentException.class, () -> {
            WordNet wordnet = new WordNet("synsets3.txt", "hypernyms3InvalidCycle.txt");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            WordNet wordnet = new WordNet("synsets6.txt", "hypernyms6InvalidCycle.txt");
        });
    }

}