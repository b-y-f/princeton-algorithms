import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;

public class SAP {


    private final Digraph G;

    /**
     * constructor takes a digraph (not necessarily a DAG)
     *
     * @param G
     */
    public SAP(Digraph G) {
        this.G = G;
    }


    /**
     * length of shortest ancestral path between v and w; -1 if no such path
     *
     * @param v
     * @param w
     */
    public int length(int v, int w) {
        return findAncestorAndDist(v, w)[0];
    }
    
    private int[] findAncestorAndDist(int v, int w) {
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(G, w);

        int distance = Integer.MAX_VALUE;
        int ancestor = -1;
        for (int i = 0; i < G.V(); i++) {
            if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
                int currDist = bfsV.distTo(i) + bfsW.distTo(i);
                if (currDist < distance) {
                    distance = currDist;
                    ancestor = i;
                }
            }
        }
        if (ancestor == -1) {
            distance = -1;
        }
        return new int[] { distance, ancestor };
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
        return findAncestorAndDist(v, w)[1];
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
    }
}
