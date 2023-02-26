public class Clock {
    private int hours;
    private int minutes;

    public Clock(int h, int m) {
        if (h < 0 || h > 23 || m < 0 || m > 59) {
            throw new IllegalArgumentException("Invalid time");
        }
        hours = h;
        minutes = m;
    }

    public Clock(String s) {
        if (!s.matches("\\d{2}:\\d{2}")) {
            throw new IllegalArgumentException("Invalid time string format");
        }
        int h = Integer.parseInt(s.substring(0, 2));
        int m = Integer.parseInt(s.substring(3, 5));
        if (h < 0 || h > 23 || m < 0 || m > 59) {
            throw new IllegalArgumentException("Invalid time");
        }
        hours = h;
        minutes = m;
    }

    public String toString() {
        String hourStr = String.format("%02d", hours);
        String minStr = String.format("%02d", minutes);
        return hourStr + ":" + minStr;
    }

    public boolean isEarlierThan(Clock that) {
        if (this.hours < that.hours) {
            return true;
        }
        else if (this.hours == that.hours && this.minutes < that.minutes) {
            return true;
        }
        else {
            return false;
        }
    }

    public void tic() {
        minutes++;
        if (minutes == 60) {
            minutes = 0;
            hours++;
            if (hours == 24) {
                hours = 0;
            }
        }
    }

    public void toc(int delta) {
        if (delta < 0) {
            throw new IllegalArgumentException("Delta must be non-negative");
        }
        int totalMinutes = hours * 60 + minutes + delta;
        hours = (totalMinutes / 60) % 24;
        minutes = totalMinutes % 60;
    }

    public static void main(String[] args) {
    }
}
