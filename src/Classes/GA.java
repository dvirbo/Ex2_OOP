package Classes;

import FileHandling.CImport;
import Interfaces.DirectedWeightedGraph;
import Interfaces.DirectedWeightedGraphAlgorithms;
import Interfaces.EdgeData;
import Interfaces.NodeData;
import org.w3c.dom.Node;

import java.io.File;
import java.util.*;

import static FileHandling.CExport.GAsave;

public class GA implements DirectedWeightedGraphAlgorithms {

    private DirectedWeightedGraph graph;


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
        return new G(this.graph);
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

        double ans = Dijkstra(this.graph.getNode(src), this.graph.getNode(dest));


        if (ans == Double.MAX_VALUE) {
            return -1;
        }
        return ans;
    }

    /**
     * This method Computes the shortest path between src to dest - as an ordered
     * by using Dijkstra algorithm and with the fact that all node contain the prev
     * node that
     * was before him (the tag) and after we're done with Dijkstra we can go back
     * and find
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
        if (shortestPathDist(src, dest) == -1) {// if no such path
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
        if (!isConnected()) {
            return null;
        }

        double minimumW = Double.MAX_VALUE;
        NodeData centerN = null;

        for (Iterator<NodeData> iter = this.graph.nodeIter(); iter.hasNext(); ) {

            NodeData node = iter.next();
            double temp = 0.0;

            resetInfo();
            resetTags();
            resetWeight();

            shortestPathDist(node.getKey(), 0);


            for (Iterator<NodeData> iter2 = this.graph.nodeIter(); iter2.hasNext(); ) {

                NodeData node2 = iter2.next();
                if (node2.getWeight() > temp) {
                    temp = node.getWeight();
                }
            }
            if (temp < minimumW) {
                minimumW = temp;
                centerN = node;
            }
        }
        return centerN;
    }

//    @Override
//    public NodeData center() {
//        if (this.isConnected()) {
//            int center = -1;
//            resetTags();
//            List<Chelper> list = new LinkedList<>();
//
//            Iterator<NodeData> iterator = this.graph.nodeIter();
//            while (iterator.hasNext()) {
//                NodeData node = iterator.next();
//                Chelper h = DijkstraCenter(node);
//                list.add(h);
//            }
//            double min = Double.MAX_VALUE;
//
//            for (Chelper h : list) {
//                if (min > h.w) {
//                    min = h.w;
//                    center = h.src;
//                }
//            }
//
//            return graph.getNode(center);
//
//        }
//        return null;
//    }
//
//    private Chelper DijkstraCenter(NodeData src) {
//        Chelper bigestWieght = new Chelper(src.getKey(), 0, Double.MIN_VALUE);
//        PriorityQueue<NodeData> pq = new PriorityQueue<>(this.graph.nodeSize(),
//                Comparator.comparingDouble(NodeData::getWeight));
//        resetWeight();
//        resetTags();
//        src.setWeight(0.0); // init the src node
//        pq.add(src);
//        NodeData pointerNode;
//        while (!pq.isEmpty()) {
//            pointerNode = pq.poll();
//            Iterator<EdgeData> edgeIter = this.graph.edgeIter(pointerNode.getKey()); // iterate all the edges that connect to the pointer
//            while (edgeIter.hasNext()) {
//                EdgeData curr = edgeIter.next(); // current edge in the iterator
//                NodeData neighborNode = this.graph.getNode(curr.getDest()); // create a neighbor node
//                if (neighborNode.getTag() == -1) { // check if we already visit the node
//                    if (pointerNode.getWeight() + curr.getWeight() < neighborNode.getWeight()) { // compare between the
//                        neighborNode.setWeight(pointerNode.getWeight() + curr.getWeight());
//
//                        neighborNode.setTag(1); // checked
//                    }
//                    pq.add(neighborNode);
//                }
//            }
//            pointerNode.setTag(1);
//        }
//
//        for (int i = 0; i < this.graph.nodeSize(); i++) {
//            if (bigestWieght.w < this.graph.getNode(i).getWeight()) {
//                bigestWieght.w = this.graph.getNode(i).getWeight();
//                bigestWieght.src = this.graph.getNode(i).getKey();
//            }
//        }
//        return bigestWieght;
//    }

    /**
     * Computes a list of consecutive nodes which go over all the nodes in cities.
     * the sum of the weights of all the consecutive (pairs) of nodes (directed) is
     * the "cost" of the solution -
     * the lower the better.
     * See: https://en.wikipedia.org/wiki/Travelling_salesman_problem
     */
    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        G gTsp = new G(); //build new graph
        cities.forEach((node) -> {  //iterate all the nodes in the list
            gTsp.addNode(new CNode(node));
        });

        cities.forEach((node) -> {  //iterate all the nodes in the list
            gTsp.addNode(new CNode(node));
            Iterator<EdgeData> iter = this.graph.edgeIter(node.getKey());
            while (iter.hasNext()) { //iter all the
                EdgeData e = iter.next();
                cities.forEach((city) -> {
                    if (city.getKey() == e.getDest()) { // if cities contain the dest
                        //System.out.println(e.getSrc() + ":" + e.getDest() + ":" + e.getWeight());
                        gTsp.connect(e.getSrc(), e.getDest(), e.getWeight());
                        // System.out.println(gTsp.getEdge(e.getSrc(), e.getDest()));
                    }
                });
            }
        });
        GA gaTsp = new GA();
        gaTsp.init(gTsp);

        resetInfo();
        resetTags();
        resetWeight();

        List<NodeData> bestWay = new LinkedList<>();

        cities.get(0).setTag(1);//check.
        bestWay.add(cities.get(0)); // add the first node in the list
        NodeData prev;
        NodeData next;

        while (gaTsp.visitNode(cities)) {
            double dist = Double.MAX_VALUE;
            prev = null;
            next = null;

            for (NodeData pointer : bestWay) { //for every node in the list
                Iterator<EdgeData> iter = gTsp.edgeIter(pointer.getKey()); //iterate all the
                while (iter.hasNext()) {
                    EdgeData edgeToNeighbor = iter.next();

                    NodeData neighborNode = gTsp.getNode(edgeToNeighbor.getDest());

                    if (neighborNode.getTag() == -1 && edgeToNeighbor.getWeight() < dist) {
                        dist = edgeToNeighbor.getWeight();
                        next = neighborNode;
                        prev = pointer;
                    }
                }
            }

            if (next != null) {

                NodeData newNext = next;
                cities.forEach((city) -> {
                    if (newNext.getKey() == city.getKey()) {
                        city.setTag(1);
                    }
                });
                gTsp.getNode(next.getKey()).setTag(1);
                bestWay.add(bestWay.indexOf(prev) + 1, next);
            }
        }

        return bestWay;
    }

    /**
     * this method check if we visit
     *
     * @param cities
     * @return
     */
    private boolean visitNode(List<NodeData> cities) {

        for (NodeData city : cities) {
            if (city.getTag() == -1) {
                return true;
            }
        }
        return false;
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
     * Creates a PriorityQueue with the specified initial capacity (nodeSize in the
     * graph),
     * and orders its elements according to the specified comparator (the weight of
     * the nodes).
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
     *
     * @param n node that the search start from
     * @return true if the number of nodes that visited and count are equals to the
     * sum of the nodes in the graph
     */
    private boolean bfs(NodeData n) {
        Queue<NodeData> queue = new LinkedList<>();
        n.setTag(1); // visit
        int count = 1; // count the sum of nodes that has visited
        queue.add(n);
        while (!queue.isEmpty()) { // contain all the nodes that we need to check
            NodeData np = queue.poll();
            Iterator<EdgeData> it = this.graph.edgeIter(np.getKey());
            while (it.hasNext()) {
                NodeData AdjNode = this.graph.getNode(it.next().getDest()); // get the neighbor nodes
                if (AdjNode.getTag() == -1) { // check his tag - if we didn't visit yet:
                    AdjNode.setTag(1); // change to visit
                    queue.add(AdjNode);
                    count++; // increase the counter
                }

            }

        }
        return (count == this.graph.nodeSize()); // if true - the graph is strongly connected
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
//
//    //        System.out.println("***");
//    // prining the Edges
//    Iterator<EdgeData> edgeiter = gTsp.edgeIter();
//        while(edgeiter.hasNext())    {
//        EdgeData e = edgeiter.next();
////            System.out.println(e);
//    }
//
//    //        System.out.println("***");
//    // returning the nodelist
//    List<NodeData> nodereturnList = new LinkedList<>();
//    var nodeiter = gTsp.nodeIter();
//        while (nodeiter.hasNext()) {
//        NodeData n = nodeiter.next();
//        nodereturnList.add(n);
//    }

}
