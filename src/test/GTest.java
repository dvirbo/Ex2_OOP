package test;

import Classes.CGeo;
import Classes.CNode;
import Classes.G;
import Interfaces.EdgeData;
import Interfaces.NodeData;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class GTest {

    private static CNode n1, n2, n3, n4, n5, n6;
    private static CGeo g1, g2, g3, g4, g5, g6;
    private static HashMap<Integer, CNode> nodes = new HashMap<>();
    private static HashMap<String, EdgeData> edges = new HashMap<>();

    public GTest() {
        g1 = new CGeo(1, 2, 3);
        g2 = new CGeo(2, 1, 3);
        g3 = new CGeo(-4, 7, 1);
        g4 = new CGeo(-5, 8, 2);
        g5 = new CGeo(-3, 6, 4);
        g6 = new CGeo(-1, 5, 4);

        n1 = new CNode(1, g1, 1.0, "White", -1);
        n2 = new CNode(2, g2, 2.0, "White", -1);
        n3 = new CNode(3, g3, 3.0, "White", -1);
        n4 = new CNode(4, g4, 4.0, "White", -1);
        n5 = new CNode(5, g5, 5.0, "White", -1);
        n6 = new CNode(6, g6, 6.0, "White", -1);

    }

    /**
     * check constructor by the maps
     */

    @Test
    void copy_by_maps() {
        nodes.put(n1.getKey(), n1);
        G graph1 = new G(nodes, edges);
        int nodeSize = graph1.nodeSize();
        assertEquals(nodeSize, 1);

    }

    /**
     * check constructor by deep copy with other Graph
     */
    @Test
    void deep_copy() {
        G graph1 = new G();
        graph1.addNode(n1);
        graph1.addNode(n2);

        G graph2 = new G(graph1);
        int node_in_g1 = graph1.nodeSize();
        int node_in_g2 = graph2.nodeSize();
        assertEquals(node_in_g1, node_in_g2);

    }

    /**
     * check node size method
     */
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

    /**
     * check addNode + connect
     */
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

        /**
         * check getNode + getEdge
         */
    }

    @Test
    void gets() {

        nodes.put(n1.getKey(), n1);
        nodes.put(n3.getKey(), n3);
        G graph = new G(nodes, edges);
        graph.connect(n1.getKey(), n3.getKey(), 1.1);

        NodeData nodeData = graph.getNode(n1.getKey());
        assertEquals(nodeData.getWeight(), 1);

        EdgeData edgeData = graph.getEdge(n1.getKey(), n3.getKey());
        assertEquals(edgeData.getWeight(), 1.1);

    }

    /**
     * check the iterator that iterate the collection representing all the nodes in
     * the graph.
     */
    @Test
    void nodeIter() {
        G graph = new G(nodes, edges);
        NodeData node1 = new CNode(n1);
        NodeData node2 = new CNode(n2);
        graph.addNode(node1);
        graph.addNode(node2);

        Iterator<NodeData> iterator = graph.nodeIter();
        int count = 0;
        while (iterator.hasNext()) {
            count++;
            // System.out.println(iterator.next());
            iterator.next();
        }
        assertEquals(count, 2);
        count = 0;

        NodeData node3 = new CNode(n3);
        graph.addNode(node3);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            graph.nodeIter(); // trow RuntimeException: node were changed since the iterator was constructed
        });

        String expectedMessage = "graph changed since the iterator was constructed";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void edgeIter() {
        G graph = new G(nodes, edges);
        NodeData node1 = new CNode(n1);
        NodeData node2 = new CNode(n2);
        NodeData node3 = new CNode(n3);
        NodeData node4 = new CNode(n4);

        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);

        graph.connect(n1.getKey(), n3.getKey(), 1.1);
        graph.connect(n2.getKey(), n4.getKey(), 1.2);

        Iterator<EdgeData> iterator = graph.edgeIter();
        int count = 0;
        while (iterator.hasNext()) {
            count++;
            iterator.next();
        }
        assertEquals(count, 2);
        // check if after changing the graph
        NodeData node5 = new CNode(n5);
        graph.addNode(node5);

        graph.connect(n2.getKey(), n5.getKey(), 1.6);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            graph.edgeIter();
        });

        String expectedMessage = "graph changed since the iterator was constructed";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void edgeIter_withNodeId() {
        G graph = new G(nodes, edges);
        NodeData node1 = new CNode(n1);
        NodeData node2 = new CNode(n2);
        NodeData node3 = new CNode(n3);
        NodeData node4 = new CNode(n4);

        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);

        graph.connect(n1.getKey(), n3.getKey(), 1.2);
        graph.connect(n1.getKey(), n4.getKey(), 1.4);

        Iterator<EdgeData> iterator = graph.edgeIter(n1.getKey());
        int count = 0;
        while (iterator.hasNext()) {
            // didn't go inside cause the graph was changed since the iterator was
            // constructed
            count++;
            iterator.next();
        }
        System.out.println(count);
        assertEquals(2, count);
    }

}