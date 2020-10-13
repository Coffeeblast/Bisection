package my;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    private JTextField tfA, tfB, tfErr, tfXmax, tfYmax, tfXmin, tfYmin;
    private JLabel lblRootFound, lblNumberOfSteps, lblA, lblB, lblErr, lblGraphTitle, lblXmax, lblXmin, lblYmax,
            lblYmin;
    private JFrame frame;
    private JPanel pnlGraph, pnlBoundaries, pnlResultDisplay, pnlSetBisection;
    private Graph graph;
    private Bisection bisection;
    private FunctionCalculator fc;

    public App(double minX, double maxX, double minY, double maxY,  FunctionCalculator fc) {
        this.fc = fc;
        bisection = new Bisection(fc);
        graph = new Graph(minX, maxX, minY, maxY, 300, 300, fc);

        createUI();
    }

    private void createUI(){
        frame = new JFrame("Bisection");
        frame.setSize(800,450);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        createPanels();

        setupGraphPart();
        createSetBoundariesPart();
        createResultDisplayPart();
        createSetBisectionPart();

        frame.setVisible(true);
    }

    private void createPanels(){
        Border bor = BorderFactory.createLineBorder(Color.BLACK);

        pnlGraph = new JPanel();
        pnlGraph.setBounds(25, 25, 300, 350);
        pnlGraph.setLayout(null);
        frame.add(pnlGraph);

        pnlBoundaries = new JPanel();
        pnlBoundaries.setBounds(350, 25, 190, 250);
        pnlBoundaries.setLayout(null);
        pnlBoundaries.setBorder(BorderFactory.createTitledBorder(bor, "Graph boundary values"));
        frame.add(pnlBoundaries);

        pnlResultDisplay = new JPanel();
        pnlResultDisplay.setBounds(350, 300, 300, 90);
        pnlResultDisplay.setLayout(null);
        pnlResultDisplay.setBorder(BorderFactory.createTitledBorder(bor, "Bisection results"));
        frame.add(pnlResultDisplay);

        pnlSetBisection = new JPanel();
        pnlSetBisection.setBounds(565, 25, 200, 250);
        pnlSetBisection.setLayout(null);
        pnlSetBisection.setBorder(BorderFactory.createTitledBorder(bor, "Bisection parameters"));
        frame.add(pnlSetBisection);
    }

    private void setupGraphPart(){
        graph.setBounds(0, 0, graph.getPixSizeX(), graph.getPixSizeY());
        pnlGraph.add(graph);

        lblGraphTitle = new JLabel ("Graph of f(x)");
        lblGraphTitle.setBounds(100, 310, 200, 25);
        pnlGraph.add(lblGraphTitle);
    }

    private void createSetBoundariesPart(){
        lblXmax = new JLabel ("Xmax");
        lblXmax.setBounds(10, 50, 50, 25);
        pnlBoundaries.add(lblXmax);

        lblXmin = new JLabel ("Xmin");
        lblXmin.setBounds(10, 85, 50, 25);
        pnlBoundaries.add(lblXmin);

        lblYmax = new JLabel ("Ymax");
        lblYmax.setBounds(10, 120, 50, 25);
        pnlBoundaries.add(lblYmax);

        lblYmin = new JLabel ("Ymin");
        lblYmin.setBounds(10, 155, 50, 25);
        pnlBoundaries.add(lblYmin);

        tfXmax = new JTextField ("9");
        tfXmax.setBounds(50, 50, 120, 25);
        pnlBoundaries.add(tfXmax);

        tfYmax = new JTextField ("9");
        tfYmax.setBounds(50, 120, 120, 25);
        pnlBoundaries.add(tfYmax);

        tfXmin = new JTextField ("-1");
        tfXmin.setBounds(50, 85, 120, 25);
        pnlBoundaries.add(tfXmin);

        tfYmin = new JTextField ("-1");
        tfYmin.setBounds(50, 155, 120, 25);
        pnlBoundaries.add(tfYmin);

        JButton bBoundaries = new JButton ("Set boundaries");
        bBoundaries.setBounds(50, 190, 120, 25);
        pnlBoundaries.add(bBoundaries);
        bBoundaries. addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double maxX = Double.parseDouble(tfXmax.getText());
                    double minX = Double.parseDouble(tfXmin.getText());
                    double maxY = Double.parseDouble(tfYmax.getText());
                    double minY = Double.parseDouble(tfYmin.getText());
                    if (maxX <= minX){
                        JOptionPane.showMessageDialog(null,"maxX must be greater than minX ");
                    } else if (maxY <= minY){
                        JOptionPane.showMessageDialog(null,"maxY must be greater than minY ");
                    } else {
                        graph.setScale(minX, maxX, minY, maxY);
                        graph.repaint();
                    }
                }catch (java.lang.NumberFormatException | java.lang.NullPointerException ex) {
                    JOptionPane.showMessageDialog(null,"The boundaries must be numbers!");
                }
            }
        });
    }

    private void createResultDisplayPart(){
        lblRootFound = new JLabel ("No results yet");
        lblRootFound.setBounds(50, 25, 250, 25);
        pnlResultDisplay.add(lblRootFound);

        lblNumberOfSteps = new JLabel ("");
        lblNumberOfSteps.setBounds(50, 55, 250, 25);
        pnlResultDisplay.add(lblNumberOfSteps);
    }

    private void createSetBisectionPart(){
        lblA = new JLabel ("a");
        lblA.setBounds(10, 50, 50, 25);
        pnlSetBisection.add(lblA);

        lblB = new JLabel ("b");
        lblB.setBounds(10, 80, 50, 25);
        pnlSetBisection.add(lblB);

        lblErr = new JLabel ("error");
        lblErr.setBounds(10, 110, 50, 25);
        pnlSetBisection.add(lblErr);

        tfA = new JTextField ("1");
        tfA.setBounds(60, 50, 120, 25);
        pnlSetBisection.add(tfA);

        tfB = new JTextField ("2");
        tfB.setBounds(60, 80, 120, 25);
        pnlSetBisection.add(tfB);

        tfErr = new JTextField ("0.0001");
        tfErr.setBounds(60, 110, 120, 25);
        pnlSetBisection.add(tfErr);

        JButton bSetBisection = new JButton ("Set values");
        bSetBisection.setBounds(60, 140, 120, 25);
        pnlSetBisection.add(bSetBisection);
        bSetBisection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double a = Double.parseDouble(tfA.getText());
                    double b = Double.parseDouble(tfB.getText());
                    double err = Double.parseDouble(tfErr.getText());

                    if (a >= b){
                        JOptionPane.showMessageDialog(null,"a must be less than b");
                    }
                    else if (err <= 0.0){
                        JOptionPane.showMessageDialog(null,"Error must be positive!");
                    }
                    else{
                        RootObject ro = bisection.findRoot(a, b, err);
                        if (ro == null){
                            lblRootFound.setText("Root not found");
                            lblNumberOfSteps.setText("");
                        } else{
                            lblRootFound.setText("Root is " + ro.getRoot());
                            lblNumberOfSteps.setText("Number of steps was " + ro.getNumberOfSteps());
                        }
                    }
                } catch (java.lang.NumberFormatException | java.lang.NullPointerException ex) {
                    JOptionPane.showMessageDialog(null,"You must enter numbers!");
                }
            }
        });
    }
}
