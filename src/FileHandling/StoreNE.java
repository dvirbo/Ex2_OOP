package FileHandling;

import java.util.HashMap;

import Classes.CNode;
import Interfaces.EdgeData;

public class StoreNE {
    public HashMap<Integer, CNode> nodes;
    public HashMap<String, EdgeData> edges;

    public StoreNE(HashMap<Integer, CNode> hp_nodes, HashMap<String, EdgeData> hp_edges) {
        this.nodes = hp_nodes;
        this.edges = hp_edges;
    }

}
