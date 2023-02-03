import edu.princeton.cs.algs4.Picture;
import org.junit.jupiter.api.Test;

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
    public void test5By6() {
        testTotalEnergyWithPicture("5x6.png", 2583.198933, 0);
        testTotalEnergyWithPicture("5x6.png", 2769.528866, 1);
    }

    @Test
    public void test12By10() {
        testTotalEnergyWithPicture("12x10.png", 3311.007347, 1);
        testTotalEnergyWithPicture("12x10.png", 3878.866388, 0);
    }


    @Test
    public void test10x10() {
        testTotalEnergyWithPicture("10x10.png", 3260.892911, 0);
        testTotalEnergyWithPicture("10x10.png", 3348.051236, 1);
    }

    @Test
    public void test4x6() {
        testTotalEnergyWithPicture("4x6.png", 2346.424595, 0);
        testTotalEnergyWithPicture("4x6.png", 2706.370116, 1);
    }

    @Test
    public void testDiagonals() {
        testTotalEnergyWithPicture("diagonals.png", 8246.198844, 0);
        testTotalEnergyWithPicture("diagonals.png", 6372.339191, 1);
    }

    @Test
    public void testStripes() {
        testTotalEnergyWithPicture("stripes.png", 5091.710692, 0);
        testTotalEnergyWithPicture("stripes.png", 6416.729559, 1);
    }


    private void testTotalEnergyWithPicture(String filename, double expectedEnergy,
                                            int type) {
        Picture picture = new Picture(filename);
        SeamCarver sc = new SeamCarver(picture);

        double totalEnergy = 0;
        // 0 means Horizontal
        if (type == 0) {
            int[] seam = sc.findHorizontalSeam();
            for (int i = 0; i < seam.length; i++) {
                totalEnergy += sc.energy(i, seam[i]);
            }
        }
        // 1 means Vertical
        else {
            int[] seam = sc.findVerticalSeam();
            for (int i = 0; i < seam.length; i++) {
                totalEnergy += sc.energy(seam[i], i);
            }
        }


        assertEquals(expectedEnergy, totalEnergy, 0.01);
    }


}