package src.api;

import src.interfaces.DirectedWeightedGraph;
import src.interfaces.EdgeData;
import src.interfaces.NodeData;

import java.util.*;

/**
 * This class represents a Directional Weighted Graph ,based on HashMap DS.
 * The implementation has a road-system or communication network in mind -
 * and should support a large number of nodes (over 100,000).
 */
public class DWGraph implements DirectedWeightedGraph {

    /**
     * Each DWGraph contains few fields:
     * nodes: HashMap DS -represents group of nodes in the graph.
     * edges: HashMap DS -represents each node group of directed edges in this graph.
     * numOfNode: stored the amount of nodes in this graph.
     * numOfEdge: stored the amount of edges in this graph.
     * mc: Mode Count - for testing changes (add/remove node,add/remove edge) in the graph.
     */
    private HashMap<Integer, Node> nodes;
    private HashMap<Integer, HashMap<Node, EdgeData>> edges;
    private int nodeSize, edgeSize, modeCount;

    private static int it_change_edge1 = 0;
    static boolean first_time_edge1 = true;

    private static int it_change_edge2 = 0;
    static boolean first_time_edge2 = true;

    private static int it_change_nodes = 0;
    static boolean first_time_node = true;


    public DWGraph() {
        this.nodes = new HashMap<>();
        this.edges = new HashMap<>();
        this.nodeSize = 0;
        this.edgeSize = 0;
        this.modeCount = 0;

    }

    public DWGraph(HashMap<Integer, Node> nodes, HashMap<Integer, HashMap<Node, EdgeData>> edges) {
        this.nodes = nodes;
        this.edges = edges;
        this.nodeSize = nodes.size();
        this.edgeSize = edges.size();
        this.modeCount = 0;

    }


    public DWGraph(DirectedWeightedGraph other) {
        Iterator<NodeData> it = other.nodeIter();
        while (!it.hasNext()) {
            NodeData n = it.next();
            nodes.put(n.getKey(), new Node(n));
            edges.put(n.getKey(), new HashMap<>());
        }
        for (int i : nodes.keySet()) {
            for (int j : edges.keySet()) {
                if (other.hasEdge(i, j)) {
                    connect(i, j, other.getEdge(i, j).getWeight());
                }
            }
        }
        this.nodeSize = other.nodeSize();
        this.edgeSize = other.edgeSize();
        this.modeCount = getMC();
    }

    /**
     * Checks if two given nodes are connected.
     *
     * @param k1 first key of the node.
     * @param k2 second key of the node.
     * @return true if they are connected.
     */
    public boolean hasEdge(int k1, int k2) {
        NodeData n1 = getNode(k1);
        NodeData n2 = getNode(k2);
        if (n1 == null || n2 == null || k1 == k2 || n1.getKey() == n2.getKey())
            return false;
        return edges.get(k1).containsKey(n2);
    }


    /**
     * returns the node_data by the node_id,
     *
     * @param key - the node_id
     * @return the NodeData by the node_id, null if none.
     */
    @Override
    public NodeData getNode(int key) {
        return this.nodes.get(key);
    }

    /**
     * returns the data of the edge (src,dest), null if none.
     * Complexity: should run in O(1) time.
     *
     * @param src:  source node
     * @param dest: destination node
     * @return the Edge, else null
     */
    @Override
    public EdgeData getEdge(int src, int dest) {
        if (this.nodes.containsKey(src) && this.nodes.containsKey(dest)) {
            return this.edges.get(src).get(dest);
        }
        return null;
    }

    /**
     * adds a new node to the graph with the given node_data.
     * Complexity: should run in O(1) time.
     *
     * @param n: NodeData
     */
    @Override
    public void addNode(NodeData n) {
        if (n != null) {
            if (!nodes.containsKey(n.getKey())) {
                // of casting or new Node need to check
                nodes.put(n.getKey(), (Node) n);
                edges.put(n.getKey(), new HashMap<>());
                nodeSize++;
                modeCount++;
            }
        }
    }

