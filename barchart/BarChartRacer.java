import java.util.Arrays;
import java.util.Collections;

public class BarChartRacer {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;
    private static final int PAUSE_FRAME = 50;

    public static void main(String[] args) {
        String fileName = args[0];
        int k = Integer.parseInt(args[1]);
        In in = new In(fileName);

        BarChart chart = initChart(in);

        while (in.hasNextLine()) {
            chart.reset();

            String totalBars = in.readLine();
            if (totalBars.isEmpty()) {
                continue;
            }
            int maxBarNumber = Integer.parseInt(totalBars);

            int linesSkip = 0;
            Bar[] bars;
            if (k < maxBarNumber) {
                bars = new Bar[k];
                linesSkip = maxBarNumber - k;
            }
            else {
                bars = new Bar[maxBarNumber];
            }
            
            loadBarsFromData(in, chart, bars);

            for (Bar b : bars) {
                chart.add(b.getName(), b.getValue(), b.getCategory());
            }

            skipLines(in, linesSkip);

            chart.draw();
            StdDraw.show();
            StdDraw.clear();
            StdDraw.pause(PAUSE_FRAME);
        }
    }

    private static void loadBarsFromData(In in, BarChart chart, Bar[] bars) {
        for (int i = 0; i < bars.length; i++) {
            String[] lines = in.readLine().split(",");
            chart.setCaption(lines[0]);
            bars[i] = new Bar(lines[1], Integer.parseInt(lines[3]), lines[4]);
        }
        Arrays.sort(bars, Collections.reverseOrder());
    }

    private static BarChart initChart(In in) {
        String TITLE = in.readLine();
        String X_AXIS_LABEL = in.readLine();
        String DATA_SOURCE = in.readLine();

        BarChart chart = new BarChart(TITLE, X_AXIS_LABEL, DATA_SOURCE);

        StdDraw.setCanvasSize(WIDTH, HEIGHT);
        StdDraw.enableDoubleBuffering();
        return chart;
    }

    private static void skipLines(In in, int linesSkip) {
        for (int i = 0; i < linesSkip; i++) {
            in.readLine();
        }
    }


}
