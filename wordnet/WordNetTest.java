import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    /**
     * there are 5 IDs associated with worm
     * 81679 worm
     * 81680 worm
     * 81681 worm
     * 81682 worm louse insect dirt_ball
     */
    @Test
    public void containWorm() {
        WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");
        for (Map.Entry<Integer, String[]> entry : wordnet.getAllNouns().entrySet()) {
            for (String s : entry.getValue()) {
                if (s.equals("worm")) {
                    System.out.println(entry.getKey() + "\t" + Arrays.toString(entry.getValue()));
                }
            }
        }
    }

    @Test
    public void containBird() {
        WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");
        for (Map.Entry<Integer, String[]> entry : wordnet.getAllNouns().entrySet()) {
            for (String s : entry.getValue()) {
                if (s.equals("bird")) {
                    System.out.println(entry.getKey() + "\t" + Arrays.toString(entry.getValue()));
                }
            }
        }
    }

    @Test
    public void validateEdgeAndVertex() {
        WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");
        SAP sap = wordnet.getSAP();
        assertEquals(82192, sap.getNumOfVertex());
        assertEquals(84505, sap.getNumOfEdges());
    }
}