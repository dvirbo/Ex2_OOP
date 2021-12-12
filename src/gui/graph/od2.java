// package gui.graph;

// import Classes.CGeo;
// import Classes.CNode;
// import Interfaces.DirectedWeightedGraph;
// import Interfaces.GeoLocation;
// import Interfaces.NodeData;
// import gui.Scale;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.MouseEvent;
// import java.awt.event.MouseListener;
// import java.awt.geom.AffineTransform;
// import java.awt.geom.Point2D;
// import java.util.HashMap;
// import java.util.LinkedList;

// // draw arrow 
// /// https://stackoverflow.com/questions/4112701/drawing-a-line-with-arrow-in-java 

// public class GraphPanel extends JPanel implements MouseListener {
//     static public boolean redEdges;
//     static public boolean blueEdges;
//     static public boolean showNodeIndex;
//     static public int centerNode;
//     static public boolean showEdgesWeight;
//     static public boolean showNodeGeoLocation;
//     static public HashMap<Integer, NodeData> shortedPathNodes = new HashMap<>();
//     static public HashMap<Integer, NodeData> nodesTsp = new HashMap<>();

//     static public LinkedList<Line> lines = new LinkedList<>();

//     static public LinkedList<NodeData> points = new LinkedList<>();

//     private final int ARR_SIZE = 4;
//     static public Double XScaleMin = Double.MAX_VALUE;
//     static public Double YScaleMin = Double.MAX_VALUE;
//     static public Double YScaleMax = Double.MIN_VALUE;
//     static public Double XScaleMax = Double.MIN_VALUE;

//     public GraphPanel(GFrame frame, DirectedWeightedGraph graph) {
//         super();
//         this.setBackground(new Color(243, 228, 162)); // change color of background
//         this.setSize(GFrame.width, GFrame.height);
//         var nodeiter = graph.nodeIter();
//         while (nodeiter.hasNext()) {
//             NodeData n = nodeiter.next();
//             GeoLocation geoLocation = n.getLocation();
//             YScaleMax = Math.max(YScaleMax, geoLocation.y());
//             YScaleMin = Math.min(YScaleMin, geoLocation.y());
//             XScaleMin = Math.min(XScaleMin, geoLocation.x());
//             XScaleMax = Math.max(XScaleMax, geoLocation.x());
//         }

// //        YScaleMax = YScaleMax ;
// //        YScaleMin = YScaleMin * 0.8;
// //        XScaleMin = XScaleMin * 0.8;
// //        XScaleMax = XScaleMax * 0.8;

//         var nodeiter2 = graph.nodeIter();
//         while (nodeiter2.hasNext()) {
//             var node = nodeiter2.next();
//             var x = Scale.scale(node.getLocation().x(), XScaleMin, XScaleMax, 0, GFrame.width);
//             var y = Scale.scale(node.getLocation().y(), YScaleMin, YScaleMax, 0, GFrame.height);
//             var p = new CNode(node.getKey(), new CGeo(x, y, 0.0), 0.0, "White", -1);

//             // System.out.println(p);
//         }

//         var edgeIter = graph.edgeIter();
//         while (edgeIter.hasNext()) {
//             var edge = edgeIter.next();

//             var x1 = Scale.scale(graph.getNode(edge.getSrc()).getLocation().x(), XScaleMin, XScaleMax, 0, GFrame.width);
//             var x2 = Scale.scale(graph.getNode(edge.getDest()).getLocation().x(), XScaleMin, XScaleMax, 0,
//                     GFrame.width);
//             var y1 = Scale.scale(graph.getNode(edge.getSrc()).getLocation().y(), YScaleMin, YScaleMax, 0,
//                     GFrame.height);
//             var y2 = Scale.scale(graph.getNode(edge.getDest()).getLocation().y(), YScaleMin, YScaleMax, 0,
//                     GFrame.height);

//             var line = new Line(x1, x2, y1, y2, edge.getWeight());
//             lines.add(line);
//             // System.out.println(p);
//         }

//         this.addMouseListener(this);

//         this.setVisible(true);
//     }

//     public void reset() {
//         points = new LinkedList<>();
//         this.repaint();
//     }

//     @Override
//     public void mouseClicked(MouseEvent e) {
//         // System.out.println("mouseClicked");

//     }

//     @Override
//     public void mousePressed(MouseEvent e) {
//         // message = "";
//         // int x = e.getX();
//         // int y = e.getY();
//         // Point2D p = new Point2D.Double(x, y);
//         // points.add(p);
//         // repaint();
//         // System.out.println("mousePressed");

//     }

//     @Override
//     public void mouseReleased(MouseEvent e) {
//         // System.out.println("mouseReleased");

//     }

