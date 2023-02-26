import java.util.Arrays;
import java.util.Collections;

public class BarChartRacer {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;
    private static In in;

    public static void main(String[] args) {
        // Load city population data from CSV file
        String fileName = args[0];
        // int k = Integer.parseInt(args[1]);
        in = new In(fileName);

        String TITLE = in.readLine();
        String X_AXIS_LABEL = in.readLine();
        String DATA_SOURCE = in.readLine();

        BarChart chart = new BarChart(TITLE, X_AXIS_LABEL, DATA_SOURCE);

        StdDraw.setCanvasSize(WIDTH, HEIGHT);
        StdDraw.enableDoubleBuffering();

        while (in.hasNextLine()) {
            chart.reset();

            int maxBarNumber = in.readInt();
            String time = "";
            Bar[] bars = new Bar[maxBarNumber];
            in.readLine();
            for (int i = 0; i < maxBarNumber; i++) {
                String line = in.readLine();
                String[] lines = line.split(",");
                time = lines[0];
                Bar bar = new Bar(lines[1], Integer.parseInt(lines[3]), lines[4]);
                bars[i] = bar;
            }
            Arrays.sort(bars, Collections.reverseOrder());

            for (Bar b : bars) {
                chart.add(b.getName(), b.getValue(), b.getCategory());
            }

            chart.setCaption(time);


            chart.draw();
            StdDraw.show();
            StdDraw.clear();
            StdDraw.pause(50);

        }
    }


}
