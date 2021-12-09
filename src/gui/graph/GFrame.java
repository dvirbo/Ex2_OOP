package gui.graph;

import Classes.G;
import Classes.GA;
import FileHandling.StoreNE;
import Interfaces.DirectedWeightedGraph;
import Interfaces.DirectedWeightedGraphAlgorithms;
import gui.windows.TspCalcWin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import static FileHandling.CImport.importJson;
import static gui.buttons.MenuBarExample.scaleImageIcon;

public class GFrame extends JFrame implements KeyListener, ActionListener {
    JMenuItem tspItem;
    JMenu AlgoMenu;
    GraphPanel panel;
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenu helpMenu;
    JMenuItem loadItem;
    JMenuItem saveItem;
    JMenuItem exitItem;
    ImageIcon loadIcon;
    ImageIcon saveIcon;
    ImageIcon exitIcon;
    DirectedWeightedGraphAlgorithms ga;
    DirectedWeightedGraph graph;

    public GFrame(DirectedWeightedGraphAlgorithms ga) {
        super();
        this.setTitle("OOPEx2_Dvir&Dolev");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.ga = ga;

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int) dim.getHeight();
        int width = (int) dim.getWidth();
        this.setSize((int) (width * 0.8), (int) (height * 0.9));


        ImageIcon image = new ImageIcon("./src/gui/resources/logo.png");
        this.setIconImage(image.getImage());

        panel = new GraphPanel(ga, this);

        this.add(panel);

        this.addKeyListener(this);


        loadIcon = new ImageIcon("./resources/load.jpg");
        saveIcon = new ImageIcon("./resources/save.png");
        exitIcon = new ImageIcon("./resources/exit.jpg");

        loadIcon = scaleImageIcon(loadIcon, 20, 20);
        saveIcon = scaleImageIcon(saveIcon, 20, 20);
        exitIcon = scaleImageIcon(exitIcon, 20, 20);

        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        helpMenu = new JMenu("Help");
        AlgoMenu = new JMenu("Algorithms");

        loadItem = new JMenuItem("Load");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");
        tspItem = new JMenuItem("Tsp");

        loadItem.setIcon(loadIcon);
        saveItem.setIcon(saveIcon);
        exitItem.setIcon(exitIcon);

        fileMenu.setMnemonic(KeyEvent.VK_F); // Alt + f for file
        helpMenu.setMnemonic(KeyEvent.VK_H); // Alt + h for help
        loadItem.setMnemonic(KeyEvent.VK_L); // l for load
        saveItem.setMnemonic(KeyEvent.VK_S); // s for save
        exitItem.setMnemonic(KeyEvent.VK_E); // e for exit

        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);

        AlgoMenu.add(tspItem);


        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        menuBar.add(AlgoMenu);

        loadItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);
        tspItem.addActionListener(this);

        this.setJMenuBar(menuBar);

        this.setVisible(true);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 32) {
            panel.reset();
            this.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loadItem) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                StoreNE ne = importJson(selectedFile.getPath());
                if (ne != null) {
                    this.graph = new G(ne.nodes, ne.edges);
                    this.ga = new GA();
                    ga.init(this.graph);
                    this.getContentPane().remove(panel);
                    panel = new GraphPanel(ga, this);
                    this.add(panel);
                    this.getContentPane().invalidate();
                    this.getContentPane().validate();


                    this.add(panel);
                }


            }
        }
        if (e.getSource() == saveItem) {
            ga.save("GraphOutPut.json");
        }
        if (e.getSource() == exitItem) {
            System.exit(0);
        }

        if (e.getSource() == tspItem) {
            new TspCalcWin(ga);
        }
    }
}
