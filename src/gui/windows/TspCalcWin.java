package gui.windows;

import Interfaces.DirectedWeightedGraph;

import Interfaces.NodeData;
import gui.graph.GFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TspCalcWin extends JFrame {

    private final JPanel contentPane;
    private final JTextField textField;
    private final JLabel result;
    
    /**
     * Create the frame.
     */
    public TspCalcWin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setBounds(97, 77, 214, 30);
        contentPane.add(textField);
        textField.setColumns(10);

        result = new JLabel("result:");
        result.setBounds(25, 121, 399, 72);
        contentPane.add(result);

        JLabel explain = new JLabel("insert list of nodes id for example : 1,2,3");
        explain.setBounds(67, 37, 292, 30);
        contentPane.add(explain);

        JButton calc = new JButton("calculate tsp");
        calc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> Slist = new ArrayList<>(Arrays.asList(textField.getText().split(",")));
                ArrayList<NodeData> nodeList = new ArrayList<>();
                DirectedWeightedGraph graph = GFrame.graph;
                Slist.forEach((s) -> nodeList.add(graph.getNode(Integer.parseInt(s))));


                List<NodeData> resultNode = GFrame.statiGA.tsp(nodeList);
                String resultS = "";
                for (NodeData n : resultNode) {
                    resultS = resultS.concat(n.getKey() + "->");
                }

                result.setText(resultS);
            }
        });
        calc.setBounds(154, 214, 89, 23);
        contentPane.add(calc);
        this.setVisible(true);
    }

}