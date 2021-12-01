package src;

import src.api.NodeEdge;
import src.api.Edge;
import src.api.Node;
import src.api.NodeEdge;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import src.interfaces.EdgeData;
import src.interfaces.NodeData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FileHandling {
    public static NodeEdge getJsonToObj(String json_file) {

        File input = new File(json_file);
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            // process all data:
            JsonArray jsonArrayNodes = fileObject.get("Nodes").getAsJsonArray();
            JsonArray jsonArrayEdges = fileObject.get("Edges").getAsJsonArray();

            List<Node> nodes = new ArrayList<>();
            List<Edge> edges = new ArrayList<>();

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

                Node node = new Node(pos, id);
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

                Edge edge = new Edge(Integer.parseInt(src),
                        Integer.parseInt(dest),
                        Double.parseDouble(w));
                edges.add(edge);
            }

            // Print :
            System.out.println("All my nodes are: " + nodes);
            System.out.println("All my edges are: " + edges);

            NodeEdge ne = new NodeEdge(nodes, edges);
            return ne;

        } catch (FileNotFoundException e) {
            System.err.println("Error file not found ");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error processing input file!");
            e.printStackTrace();
        }

        NodeEdge ne = null;
        return ne;
    }


//    public HashMap<Integer, NodeData> convertListAfterJava8(List<Node> list) {
//        HashMap<Integer, NodeData> map = list.stream()
//                .collect(Collectors.toMap(NodeData::getId, Function.identity()));
//        return map;
//    }

    public HashMap<Integer, NodeData> convertListAfterJava8(List<Node> list) {
        HashMap<Integer, NodeData> map = new HashMap<>();
        for (NodeData node : list) {
            map.put(node.getKey(), node);
        }
        return map;
    }

    public HashMap<Integer, EdgeData> convertListAfterJava8(List<Node> list) {
        HashMap<Integer, EdgeData> map = new HashMap<>();
        for (EdgeData edge : list) {
            map.put(Edge.uniq , node);
        }
        return map;
    }


    public static void main(String[] args) {

        NodeEdge ne = getJsonToObj("Ex2/data/G1.json");
    }
}
