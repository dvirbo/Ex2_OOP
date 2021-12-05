package test;

import org.junit.jupiter.api.Test;

import Classes.G;
import Classes.CGeo;
import Classes.CNode;
import Interfaces.EdgeData;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

class DWGraphTest {

    private static CNode n1, n2, n3, n4, n5, n6;
    private static CGeo g1, g2, g3, g4, g5, g6;

    public DWGraphTest() {
        g1 = new CGeo(1, 2, 3);
        g2 = new CGeo(2, 1, 3);
        g3 = new CGeo(-4, 7, 1);
        g4 = new CGeo(-5, 8, 2);
        g5 = new CGeo(-3, 6, 4);
        g6 = new CGeo(-1, 5, 4);

        n1 = new CNode(1, g1, 1, "White", -1);
        n2 = new CNode(2, g2, 2, "White", -1);
        n3 = new CNode(3, g3, 3, "White", -1);
        n4 = new CNode(4, g4, 4, "White", -1);
        n5 = new CNode(5, g5, 5, "White", -1);
        n6 = new CNode(6, g6, 6, "White", -1);
    }
    @Test
    void deep_copy() {
        G graph1 = new G();
        graph1.addNode(n1);
        graph1.addNode(n2);

        G graph2 = new G(graph1);
        int node_in_g1 = graph1.nodeSize();
        int node_in_g2 = graph2.nodeSize();
        assertEquals(node_in_g1,node_in_g2);

    }

    @Test
    void add_nodeSize() {

        G graph = new G();
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.addNode(n5);
        graph.addNode(n6);

        int node_in_graph = graph.nodeSize();
        assertEquals(node_in_graph, 6);
    }

    @Test
    void connect() {
        G graph = new G();
        graph.addNode(n1);
        graph.addNode(n3);
        graph.connect(n1.getKey(), n3.getKey(), 1.1);

        int edge_in_g1 = graph.edgeSize();
        assertEquals(edge_in_g1, 1);

        EdgeData w03 = graph.getEdge(n1.getKey(), n3.getKey());

        assertEquals(w03.getSrc(), 1);
        assertEquals(w03.getDest(), 3);
        assertEquals(w03.getWeight(), 1.1);


    }

}
