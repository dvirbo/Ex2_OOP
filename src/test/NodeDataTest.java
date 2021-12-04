package test;

import org.junit.jupiter.api.Test;

import Classes.CGeo;
import Classes.CNode;

import static org.junit.jupiter.api.Assertions.*;

class NodeDataTest {
    private CNode n;
    CGeo g;

    public void nodeCreator(){
//        g = new Geo_Location(-2,7,3.1);
//        n = new Node(1, g, 5, "White", 4);
    }

    @Test
    void getKey() {
        g = new CGeo(-2,7,3.1);
        n = new CNode(1, g, 5, "White", 4);
        int i = n.getKey();
        assertEquals(4,4);
    }

}