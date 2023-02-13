/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
    private static final int ASCII = 256;
    private static char[] chars;


    /**
     * apply move-to-front encoding, reading from standard input and writing to standard output
     */
    public static void encode() {
        initChars();

        while (!BinaryStdIn.isEmpty()) {
            char inputChar = BinaryStdIn.readChar();
            for (int i = 0; i < ASCII; i++) {
                if (inputChar == chars[i]) {
                    BinaryStdOut.write(i, 8);
                    moveElementToFront(i);
                }
            }
        }
        cleanup();
    }

    /**
     * apply move-to-front decoding, reading from standard input and writing to standard output
     */
    public static void decode() {

        cleanup();
    }

    private static void cleanup() {
        BinaryStdIn.close();
        BinaryStdOut.close();
    }

    private static void moveElementToFront(int index) {
        char element = chars[index];
        for (int i = index; i > 0; i--) {
            chars[i] = chars[i - 1];
        }
        chars[0] = element;
    }

    private static void initChars() {
        chars = new char[ASCII];
        for (int i = 0; i < ASCII; i++) {
            chars[i] = (char) i;
        }
    }

    /**
     * if args[0] is "-", apply move-to-front encoding
     * <br/>
     * if args[0] is "+", apply move-to-front decoding
     */
    public static void main(String[] args) {
        if (args[0].equals("-")) {
            encode();
        }
        else if (args[0].equals("+")) {
            decode();
        }
        else {
            throw new IllegalArgumentException("wrong arguments");
        }
    }

}