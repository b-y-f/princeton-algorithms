import java.util.Arrays;

public class AudioCollage {

    // Returns a new array that rescales a[] by a multiplicative factor of alpha.
    public static double[] amplify(double[] a, double alpha) {
        double[] newA = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            newA[i] = a[i] * alpha;
        }
        return newA;
    }

    // Returns a new array that is the reverse of a[].
    public static double[] reverse(double[] a) {
        int n = a.length;
        int left = 0;
        double[] rev = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            rev[left] = a[i];
            left++;
        }
        return rev;
    }

    // Returns a new array that is the concatenation of a[] and b[].
    public static double[] merge(double[] a, double[] b) {
        int n = a.length + b.length;
        double[] merged = new double[n];

        for (int i = 0; i < a.length; i++) {
            merged[i] = a[i];
        }
        for (int i = 0; i < b.length; i++) {
            merged[i + a.length] = b[i];
        }
        return merged;
    }

    // Returns a new array that is the sum of a[] and b[],
    // padding the shorter arrays with trailing 0s if necessary.
    public static double[] mix(double[] a, double[] b) {
        double[] minArr;
        double[] maxArr;
        if (a.length > b.length) {
            minArr = b;
            maxArr = a;
        }
        else {
            minArr = a;
            maxArr = b;
        }

        double[] mixed = new double[maxArr.length];
        for (int i = 0; i < maxArr.length; i++) {
            mixed[i] = maxArr[i];
        }
        for (int i = 0; i < minArr.length; i++) {
            mixed[i] = maxArr[i] + minArr[i];
        }
        return mixed;
    }


    // Returns a new array that changes the speed by the given factor.
    public static double[] changeSpeed(double[] a, double alpha) {
        int newLength = (int) Math.round(a.length / alpha);
        double[] b = new double[newLength];
        double index, lower, upper, frac;
        int ilower;

        for (int i = 0; i < newLength; i++) {
            index = i * alpha;
            ilower = (int) index;
            frac = index - ilower;

            if (ilower >= a.length - 1) {
                lower = a[a.length - 2];
                upper = a[a.length - 1];
            }
            else {
                lower = a[ilower];
                upper = a[ilower + 1];
            }

            // Linear interpolation
            b[i] = (1 - frac) * lower + frac * upper;
        }

        return b;
    }

    // Creates an audio collage and plays it on standard audio.
    // See below for the requirements.
    public static void main(String[] args) {
        double[] a = new double[] { -0.4375, -0.5, -0.3125 };
        double[] b = new double[] { 0.25, -0.25 };
        System.out.println(Arrays.toString(mix(a, b)));
    }
}