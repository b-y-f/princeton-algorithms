/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

public class ThueMorse {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        boolean[] sequence = new boolean[n * n];
        for (int i = 1; i < n * n; i = i + i) {
            for (int j = 0; j < i && i + j < n * n; j++) {
                sequence[i + j] = !sequence[j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (sequence[i * n + j]) {
                    System.out.print("- ");
                }
                else {
                    System.out.print("+ ");
                }
            }
            System.out.println();
        }
    }
}

