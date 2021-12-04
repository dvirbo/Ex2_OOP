package test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Classes.G;
import Classes.CGeo;
import Classes.NodeC;
import Interfaces.EdgeData;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;

class DWGraphTest {
    private static Random _rnd = null;

//    @BeforeAll
//      void nodeCreator() {
//
//    }

    @Test
    void nodeSize() {
        NodeC n1, n2, n3, n4, n5, n6;
        CGeo g1, g2, g3, g4, g5, g6;
        g1 = new CGeo(1, 2, 3);
        g2 = new CGeo(2, 1, 3);
        g3 = new CGeo(-4, 7, 1);
        g4 = new CGeo(-5, 8, 2);
        g5 = new CGeo(-3, 6, 4);
        g6 = new CGeo(-1, 5, 4);

        n1 = new NodeC(1, g1, 1, "White", -1);
        n2 = new NodeC(2, g2, 2, "White", -1);
        n3 = new NodeC(3, g3, 3, "White", -1);
        n4 = new NodeC(4, g4, 4, "White", -1);
        n5 = new NodeC(5, g5, 5, "White", -1);
        n6 = new NodeC(6, g6, 6, "White", -1);

        G graph = new G();
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.addNode(n5);
        graph.addNode(n6);

        int node_in_g1 = graph.nodeSize();
        assertEquals(node_in_g1,6);

        graph.connect(n1.getKey(),n3.getKey(),1.1);

        int edge_in_g1 = graph.edgeSize();
        assertEquals(edge_in_g1,1);

        EdgeData w03 = graph.getEdge(n1.getKey(),n3.getKey());

       // assertEquals(w03, w30);

    }

}
