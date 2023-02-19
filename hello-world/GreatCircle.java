public class GreatCircle {
    public static void main(String[] args) {

        // Parse the latitude and longitude values from the arguments and convert to radians
        double x1 = Math.toRadians(Double.parseDouble(args[0]));
        double y1 = Math.toRadians(Double.parseDouble(args[1]));
        double x2 = Math.toRadians(Double.parseDouble(args[2]));
        double y2 = Math.toRadians(Double.parseDouble(args[3]));

        // Calculate the great-circle distance using the Haversine formula
        double r = 6371.0; // mean radius of Earth in kilometers
        double distance = 2 * r * Math.asin(Math.sqrt(Math.pow(Math.sin((x2 - x1) / 2), 2) +
                                                              Math.cos(x1) * Math.cos(x2)
                                                                      * Math.pow(
                                                                      Math.sin((y2 - y1) / 2), 2)));

        // Output the result
        System.out.println(distance + " kilometers");
    }
}
