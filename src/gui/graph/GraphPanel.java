package gui.graph;

import javax.swing.*;

import Classes.G;
import Interfaces.NodeData;
import Interfaces.GeoLocation;
import gui.Scale;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.HashMap;
import java.util.LinkedList;
// draw arrow 
/// https://stackoverflow.com/questions/4112701/drawing-a-line-with-arrow-in-java 

public class GraphPanel extends JPanel {
    private final int ARR_SIZE = 4;
    static public LinkedList<NodeData> points = new LinkedList<>();
    static public LinkedList<Line> lines = new LinkedList<>();
    static public boolean redEdges;
    static public boolean blueEdges;
    static public boolean showNodeIndex;
    static public int centerNode;
    static public boolean showEdgesWeight;
    static public boolean showNodeGeoLocation;
    static public HashMap<Integer, NodeData> shortedPathNodes = new HashMap<>();
    static public HashMap<Integer, NodeData> nodesTsp = new HashMap<>();
    static public HashMap<Integer, NodeData> graphNodes = new HashMap<>();
    static public String NodeState = "regularNode";
    static public String EdgeState = "regularEdge";
    public String message = "";

    public GraphPanel() {
        super();
        this.setBackground(new Color(237, 255, 252)); // change color of background
        if (((G) GFrame.GFrameG).Nodes.isEmpty()) {
            message = "load a graph from menu ";
        } else {
            message = "";
            graphNodes = ((G) GFrame.GFrameG).Nodes;
            minMaxVal.getBorderValues();
            graphNodes.forEach((key, node) -> {
                points.add(node);
            });
            ((G) GFrame.GFrameG).Edges.forEach((key, edge) -> {
                var x1 = (int) Scale.scale(GFrame.GFrameG.getNode(edge.getSrc()).getLocation().x(), minMaxVal.minX, minMaxVal.maxX, 50, GFrame.width - 70);
                var x2 = (int) Scale.scale(GFrame.GFrameG.getNode(edge.getDest()).getLocation().x(), minMaxVal.minX, minMaxVal.maxX, 50, GFrame.width - 70);
                var y1 = (int) Scale.scale(GFrame.GFrameG.getNode(edge.getSrc()).getLocation().y(), minMaxVal.minY, minMaxVal.maxY, 50, GFrame.height - 150);
                var y2 = (int) Scale.scale(GFrame.GFrameG.getNode(edge.getDest()).getLocation().y(), minMaxVal.minY, minMaxVal.maxY, 50, GFrame.height - 150);
                var line = new Line(x1, x2, y1, y2, edge.getWeight());
                lines.add(line);
            });
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);

        switch (NodeState) {
            case "regularNode":
                drawNodesRegular(g);
                break;
            case "showNodeID":
                drawNodesID(g);
                break;
            case "showNodeLocation":
                drawNodesGeoLocation(g);
                break;
            default:
                break;
        }

        switch (EdgeState) {
            case "regularEdge":
                drawEdgesRegular(g);
                break;
            case "showRedEdges":
                // drawNodesID(g);
                break;
            case "showBlueEdges":
                // drawNodesGeoLocation(g);
                break;
            case "showEdgesWeight":
                drawEdgesWeight(g);
                break;
            case "showAllColor":
                // drawNodesGeoLocation(g);
                break;
            default:
                break;
        }

    }

    public void drawEdgesWeight(Graphics g) {
        g.setFont(new Font("Font.SERIF", Font.PLAIN, 15)); // set font of text
        for (Line l : lines) {
            g.setColor(Color.black);
            Graphics2D g2 = (Graphics2D) g.create();
            String str = l.w;
            var x1 = l.x1;
            var x2 = l.x2;
            var y1 = l.y1;
            var y2 = l.y2;
            if (l.x1 > l.x2) {
                g.drawString(str, (int) ((l.x2 + l.x1) / 2) + 50, (int) ((l.y2 + l.y1) / 2) + 50);
                y1 = y1 - 5;
                y2 = y2 - 5;
            } else {
                g.drawString(str, (int) ((l.x2 + l.x1) / 2), (int) ((l.y2 + l.y1) / 2));
                y1 = y1 + 10;
                y2 = y2 + 10;
            }

            double dx = x2 - x1, dy = y2 - y1;
            double angle = Math.atan2(dy, dx);
            int len = (int) Math.sqrt(dx * dx + dy * dy);
            AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
            at.concatenate(AffineTransform.getRotateInstance(angle));
            g2.transform(at);
            g2.drawLine(0, 0, len, 0);
            g2.fillPolygon(new int[]{len, len - ARR_SIZE, len - ARR_SIZE, len},
                    new int[]{0, -ARR_SIZE, ARR_SIZE, 0}, 4);
        }
    }

