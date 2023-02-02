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

        int[] exp = new int[] { 4, 4, 3, 2, 2 };
        int[] vIndex = sc.findVerticalSeam();
        assertArrayEquals(exp, vIndex);
    }

    @Test
    public void testHorizontalSeam() {
        Picture picture = new Picture("6x5.png");
        SeamCarver sc = new SeamCarver(picture);

        int[] exp = new int[] { 2, 2, 1, 2, 1, 1 };
        int[] hIndex = sc.findHorizontalSeam();
        assertArrayEquals(exp, hIndex);
    }

    @Test
    public void testHorizontalSeam5By6() {
        Picture picture = new Picture("5x6.png");
        SeamCarver sc = new SeamCarver(picture);

        double exp = 2583.198933;
        double totalEnergy = 0;
        int[] seam = sc.findHorizontalSeam();
        for (int i = 0; i < seam.length; i++) {
            totalEnergy += sc.energy(i, seam[i]);
        }

        assertEquals(exp, totalEnergy, 0.01);
    }

    @Test
    public void testVerticalSeam5By6() {
        Picture picture = new Picture("5x6.png");
        SeamCarver sc = new SeamCarver(picture);

        double exp = 2769.528866;
        double totalEnergy = 0;
        int[] seam = sc.findVerticalSeam();
        for (int i = 0; i < seam.length; i++) {
            totalEnergy += sc.energy(seam[i], i);
        }

        assertEquals(exp, totalEnergy, 0.01);
    }


}