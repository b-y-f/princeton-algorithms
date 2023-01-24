import org.junit.jupiter.api.Test;

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
}