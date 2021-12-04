package FileHandling;

import java.util.HashMap;

import Classes.NodeC;
import Interfaces.EdgeData;

public class StoreNE {
    public HashMap<Integer, NodeC> nodes;
    public HashMap<String, EdgeData> edges;

    public StoreNE(HashMap<Integer, NodeC> hp_nodes, HashMap<String, EdgeData> hp_edges) {
        this.nodes = hp_nodes;
        this.edges = hp_edges;
    }

}
