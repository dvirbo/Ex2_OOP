// package gui.graph;

// import Classes.CGeo;
// import Classes.CNode;
// import Classes.G;
// import Classes.GA;
// import FileHandling.StoreNE;
// import Interfaces.DirectedWeightedGraph;
// import Interfaces.DirectedWeightedGraphAlgorithms;
// import Interfaces.NodeData;
// import org.junit.jupiter.api.condition.OS;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.awt.event.KeyEvent;
// import java.awt.event.KeyListener;
// import java.io.File;
// import java.util.ArrayList;
// import java.util.Arrays;

// import static FileHandling.CImport.importJson;
// import static gui.buttons.MenuBarExample.scaleImageIcon;

// public class GFrame extends JFrame implements KeyListener, ActionListener {
//     public DirectedWeightedGraphAlgorithms statiGA;
//     public DirectedWeightedGraph graph;
//     JMenu editMenu;
//     JMenuItem tspItem;
//     JMenu AlgoMenu;
//     GraphPanel panel;
//     JMenuBar menuBar;
//     JMenu fileMenu;
//     JMenu helpMenu;
//     ImageIcon loadIcon;
//     ImageIcon saveIcon;
//     ImageIcon exitIcon;

//     JMenuItem loadItem;
//     JMenuItem saveItem;
//     JMenuItem exitItem;
//     JMenuItem redEdgeItem;
//     JMenuItem BlueEdgeItem;
//     JMenuItem centerItem;
//     JMenuItem explainItem;
//     JMenuItem addNode;
//     JMenuItem removeNode;
//     JMenuItem showEdgeWeight;
//     JMenuItem showNodeGeoLocation;
//     JMenuItem showNodeIndex;
//     JMenuItem addEdge;
//     JMenuItem removeEdge;
//     JMenuItem shortestPathDist;
//     JMenuItem isConnected;
//     JMenuItem shortestPath;
//     public static int width;
//     public static int height;

//     public GFrame(DirectedWeightedGraphAlgorithms ga) {
//         super();
//         this.setTitle("OOPEx2_Dvir&Dolev");
//         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//         this.statiGA = new GA();
//         this.statiGA.init(ga.getGraph());
//         this.graph = ga.getGraph();

//         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//         height = (int) (dim.getHeight() * 0.8);
//         width = (int) (dim.getWidth() * 0.8);
//         this.setSize(width, height);

//         ImageIcon image = new ImageIcon("./src/gui/resources/logo.png");
//         this.setIconImage(image.getImage());

//         panel = new GraphPanel(this, this.graph);

//         // this.getContentPane().add(panel);
//         this.add(panel);
//         this.addKeyListener(this);

//         loadIcon = new ImageIcon("./resources/load.jpg");
//         saveIcon = new ImageIcon("./resources/save.png");
//         exitIcon = new ImageIcon("./resources/exit.jpg");

//         loadIcon = scaleImageIcon(loadIcon, 20, 20);
//         saveIcon = scaleImageIcon(saveIcon, 20, 20);
//         exitIcon = scaleImageIcon(exitIcon, 20, 20);

//         menuBar = new JMenuBar();

//         fileMenu = new JMenu("File");
//         helpMenu = new JMenu("Help");
//         editMenu = new JMenu("Edit");
//         AlgoMenu = new JMenu("Algorithms");

//         loadItem = new JMenuItem("Load");
//         saveItem = new JMenuItem("Save");
//         exitItem = new JMenuItem("Exit");
//         tspItem = new JMenuItem("Tsp");
//         centerItem = new JMenuItem("center");
//         explainItem = new JMenuItem("explain color");
//         addNode = new JMenuItem("add Node");
//         removeNode = new JMenuItem("remove Node");
//         showEdgeWeight = new JMenuItem("show Edges Weight");
//         showNodeGeoLocation = new JMenuItem("show Geo Location");
//         showNodeIndex = new JMenuItem("show Node Index");
//         addEdge = new JMenuItem("add Edge");
//         removeEdge = new JMenuItem("remove Edge");
//         shortestPathDist = new JMenuItem("shortest Path Distance");
//         isConnected = new JMenuItem("is Connected()");
//         shortestPath = new JMenuItem("shortest Path()");
//         redEdgeItem = new JMenuItem("show red edges ");
//         BlueEdgeItem = new JMenuItem("show blue edges ");

//         loadItem.setIcon(loadIcon);
//         saveItem.setIcon(saveIcon);
//         exitItem.setIcon(exitIcon);

//         fileMenu.setMnemonic(KeyEvent.VK_F); // Alt + f for file
//         helpMenu.setMnemonic(KeyEvent.VK_H); // Alt + h for help
//         loadItem.setMnemonic(KeyEvent.VK_L); // l for load
//         saveItem.setMnemonic(KeyEvent.VK_S); // s for save
//         exitItem.setMnemonic(KeyEvent.VK_E); // e for exit

