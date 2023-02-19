public class RightTriangle {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Please provide three arguments as the sides of a triangle.");
            return;
        }

        double side1 = Double.parseDouble(args[0]);
        double side2 = Double.parseDouble(args[1]);
        double side3 = Double.parseDouble(args[2]);

        // Determine if the given sides form a right-angled triangle
        boolean isRightTriangle = false;
        if (Math.pow(side1, 2) + Math.pow(side2, 2) == Math.pow(side3, 2) ||
                Math.pow(side2, 2) + Math.pow(side3, 2) == Math.pow(side1, 2) ||
                Math.pow(side3, 2) + Math.pow(side1, 2) == Math.pow(side2, 2)) {
            isRightTriangle = true;
        }
        System.out.println(isRightTriangle);
    }
}
