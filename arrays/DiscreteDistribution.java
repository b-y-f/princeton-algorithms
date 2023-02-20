/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

public class DiscreteDistribution {
    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);
        int[] a = new int[args.length - 1];
        int sum = 0;

        for (int i = 1; i < args.length; i++) {
            a[i - 1] = Integer.parseInt(args[i]);
            sum += a[i - 1];
        }

        int[] s = new int[args.length - 1];
        s[0] = a[0];
        for (int i = 1; i < s.length; i++) {
            s[i] = s[i - 1] + a[i];
        }

        for (int i = 0; i < m; i++) {
            int r = (int) (Math.random() * sum);
            int j = 0;
            while (r >= s[j]) {
                j++;
            }
            System.out.print(j + 1);
            if (i < m - 1) {
                System.out.print(" ");
            }
        }

        System.out.println();
    }
}

