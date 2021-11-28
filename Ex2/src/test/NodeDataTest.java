package src.test;

import org.junit.jupiter.api.Test;
import src.api.Geo_Location;
import src.api.Node;

import static org.junit.jupiter.api.Assertions.*;

class NodeDataTest {
    private Node n;
    Geo_Location g;

    public void nodeCreator(){
        g = new Geo_Location(-2,7,3.1);
        n = new Node(4, g, -2, "White", 4);
    }

    @Test
    void getKey() {
    }

}