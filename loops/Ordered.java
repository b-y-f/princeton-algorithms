public class Ordered {

    private boolean isOrdered = false;


    public Ordered(String arg, String arg1, String arg2) {
        int a = Integer.parseInt(arg);
        int b = Integer.parseInt(arg1);
        int c = Integer.parseInt(arg2);

        if (a <= b && b <= c) {
            isOrdered = true;
        }
        if (a > b && b > c) {
            isOrdered = true;
        }
    }

    public boolean isOrdered() {
        return isOrdered;
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("need 3 args");
        }

        Ordered order = new Ordered(args[0], args[1], args[2]);
        System.out.println(order.isOrdered());
    }
}
