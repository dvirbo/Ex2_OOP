package FileHandling;

import Interfaces.DirectedWeightedGraph;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;

public class CExport {

    public static boolean GAsave(String fileName, DirectedWeightedGraph graph) {
        Gson gson = new Gson();
        String json = gson.toJson(graph);
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(json);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        return true;
    }


}