//         fileMenu.add(loadItem);
//         fileMenu.add(saveItem);
//         fileMenu.add(exitItem);

//         menuBar.add(fileMenu);
//         menuBar.add(helpMenu);
//         menuBar.add(AlgoMenu);
//         menuBar.add(editMenu);

//         AlgoMenu.add(tspItem);
//         AlgoMenu.add(centerItem);
//         AlgoMenu.add(isConnected);
//         AlgoMenu.add(shortestPathDist);
//         AlgoMenu.add(shortestPath);

//         editMenu.add(addNode);
//         editMenu.add(removeNode);
//         editMenu.add(showEdgeWeight);
//         editMenu.add(showNodeGeoLocation);
//         editMenu.add(showNodeIndex);
//         editMenu.add(addEdge);
//         editMenu.add(removeEdge);
//         editMenu.add(addEdge);
//         editMenu.add(redEdgeItem);
//         editMenu.add(BlueEdgeItem);

//         helpMenu.add(explainItem);

//         loadItem.addActionListener(this);
//         saveItem.addActionListener(this);
//         exitItem.addActionListener(this);
//         tspItem.addActionListener(this);
//         centerItem.addActionListener(this);
//         isConnected.addActionListener(this);
//         shortestPathDist.addActionListener(this);
//         shortestPath.addActionListener(this);
//         addNode.addActionListener(this);
//         removeNode.addActionListener(this);
//         showNodeGeoLocation.addActionListener(this);
//         showNodeIndex.addActionListener(this);
//         addEdge.addActionListener(this);
//         removeEdge.addActionListener(this);
//         explainItem.addActionListener(this);
//         redEdgeItem.addActionListener(this);
//         BlueEdgeItem.addActionListener(this);

//         this.setJMenuBar(menuBar);

//         // this.pack();
//         // this.setLocationRelativeTo(null);
//         this.setVisible(true);
//     }

//     @Override
//     public void keyTyped(KeyEvent e) {

//     }

//     @Override
//     public void keyPressed(KeyEvent e) {
//         if (e.getKeyCode() == 32) {
//             panel.reset();
//             this.repaint();
//         }
//     }

//     @Override
//     public void keyReleased(KeyEvent e) {

//     }

//     @Override
//     public void actionPerformed(ActionEvent e) {

//         if (e.getSource() == loadItem) {
//             JFileChooser fileChooser = new JFileChooser();
//             int returnValue = fileChooser.showOpenDialog(null);
//             if (returnValue == JFileChooser.APPROVE_OPTION) {
//                 File selectedFile = fileChooser.getSelectedFile();
//                 StoreNE ne = importJson(selectedFile.getPath());
//                 if (ne != null) {
//                     graph = new G(ne.nodes, ne.edges);
//                     statiGA = new GA();
//                     statiGA.init(graph);
//                     this.getContentPane().remove(panel);
//                     panel = new GraphPanel(this, this.graph);
//                     this.add(panel);
//                     this.getContentPane().invalidate();
//                     this.getContentPane().validate();

//                     this.add(panel);
//                 }

//             }
//         }
//         if (e.getSource() == saveItem) {
//             String fileName = JOptionPane.showInputDialog("insert name of the File : for example: G5");
//             JFileChooser fileChooser = new JFileChooser();
//             fileChooser.setCurrentDirectory(new java.io.File("."));
//             fileChooser.setDialogTitle("save");
//             fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//             fileChooser.setAcceptAllFileFilterUsed(false);
//             int save_input = fileChooser.showOpenDialog(null);
//             if (save_input == fileChooser.APPROVE_OPTION) {
//                 String pathToSave = fileChooser.getSelectedFile().getAbsolutePath();
//                 if (System.getProperty("os.name").contains("win")) {
//                     statiGA.save(pathToSave + "\\" + fileName + ".json");
//                 } else {
//                     statiGA.save(pathToSave + "/" + fileName + ".json");
//                 }
//             }
//         }
//         if (e.getSource() == exitItem) {
//             System.exit(0);
//         }
//         if (e.getSource() == tspItem) {
//             String str = JOptionPane
//                     .showInputDialog("insert list of nodes id seperated by comma: for example : 1,2,3 ");
//             ArrayList<String> Slist = new ArrayList<>(Arrays.asList(str.split(",")));
//             ArrayList<NodeData> nodeList = new ArrayList<>();
//             Slist.forEach((s) -> nodeList.add(graph.getNode(Integer.parseInt(s))));

