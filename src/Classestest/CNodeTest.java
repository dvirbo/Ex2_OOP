package Classestest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Classes.CEdge;
import Classes.CGeo;
import Classes.CNode;
import Interfaces.NodeData;

import static org.junit.jupiter.api.Assertions.*;

class CNodeTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getKey() {
        CNode n = new CNode(1, new CGeo(1, 2, 3), 1.0, "white", -1);
        assertEquals(1, n.getKey());
    }

    @Test
    void getLocation() {
        CNode n = new CNode(1, new CGeo(1, 2, 3), 1.0, "white", -1);
        assertEquals(1, n.getLocation().x());
        assertEquals(2, n.getLocation().y());
        assertEquals(3, n.getLocation().z());
    }

    @Test
    void setLocation() {
        CNode n = new CNode(1, new CGeo(1, 2, 3), 1.0, "white", -1);
        n.setLocation(new CGeo(4, 5, 6));
        assertEquals(4, n.getLocation().x());
        assertEquals(5, n.getLocation().y());
        assertEquals(6, n.getLocation().z());
    }

    @Test
    void getWeight() {
        CNode n = new CNode(1, new CGeo(1, 2, 3), 1.0, "white", -1);
        assertEquals(1.0, n.getWeight());
    }

    @Test
    void setWeight() {
        CNode n = new CNode(1, new CGeo(1, 2, 3), 1.0, "white", -1);
        n.setWeight(2.0);
        assertEquals(2.0, n.getWeight());
    }

    @Test
    void getInfo() {
        CNode n = new CNode(1, new CGeo(1, 2, 3), 1.0, "white", -1);
        assertEquals("white", n.getInfo());
    }

    @Test
    void setInfo() {
        CNode n = new CNode(1, new CGeo(1, 2, 3), 1.0, "white", -1);
        n.setInfo("black");
        assertEquals("black", n.getInfo());
    }

    @Test
    void getTag() {
        CNode n = new CNode(1, new CGeo(1, 2, 3), 1.0, "white", -1);
        assertEquals(-1, n.getTag());
    }

    @Test
    void setTag() {
        CNode n = new CNode(1, new CGeo(1, 2, 3), 1.0, "white", -1);
        n.setTag(0);
        assertEquals(0, n.getTag());
    }

    @Test
    void testToString() {
        CNode n = new CNode(1, new CGeo(1, 2, 3), 1.0, "white", -1);
        assertEquals("Node{" +
                "key=" + 1 +
                ", location=" + 1.0 + ", " + 2.0 + ", " + 3.0 +
                ", weight=" + 1.0 +
                ", info='" + "white" +
                ", tag=" + -1 +
                '}', n.toString());
    }

    @Test
    void compareTo() {

    }

    @Test
    void testEquals() {
        CNode n1 = new CNode(1, new CGeo(1, 2, 3), 1.0, "white", -1);
        CNode n2 = new CNode(1, new CGeo(1, 2, 3), 1.0, "white", -1);
        assertEquals(n1, n2);
        assertFalse(n1 == n2);
        assertTrue(n1.equals(n2));
    }

    @Test
    void testHashCode() {
        NodeData n1 = new CNode(1, new CGeo(1, 2, 3), 1.0, "white", -1);
        NodeData n2 = new CNode(1, new CGeo(1, 2, 3), 1.0, "white", -1);
        assertEquals(n1.hashCode(), n2.hashCode());
    }

    @Test
    void addEdge() {
        CNode n1 = new CNode(1, new CGeo(1, 2, 3), 1.0, "white", -1);
        CEdge e = new CEdge(1, 2, 3);
        n1.addEdge(2, e);
        assertEquals(n1.outEdges.get(2), e);
    }

    @Test
    void getEdge() {
        CNode n1 = new CNode(1, new CGeo(1, 2, 3), 1.0, "white", -1);
        CEdge e = new CEdge(1, 2, 3);
        n1.addEdge(2, e);
        assertEquals(n1.getEdge(2), e);
    }
}