package Classes;


import FileHandling.CImport;
import Interfaces.DirectedWeightedGraph;
import Interfaces.DirectedWeightedGraphAlgorithms;
import Interfaces.EdgeData;
import Interfaces.NodeData;

import java.io.File;
import java.util.*;

import static FileHandling.CExport.GAsave;

public class GA implements DirectedWeightedGraphAlgorithms {

    private DirectedWeightedGraph graph;
    //  private HashMap<Integer, Integer> nodesDegree = new HashMap<>(this.graph.nodeSize());

    /**
     * Inits the graph on which this set of algorithms operates on.
     *
     * @param g DirectedWeightedGraph
     */
    @Override
    public void init(DirectedWeightedGraph g) {
        this.graph = g;

    }

    /**
     * Returns the underlying graph of which this class works.
     *
     * @return DirectedWeightedGraph graph
     */
    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    /**
     * Computes a deep copy of this weighted graph.
     * using copy constructor that build in DWGraph class
     *
     * @return DirectedWeightedGraph with deep copy
     */
    @Override
    public DirectedWeightedGraph copy() {
        return new G((G) this.graph);
    }

    /**
     * This method check if strongly connected by using BFS
     * NOTE: assume directional graph (all n*(n-1) ordered pairs).
     * wiki: https://en.wikipedia.org/wiki/Breadth-first_search
     *
     * @return true if the graph is strongly connected, else false
     */
    @Override
    public boolean isConnected() {
        if (this.graph.nodeSize() == 0) {
            return true;
        }
        Iterator<NodeData> it = this.graph.nodeIter();
        while (it.hasNext()) {
            NodeData n = it.next();
            boolean check = this.bfs(n);
            resetTags();
            if (!check) {
                return false;
            }
        }
        return true;
    }

    /**
     * Computes the length of the shortest path between 2 nodes
     * we use Dijkstra algorithm to compute that.
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return the shortestPath if existed. ath between src to dest
     * * Note:
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        if (src == dest) {
            return 0;
        }
        resetInfo();
        resetTags();
        resetWeight();

        double ans = Dijkstra(this.graph.getNode(src), this.graph.getNode(dest));

        resetInfo();
        resetTags();
        resetWeight();

        if (ans == Double.MAX_VALUE) {
            return -1;
        }
        return ans;
    }


    /**
     * This method Computes the shortest path between src to dest - as an ordered List of
     * by using Dijkstra algorithm and with the fact that all node contain the prev node that
     * was before him (the tag) and after we're done with Dijkstra we can go back and find
     * the shortestPath of the nodes and pot then into a list
     * if no such path --> returns null;
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        List<NodeData> ans = new LinkedList<>();
        if (this.graph.getNode(src) == null || this.graph.getNode(dest) == null) {
            throw new RuntimeException("bad input");
        }
        if (src == dest) {
            ans.add(this.graph.getNode(src));
            return ans;
        }
        if (shortestPathDist(src, dest) == -1) {//if no such path
            return null;
        }
        double d = Dijkstra(this.graph.getNode(src), this.graph.getNode(dest));
        if (d != Double.MAX_VALUE) {
            int stop = this.graph.getNode(src).getKey(); // were to stop - when we get to the src node
            int ptr = this.graph.getNode(dest).getKey(); // start from the last node
            while (ptr != stop) {
                NodeData curr = this.graph.getNode(ptr);
                ans.add(0, curr); // add in th first place
                ptr = this.graph.getNode(ptr).getTag();
            }
            NodeData first = this.graph.getNode(stop);
            ans.add(0, first);
        }
        resetInfo();
        resetTags();
        resetWeight();
        return ans;
    }

    /**
     * Finds the NodeData which minimizes the max distance to all the other nodes.
     * Assuming the graph isConnected, else return null.
     * source: https://codeforces.com/blog/entry/17974
     *
     * @return the Node data which have max the shortest path to all the other nodes
     * is minimized.
     */
    @Override
    public NodeData center() {
        if (this.isConnected()) {

            int minKey = findAvg();
            NodeData center = this.graph.getNode(minKey);
            return center;
        }
        return null;

    }


    /**
     * this method iterate all the nodes in the graph and find his degree
     * we're using two loops to iterate all the nodes in the graph and calculate their avg dist to each node
     * the key of the NodeData which minimizes the max distance to all the other nodes is saved in static integer
     *
     * @return HashMap <nodeKey, degree>
     */
    private int findAvg() {

        double min = Double.MAX_VALUE;
        int minKey = 0;

        for (Iterator<NodeData> outer = this.graph.nodeIter(); outer.hasNext(); ) {
            double countDeg = 0;//count the shortest path to all the nodes in the graph
            Iterator<NodeData> inner = this.graph.nodeIter();
            int fatherKey = outer.next().getKey();
            //System.out.println("father: " + fatherKey);
            while (inner.hasNext()) {
                int sonKey = inner.next().getKey();
                if (sonKey != fatherKey) {
                    //  System.out.println("son : " + sonKey);
                    countDeg += this.shortestPathDist(fatherKey, sonKey);
                }
            }
            countDeg = (countDeg / this.graph.nodeSize()); //the avg dist to all the node in the graph
            if (countDeg < min) {   //if the node is smaller than the min
                min = countDeg;
                minKey = fatherKey;
            }
        }
        return minKey;
    }

