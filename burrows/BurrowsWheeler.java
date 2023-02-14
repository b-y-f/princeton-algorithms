/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;


/**
 * > java-algs4 BurrowsWheeler - < abra.txt | java-algs4 BurrowsWheeler +
 * <p/>
 * ABRACADABRA!
 */
public class BurrowsWheeler {
    private static final int R = 256;

    /**
     * apply Burrows-Wheeler transform,
     * reading from standard input and writing to standard output
     */
    public static void transform() {
        String s = BinaryStdIn.readString();
        CircularSuffixArray csa = new CircularSuffixArray(s);
        StringBuilder t = new StringBuilder();
        int first = -1;
        for (int i = 0; i < csa.length(); i++) {
            if (csa.index(i) == 0) {
                first = i;
            }
            int position = (csa.index(i) + csa.length() - 1) % csa.length();
            t.append(s.charAt(position));
        }

        BinaryStdOut.write(first);
        BinaryStdOut.write(t.toString());

        BinaryStdOut.close();
    }


    /**
     * apply Burrows-Wheeler inverse transform,
     * reading from standard input and writing to standard output
     */
    public static void inverseTransform() {
        int first = BinaryStdIn.readInt();
        String t = BinaryStdIn.readString();

        int n = t.length();
        char[] original = new char[n];
        int[] next = new int[n];
        int[] freq = new int[R + 1];

        // calculate frequency table
        for (int i = 0; i < n; i++) {
            freq[t.charAt(i) + 1]++;
        }
        // calculate accumulate repeat
        for (int i = 0; i < R; i++) {
            freq[i + 1] += freq[i];
        }

        for (int i = 0; i < n; i++) {
            int j = freq[t.charAt(i)]++;
            original[j] = t.charAt(i);
            next[j] = i;
        }

        // reconstruct original string
        for (int i = 0; i < n; i++) {
            BinaryStdOut.write(original[first]);
            first = next[first];
        }

        BinaryStdOut.close();
    }

    /**
     * if args[0] is "-", apply Burrows-Wheeler transform
     * <br/>
     * if args[0] is "+", apply Burrows-Wheeler inverse transform
     */
    public static void main(String[] args) {
        if (args[0].equals("-")) {
            transform();
        }
        else if (args[0].equals("+")) {
            inverseTransform();
        }
        else {
            throw new IllegalArgumentException("wrong arguments");
        }
    }

}
