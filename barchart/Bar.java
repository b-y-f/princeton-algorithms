import java.util.Arrays;

public class Bar implements Comparable<Bar> {
    private String name;
    private int value;
    private String category;

    public Bar(String name, int value, String category) {
        if (name == null || category == null || value < 0) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        this.name = name;
        this.value = value;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public String getCategory() {
        return category;
    }

    public int compareTo(Bar that) {
        if (that == null) {
            throw new NullPointerException("Argument is null");
        }
        return Integer.compare(this.value, that.value);
    }

    public static void main(String[] args) {
        // create an array of 10 bars
        Bar[] bars = new Bar[10];
        bars[0] = new Bar("Beijing", 22674, "East Asia");
        bars[1] = new Bar("Cairo", 19850, "Middle East");
        bars[2] = new Bar("Delhi", 27890, "South Asia");
        bars[3] = new Bar("Dhaka", 19633, "South Asia");
        bars[4] = new Bar("Mexico City", 21520, "Latin America");
        bars[5] = new Bar("Mumbai", 22120, "South Asia");
        bars[6] = new Bar("Osaka", 20409, "East Asia");
        bars[7] = new Bar("São Paulo", 21698, "Latin America");
        bars[8] = new Bar("Shanghai", 25779, "East Asia");
        bars[9] = new Bar("Tokyo", 38194, "East Asia");

        // sort in ascending order by value
        Arrays.sort(bars);

        // print the bars in ascending order by value
        for (Bar bar : bars) {
            System.out.printf("%s: %d (%s)%n", bar.getName(), bar.getValue(), bar.getCategory());
        }
    }
}
