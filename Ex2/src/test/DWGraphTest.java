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
    private Node n1, n2, n3, n4, n5, n6;
    Geo_Location g1, g2, g3, g4, g5, g6;

    public void nodeCreator() {
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
    }

    @Test
    void nodeSize() {
        DWGraph g1 = new DWGraph();
        g1.addNode(n1);
        g1.addNode(n2);
        g1.addNode(n3);
        g1.addNode(n4);
        g1.addNode(n5);
        g1.addNode(n6);
        g1.connect(n1.getKey(),n3.getKey(),1.1);
        int e_size =  g1.edgeSize();
        assertEquals(6, e_size);
        EdgeData w03 = g1.getEdge(0,3);
        EdgeData w30 = g1.getEdge(3,0);
        assertEquals(w03, w30);
        assertEquals(w03, 3);

    }

}
