/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

import java.util.Arrays;

/**
 * > java-algs4 BurrowsWheeler - < abra.txt | java-algs4 BurrowsWheeler +
 * <p/>
 * ABRACADABRA!
 */
public class BurrowsWheeler {
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

        cleanup();
    }

    private static void cleanup() {
        BinaryStdIn.close();
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
        String[] s = new String[n];

        for (int ignore = 0; ignore < n; ignore++) {
            for (int j = 0; j < n; j++) {
                if (s[j] == null) {
                    s[j] = "";
                }
                s[j] = t.charAt(j) + s[j];
            }
            Arrays.sort(s);
        }

        BinaryStdOut.write(s[first]);
        cleanup();

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
