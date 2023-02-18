/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class BaseballElimination {
    private final String[] teams;
    private final int[] wins;
    private final int[] losses;
    private final int[] remaining;
    private final int[][] against;
    private final List<String> eliminated;

    public BaseballElimination(String filename) {
        In in = new In(filename);
        int n = in.readInt();

        teams = new String[n];
        wins = new int[n];
        losses = new int[n];
        remaining = new int[n];
        against = new int[n][n];
        eliminated = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            teams[i] = in.readString();
            wins[i] = in.readInt();
            losses[i] = in.readInt();
            remaining[i] = in.readInt();
            for (int j = 0; j < n; j++) {
                against[i][j] = in.readInt();
            }
        }

        checkElimination();
    }

    public int numberOfTeams() {
        return 0;
    }

    public Iterable<String> teams() {
        return null;
    }

    public int wins(String team) {
        return 0;
    }

    public int losses(String team) {
        return 0;
    }

    public int remaining(String team) {
        return 0;
    }

    public int against(String team1, String team2) {
        return 0;
    }

    public boolean isEliminated(String team) {
        return false;
    }

    /**
     * subset R of teams that eliminates given team; null if not eliminated
     *
     * @param team
     * @return
     */
    public Iterable<String> certificateOfElimination(String team) {
        return null;
    }

    public static void main(String[] args) {

    }
}
