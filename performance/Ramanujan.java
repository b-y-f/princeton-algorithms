import java.util.HashMap;
import java.util.Map;

public class Ramanujan {

    // Is n a Ramanujan number?
    public static boolean isRamanujan(long n) {
        // Calculate the cube root of n
        long cbrt = (long) Math.cbrt(n);

        // Create a hash table to store the cube roots
        Map<Long, Integer> cubeRoots = new HashMap<>();

        // Loop through all possible cube roots up to the cube root of n
        for (long i = 1; i <= cbrt; i++) {
            long cube = i * i * i;
            cubeRoots.put(cube, (int) i);
            long complement = n - cube;
            Integer complementRoot = cubeRoots.get(complement);
            if (complementRoot != null && complementRoot != i) {
                // Found two distinct pairs of cube roots that add up to n
                return true;
            }
        }

        // Could not find two distinct pairs of cube roots that add up to n
        return false;
    }

    // Takes a long integer command-line arguments n and prints true if
    // n is a Ramanujan number, and false otherwise.
    public static void main(String[] args) {
        long n = Long.parseLong(args[0]);
        System.out.println(isRamanujan(n));
    }
}
