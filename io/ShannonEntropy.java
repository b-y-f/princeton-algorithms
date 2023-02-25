/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

public class ShannonEntropy {
    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);
        int[] freq = new int[m + 1]; // array to store the frequencies of each integer
        int n = 0; // total number of integers read from input

        while (!StdIn.isEmpty()) {
            int x = StdIn.readInt();
            if (x >= 1 && x <= m) {
                freq[x]++;
                n++;
            }
        }

        double entropy = 0.0;
        for (int i = 1; i <= m; i++) {
            double p = (double) freq[i] / n;
            if (p > 0) {
                entropy -= p * Math.log(p) / Math.log(2);
            }
        }

        StdOut.printf("%.4f\n", entropy);
    }
}
