
import FileHandling.StoreNE;
import Interfaces.DirectedWeightedGraph;
import Interfaces.DirectedWeightedGraphAlgorithms;

import static FileHandling.FileHandling.getJsonToObj;

import Classes.G;


/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     * for testing: "Ex2/data/G1.json"
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {
        StoreNE ne = getJsonToObj(json_file);
        DirectedWeightedGraph ans = new G(ne.nodes,ne.edges);
         //    ans.nodeIter(); ok
        //    NodeData n = new Node("35.21007339305892,32.10107446554622,0.0", "500"); // ok
//            ans.addNode(n); // ok
      //      ans.edgeSize()//ok
      //      ans.edgeIter() ok
      //      ans.connect(); //ok but check with boaz
        //   ans.edgeIter(n.getKey()); ok
      //      ans.getMC() ok
      //      ans.getNode()
      //      ans.hasEdge()
      //      ans.nodeSize()
      //      ans.removeEdge()
      //      ans.removeNode()
        return ans;
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraphAlgorithms ans = null;
        // ****** Add your code here ******
        getJsonToObj(json_file);
        // ********************************
        return ans;
    }
    /**
     * This static function will run your GUI using the json fime.
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        // ****** Add your code here ******
        //
        // ********************************
    }

    public static void main(String[] args) {
        getGrapg("Ex2/data/G1.json");
    }
}