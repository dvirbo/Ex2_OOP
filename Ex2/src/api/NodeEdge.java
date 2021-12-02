package src.api;

import src.interfaces.NodeData;

import java.util.HashMap;
import java.util.List;


public class NodeEdge {


    public HashMap<Integer, Node> nodes;
    public HashMap<Integer, HashMap<Node, Edge>> edges;

    public NodeEdge(HashMap<Integer, Node> nodes, HashMap<Integer, HashMap<Node, Edge>> edges) {
        this.nodes = nodes;
        this.edges = edges;


    }

}

