import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SAPTest {
    @Test
    public void testSAPwithFile1() {
        In in = new In("digraph1.txt");
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        int v = 3;
        int w = 11;
        int length = sap.length(v, w);
        int ancestor = sap.ancestor(v, w);
        assertEquals(4, length);
        assertEquals(1, ancestor);
    }

    @Test
    public void testSAPwithAmbiguous() {
        In in = new In("digraph-ambiguous-ancestor.txt");
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        int v = 0;
        int w = 6;
        int length = sap.length(v, w);
        int ancestor = sap.ancestor(v, w);
        assertEquals(5, length);
        assertEquals(2, ancestor);
    }

}