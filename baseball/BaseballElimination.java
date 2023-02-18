import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

public class BaseballElimination {
    private String[] teams;
    private int[] wins;
    private int[] losses;
    private int[] remaining;
    private int[][] against;
    private List<String> eliminated;

    public BaseballElimination(String filename) {
        In in = new In(filename);
        int n = in.readInt();
        parseData(in, n);
        checkElimination();
    }

    private void parseData(In in, int n) {
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
    }

    public int numberOfTeams() {
        return teams.length;
    }

    public Iterable<String> teams() {
        return null;
    }

    public int wins(String team) {
        int index = getTeamIndex(team);
        return remaining[index];
    }

    public int losses(String team) {
        int index = getTeamIndex(team);
        return losses[index];
    }

    public int remaining(String team) {
        int index = getTeamIndex(team);
        return remaining[index];
    }

    public int against(String team1, String team2) {
        int i = getTeamIndex(team1);
        int j = getTeamIndex(team2);
        return against[i][j];
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
