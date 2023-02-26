public class Divisors {

    // Returns the greatest common divisor of a and b.
    public static int gcd(int a, int b) {
        if (b == 0) {
            return Math.abs(a); // return absolute value of a if b is 0
        }
        return gcd(b, a % b); // recursively compute gcd with b and a mod b
    }

    // Returns the least common multiple of a and b.
    public static int lcm(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        else {
            int gcd = gcd(a, b);
            return Math.abs(a / gcd * b);
        }

    }

    // Returns true if a and b are relatively prime; false otherwise.
    public static boolean areRelativelyPrime(int a, int b) {
        return gcd(a, b) == 1; // a and b are relatively prime if their gcd is 1
    }

    // Returns the number of integers between 1 and n that are
    // relatively prime with n.
    public static int totient(int n) {
        if (n <= 0) {
            return 0; // totient is 0 if n <= 0
        }
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (areRelativelyPrime(i, n)) {
                count++;
            }
        }
        return count;
    }

    // Takes two integer command-line arguments a and b and prints
    // each function, evaluated in the format (and order) given below.
    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);

        System.out.printf("gcd(%d, %d) = %d\n", a, b, gcd(a, b));
        System.out.printf("lcm(%d, %d) = %d\n", a, b, lcm(a, b));
        System.out.printf("areRelativelyPrime(%d, %d) = %b\n", a, b, areRelativelyPrime(a, b));
        System.out.printf("totient(%d) = %d\n", a, totient(a));
    }
}
