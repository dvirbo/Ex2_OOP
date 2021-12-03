package src.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import src.api.DWGraph;
import src.api.Geo_Location;
import src.api.Node;
import src.interfaces.EdgeData;

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
        Node n1, n2, n3, n4, n5, n6;
        Geo_Location g1, g2, g3, g4, g5, g6;
        g1 = new Geo_Location(1, 2, 3);
        g2 = new Geo_Location(2, 1, 3);
        g3 = new Geo_Location(-4, 7, 1);
        g4 = new Geo_Location(-5, 8, 2);
        g5 = new Geo_Location(-3, 6, 4);
        g6 = new Geo_Location(-1, 5, 4);

        n1 = new Node(1, g1, 1, "White", -1);
        n2 = new Node(2, g2, 2, "White", -1);
        n3 = new Node(3, g3, 3, "White", -1);
        n4 = new Node(4, g4, 4, "White", -1);
        n5 = new Node(5, g5, 5, "White", -1);
        n6 = new Node(6, g6, 6, "White", -1);

        DWGraph graph = new DWGraph();
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
