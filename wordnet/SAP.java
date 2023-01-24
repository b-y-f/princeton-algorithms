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
        this.G = new Digraph(G);
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
        int shortest = Integer.MAX_VALUE;
        int ancestor = -1;
        validateVW(v, w);
        for (int vv : v) {
            for (int ww : w) {
                int currLength = length(vv, ww);
                if (currLength < shortest) {
                    shortest = currLength;
                    ancestor = ancestor(vv, ww);
                }
            }
        }
        if (ancestor == -1) {
            shortest = -1;
        }
        return shortest;
    }

    /**
     * a common ancestor that participates in shortest ancestral path; -1 if no such path
     *
     * @param v
     * @param w
     * @return
     */
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        validateVW(v, w);
        int shortest = Integer.MAX_VALUE;
        int ancestor = -1;
        validateVW(v, w);
        for (int vv : v) {
            for (int ww : w) {
                int currLength = length(vv, ww);
                if (currLength < shortest) {
                    shortest = currLength;
                    ancestor = ancestor(vv, ww);
                }
            }
        }
        return ancestor;
    }

    private void validateVW(Iterable<Integer> v, Iterable<Integer> w) {
        iterValidate(v);
        iterValidate(w);
    }

    private void iterValidate(Iterable<Integer> iter) {
        if (iter == null) {
            throw new IllegalArgumentException();
        }
        for (Integer item : iter) {
            if (item == null || item < 0) {
                throw new IllegalArgumentException();
            }
        }
    }

    /**
     * do unit testing of this class
     *
     * @param args
     */
    public static void main(String[] args) {
    }
}
