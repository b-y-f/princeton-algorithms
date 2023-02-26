public class Inversions {

    /**
     * computes the number of inversions in an array of integers using a nested
     * loop that iterates over all pairs of elements in the array.
     *
     * @param a
     * @return total count of inversions.
     */
    public static long count(int[] a) {
        int n = a.length;
        long count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[i] > a[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * creates an array of integers containing the numbers 0 to n-1 in ascending order,
     * and then applies a series of swaps to create a permutation
     * with exactly k inversions. It uses a while loop to repeatedly
     * swap elements in the array until the desired number of inversions has
     * been achieved. The algorithm works by repeatedly swapping adjacent elements
     * until there are no more inversions in the first half of the array.
     * Then it swaps the smallest element in the second half of the array with
     * the largest element in the first half, creating n-1 new inversions.
     * If k is still greater than n-1, the algorithm repeats the
     * process with the remaining inversions until k is zero.
     *
     * @param n
     * @param k
     * @return
     */
    public static int[] generate(int n, long k) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }

        int lo = 0;
        int hi = n - 1;
        while (lo < hi && k > 0) {
            if (k >= hi - lo) {
                k -= hi - lo;
                swap(a, lo, hi);
                lo++;
                hi--;
            }
            else {
                swap(a, (int) (lo + k), hi);
                k = 0;
            }
        }
        return a;
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    // Takes an integer n and a long k as command-line arguments,
    // and prints a permutation of length n with exactly k inversions.
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        long k = Long.parseLong(args[1]);
        int[] a = generate(n, k);
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}
