package ClassesTest;

import Classes.CGeo;
import Classes.CNode;
import Classes.G;
import Classes.GA;
import Interfaces.DirectedWeightedGraph;
import Interfaces.DirectedWeightedGraphAlgorithms;
import Interfaces.NodeData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GATest {
    private static final DirectedWeightedGraph g = new G();
    private static final DirectedWeightedGraphAlgorithms ga = new GA();

    @BeforeAll
    static void createG() {
        // 1-5
        for (int i = 1; i < 6; i++) {
            NodeData n = new CNode(i, new CGeo(i, i, i), 0, "White", -1);
            g.addNode(n);
        }
        g.connect(1, 2, 1.1);
        g.connect(1, 4, 1.2);
        g.connect(2, 3, 4);
        g.connect(3, 4, 1.4);
        g.connect(3, 1, 1.6);
        g.connect(4, 5, 1.8);
        g.connect(5, 3, 1.0);
        ga.init(g);
    }

    /**
     * checking: init, getGraph and copy methods
     */
    @Test
    void init_getGraph_copy() {

        DirectedWeightedGraph gCopy = new G((G) g);
        DirectedWeightedGraphAlgorithms gaCopy = new GA();
        gaCopy.init(gCopy); // reset the graph by init
        assertEquals(gCopy, gaCopy.getGraph()); // equals cause

        DirectedWeightedGraph gc = gaCopy.copy(); // check the copy
        assertEquals(gc.toString(), gCopy.toString());

    }

    @Test
    void isConnected() {

        assertTrue(ga.isConnected()); // the graph that made BeforeAll
        // build another graph that not connected
        NodeData nCheck1 = new CNode(6, new CGeo(6, 6, 6), 6 + 1.1, "White", -1);
        NodeData nCheck2 = new CNode(7, new CGeo(7, 7, 7), 7 + 1.1, "White", -1);
        DirectedWeightedGraph g1 = new G();
        DirectedWeightedGraphAlgorithms ga1 = new GA();
        g1.addNode(nCheck1);
        g1.addNode(nCheck2);
        ga1.init(g1);
        assertFalse(ga1.isConnected());
    }

    /**
     * return the shortestPathDist (double)
     */
    @Test
    void shortestPathDist() {

        double ans = ga.shortestPathDist(1, 3);
        assertEquals(ans, 4.0);
        ans = ga.shortestPathDist(1, 1);
        assertEquals(ans, 0);
    }

    /**
     * return list<NodeData> of the shortest Path in the graph
     */
    @Test
    void shortestPath() {
        List<NodeData> ans = ga.shortestPath(1, 3);
        assertEquals(ans.size(), 4);
        ans = ga.shortestPath(1, 1);
        assertEquals(ans.size(), 1);
    }

    @Test
    void center() {
        DirectedWeightedGraph gg = new G();
        DirectedWeightedGraphAlgorithms gga = new GA();

        for (int i = 1; i < 4; i++) {
            NodeData n = new CNode(i, new CGeo(i, i, i), 0, "White", -1);
            gg.addNode(n);
        }
        gg.connect(1, 2, 1.3);
        gg.connect(2, 1, 1.3);
        gg.connect(1, 3, 2.7);
        gg.connect(3, 1, 1.0);

        gga.init(gg);

        System.out.println(gga.center());

        // example
        // Iterator<NodeData> iter = gg.nodeIter();
        // while (iter.hasNext()) {
        // Iterator<NodeData> iter1 = gg.nodeIter();
        // int fatherKey = iter.next().getKey();
        // System.out.println(fatherKey);
        // System.out.println("father ^ sons :");
        // while (iter1.hasNext()) {
        // int sonKey = iter1.next().getKey();
        // if (sonKey != fatherKey) {
        // System.out.println("son : " + sonKey);
        // }
        // }
        // }

        // example 2
        // for (Iterator<NodeData> outer = gg.nodeIter(); outer.hasNext();) {
        // Iterator<NodeData> inner = gg.nodeIter();
        // int fatherKey = outer.next().getKey();
        // System.out.println(fatherKey);
        // System.out.println("father ^ sons :");
        // while (inner.hasNext()) {
        // int sonKey = inner.next().getKey();
        // if (sonKey != fatherKey) {
        // System.out.println("son : " + sonKey);
        // }
        // }
        // }

    }

    @Test
    void tsp() {
    }

    @Test
    void save() {
    }

    @Test
    void load() {
        ga.load("./json_data/G1.json");
    }
}