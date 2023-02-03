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
    public void testHorizontalSeam5By6() {
        testTotalEnergyWithPicture("5x6.png", 2583.198933, 0);
    }

    @Test
    public void testVerticalSeam5By6() {
        testTotalEnergyWithPicture("5x6.png", 2769.528866, 1);
    }

    @Test
    public void testVerticalSeam12By10() {
        testTotalEnergyWithPicture("12x10.png", 3311.007347, 1);
    }

    @Test
    public void testHorizotalSeam12By10() {
        testTotalEnergyWithPicture("12x10.png", 3878.866388, 0);
    }

    @Test
    public void testDiagonals() {
        testTotalEnergyWithPicture("10x10.png", 3260.892911, 0);
        testTotalEnergyWithPicture("10x10.png", 3348.051236, 1);
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