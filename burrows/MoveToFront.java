/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
    private static final int ASCII = 256;


    /**
     * apply move-to-front encoding, reading from standard input and writing to standard output
     */
    public static void encode() {
        char[] chars = initChars();

        while (!BinaryStdIn.isEmpty()) {
            char inputChar = BinaryStdIn.readChar();
            for (int i = 0; i < ASCII; i++) {
                if (inputChar == chars[i]) {
                    BinaryStdOut.write(i, 8);
                    moveElementToFront(i, chars);
                }
            }
        }

        BinaryStdOut.close();
    }

    /**
     * apply move-to-front decoding, reading from standard input and writing to standard output
     */
    public static void decode() {
        char[] chars = initChars();

        while (!BinaryStdIn.isEmpty()) {
            char index = BinaryStdIn.readChar();
            char c = chars[index];
            BinaryStdOut.write(c);
            moveElementToFront(index, chars);
        }

        BinaryStdOut.close();
    }


    private static void moveElementToFront(int index, char[] old) {
        char element = old[index];
        for (int i = index; i > 0; i--) {
            old[i] = old[i - 1];
        }
        old[0] = element;
    }

    private static char[] initChars() {
        char[] chars = new char[ASCII];
        for (int i = 0; i < ASCII; i++) {
            chars[i] = (char) i;
        }
        return chars;
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