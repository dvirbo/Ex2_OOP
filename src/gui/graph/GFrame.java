package gui.graph;

import Interfaces.DirectedWeightedGraphAlgorithms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static gui.buttons.MenuBarExample.scaleImageIcon;

public class GFrame extends JFrame implements KeyListener, ActionListener {
    GraphPanel panel;
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenu editMenu;
    JMenu helpMenu;
    JMenuItem loadItem;
    JMenuItem saveItem;
    JMenuItem exitItem;
    ImageIcon loadIcon;
    ImageIcon saveIcon;
    ImageIcon exitIcon;
    DirectedWeightedGraphAlgorithms ga;

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
        editMenu = new JMenu("Edit");
        helpMenu = new JMenu("Help");

        loadItem = new JMenuItem("Load");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");

        loadItem.setIcon(loadIcon);
        saveItem.setIcon(saveIcon);
        exitItem.setIcon(exitIcon);

        fileMenu.setMnemonic(KeyEvent.VK_F); // Alt + f for file
        editMenu.setMnemonic(KeyEvent.VK_E); // Alt + e for edit
        helpMenu.setMnemonic(KeyEvent.VK_H); // Alt + h for help
        loadItem.setMnemonic(KeyEvent.VK_L); // l for load
        saveItem.setMnemonic(KeyEvent.VK_S); // s for save
        exitItem.setMnemonic(KeyEvent.VK_E); // e for exit

        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        loadItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);

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
            
// ga.load(file)
        }
        if (e.getSource() == saveItem) {
            
        }
        if (e.getSource() == exitItem) {
            System.exit(0);
        }
    }
}