//     @Override
//     public void mouseEntered(MouseEvent e) {
//         // System.out.println("mouseEntered");
//     }

//     @Override
//     public void mouseExited(MouseEvent e) {
//         // System.out.println("mouseExited");
//     }

//     void drawArrow(Graphics g1, double x1, double y1, double x2, double y2) {
//         Graphics2D g2 = (Graphics2D) g1.create();

//         double dx = x2 - x1 - 15, dy = y2 - y1;
//         double angle = Math.atan2(dy, dx);
//         int len = (int) Math.sqrt(dx * dx + dy * dy);
//         AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
//         at.concatenate(AffineTransform.getRotateInstance(angle));
//         g2.transform(at);

//         // Draw horizontal arrow starting in (0, 0)
//         g2.drawLine(0, 0, len, 0);
//         g2.fillPolygon(new int[] { len, len - ARR_SIZE, len - ARR_SIZE, len },
//                 new int[] { 0, -ARR_SIZE, ARR_SIZE, 0 }, 4);
//     }

//     void drawlinesColor(Graphics g) {
//         lines.forEach((l) -> {

//             if (l.x2 > l.x1) {
//                 g.setColor(new Color(183, 24, 48));
//                 // g.drawString(l.w, (int) ((l.x2 + l.x1) / 2) + 50, (int) ((l.y2 + l.y1) / 2) +
//                 // 50);
//                 drawArrow(g, l.x1 + 5, l.y1 + 5, l.x2 + 5, l.y2 + 5);
//             } else {
//                 g.setColor(new Color(28, 119, 23));
//                 // g.drawString(l.w, (int) ((l.x2 + l.x1) / 2), (int) ((l.y2 + l.y1) / 2));
//                 drawArrow(g, l.x1, l.y1, l.x2, l.y2);
//             }

//         });
//     }

//     void drawlinesDirected(Graphics g) {
//         lines.forEach((l) -> {

//             if (l.x2 > l.x1) {
//                 // g.setColor(new Color(183, 24, 48));
//                 // g.drawString(l.w, (int) ((l.x2 + l.x1) / 2) + 50, (int) ((l.y2 + l.y1) / 2) +
//                 // 50);
//                 drawArrow(g, l.x1, l.y1 - 5, l.x2, l.y2 - 5);
//             } else {
//                 // g.setColor(new Color(28, 119, 23));
//                 // g.drawString(l.w, (int) ((l.x2 + l.x1) / 2), (int) ((l.y2 + l.y1) / 2));
//                 drawArrow(g, l.x1, l.y1 + 15, l.x2, l.y2 + 15);
//             }

//         });
//     }

//     void drawlinesColorWithStr(Graphics g) {
//         lines.forEach((l) -> {

//             if (l.x2 > l.x1) {
//                 g.setColor(new Color(183, 24, 48));
//                 g.drawString(l.w, (int) ((l.x2 + l.x1) / 2) + 50, (int) ((l.y2 + l.y1) / 2) + 50);
//                 drawArrow(g, l.x1 + 5, l.y1 + 5, l.x2 + 5, l.y2 + 5);
//             } else {
//                 g.setColor(new Color(28, 119, 23));
//                 g.drawString(l.w, (int) ((l.x2 + l.x1) / 2), (int) ((l.y2 + l.y1) / 2));
//                 drawArrow(g, l.x1, l.y1, l.x2, l.y2);
//             }

//         });
//     }

//     @Override
//     public void paintComponent(Graphics g) {
//         super.paintComponent(g);

//         int i = 0;
//         for (NodeData p : points) {
//             var xPos = (int) p.getLocation().x();
//             var yPos = (int) p.getLocation().y();
//             var key = p.getKey();
//             if (nodesTsp.containsKey(i)) {
//                 g.setColor(new Color(11, 199, 4));
//                 g.fillOval(xPos, yPos, 15, 15);
//                 g.setFont(new Font("MV Boli", Font.PLAIN, 15)); // set font of text
//                 g.drawString(String.valueOf(key), xPos, yPos);
//             }

//             if (shortedPathNodes.containsKey(key)) {
//                 g.setColor(new Color(4, 59, 199));
//                 g.fillOval(xPos, yPos, 15, 15);
//                 g.setFont(new Font("MV Boli", Font.PLAIN, 15)); // set font of text
//                 g.drawString(String.valueOf(key), xPos, yPos);
//             }

//             g.setColor(new Color(42, 12, 15));
//             g.fillOval(xPos, yPos, 10, 10);
//             g.setFont(new Font("MV Boli", Font.PLAIN, 15)); // set font of text
//             g.drawString(String.valueOf(key), xPos, yPos);
//         }

//         lines.forEach((l) -> drawArrow(g, l.x1, l.y1, l.x2, l.y2));
//     }

// }
