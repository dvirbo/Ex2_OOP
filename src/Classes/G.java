package Classes;

import Interfaces.DirectedWeightedGraph;
import Interfaces.EdgeData;
import Interfaces.NodeData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class G implements DirectedWeightedGraph {

    public HashMap<Integer, NodeData> nodes;
    public HashMap<String, EdgeData> edges; // String ="src_" + src + "_dest_" + dest

    public int modeCount;

    int it_change;
    boolean first_time;

    /**
     * constructor
     */
    public G() {
        this.nodes = new HashMap<>();
        this.edges = new HashMap<>();
        modeCount = 0;
        it_change = 0;
        first_time = true;

    }

    public G(HashMap<Integer, NodeData> nodes, HashMap<String, EdgeData> edges) {
        this.nodes = nodes;
        this.edges = edges;
        this.modeCount = 0;
        it_change = 0;
        first_time = true;

    }

    public G(DirectedWeightedGraph other) {
        this.nodes = new HashMap<>();
        this.edges = new HashMap<>();
        for (Iterator<NodeData> iter = other.nodeIter(); iter.hasNext();) {
            NodeData node = iter.next();
            nodes.put(node.getKey(), new CNode(node));
        }
        for (Iterator<EdgeData> iter = other.edgeIter(); iter.hasNext();) {
            EdgeData edge = iter.next();
            edges.put("src_" + edge.getSrc() + "_dest_" + edge.getDest(), new CEdge(edge));
        }
        this.modeCount = other.getMC();
        it_change = 0;
        first_time = true;

    }

    // /**
    // *
    // * @param i
    // * @param j
    // * @return
    // */
    // public boolean hasEdge(int i, int j) {
    // return edges.containsKey("src_" + i + "_dest_" + j);
    // }

    @Override
    public NodeData getNode(int key) {
        return this.nodes.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        if (this.edges.containsKey("src_" + src + "_dest_" + dest)) {
            return this.edges.get("src_" + src + "_dest_" + dest);
        }
        return null;
    }

    @Override
    public void addNode(NodeData n) {
        if (n != null) {
            if (!nodes.containsKey(n.getKey())) {
                NodeData nc = new CNode(n);
                nodes.put(n.getKey(), nc);
                modeCount++;
            }
        }
    }

    /**
     * this function connect 2 nodes by create new edge between them
     * eventually, we add the new edge to outEdges hashMap
     *
     * @param src  - the source of the edge.
     * @param dest - the destination of the edge.
     * @param w    - positive weight representing the cost (aka time, price, etc)
     *             between src-->dest.
     */
    @Override
    public void connect(int src, int dest, double w) {
        if (w < 0) {
            throw new RuntimeException("Only Positive Numbers");
        }
        if (!nodes.containsKey(src) || !nodes.containsKey(dest) || src == dest) {
            return;
        }
        EdgeData e = new CEdge(src, dest, w);
        this.edges.put("src_" + src + "_dest_" + dest, e);
        modeCount++;
    }

    /**
     * This method returns an Iterator for the
     * collection representing all the nodes in the graph.
     * Note: if the graph was changed since the iterator was constructed - a
     * RuntimeException should be thrown.
     *
     * @return Iterator<node_data>
     */

    @Override
    public Iterator<NodeData> nodeIter() {
        if (first_time) {
            it_change = modeCount;
            first_time = false;
        }
        if (it_change == modeCount) {
            List<NodeData> list = new ArrayList<>(this.nodes.values());
            Iterator<NodeData> it = list.iterator();
            return it;
        }
        throw new RuntimeException("graph changed since the iterator was constructed");
    }

    /**
     * This method returns an Iterator for all the edges in this graph.
     * Note: if any of the edges going out of this node were changed since the
     * iterator was constructed - a RuntimeException should be thrown.
     *
     * @return Iterator<EdgeData>
     */
    @Override
    public Iterator<EdgeData> edgeIter() {
        if (first_time) {
            it_change = modeCount;
            first_time = false;
        }

        if (it_change == modeCount) {
            return this.edges.values().iterator();

        }
        throw new RuntimeException("graph changed since the iterator was constructed");
    }

    /**
     * This method returns an Iterator for edges getting out of the given node (all
     * the edges starting (source) at the given node).
     * Note: if the graph was changed since the iterator was constructed - a
     * RuntimeException should be thrown.
     *
     * @return Iterator<EdgeData>
     */
    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        if (first_time) {
            it_change = modeCount;
            first_time = false;
        }
        if (it_change == modeCount) {
            List<EdgeData> edgeList = new ArrayList<>();
            this.edges.values().forEach((Edg) -> {
                if (Edg.getSrc() == node_id) {
                    edgeList.add(Edg);
                }
            });
            return edgeList.iterator();
        }
        throw new RuntimeException("graph changed since the iterator was constructed");
    }

    @Override
    public NodeData removeNode(int NodeId) {
        if (this.nodes.containsKey(NodeId)) {
            NodeData n = new CNode(this.nodes.get(NodeId));


            this.edges.entrySet().removeIf((entry) -> {
                modeCount++;
                return Integer.toString(NodeId).equals(entry.getKey().substring(4, 5));
            });
          
            this.nodes.remove(NodeId);
            modeCount++;

            return n;
        }
        return null;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        if (edges.containsKey("src_" + src + "_dest_" + dest)) {
            // need to check if return by value or address
            EdgeData e = new CEdge(edges.get("src_" + src + "_dest_" + dest));
            edges.remove("src_" + src + "_dest_" + dest);
            modeCount++;
            return e;
        }
        return null;
    }

    @Override
    public int nodeSize() {
        return this.nodes.size();
    }

    @Override
    public int edgeSize() {
        return this.edges.size();
    }

    @Override
    public int getMC() {
        return this.modeCount;
    }

    @Override
    public String toString() {
        return "G{" +
                "nodes=" + nodes +
                ", edges=" + edges +
                ", modeCount=" + modeCount +
                '}';
    }
}