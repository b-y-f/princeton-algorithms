/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

public class RandomWalkers {
    public static void main(String[] args) {
        int r = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        int totalSteps = 0;

        for (int i = 0; i < trials; i++) {
            int x = 0, y = 0;
            int steps = 0;

            while (Math.abs(x) + Math.abs(y) < r) {
                double rand = Math.random();
                if (rand < 0.25) x++;
                else if (rand < 0.5) x--;
                else if (rand < 0.75) y++;
                else y--;

                steps++;
            }

            totalSteps += steps;
        }

        double avgSteps = (double) totalSteps / trials;
        System.out.printf("average number of steps = %.5f\n", avgSteps);
    }
}
