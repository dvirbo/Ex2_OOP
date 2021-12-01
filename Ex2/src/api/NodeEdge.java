package src.api;

import java.util.List;


public class NodeEdge {


    List<Node> nodes;
    List<Edge> edges;

    public NodeEdge(List<Node> nodes, List<Edge> edges) {
        this.edges = edges;
        this.nodes = nodes;

    }

}