    /**
     * Connects an edge with weight w between node src to node dest.
     * This method run in O(1) time.
     *
     * @param src  - the source of the edge.
     * @param dest - the destination of the edge.
     * @param w    - positive weight representing the cost (aka time, price, etc) between src-->dest.
     */
    @Override
    public void connect(int src, int dest, double w) {
        if (w < 0) {
            throw new RuntimeException("Only Positive Numbers");
        }
        NodeData n1 = getNode(src);
        NodeData n2 = getNode(dest);
        if (n1 == null || n2 == null || src == dest)
            return;
        if (!hasEdge(src, dest)) {
            Edge e = new Edge(src, dest, w);
            edges.get(src).put((Node) n2, e);
            edgeSize++;
        }
    }

    /**
     * This method returns an Iterator for the
     * collection representing all the nodes in the graph.
     * Note: if the graph was changed since the iterator was constructed - a RuntimeException should be thrown.
     *
     * @return Iterator<NodeData>
     */
    @Override
    public Iterator<NodeData> nodeIter() {
        if (first_time_node) {
            it_change_nodes = modeCount;
            first_time_node = false;
        }
        if ( it_change_nodes == modeCount){
            Iterator iterator = this.nodes.values().iterator();
            return iterator;
        }
        throw new RuntimeException("node were changed since the iterator was constructed");
    }


    /**
     * This method returns an Iterator for all the edges in this graph.
     * Note: if any of the edges going out of this node were changed since the iterator was constructed - a RuntimeException should be thrown.
     *
     * @return Iterator<EdgeData>
     */
    @Override
    public Iterator<EdgeData> edgeIter() {
        if (first_time_edge1) {
            it_change_edge1 = modeCount;
            first_time_edge1 = false;
        }

        if (it_change_edge1 == modeCount) {
            //how do I know if the graph was changed since the iterator was constructed??
            Iterator iterator = this.edges.values().stream().iterator();
            return iterator;
        }
        throw new RuntimeException("node were changed since the iterator was constructed");
    }//entrySet().iterator();

    /**
     * This method returns an Iterator for edges getting out of the given node (all the edges starting (source) at the given node).
     * Note: if the graph was changed since the iterator was constructed - a RuntimeException should be thrown.
     *
     * @return Iterator<EdgeData>
     */
    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        if (first_time_edge2) {
            it_change_edge2 = modeCount;
            first_time_edge2 = false;
        }
        if (it_change_edge2 == modeCount) {
            return this.edges.get(node_id).values().iterator();
        }
        throw new RuntimeException("edges were changed since the iterator was constructed");
    }

    /**
     * Deletes the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * This method run in O(k), V.degree=k, as all the edges should be removed.
     *
     * @param key of the node that need to remove
     * @return the data of the removed node (null if none).
     */
    @Override
    public NodeData removeNode(int key) {
        if (this.nodes.containsKey(key)) {
            Iterator<Integer> it = edges.keySet().iterator();
            while (!it.hasNext()) {
                removeEdge(it.next(), key);
                modeCount++;
                nodeSize--;
                return nodes.remove(key);
            }
        }
        return null;
    }

    /**
     * Deletes the edge from the graph,
     * Complexity: run in O(1) time.
     *
     * @param src  - edge source node.
     * @param dest - edge destination node.
     * @return the data of the removed edge (null if none).
     */
    @Override
    public EdgeData removeEdge(int src, int dest) {
        EdgeData e = edges.get(src).remove(dest);
        if (e != null) {
            edgeSize--;
            modeCount++;
        }
        return e;
    }

    /**
     * Returns the number of vertices (nodes) in the graph.
     * Complexity: run in O(1) time.
     *
     * @return number of nodes in the graph.
     */
    @Override
    public int nodeSize() {
        return this.nodeSize;
    }

    /**
     * Returns the number of edges (assume directional graph).
     * Complexity: run in O(1) time.
     *
     * @return number of edges in the graph.
     */
    @Override
    public int edgeSize() {
        return this.edgeSize;
    }

    /**
     * Returns the Mode Count - for testing changes in the graph.
     * Complexity: run in O(1) time.
     *
     * @return number of changes in the graph.
     */
    @Override
    public int getMC() {
        return this.modeCount;
    }
}
