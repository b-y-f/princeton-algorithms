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
    /**
     * apply Burrows-Wheeler transform,
     * reading from standard input and writing to standard output
     */
    public static void transform() {
        String s = BinaryStdIn.readString();
        CircularSuffixArray csa = new CircularSuffixArray(s);
        StringBuilder sb = new StringBuilder();
        int first = -1;
        for (int i = 0; i < csa.length(); i++) {
            if (csa.index(i) == 0) {
                first = i;
            }
            int position = (csa.index(i) + csa.length() - 1) % csa.length();
            sb.append(s.charAt(position));
        }
        // System.out.println(first);
        // System.out.println(sb.toString());

        BinaryStdOut.write(first);
        BinaryStdOut.write(sb.toString());

        BinaryStdIn.close();
        BinaryStdOut.close();
    }

    /**
     * apply Burrows-Wheeler inverse transform,
     * reading from standard input and writing to standard output
     */
    public static void inverseTransform() {
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
        // BWT: annb$aa
        // Inverse BWT: banana
    }

}
