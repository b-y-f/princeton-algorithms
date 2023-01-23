import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SAP {


    /**
     * constructor takes a digraph (not necessarily a DAG)
     *
     * @param G
     */
    public SAP(Digraph G) {
        if (G == null) {
            throw new IllegalArgumentException();
        }

        int[] dist1 = test(G, 1);
        System.out.println();
        int[] dist2 = test(G, 6);

        for (int i = dist1.length - 1; i >= 0; i--) {
            if (dist2[i] != 0 && dist1[i] != 0) {
                System.out.println("ancestor: " + i + ", dist: " + (dist2[i] + dist1[i]));
                break;
            }
        }
        System.out.println("ancestor: " + -1 + ", dist: " + -1);


    }

    private static int[] test(Digraph G, int init) {
        int[] edgeTo = new int[G.V()];
        int[] distTo = new int[G.V()];
        boolean[] marked = new boolean[G.V()];
        Queue<Integer> q = new Queue<>();

        q.enqueue(init);
        marked[init] = true;
        while (!q.isEmpty()) {
            int vec = q.dequeue();
            for (int wi : G.adj(vec)) {
                if (!marked[wi]) {
                    q.enqueue(wi);
                    marked[wi] = true;
                    distTo[wi] = 1 + distTo[vec];
                    edgeTo[wi] = vec;
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
     * @return
     */
    public int length(int v, int w) {
        return 0;
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
        return v;
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
