package FileHandling;

// import FileHandling.StoreNE;
import Classes.CEdge;
import Classes.CNode;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import Interfaces.EdgeData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileHandling {
    public static StoreNE getJsonToObj(String json_file) {

        File input = new File(json_file);
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            // process all data:
            JsonArray jsonArrayNodes = fileObject.get("Nodes").getAsJsonArray();
            JsonArray jsonArrayEdges = fileObject.get("Edges").getAsJsonArray();

            List<CNode> nodes = new ArrayList<>();
            List<EdgeData> edges = new ArrayList<>();

            for (JsonElement jnode : jsonArrayNodes) {
                JsonObject nodeJsonObject = jnode.getAsJsonObject();
                String pos = null;
                if (nodeJsonObject.has("pos")) {
                    pos = nodeJsonObject.get("pos").getAsString();
                }

                String id = null;
                if (nodeJsonObject.has("id")) {
                    id = nodeJsonObject.get("id").getAsString();
                }

                CNode node = new CNode(pos, id);
                nodes.add(node);
            }

            /// edges
            for (JsonElement jedge : jsonArrayEdges) {
                JsonObject edgeJsonObject = jedge.getAsJsonObject();
                String src = null;
                if (edgeJsonObject.has("src")) {
                    src = edgeJsonObject.get("src").getAsString();
                }

                String dest = null;
                if (edgeJsonObject.has("dest")) {
                    dest = edgeJsonObject.get("dest").getAsString();
                }

                String w = null;
                if (edgeJsonObject.has("w")) {
                    w = edgeJsonObject.get("w").getAsString();
                }

                CEdge edge = new CEdge(Integer.parseInt(src),
                        Integer.parseInt(dest),
                        Double.parseDouble(w));
                edges.add(edge);
            }
            // Print :
            // System.out.println("All my nodes are: " + nodes);
            // System.out.println("All my edges are: " + edges);

            HashMap<Integer, CNode> hp_nodes = convertNodesList(nodes);
            HashMap<String, EdgeData> hp_edges = convertEdgesList(edges);
            // The method convertEdgesList(List<EdgeData>, List<NodeC>) in the type
            // FileHandling is not applicable for the arguments (List<CEdge>,
            // List<NodeC>)Java(67108979)
            // hp_nodes.entrySet().forEach(entry -> {
            // System.out.println(entry.getKey() + " " + entry.getValue());
            // });
            // System.out.println("*******************************");
            // hp_edges.entrySet().forEach(entry -> {
            // System.out.println(entry.getKey() + " " + entry.getValue());
            // });

            StoreNE ne = new StoreNE(hp_nodes, hp_edges);
            return ne;

        } catch (FileNotFoundException e) {
            System.err.println("Error file not found ");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error processing input file!");
            e.printStackTrace();
        }

        StoreNE ne = null;
        return ne;
    }

    public static HashMap<Integer, CNode> convertNodesList(List<CNode> list) {
        HashMap<Integer, CNode> map = new HashMap<>();
        for (CNode node : list) {
            map.put(node.getKey(), node);
        }
        return map;
    }

    public static HashMap<String, EdgeData> convertEdgesList(List<EdgeData> edgeDataList) {
        HashMap<String, EdgeData> map = new HashMap<>();
        for (EdgeData edge : edgeDataList) {
            CEdge e = new CEdge(edge.getSrc(), edge.getDest(), edge.getWeight());
            map.put("src_" + edge.getSrc() + "_dest_" + edge.getDest(), e);
        }
        return map;
    }

    // public static void main(String[] args) {

    // StoreNE ne = getJsonToObj("Ex2/data/G1.json");
    // }
}
