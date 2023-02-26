public class ColorHSB {
    private int hue;
    private int saturation;
    private int brightness;

    // Creates a color with hue h, saturation s, and brightness b.
    public ColorHSB(int h, int s, int b) {
        if (h < 0 || h >= 360 || s < 0 || s > 100 || b < 0 || b > 100) {
            throw new IllegalArgumentException("Invalid HSB values");
        }
        this.hue = h;
        this.saturation = s;
        this.brightness = b;
    }

    // Returns a string representation of this color, using the format (h, s, b).
    public String toString() {
        return "(" + hue + ", " + saturation + ", " + brightness + ")";
    }

    // Is this color a shade of gray?
    public boolean isGrayscale() {
        return saturation == 0 || brightness == 0;
    }

    // Returns the squared distance between the two colors.
    public int distanceSquaredTo(ColorHSB that) {
        int dh = Math.min(Math.abs(this.hue - that.hue), 360 - Math.abs(this.hue - that.hue));
        int ds = this.saturation - that.saturation;
        int db = this.brightness - that.brightness;
        return dh * dh + ds * ds + db * db;
    }

    // Sample client (see below).
    public static void main(String[] args) {
        int h = Integer.parseInt(args[0]);
        int s = Integer.parseInt(args[1]);
        int b = Integer.parseInt(args[2]);
        ColorHSB hsb = new ColorHSB(h, s, b);

        ColorHSB closestColor = null;
        String closestName = "";
        int minDistance = Integer.MAX_VALUE;

        while (!StdIn.isEmpty()) {
            String name = StdIn.readString();
            int h2 = StdIn.readInt();
            int s2 = StdIn.readInt();
            int b2 = StdIn.readInt();
            ColorHSB otherColor = new ColorHSB(h2, s2, b2);
            int distance = hsb.distanceSquaredTo(otherColor);
            if (distance < minDistance) {
                closestColor = otherColor;
                closestName = name;
                minDistance = distance;
            }
        }

        StdOut.printf("%s %s\n", closestName, closestColor.toString());

    }
}
