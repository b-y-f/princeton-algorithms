import edu.princeton.cs.algs4.Picture;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


class SeamCarverTest {
    @Test
    public void testEnergy3by4Image() {
        Picture picture = new Picture("3x4.png");
        SeamCarver sc = new SeamCarver(picture);

        assertEquals(Math.sqrt(52225), sc.energy(1, 1), 0.00001);
        assertEquals(Math.sqrt(52024), sc.energy(1, 2), 0.00001);

        // boarder should be 1000
        assertEquals(1000, sc.energy(0, 0));
        assertEquals(1000, sc.energy(2, 2));
    }

    @Test
    public void testVerticalSeam() {
        Picture picture = new Picture("6x5.png");
        SeamCarver sc = new SeamCarver(picture);

        int[] exp = new int[] { 3, 4, 3, 2, 2 };
        assertArrayEquals(exp, sc.findVerticalSeam());
    }

    @Test
    public void testHorizontalSeam() {
        Picture picture = new Picture("6x5.png");
        SeamCarver sc = new SeamCarver(picture);

        int[] exp = new int[] { 2, 2, 1, 2, 1, 2 };
        assertArrayEquals(exp, sc.findHorizontalSeam());
    }

}