    /**
     * Computes a list of consecutive nodes which go over all the nodes in cities.
     * the sum of the weights of all the consecutive (pairs) of nodes (directed) is
     * the "cost" of the solution -
     * the lower the better.
     * See: https://en.wikipedia.org/wiki/Travelling_salesman_problem
     */
    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        // TODO Auto-generated method stub
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
        return GAsave(file, getGraph());
    }

    /**
     * This method loads a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     *
     * @param file - file name of JSON file
     * @return true - iff the graph was successfully loaded.
     */
    @Override
    public boolean load(String file) {
        try {
            if (new File(file).isFile() && new File(file).canRead()) {
                DirectedWeightedGraph g = CImport.GAload(file);
                init(g);
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }


    /**
     * Creates a PriorityQueue with the specified initial capacity (nodeSize in the graph),
     * and orders its elements according to the specified comparator (the weight of the nodes).
     * the explanation to the code is inside the code
     *
     * @param src  the id of the sre node
     * @param dest the id of the dest node
     * @return the weight (double) of the shortest path between the src and the dest
     */
    private double Dijkstra(NodeData src, NodeData dest) {
        double shortestPath = Integer.MAX_VALUE;
        PriorityQueue<NodeData> pq = new PriorityQueue<>(this.graph.nodeSize(), Comparator.comparingDouble(NodeData::getWeight));
        src.setWeight(0.0); //init the src node
        pq.add(src);
        while (!pq.isEmpty()) {
            NodeData pointerNode = pq.poll();
            Iterator<EdgeData> it = this.graph.edgeIter(pointerNode.getKey()); //iterate all the edges that connect to pointerNode
            while (it.hasNext()) {
                EdgeData curr = it.next(); //current edge in the iterator
                NodeData neighborNode = this.graph.getNode(curr.getDest()); //create a neighbor node
                if (Objects.equals(neighborNode.getInfo(), "White")) { //check if we already visit the node
                    if (pointerNode.getWeight() + curr.getWeight() < neighborNode.getWeight()) { // compare between the neighbor node w and pointerNode + the edge that connect to the neighbor
                        neighborNode.setWeight(Math.min(pointerNode.getWeight() + curr.getWeight(), neighborNode.getWeight()));
                        neighborNode.setTag(pointerNode.getKey()); //to track where he came from
                    }
                    pq.add(neighborNode);
                }
            }
            pointerNode.setInfo("Black"); //after we check all pointerNode neighbors make him black - not relevant
            if (pointerNode.getKey() == dest.getKey()) {// if we get to the dest node
                return pointerNode.getWeight();
            }

        }
        return shortestPath;
    }

    /**
     * This method check the sum of the nodes in the graph by iterate with BFS
     * Using queue we
     *
     * @param n node that the search start from
     * @return true if the number of nodes that visited and count are equals to the sum of the nodes in the graph
     */
    private boolean bfs(NodeData n) {
        Queue<NodeData> queue = new LinkedList<>();
        n.setTag(1); //visit
        int count = 1; // count the sum of nodes that has visited
        queue.add(n);
        while (!queue.isEmpty()) { //contain all the nodes that we need to check
            NodeData np = queue.poll();
            Iterator<EdgeData> it = this.graph.edgeIter(np.getKey());
            while (it.hasNext()) {
                NodeData AdjNode = this.graph.getNode(it.next().getDest()); //get the neighbor nodes
                if (AdjNode.getTag() == -1) { //check his tag - if we didn't visit yet:
                    AdjNode.setTag(1); //change to visit
                    queue.add(AdjNode);
                    count++; //increase the counter
                }

            }

        }
        return (count == this.graph.nodeSize()); //if true - the graph is strongly connected
    }

    /**
     * This method reset all the tags of the graph nodes
     * by iterate all the nodes in the graph
     */
    private void resetTags() {
        Iterator<NodeData> it = this.graph.nodeIter();
        while (it.hasNext()) {
            it.next().setTag(-1);
        }
    }

    /**
     * This method reset all the info of the graph nodes
     * by iterate all the nodes in the graph
     */
    private void resetInfo() {
        Iterator<NodeData> it = this.graph.nodeIter();
        while (it.hasNext()) {
            it.next().setInfo("White");
        }
    }

    /**
     * This method reset all the weight of the graph nodes to MAX_VALUE
     * by iterate all the nodes in the graph
     */
    private void resetWeight() {
        Iterator<NodeData> it = this.graph.nodeIter();
        while (it.hasNext()) {
            it.next().setWeight(Double.MAX_VALUE);
        }
    }


}
