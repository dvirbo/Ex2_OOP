import Classes.G;
import Classes.GA;
import FileHandling.StoreNE;
import Interfaces.DirectedWeightedGraph;
import Interfaces.DirectedWeightedGraphAlgorithms;
import Interfaces.NodeData;
import gui.graph.GFrame;

import java.util.ArrayList;
import java.util.Arrays;

import static FileHandling.CImport.importJson;

/**
 * This class is the main class for Ex2 - your implementation will be tested
 * using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to ClassesTest your implementation
     * for testing: "Ex2/data/G1.json"
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return ans - DirectedWeightedGraph
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {
        StoreNE ne = importJson(json_file);
        if (ne != null) {
            return new G(ne.nodes, ne.edges);
        }
        return null;
    }

    /**
     * This static function will be used to ClassesTest your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return ans - DirectedWeightedGraphAlgorithms
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraphAlgorithms ans = new GA();
        // ****** Add your code here ******
        ans.init(getGrapg(json_file));
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
        new GFrame(alg);
        // ********************************
    }

    public static void main(String[] args) {

<<<<<<< HEAD
        runGUI("./json_data/G1.json");
=======
        // var ans = getGrapgAlgo("./json_data/1000Nodes.json");
        // System.out.println("daniel");
        // System.out.println(ans.center());
        // System.out.println(((GA) ans).bfs());
        // לעשות bfs
        // לוקח קודקוד אחד
        // לבדוק שהוא הגיע לכולם
        // אותו קודקוד לוקח עוד פעם
        // ומפעיל bfs
        // על הגרף ההופכי שלו
        // מה זה אומר הופכי הופך את כל הכיוונים של הצלעות
        // אם זה גם עובד סימן שהוא מחובר
      //  runGUI("./json_data/G2.json");

        var ans = getGrapgAlgo("./json_data/1000Nodes.json");
      //  var ans = getGrapgAlgo("./json_data/1000Nodes.json");
      //  System.out.println("daniel");
        System.out.println(ans.shortestPathDist(790,830));
//       var ans = getGrapgAlgo("./json_data/G1.json");
//        ArrayList<String> Slist = new ArrayList<>(Arrays.asList("0,1,5,7".split(",")));
//        ArrayList<NodeData> nodeList = new ArrayList<>();
//        DirectedWeightedGraph graph = ans.getGraph();
//        Slist.forEach((s) -> nodeList.add(graph.getNode(Integer.parseInt(s))));
//
//        System.out.println(ans.tsp(nodeList));

       // runGUI("./json_data/G1.json");

>>>>>>> d446a842827b5080a96ae6c0a1527cbe646e735f

    }
}