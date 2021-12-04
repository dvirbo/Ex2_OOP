package Classes;

import java.util.List;

import Interfaces.DirectedWeightedGraph;
import Interfaces.DirectedWeightedGraphAlgorithms;
import Interfaces.NodeData;

public class GA implements DirectedWeightedGraphAlgorithms {

    @Override
    public void init(DirectedWeightedGraph g) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DirectedWeightedGraph copy() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isConnected() {
        // TODO Auto-generated method stub
        return false;
    }

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
    
}
