package ClassesTest;

import Classes.CGeo;
import Classes.CNode;
import Classes.G;
import Classes.GA;
import Interfaces.DirectedWeightedGraph;
import Interfaces.DirectedWeightedGraphAlgorithms;
import Interfaces.NodeData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GATest {
    private static DirectedWeightedGraph g = new G();
    private static DirectedWeightedGraphAlgorithms ga = new GA();

    @BeforeEach
    void createG() {
        for (int i = 1; i < 5; i++) {
            NodeData n = new CNode(i + 1, new CGeo(i, i, i), i + 1.1, "white", -1);
            g.addNode(n);
        }
        g.connect(1, 2, 1.1);
        g.connect(1, 3, 1.2);
        g.connect(2, 3, 1.3);
        g.connect(2, 4, 1.4);
        g.connect(3, 4, 1.5);
        g.connect(3, 1, 1.6);
        g.connect(4, 1, 1.7);
        ga.init(g);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void init() {
        DirectedWeightedGraph gCopy = new G((G) g);
        DirectedWeightedGraphAlgorithms gaCopy = new GA();
        gaCopy.init(gCopy); // reset the graph by init
        assertEquals(gCopy, gaCopy.getGraph()); // equals cause

        DirectedWeightedGraph gc = gaCopy.copy(); // check the copy
        assertEquals(gc.toString(), gCopy.toString());
    }

    @Test
    void getGraph() {
    }

    @Test
    void copy() {
    }

    @Test
    void isConnected() {
    }

    @Test
    void shortestPathDist() {
    }

    @Test
    void shortestPath() {
    }

    @Test
    void center() {
    }

    @Test
    void tsp() {
    }

    @Test
    void save() {
    }

    @Test
    void load() {
    }
}