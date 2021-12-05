import Classes.G;
import FileHandling.StoreNE;
import Interfaces.DirectedWeightedGraph;
import Interfaces.DirectedWeightedGraphAlgorithms;

import static FileHandling.FileHandling.getJsonToObj;


/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to Classestest your implementation
     * for testing: "Ex2/data/G1.json"
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {
        StoreNE ne = getJsonToObj(json_file);
        DirectedWeightedGraph ans = new G(ne.nodes, ne.edges);
        return ans;
    }

    /**
     * This static function will be used to Classestest your implementation
     *
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
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        // ****** Add your code here ******
        //
        // ********************************
    }

    public static void main(String[] args) {
        getGrapg("./json_data/G1.json");
    }
}