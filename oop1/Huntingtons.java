public class Huntingtons {

    public static int maxRepeats(String dna) {
        String dnaClean = removeWhitespace(dna);
        int max = 0;
        int count = 0;
        int i = 0;
        while (i < dnaClean.length() - 2) {
            if (dnaClean.substring(i, i + 3).equals("CAG")) {
                count++;
                i += 3;
            }
            else {
                max = Math.max(max, count);
                count = 0;
                i++;
            }
        }
        return Math.max(max, count);
    }

    public static String removeWhitespace(String s) {
        return s.replaceAll("\\s", "");
    }

    public static String diagnose(int maxRepeats) {
        if (maxRepeats < 10 || maxRepeats > 180) {
            return "not human";
        }
        else if (maxRepeats < 36) {
            return "normal";
        }
        else if (maxRepeats < 40) {
            return "high risk";
        }
        else {
            return "Huntington's";
        }
    }

    public static void main(String[] args) {
        In In = new In(args[0]);
        int repeat = maxRepeats(In.readAll());
        System.out.println("max repeats = " + repeat);
        System.out.println(diagnose(repeat));
    }
}
