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

    private void checkElimination() {
        for (String t : teams) {
            isEliminated(t);
        }
    }

    private FlowNetwork buildFlowNetwork() {
        int n = numberOfTeams();
        int numVertices = 2 + n + n * (n - 1) / 2;
        FlowNetwork flowNetwork = new FlowNetwork(numVertices);

        // Constants for the vertex indices of source and sink
        int source = 0;
        int sink = 1;

        // Connect source to team vertices with edges of capacity equal
        // to the maximum number of wins
        for (int i = 0; i < n; i++) {
            int maxWins = wins[i] + remaining[i];
            flowNetwork.addEdge(new FlowEdge(source, 2 + i, maxWins));
        }

        // Connect remaining game vertices to source with edges of
        // capacity equal to the number of remaining games
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (i != j) {
                    int remainingGames = against[i][j];
                    int gameVertex = 2 + n + (i * (n - 1)) / 2 + (j - 1 - i);
                    flowNetwork.addEdge(new FlowEdge(gameVertex, source, remainingGames));
                }
            }
        }

        // Connect remaining game vertices to team vertices with edges of capacity infinity
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (i != j) {
                    int gameVertex = 2 + n + (i * (n - 1)) / 2 + (j - 1 - i);
                    flowNetwork.addEdge(new FlowEdge(gameVertex, 2 + i, Double.POSITIVE_INFINITY));
                    flowNetwork.addEdge(new FlowEdge(gameVertex, 2 + j, Double.POSITIVE_INFINITY));
                }
            }
        }

        // Connect team vertices to sink with edges of capacity equal to the
        // difference between the maximum number of wins and the current number of wins
        for (int i = 0; i < n; i++) {
            int maxWins = wins[i] + remaining[i];
            flowNetwork.addEdge(new FlowEdge(2 + i, sink, maxWins - wins[i]));
        }

        return flowNetwork;
    }

    private int againstLeader() {
        int maxWins = 0;
        int leaderIndex = 0;
        for (int i = 0; i < numberOfTeams(); i++) {
            if (wins[i] > maxWins) {
                maxWins = wins[i];
                leaderIndex = i;
            }
        }
        return leaderIndex;
    }

    private boolean isEliminated(String team) {
        int teamIndex = getTeamIndex(team);
        int n = numberOfTeams();

        // Check if the team is already eliminated
        if (wins[teamIndex] + remaining[teamIndex] < wins[againstLeader()]) {
            return true;
        }

        // Build the flow network
        FlowNetwork flowNetwork = buildFlowNetwork();

        // Compute the maximum flow using the Ford-Fulkerson algorithm
        FordFulkerson maxflow = new FordFulkerson(flowNetwork, 0, 1);

        // Check if the team is eliminated
        for (int i = 0; i < n; i++) {
            if (i == teamIndex) {
                continue;
            }
            int teamCapacity = wins[teamIndex] + remaining[teamIndex] - wins[i];
            int teamVertex = 2 + i;
            if (maxflow.inCut(teamVertex)) {
                return true;
            }
        }
        return false;
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
        Stack<String> iter = new Stack<>();
        for (String t : teams) {
            iter.push(t);
        }
        return iter;
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


    public Iterable<String> certificateOfElimination(String team) {
        return isEliminated(team) ? eliminated : null;
    }

    // Private helper methods

    private int getTeamIndex(String name) {
        for (int i = 0; i < teams.length; i++) {
            if (teams[i].equals(name)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Target value not found in array.");
    }


    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination(args[0]);
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            }
            else {
                StdOut.println(team + " is not eliminated");
            }
        }
    }

}
