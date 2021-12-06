package Classes;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Interfaces.DirectedWeightedGraph;
import Interfaces.DirectedWeightedGraphAlgorithms;
import Interfaces.EdgeData;
import Interfaces.NodeData;

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
            if (!check){
                return false;
            }
        }
        return true;
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
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public NodeData center() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean save(String file) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean load(String file) {
        // TODO Auto-generated method stub
        return false;
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


}
