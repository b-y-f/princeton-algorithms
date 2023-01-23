import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SAP {


    private final Digraph G;
    private int distance;
    private int ancestor;

    /**
     * constructor takes a digraph (not necessarily a DAG)
     *
     * @param G
     */
    public SAP(Digraph G) {
        if (G == null) {
            throw new IllegalArgumentException();
        }
        this.G = G;
    }

    private int[] helper(Digraph g, int fromVertex) {
        int[] distTo = new int[g.V()];
        boolean[] marked = new boolean[g.V()];
        Queue<Integer> q = new Queue<>();

        q.enqueue(fromVertex);
        marked[fromVertex] = true;
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    q.enqueue(w);
                    marked[w] = true;
                    distTo[w] = 1 + distTo[v];
                }
            }
        }
        return distTo;
    }

    /**
     * length of shortest ancestral path between v and w; -1 if no such path
     *
     * @param v
     * @param w
     */
    public int length(int v, int w) {
        findAncestorAndDist(v, w);
        return distance;
    }

    private void findAncestorAndDist(int v, int w) {
        int[] dist1 = helper(G, v);
        int[] dist2 = helper(G, w);
        for (int i = dist1.length - 1; i >= 0; i--) {
            if (dist2[i] != 0 && dist1[i] != 0) {
                distance = dist1[i] + dist2[i];
                ancestor = i;
                return;
            }
        }
        distance = -1;
        ancestor = -1;
    }

    /**
     * a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such
     * path
     *
     * @param v
     * @param w
     * @return
     */
    public int ancestor(int v, int w) {
        findAncestorAndDist(v, w);
        return ancestor;
    }

    /**
     * length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such
     * path
     *
     * @param v
     * @param w
     * @return
     */
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        return 0;
    }

    /**
     * a common ancestor that participates in shortest ancestral path; -1 if no such path
     *
     * @param v
     * @param w
     * @return
     */
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        return 0;
    }

    /**
     * do unit testing of this class
     *
     * @param args
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
