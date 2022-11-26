package hello;

public class HelloGoodbye {
    public static void main(String[] args) {

        // Prints "Hello, World" in the terminal window.
        String hi = String.format("Hello %s and %s.", args[0], args[1]);
        String bye = String.format("Goodbye %s and %s.", args[1], args[0]);
        System.out.println(hi);
        System.out.println(bye);
    }
}