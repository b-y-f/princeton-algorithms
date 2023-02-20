/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

public class Birthday {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);  // number of days in a year
        int trials = Integer.parseInt(args[1]);  // number of trials to run

        int[] count = new int[n + 2];  // count[i] = number of trials with i people or fewer
        for (int t = 0; t < trials; t++) {
            boolean[] hasBirthday
                    = new boolean[n];  // hasBirthday[i] = true if someone has birthday i
            int people = 0;
            while (true) {
                people++;
                int birthday = (int) (Math.random() * n);
                if (hasBirthday[birthday]) {
                    break;
                }
                hasBirthday[birthday] = true;
            }
            count[Math.min(people, n + 1)]++;
        }

        double sum = 0;
        int i = 1;
        while (sum < 0.5) {
            sum += count[i] / (double) trials;
            System.out.println(i + "\t" + count[i] + "\t" + sum);
            i++;
        }
    }
}

