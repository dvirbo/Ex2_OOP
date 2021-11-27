package src.api;


import java.util.*;
/**
 * This class represents a Directional Weighted Graph ,based on HashMap DS.
 * The implementation has a road-system or communication network in mind -
 * and should support a large number of nodes (over 100,000).
 */
public class DWGraph implements  DirectedWeightedGraph{

    /**
     * Each DWGraph contains few fields:
     * nodes: HashMap DS -represents group of nodes in the graph.
     * edges: HashMap DS -represents each node group of directed edges in this graph.
     * numOfNode: stored the amount of nodes in this graph.
     * numOfEdge: stored the amount of edges in this graph.
     * mc: Mode Count - for testing changes (add/remove node,add/remove edge) in the graph.
     */
    private HashMap<Integer, NodeData> nodes;
    private HashMap<Integer, HashMap<Integer, EdgeData>> edges;
    private int nodesNum;
    private int edgesNum;
    private int modeCounter;


    public DWGraph(){
        this.nodes = new HashMap<>();
        this.edges = new HashMap<>();
        this.edgesNum = 0;
        this.nodesNum = 0;
        this.modeCounter = 0;

    }
    public DWGraph(DirectedWeightedGraph other){
        this.nodes = new HashMap<>();
        this.edges = new HashMap<>();
        nodes_DeepCopy(other, this.nodes);
        edges_DeepCopy(other, this.edges);
        this.nodesNum = other.nodeSize();
        this.edgesNum = other.edgeSize();

    }

    private HashMap<Integer, NodeData> nodes_DeepCopy(DirectedWeightedGraph other, HashMap nodes) {
        HashMap<Integer, NodeData> nodes_DC = nodes;
        for (NodeData n: other.)
              {

        }
    }

    private HashMap<Integer, HashMap<Integer, EdgeData>> edges_DeepCopy(DirectedWeightedGraph other, HashMap edges) {
    
    }




    /**
     * returns the node_data by the node_id,
     *
     * @param key - the node_id
     * @return the node_data by the node_id, null if none.
     */
    public NodeData getNode(int key) {
        return null;
    }

    /**
     * returns the data of the edge (src,dest), null if none.
     * Note: this method should run in O(1) time.
     *
     * @param src
     * @param dest
     * @return
     */
    public EdgeData getEdge(int src, int dest) {
        return null;
    }

    /**
     * adds a new node to the graph with the given node_data.
     * Note: this method should run in O(1) time.
     *
     * @param n
     */
    public void addNode(NodeData n) {

    }

    /**
     * Connects an edge with weight w between node src to node dest.
     * * Note: this method should run in O(1) time.
     *
     * @param src  - the source of the edge.
     * @param dest - the destination of the edge.
     * @param w    - positive weight representing the cost (aka time, price, etc) between src-->dest.
     */
    public void connect(int src, int dest, double w) {
    }

    /**
     * This method returns an Iterator for the
     * collection representing all the nodes in the graph.
     * Note: if the graph was changed since the iterator was constructed - a RuntimeException should be thrown.
     *
     * @return Iterator<NodeData>
     */
    public Iterator<NodeData> nodeIter() {
        Iterator it = nodes.entrySet().iterator();
        while (it.hasNext()){


        }

    }

    /**
     * This method returns an Iterator for all the edges in this graph.
     * Note: if any of the edges going out of this node were changed since the iterator was constructed - a RuntimeException should be thrown.
     *
     * @return Iterator<EdgeData>
     */
    public Iterator<EdgeData> edgeIter() {
        return null;
    }

    /**
     * This method returns an Iterator for edges getting out of the given node (all the edges starting (source) at the given node).
     * Note: if the graph was changed since the iterator was constructed - a RuntimeException should be thrown.
     *
     * @return Iterator<EdgeData>
     */
    public Iterator<EdgeData> edgeIter(int node_id) {
        return null;
    }

    /**
     * Deletes the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * This method should run in O(k), V.degree=k, as all the edges should be removed.
     *
     * @param key
     * @return the data of the removed node (null if none).
     */
    public NodeData removeNode(int key) {
        return null;
    }

    /**
     * Deletes the edge from the graph,
     * Note: this method should run in O(1) time.
     *
     * @param src
     * @param dest
     * @return the data of the removed edge (null if none).
     */
    public EdgeData removeEdge(int src, int dest) {
        return null;
    }

    /**
     * Returns the number of vertices (nodes) in the graph.
     * Note: this method should run in O(1) time.
     *
     * @return
     */
    public int nodeSize() {
        return 0;
    }

    /**
     * Returns the number of edges (assume directional graph).
     * Note: this method should run in O(1) time.
     *
     * @return
     */
    public int edgeSize() {
        return 0;
    }

    /**
     * Returns the Mode Count - for testing changes in the graph.
     *
     * @return
     */
    public int getMC() {
        return 0;
    }
}
