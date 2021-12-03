package src.api;

import src.interfaces.DirectedWeightedGraph;
import src.interfaces.DirectedWeightedGraphAlgorithms;
import src.interfaces.EdgeData;
import src.interfaces.NodeData;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * This interface represents a Directed (positive) Weighted Graph Theory Algorithms including:
 * 0. clone(); (copy)
 * 1. init(graph);
 * 2. isConnected(); // strongly (all ordered pais connected)
 * 3. double shortestPathDist(int src, int dest);
 * 4. List<NodeData> shortestPath(int src, int dest);
 * 5. NodeData center(); // finds the NodeData which minimizes the max distance to all the other nodes.
 * // Assuming the graph isConnected, elese return null. See: https://en.wikipedia.org/wiki/Graph_center
 * 6. List<NodeData> tsp(List<NodeData> cities); // computes a list of consecutive nodes which go over all the nodes in cities.
 * // See: https://en.wikipedia.org/wiki/Travelling_salesman_problem
 * 7. save(file); // JSON file
 * 8. load(file); // JSON file
 */
public class DWGraphAlgo implements DirectedWeightedGraphAlgorithms {

    private DirectedWeightedGraph graph;

    /**
     * Default constructor that received the field of the DWGraph
     */
    public DWGraphAlgo() {
        this.graph = new DWGraph();
    }

    /**
     * Inits the graph on which this set of algorithms operates on.
     *
     * @param g - DirectedWeightedGraph
     */
    @Override
    public void init(DirectedWeightedGraph g) {
        this.graph = g;
    }

    /**
     * Returns the underlying graph of which this class works.
     *
     * @return
     */
    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    /**
     * Computes a deep copy of this weighted graph
     * using copy constructor that build in DWGraph class
     *
     * @return a copy of the graph
     */
    @Override
    public DirectedWeightedGraph copy() {
        return new DWGraph((DWGraph) this.graph);
    }

    /**
     * Check if the graph strongly connected (all ordered pais connected)
     * we use BFS to check it
     * NOTE: assume directional graph (all n*(n-1) ordered pairs).
     *
     * @return true (iff) there is a valid path from each node to each other node.
     */
    @Override
    public boolean isConnected() {
        if (this.graph.nodeSize() == 0) { //base case
            return true;
        }
        Iterator<NodeData> it = this.graph.nodeIter();
        while (!it.hasNext()) {
            NodeData n = it.next();
            boolean check = this.bfs(n);
            resetTags();
        }
        return false;
    }

    private void resetTags() {
        Iterator<NodeData> it = this.graph.nodeIter();
        while (!it.hasNext()){
            it.next().setTag(-1);
        }
    }

    /**
     * Computes the length of the shortest path between src to dest
     * Note: if no such path --> returns -1
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }

    /**
     * Computes the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * see: https://en.wikipedia.org/wiki/Shortest_path_problem
     * Note if no such path --> returns null;
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return null;
    }

    /**
     * Finds the NodeData which minimizes the max distance to all the other nodes.
     * Assuming the graph isConnected, elese return null. See: https://en.wikipedia.org/wiki/Graph_center
     *
     * @return the Node data to which the max shortest path to all the other nodes is minimized.
     */
    @Override
    public NodeData center() {
        return null;
    }

    /**
     * Computes a list of consecutive nodes which go over all the nodes in cities.
     * the sum of the weights of all the consecutive (pairs) of nodes (directed) is the "cost" of the solution -
     * the lower the better.
     * See: https://en.wikipedia.org/wiki/Travelling_salesman_problem
     */
    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    /**
     * Saves this weighted (directed) graph to the given
     * file name - in JSON format
     *
     * @param file - the file name (may include a relative path).
     * @return true - iff the file was successfully saved
     */
    @Override
    public boolean save(String file) {
        return false;
    }

    /**
     * This method loads a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     * counter - at the end of
     * @param file - file name of JSON file
     * @return true - iff the graph was successfully loaded.
     */
    @Override
    public boolean load(String file) {
        return false;
    }
    private  boolean bfs(NodeData n){
        Queue<NodeData> queue = new LinkedList<>();
        n.setTag(1);
        int count = 1;
        queue.add(n);
        while (!queue.isEmpty()){
            NodeData np = queue.poll();
            Iterator <EdgeData> it = this.graph.edgeIter(np.getKey());
            while (!it.hasNext()){
                NodeData dest = this.graph.getNode(it.next().getDest());
                if(dest.getTag() == -1){
                    dest.setTag(1); //visited
                    queue.add(dest);
                    count++; //increase the counter
                }
            }
        }
        return (count == this.graph.nodeSize());
    }




}