    public void drawEdgesRegular(Graphics g) {
        for (Line l : lines) {
            g.setColor(Color.black);
            Graphics2D g2 = (Graphics2D) g.create();

            var x1 = l.x1;
            var x2 = l.x2;
            var y1 = l.y1;
            var y2 = l.y2;

            if (l.x1 > l.x2) {
                y1 = y1 - 5;
                y2 = y2 - 5;
            } else {
                y1 = y1 + 10;
                y2 = y2 + 10;
            }


            double dx = x2 - x1, dy = y2 - y1;
            double angle = Math.atan2(dy, dx);
            int len = (int) Math.sqrt(dx * dx + dy * dy);
            AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
            at.concatenate(AffineTransform.getRotateInstance(angle));
            g2.transform(at);
            g2.drawLine(0, 0, len, 0);
            g2.fillPolygon(new int[]{len, len - ARR_SIZE, len - ARR_SIZE, len},
                    new int[]{0, -ARR_SIZE, ARR_SIZE, 0}, 4);
        }
    }

    public void drawNodesRegular(Graphics g) {
        for (NodeData p : points) {
            g.setColor(Color.black);
            double xPos = p.getLocation().x();
            double yPos = p.getLocation().y();
            int xPosScale = (int) Scale.scale(xPos, minMaxVal.minX, minMaxVal.maxX, 50, GFrame.width - 70);
            int yPosScale = (int) Scale.scale(yPos, minMaxVal.minY, minMaxVal.maxY, 50, GFrame.height - 150);
            g.fillOval(xPosScale, yPosScale, 10, 10);
        }

        // g.setFont(new Font("Font.SERIF", Font.PLAIN, 15)); // set font of text
        // for (NodeData p : points) {
        // g.setColor(Color.black);
        // double xPos = p.getLocation().x();
        // double yPos = p.getLocation().y();
        // int xPosScale = (int) Scale.scale(xPos, minMaxVal.minX, minMaxVal.maxX, 50,
        // GFrame.width - 70);
        // int yPosScale = (int) Scale.scale(yPos, minMaxVal.minY, minMaxVal.maxY, 50,
        // GFrame.height - 150);
        // String xPosS = String.valueOf(xPos).substring(0,
        // String.valueOf(xPos).indexOf(".") + 4);
        // String yPosS = String.valueOf(yPos).substring(0,
        // String.valueOf(yPos).indexOf(".") + 4);
        // // int xPosI = (int) xPos;
        // // int yPosI = (int) yPos;
        // g.drawString("x: " + xPosS, xPosScale - 10, yPosScale - 15);
        // g.drawString("y: " + yPosS, xPosScale + 10, yPosScale);
        // g.fillOval(xPosScale, yPosScale, 10, 10);

        // // g.setColor(Color.RED);
        // // g.drawLine(xPosI, (int) yPos,xPosI, (int) yPos);
        // // g.setFont(new Font("MV Boli", Font.TRUETYPE_FONT, 15)); // set font of
        // text
        // // g.drawString(xPosS, xPosI, (int) ((yPos + yPos) / 2));
        // // g.drawString(yPosS, yPosI, (int) ((yPos + yPos) / 2));
        // }
    }

    public void drawNodesGeoLocation(Graphics g) {
        g.setFont(new Font("Font.SERIF", Font.PLAIN, 15)); // set font of text
        for (NodeData p : points) {
            g.setColor(Color.black);
            double xPos = p.getLocation().x();
            double yPos = p.getLocation().y();
            int xPosScale = (int) Scale.scale(xPos, minMaxVal.minX, minMaxVal.maxX, 50, GFrame.width - 70);
            int yPosScale = (int) Scale.scale(yPos, minMaxVal.minY, minMaxVal.maxY, 50, GFrame.height - 150);
            String xPosS = String.valueOf(xPos).substring(0, String.valueOf(xPos).indexOf(".") + 4);
            String yPosS = String.valueOf(yPos).substring(0, String.valueOf(yPos).indexOf(".") + 4);
            g.drawString("x: " + xPosS, xPosScale - 10, yPosScale - 15);
            g.drawString("y: " + yPosS, xPosScale + 10, yPosScale);
            g.fillOval(xPosScale, yPosScale, 10, 10);

        }

    }

    public void drawNodesID(Graphics g) {
        g.setFont(new Font("MV Boli", Font.PLAIN, 25)); // set font of text
        for (NodeData p : points) {
            g.setColor(Color.black);
            double xPos = p.getLocation().x();
            double yPos = p.getLocation().y();
            int xPosScale = (int) Scale.scale(xPos, minMaxVal.minX, minMaxVal.maxX, 50, GFrame.width - 70);
            int yPosScale = (int) Scale.scale(yPos, minMaxVal.minY, minMaxVal.maxY, 50, GFrame.height - 150);
            g.drawString(String.valueOf(p.getKey()), xPosScale, yPosScale);
            g.fillOval(xPosScale, yPosScale, 10, 10);

        }
    }

    public void displayHelp() {
        JOptionPane.showMessageDialog(null, "This is a message dialog box", "title", JOptionPane.PLAIN_MESSAGE);
        // message = "Load a json file";

        repaint();
    }

}