//             String resultS = "";
//             for (NodeData node : statiGA.tsp(nodeList)) {
//                 GraphPanel.nodesTsp.put(node.getKey(), node);
//                 resultS = resultS.concat(node.getKey() + "->");
//             }
//             new java.util.Timer().schedule(
//                     new java.util.TimerTask() {
//                         @Override
//                         public void run() {
//                             GraphPanel.nodesTsp.clear();
//                             repaint();
//                         }
//                     },
//                     5000);
//             repaint();

//             if (!resultS.equals("")) {
//                 resultS = resultS.substring(0, resultS.length() - 2);
//             }
//             JOptionPane.showMessageDialog(null, "Green Nodes result: " + resultS, "Tsp result",
//                     JOptionPane.INFORMATION_MESSAGE);

//         }
//         if (e.getSource() == isConnected) {
//             JOptionPane.showMessageDialog(null, "Calculating ...", "is Connected?", JOptionPane.PLAIN_MESSAGE);
//             if (statiGA.isConnected()) {
//                 JOptionPane.showMessageDialog(null, "your graph is connected", "conncted!",
//                         JOptionPane.INFORMATION_MESSAGE);
//             }
//             JOptionPane.showMessageDialog(null, "your graph is *Not* connected", "Not conncted",
//                     JOptionPane.INFORMATION_MESSAGE);

//         }
//         if (e.getSource() == shortestPathDist) {
//             String src = JOptionPane
//                     .showInputDialog("insert id of source: for example : 1 ");
//             String dest = JOptionPane
//                     .showInputDialog("insert id of destination: for example : 2");
//             String resultS;
//             double w = statiGA.shortestPathDist(Integer.parseInt(src), Integer.parseInt(dest));
//             resultS = String.valueOf(w);
//             JOptionPane.showMessageDialog(null, "result: " + resultS, "shortest path distance result",
//                     JOptionPane.INFORMATION_MESSAGE);

//         }
//         if (e.getSource() == shortestPath) {
//             String src = JOptionPane
//                     .showInputDialog("insert id of source: for example : 1 ");
//             String dest = JOptionPane
//                     .showInputDialog("insert id of destination: for example : 2");

//             String resultS = "";
//             for (NodeData node : statiGA.shortestPath(Integer.parseInt(src), Integer.parseInt(dest))) {
//                 GraphPanel.shortedPathNodes.put(node.getKey(), node);
//                 resultS = resultS.concat(node.getKey() + "->");
//             }
//             new java.util.Timer().schedule(
//                     new java.util.TimerTask() {
//                         @Override
//                         public void run() {
//                             GraphPanel.shortedPathNodes.clear();
//                             repaint();
//                         }
//                     },
//                     5000);
//             repaint();

//             if (!resultS.equals("")) {
//                 resultS = resultS.substring(0, resultS.length() - 2);
//             }
//             JOptionPane.showMessageDialog(null, "Blue Nodes result: " + resultS, "Shortest Path result",
//                     JOptionPane.INFORMATION_MESSAGE);
//         }
//         if (e.getSource() == addNode) {
//             String x1 = JOptionPane.showInputDialog("give me x coordinate: ");
//             String y1 = JOptionPane.showInputDialog("give me y coordinate: ");
//             var g = new CGeo(Double.parseDouble(x1), Double.parseDouble(y1), 0.0);
//             var n = new CNode(GraphPanel.points.size(), g, 0.0, "White", -1);
//             this.graph.addNode(n);
//             this.remove(this.panel);
//             panel = new GraphPanel(this, this.graph);
//             this.add(panel);
//         }
//         if (e.getSource() == removeNode) {
//             String id = JOptionPane.showInputDialog("give me id of node to remove: ");
//             var idInt = Integer.parseInt(id);
//             var g = new G(this.graph);

//             g.removeNode(idInt);
//             this.remove(this.panel);

//             panel = new GraphPanel(this, g);
//             this.add(panel);
//             repaint();
//         }
//         if (e.getSource() == showNodeGeoLocation) {
//             GraphPanel.showNodeGeoLocation = true;
//             repaint();
//         }
//         if (e.getSource() == showNodeIndex) {
//             GraphPanel.showNodeIndex = true;
//             repaint();
//         }
//         if (e.getSource() == addEdge) {
//             String id = JOptionPane.showInputDialog("give me id of node to remove: ");
//             this.graph.removeNode(Integer.parseInt(id));
//             this.remove(this.panel);
//             panel = new GraphPanel(this, this.graph);
//             this.add(panel);
//         }
//         if (e.getSource() == removeEdge) {

//         }
//         if (e.getSource() == explainItem) {
//             JOptionPane.showMessageDialog(null, "red edges go right , blue the opposite side", "Info",
//                     JOptionPane.INFORMATION_MESSAGE);

//         }
//         if (e.getSource() == redEdgeItem) {
//         }
//         if (e.getSource() == BlueEdgeItem) {

//         }
//     }
// }
