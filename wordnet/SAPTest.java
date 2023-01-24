import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    public void testSAPwithAmbiguousCannotReach() {
        In in = new In("digraph-ambiguous-ancestor.txt");
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        int v = 0;
        int w = 9;
        int length = sap.length(v, w);
        int ancestor = sap.ancestor(v, w);
        assertEquals(2, length);
        assertEquals(9, ancestor);
    }

    @Test
    public void circleSAPDigraph2a() {
        In in = new In("digraph2.txt");
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        int v = 2;
        int w = 3;
        int length = sap.length(v, w);
        int ancestor = sap.ancestor(v, w);
        assertEquals(1, length);
        assertEquals(3, ancestor);
    }

    @Test
    public void circleSAPDigraph2b() {
        In in = new In("digraph2.txt");
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        int v = 1;
        int w = 5;
        int length = sap.length(v, w);
        int ancestor = sap.ancestor(v, w);
        assertEquals(2, length);
        assertEquals(0, ancestor);
    }

    @Test
    public void checkWhetherSAPIsImmutable() {
        In in = new In("digraph1.txt");
        Digraph G = new Digraph(in);
        int v = 0;
        int w = 3;

        SAP sap = new SAP(G);
        assertEquals(2, sap.length(v, w));
        G.addEdge(0, 3);
        assertEquals(2, sap.length(v, w));
    }

    @Test
    public void checkLengthAndAncestorWithIterableArgumentsA() {
        In in = new In("digraph-wordnet.txt");
        Digraph G = new Digraph(in);
        int[] v = new int[] { 20707, 34599 };
        int[] w = new int[] { 25528, 60119 };

        Iterable<Integer> iv = convertToIter(v);
        Iterable<Integer> iw = convertToIter(w);

        SAP sap = new SAP(G);
        assertEquals(11, sap.length(iv, iw));
    }

    @Test
    public void checkLengthAndAncestorWithIterableArgumentsB() {
        In in = new In("digraph-wordnet.txt");
        Digraph G = new Digraph(in);
        int[] v = new int[] { 40692, 53822, 66763 };
        int[] w = new int[] {
                563, 9065, 9209, 10484, 11248, 14475, 38148, 45678, 58277, 59757, 61220
        };

        Iterable<Integer> iv = convertToIter(v);
        Iterable<Integer> iw = convertToIter(w);

        SAP sap = new SAP(G);
        assertEquals(10, sap.length(iv, iw));
    }

    @Test
    public void zeroLengthIterableArgumentsA() {
        In in = new In("digraph-wordnet.txt");
        Digraph G = new Digraph(in);
        int[] v = new int[] { };
        int[] w = new int[] {
                18064, 40021, 66123, 68450, 69042
        };

        Iterable<Integer> iv = convertToIter(v);
        Iterable<Integer> iw = convertToIter(w);

        SAP sap = new SAP(G);
        assertEquals(-1, sap.length(iv, iw));
    }

    @Test
    public void zeroLengthIterableArgumentsB() {
        In in = new In("digraph-wordnet.txt");
        Digraph G = new Digraph(in);
        int[] v = new int[] { 24815, 38026, 48547, 68258, 72177 };
        int[] w = new int[] { };

        Iterable<Integer> iv = convertToIter(v);
        Iterable<Integer> iw = convertToIter(w);

        SAP sap = new SAP(G);
        assertEquals(-1, sap.length(iv, iw));
    }

    @Test
    public void zeroLengthIterableArgumentsC() {
        In in = new In("digraph-wordnet.txt");
        Digraph G = new Digraph(in);
        int[] v = new int[] { };
        int[] w = new int[] { };

        Iterable<Integer> iv = convertToIter(v);
        Iterable<Integer> iw = convertToIter(w);

        SAP sap = new SAP(G);
        assertEquals(-1, sap.length(iv, iw));
    }

    @Test
    public void iterableInvalidArguments() {
        assertThrows(IllegalArgumentException.class, () -> {
            In in = new In("digraph-wordnet.txt");
            Digraph G = new Digraph(in);
            int[] v = new int[] { 1, 2, 4, 5, 7, 9, 12 };

            Iterable<Integer> iv = convertToIter(v);
            Iterable<Integer> iw = null;

            SAP sap = new SAP(G);
            sap.length(iv, iw);
        });
    }

    @Test
    public void iterableInvalidArgumentsA() {
        assertThrows(IllegalArgumentException.class, () -> {
            In in = new In("digraph-wordnet.txt");
            Digraph G = new Digraph(in);
            int[] v = new int[] { 1, 2, 4, 5, 7, 9, 12 };

            Iterable<Integer> iv = convertToIter(v);
            Iterable<Integer> iw = null;

            SAP sap = new SAP(G);
            sap.length(iv, iw);
        });
    }

    @Test
    public void iterableInvalidArgumentsB() {
        assertThrows(IllegalArgumentException.class, () -> {
            In in = new In("digraph-wordnet.txt");
            Digraph G = new Digraph(in);
            int[] v = new int[] { 1, 2, 4, 5, 7, 9, 12, -1 };
            int[] w = new int[] { 3, 6, 8, 10, 11 };

            Iterable<Integer> iv = convertToIter(v);
            Iterable<Integer> iw = convertToIter(w);

            SAP sap = new SAP(G);
            sap.length(iv, iw);
        });
    }


    private static Iterable<Integer> convertToIter(int[] v) {
        Iterable<Integer> iv = () -> Arrays.stream(v).iterator();
        return iv;
    }

}