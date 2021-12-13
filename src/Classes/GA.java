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
     * * Note: Due to problems with weight calculation we decided we will make diffrent functions
     */
    @Override
    public double shortestPathDist(int src, int dest) {

        resetInfo();
        resetTags();
        resetWeight();

        double ans = DijkstraDist(this.graph.getNode(src), this.graph.getNode(dest));
        if (ans == Double.MAX_VALUE) {
            return -1;
        }
        return ans;
    }


    /**
     * Computes the length of the shortest path between 2 nodes
     * we use Dijkstra algorithm to compute that.
     *
     * @param src - start node
     * @return the shortestPath if existed. ath between src to dest
     * * Note:
     */
    public double shortestPathCenter(int src) {

        resetWeightInfo();

        double ans = DijkstraCenter(this.graph.getNode(src));
        if (ans == Double.MAX_VALUE) {
            return -1;
        }
        return ans;
    }

    private double DijkstraDist(NodeData src, NodeData dest) {

        double shortestPath = Double.MAX_VALUE;
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
     * This method Computes the shortest path between src to dest - as an ordered List of
     * by using Dijkstra algorithm and with the fact that all node contain the prev node that
     * was before him (the tag) and after we're done with Dijkstra we can go back and find
     * the shortestPath of the nodes and pot then into a list
     * if no such path --> returns null;
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return List<NodeData> shortestPath of nodes
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
        resetInfo();
        resetTags();
        resetWeight();
        double d = DijkstraDist(this.graph.getNode(src), this.graph.getNode(dest));
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
        return ans;
    }


    /**
     * Finds the NodeData which minimizes the max distance to all the other nodes.
     * Assuming the graph isConnected, else return null.
     * source: https://codeforces.com/blog/entry/17974
     *
     * @return the Node data which have max the shortest path to all the other node is minimized.
     */
    @Override
    public NodeData center() {
        if (!isConnected()) {
            return null;
        }
        double minimumW = Double.MAX_VALUE;
        NodeData centerN = null;
        Iterator<NodeData> iter = this.graph.nodeIter();

        while (iter.hasNext()) {
            NodeData src = iter.next();
            double max = Double.MIN_VALUE;

            shortestPathCenter(src.getKey());
            Iterator<NodeData> iter2 = this.graph.nodeIter();
            while (iter2.hasNext()) {
                NodeData dest = iter2.next();
                if (dest.getWeight() > max) {
                    max = dest.getWeight();
                }
            }
            if (max < minimumW) {
                minimumW = max;
                centerN = src;
            }
        }
        return centerN;
    }

    /**
     * Creates a PriorityQueue with the specified initial capacity (nodeSize in the graph),
     * and orders its elements according to the specified comparator (the weight of the nodes).
     * the explanation to the code is inside the code
     *
     * @param src the id of the sre node
     * @return the weight (double) of the shortest path between the src and the dest
     */
    public double DijkstraCenter(NodeData src) {

        double shortestPath = Double.MAX_VALUE;
        PriorityQueue<NodeData> pq = new PriorityQueue<>(this.graph.nodeSize(), Comparator.comparingDouble(NodeData::getWeight));
        src.setWeight(0.0); //init the src node
        pq.add(src);
        while (!pq.isEmpty()) {
            NodeData pointerNode = pq.poll();
            Iterator<EdgeData> it = this.graph.edgeIter(pointerNode.getKey()); //iterate all the edges that connect to pointerNode
            while (it.hasNext()) {
                EdgeData curr = it.next(); //current edge in the iterator
                NodeData neighborNode = this.graph.getNode(curr.getDest()); //create a neighbor node
                if (neighborNode.getInfo().equals("White")) { //check if we already visit the node
                    if (pointerNode.getWeight() + curr.getWeight() < neighborNode.getWeight()) { // compare between the neighbor node w and pointerNode + the edge that connect to the neighbor
                        neighborNode.setWeight(pointerNode.getWeight() + curr.getWeight());
                    }
                    pq.add(neighborNode);
                }
            }
            pointerNode.setInfo("Black");
        }
        return shortestPath;
    }


    /**
     * Computes a list of consecutive nodes which go over all the nodes in cities.
     * the sum of the weights of all the consecutive (pairs) of nodes (directed) is
     * the "cost" of the solution - the lower, the better.
     * At first, we create a new graph that made from all the nodes that we get in the list
     * then check if the graph is strongly connected
     * We use list of lists that contain all the optional path's and check which neighbor node wil be
     * the closest every iteration. lie that we make path and at the end we will choose the path that have
     * the lowest weight.
     *
     * @param cities list of nodes.
     * @return the list that have the lowest weight .
     */
    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        G gTsp = new G(); //build new graph
        var keysCities = new ArrayList<Integer>();
        cities.forEach((node) -> {  //iterate all the nodes in the list
            gTsp.addNode(new CNode(node));
            keysCities.add(node.getKey());
        });

        cities.forEach((node) -> {  //iterate all the nodes in the list
            Iterator<EdgeData> iter = this.graph.edgeIter(node.getKey());
            while (iter.hasNext()) { //iter all the
                EdgeData e = iter.next();
                cities.forEach((city) -> {
                    if (city.getKey() == e.getDest()) { // if cities contain the dest
                        gTsp.connect(e.getSrc(), e.getDest(), e.getWeight());
                    }
                });
            }
        });
        GA gaTsp = new GA();
        gaTsp.init(gTsp);
        if (!gaTsp.isConnected()) {
            return null;
        } else { //if the new graph strongly connected:
            int size = cities.size();
            List<List<NodeData>> allWays = new LinkedList<>(); // list of lists of all exist path
            double[] myDist = new double[size]; // List that will contain all the paths final weight.

            for (int city = 0; city < size; city++) { //loop that iter all the cities
                int current = city; //the current city
                List<NodeData> path = new LinkedList<>(); //list of the curr path that we check
                double[] dist = new double[size]; //array that contain all the final weight of the path
                int temp;
                int count = 0; // counter to check if we iter all the cities
                NodeData ptr = cities.get(current); //
                path.add(ptr);//add the first node (0)
                while (count < size && path.size() < size) {
                    // ptr = cities.get(current);
                    temp = current; //
                    for (int i = 0; i < size; i++) {
                        int src = ptr.getKey();
                        int dest = cities.get(i).getKey();
                        if (src != dest) { // to avoid calc the path between same node
                            dist[i] = shortestPathDist(src, dest);
                        } else {
                            dist[i] = Double.MAX_VALUE; //same node..
                        }

                        while (current == temp) {
                            int lowest = shortest(dist); //take the closest city
                            if (!path.contains(cities.get(lowest))) { //if the city isn't in the list-
                                path.add(cities.get(lowest));
                                count++;
                                current = lowest;

                            } else if (path.size() != size) {
                                dist[lowest] = Double.MAX_VALUE;
                            } else {
                                break;
                            }
                        }
                    }

                    if (path.size() == size) { //finish iter all the cities from the current city
                        allWays.add(path);
                    }
                }
            }

            int chosen;
            for (int i = 0; i < allWays.size(); i++) {
                myDist[i] = weightSum(allWays.get(i));
            }
            chosen = shortest(myDist);

            return allWays.get(chosen);
        }
    }

    /**
     * calculate the total Weight of path
     *
     * @param cities list of nodes.
     * @return sum of the weight.
     */
    private double weightSum(List<NodeData> cities) {
        double Weight = 0.0;
        for (int i = 0; i <= cities.size() - 2; i++) {
            Weight = Weight + shortestPathDist(cities.get(i).getKey(), cities.get(i + 1).getKey());
        }
        return Weight;
    }

    /**
     * @param dist - array that contain all the final weight of the path
     * @return index that contain the lowest number.
     */
    private int shortest(double[] dist) {
        double check = dist[0];
        int lowest = 0;
        for (int i = 1; i < dist.length; i++) {
            if (dist[i] < check) {
                check = dist[i];
                lowest = i;
            }
        }
        return lowest;
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
            e.printStackTrace();
        }
        return false;
    }


    /**
     * This method check the sum of the nodes in the graph by iterate with BFS
     * Using queue we
     *
     * @param n node that the search start from
     * @return true if the number of nodes that visited and count are equals to the sum of the nodes in the graph
     */
    public boolean bfs(NodeData n) {
        resetTags();
        Queue<NodeData> queue = new LinkedList<>();
        n.setTag(1); //visit
        int count = 1; // count the sum of nodes that has visited
        queue.add(n);
        while (!queue.isEmpty()) { //contain all the nodes that we need to check
            NodeData np = queue.poll();
            var key = np.getKey();
            Iterator<EdgeData> it = this.graph.edgeIter(key);
            while (it.hasNext()) {
                var edge = it.next();
                var edgeNeighbor = edge.getDest();
                NodeData AdjNode = this.graph.getNode(edgeNeighbor ); //get the neighbor nodes
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

    private void resetWeightInfo() {
        Iterator<NodeData> it = this.graph.nodeIter();

        while (it.hasNext()) {
            var node = it.next();
            node.setInfo("White");
            node.setWeight(Double.MAX_VALUE);
        }
    }

    private void resetAll() {
        Iterator<NodeData> it = this.graph.nodeIter();

        while (it.hasNext()) {
            var node = it.next();
            node.setInfo("White");
            node.setTag(-1);
            node.setWeight(Double.MAX_VALUE);
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
