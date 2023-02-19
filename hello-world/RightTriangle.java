public class RightTriangle {
    public static void main(String[] args) {
        double side1 = Double.parseDouble(args[0]);
        double side2 = Double.parseDouble(args[1]);
        double side3 = Double.parseDouble(args[2]);

        double longestSide = Math.max(Math.max(side1, side2), side3);
        double sumOfSquares = Math.pow(side1, 2) + Math.pow(side2, 2) + Math.pow(side3, 2)
                - Math.pow(longestSide, 2);

        boolean isRightTriangle = Math.abs(sumOfSquares - Math.pow(longestSide, 2)) < 0.0001;

        System.out.println(isRightTriangle);
    }
}
