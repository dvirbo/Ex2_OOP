package Classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import Interfaces.DirectedWeightedGraph;
import Interfaces.EdgeData;
import Interfaces.NodeData;

public class G implements DirectedWeightedGraph {

    public HashMap<Integer, CNode> nodes;
    public HashMap<String, EdgeData> edges;  // String ="src_" + src + "_dest_" + dest

    public int modeCount;

    public static int it_change_edge1 = 0;
    static boolean first_time_edge1 = true;

    public static int it_change_edge2 = 0;
    static boolean first_time_edge2 = true;

    public static int it_change_nodes = 0;
    static boolean first_time_node = true;

    public G() {
        this.nodes = new HashMap<>();
        this.edges = new HashMap<>();
        this.modeCount = 0;
    }

    public G(HashMap<Integer, CNode> nodes, HashMap<String, EdgeData> edges) {
        this.nodes = nodes;
        this.edges = edges;
        this.modeCount = 0;
    }

    public G(G other) {
        // need to check if deep copy or shallow
        other.nodes.forEach((key, value) -> {
            this.addNode(value);
        });
        other.edges.forEach((key, value) -> {
            this.connect(Integer.parseInt(key.substring(4, 5)), Integer.parseInt(key.substring(key.length() - 1)),
                    value.getWeight());
        });
        this.modeCount = other.getMC();
    }

    private boolean hasEdge(int i, int j) {
        return edges.containsKey("src_" + i + "_dest_" + j);
    }

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
                CNode nc = new CNode(n);
                nodes.put(n.getKey(), nc);
                modeCount++;
            }
        }
    }

    /**
     * this function connect 2 nodes by create new edge between them
     * eventually, we add the new  edge to outEdges hashMap
     * @param src - the source of the edge.
     * @param dest - the destination of the edge.
     * @param w - positive weight representing the cost (aka time, price, etc) between src-->dest.
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
        edges.put("src_" + src + "_dest_" + dest, e);

        CNode n1 = nodes.get(src);
        n1.addEdge(dest, e); //add the new edge to outEdges hashMap
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        if (first_time_node) {
            it_change_nodes = modeCount;
            first_time_node = false;
        }
        if (it_change_nodes == modeCount) {
            List<NodeData> list = new ArrayList<NodeData>(this.nodes.values());
            Iterator<NodeData> it = list.iterator();
            // maybe need to fix to NodeC in the end
            return it;
           // return this.nodes.values().iterator(); didn't work..
        }
        throw new RuntimeException("node were changed since the iterator was constructed");
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        if (first_time_edge1) {
            it_change_edge1 = modeCount;
            first_time_edge1 = false;
        }

        if (it_change_edge1 == modeCount) {
            // List<EdgeData> list = new ArrayList<EdgeData>(this.edges.values());
            // Iterator<EdgeData> it = list.iterator();
            return this.edges.values().iterator();

        }

        throw new RuntimeException("node were changed since the iterator was constructed");
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        if (first_time_edge2) {
            it_change_edge2 = modeCount;
            first_time_edge2 = false;
        }
        if (it_change_edge2 == modeCount) {
            // List<EdgeData> list = new
            // ArrayList<EdgeData>(this.nodes.get(node_id).outEdges.values());
            // Iterator<EdgeData> it = list.iterator();

            // working
            return this.nodes.get(node_id).outEdges.values().iterator();

            // if we want to give up the outEdges idea
            // List<EdgeData> list = new ArrayList<>();
            // for (String key : this.edges.keySet()) {
            // if (key.substring(4, 5).equals(node_id)) {
            // list.add(this.edges.get(key));
            // }
            // }
            // return list.iterator();

        }
        throw new RuntimeException("edges were changed since the iterator was constructed");
    }

    @Override
    public NodeData removeNode(int NodeId) {
        if (this.nodes.containsKey(NodeId)) {
            NodeData n = new CNode(this.nodes.get(NodeId));

            for (String key : this.edges.keySet()) {
                String strSrc = Integer.toString(NodeId);
                if (key.substring(4, 5).equals(strSrc)) {
                    removeEdge(NodeId, Integer.parseInt(key.substring(key.length() - 1)));
                }
            }

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

}
