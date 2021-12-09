package gui.graph;

import Interfaces.DirectedWeightedGraph;
import Interfaces.DirectedWeightedGraphAlgorithms;
import gui.Scale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.LinkedList;

// draw arrow 
/// https://stackoverflow.com/questions/4112701/drawing-a-line-with-arrow-in-java 

public class GraphPanel extends JPanel implements MouseListener {

    private final int ARR_SIZE = 4;
    private final DirectedWeightedGraphAlgorithms ga;
    private final DirectedWeightedGraph graph;
    private final LinkedList<Line> lines;
    private final int width;
    private final int height;
    String message;
    private LinkedList<Point2D> points;

    public GraphPanel(DirectedWeightedGraphAlgorithms ga, GFrame frame) {
        super();
        this.ga = ga;
        this.graph = ga.getGraph();
        frame.setVisible(true);
        this.setBackground(new Color(206, 194, 114)); // change color of background
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.height = (int) dim.getHeight();
        this.width = (int) dim.getWidth();
        this.setSize((int) (this.width * 0.8), (int) (this.height * 0.9));
        points = new LinkedList<>();
        lines = new LinkedList<>();
        var nodeiter = ga.getGraph().nodeIter();
        while (nodeiter.hasNext()) {
            var node = nodeiter.next();
            var x = Scale.scale(node.getLocation().x(), 35.177, 35.23, 0, width - 150);
            var y = Scale.scale(node.getLocation().y(), 32.101, 32.2, 150, height * 7);
            var p = new Point2D.Double(x, y);
            points.add(p);
            // System.out.println(p);
        }

        var edgeIter = ga.getGraph().edgeIter();
        while (edgeIter.hasNext()) {
            var edge = edgeIter.next();

            var x1 = Scale.scale(graph.getNode(edge.getSrc()).getLocation().x(), 35.177, 35.23, 0, width - 150);
            var x2 = Scale.scale(graph.getNode(edge.getDest()).getLocation().x(), 35.177, 35.23, 0, width - 150);
            var y1 = Scale.scale(graph.getNode(edge.getSrc()).getLocation().y(), 32.101, 32.2, 150, height * 7);
            var y2 = Scale.scale(graph.getNode(edge.getDest()).getLocation().y(), 32.101, 32.2, 150, height * 7);

            var line = new Line(x1, x2, y1, y2, edge.getWeight());
            lines.add(line);
            // System.out.println(p);
        }
        repaint();
        this.addMouseListener(this);
        message = "";
    }

    public void reset() {
        points = new LinkedList<>();
        this.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // System.out.println("mouseClicked");

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // message = "";
        // int x = e.getX();
        // int y = e.getY();
        // Point2D p = new Point2D.Double(x, y);
        // points.add(p);
//        repaint();
        // System.out.println("mousePressed");

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // System.out.println("mouseReleased");

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // System.out.println("mouseEntered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // System.out.println("mouseExited");
    }

    void drawArrow(Graphics g1, double x1, double y1, double x2, double y2) {
        Graphics2D g2 = (Graphics2D) g1.create();

        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx * dx + dy * dy);
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g2.transform(at);

        // Draw horizontal arrow starting in (0, 0)
        g2.drawLine(0, 0, len, 0);
        g2.fillPolygon(new int[]{len, len - ARR_SIZE, len - ARR_SIZE, len},
                new int[]{0, -ARR_SIZE, ARR_SIZE, 0}, 4);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int i = 0;
        for (Point2D p : points) {
            g.setColor(new Color(42, 12, 15));
            g.fillOval((int) p.getX(), (int) p.getY(), 10, 10);
            g.setFont(new Font("MV Boli", Font.PLAIN, 15)); // set font of text
            g.drawString(String.valueOf(i++), (int) p.getX(),
                    (int) p.getY());
        }

        lines.forEach((l) -> {

            if (l.x2 > l.x1) {
                g.setColor(new Color(21, 50, 145));
                g.drawString(l.w, (int) ((l.x2 + l.x1) / 2) + 50, (int) ((l.y2 + l.y1) / 2) + 50);
                drawArrow(g, l.x1 + 5, l.y1 + 5, l.x2 + 5, l.y2 + 5);
            } else {
                g.setColor(new Color(159, 15, 15));
                g.drawString(l.w, (int) ((l.x2 + l.x1) / 2), (int) ((l.y2 + l.y1) / 2));
                drawArrow(g, l.x1, l.y1, l.x2, l.y2);
            }


        });
    }
}
