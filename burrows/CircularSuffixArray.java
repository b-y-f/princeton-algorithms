/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Arrays;

public class CircularSuffixArray {
    private int n;
    private Integer[] indices;

    /**
     * circular suffix array of s
     *
     * @param s
     */
    public CircularSuffixArray(String s) {
        if (s == null) throw new IllegalArgumentException("s cannot be null");
        this.n = s.length();
        indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        Arrays.sort(indices, (i, j) -> {
            int k = 0;
            while (k < n) {
                char a = s.charAt((i + k) % n);
                char b = s.charAt((j + k) % n);
                if (a < b) return -1;
                if (a > b) return 1;
                k++;
            }
            return 0;
        });

    }

    public int length() {
        return n;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i >= n) throw new IllegalArgumentException("i is out of range");
        return indices[i];
    }

    // unit testing (required)
    public static void main(String[] args) {
        CircularSuffixArray csa = new CircularSuffixArray("ABRACADABRA!");
        for (int i = 0; i < csa.length(); i++) {
            System.out.println(csa.index(i));
        }
    }

}
