package package1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class NummericPanel extends JPanel {
    private final static int NUMBEROFCOLUMNS = 13;
    private final static String ERRORLINE = " -- ";
    private final static Color FIELDCOLOR = new Color(240, 240, 240);
    private JButton buttonSolve;
    private IModel model;
    private IButtonListener myButtonListener;
    private JTextField fieldA;
    private JTextField fieldB;
    private JTextField fieldC;
    private JTextField fieldD;
    private JTextField fieldX1;
    private JTextField fieldX2;
    private JLabel statusLabel;
    private GraphicPanel graphicPanel;

    public NummericPanel(IModel model, GraphicPanel graphicPanel) {
        this.model = model;
        this.graphicPanel = graphicPanel;
        this.setLayout(new BorderLayout());
//        this.setBackground(Color.RED);
//        this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        JLabel labelEq = new JLabel("ax^2 + bx + c = 0");
        buttonSolve = new JButton("solve!");
        buttonSolve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonPressedInit();
            }
        });
//    JFrame fr = (JFrame) SwingUtilities.getWindowAncestor(this);
        JPanel panel1 = new JPanel();
//        panel1.setBackground(Color.GREEN);
        panel1.setMaximumSize(new Dimension(190, 35));
        panel1.setPreferredSize(new Dimension(190, 35));
        panel1.add(labelEq);
        JPanel panel2 = getPanel2();
        JPanel panel3 = new JPanel();
//        panel3.setBackground(Color.GREEN);
        panel3.setMaximumSize(new Dimension(190, 50));
        panel3.setPreferredSize(new Dimension(190, 50));
        panel3.add(buttonSolve);
        JPanel panel4 = getPanel4();
        JPanel panelUp = new JPanel();
        panelUp.setLayout(new BoxLayout(panelUp, BoxLayout.Y_AXIS));
//        panelUp.setBackground(Color.YELLOW);
        panelUp.add(panel1);
        panelUp.add(panel2);
        panelUp.add(panel3);
        panelUp.add(panel4);
        JPanel panelDown = new JPanel();
//        panelDown.setBackground(Color.BLACK);
        panelDown.setLayout(new FlowLayout(FlowLayout.LEFT));
        statusLabel = new JLabel(model.getStatus());
        statusLabel.setForeground(Color.RED);
        panelDown.add(statusLabel);
        this.add(panelUp, BorderLayout.CENTER);
        this.add(panelDown, BorderLayout.SOUTH);
        this.setMaximumSize(new Dimension(300, 320));
        this.setPreferredSize(new Dimension(300, 320));
    }

    public void setButtonListener(IButtonListener myButtonListener) {
        this.myButtonListener = myButtonListener;
    }

    public JButton getControlButton() {
        return buttonSolve;
    }

    private void buttonPressedInit() {
//      System.out.println(this.getSize());
        try {
            double d;
            d = Double.parseDouble(fieldA.getText());
            model.setValue(0, d);
            d = Double.parseDouble(fieldB.getText());
            model.setValue(1, d);
            d = Double.parseDouble(fieldC.getText());
            model.setValue(2, d);
            myButtonListener.activated(); // call the Controller!
        } catch (NumberFormatException e1) {
            model.setQuadratic(false);
            model.setRootsExist(false);
            model.setStatus("Proper numbers, please! Format example: 37.891");
            refresh();
        }
    }

    public void refresh() {
        String s = (model.getQuadratic()) ? model.fieldToString(3) : ERRORLINE;
        fieldD.setText(s);
        if (model.rootsExist()) {
            fieldX1.setText(model.fieldToString(4));
            fieldX2.setText(model.fieldToString(5));
        } else {
            fieldX1.setText(ERRORLINE);
            fieldX2.setText(ERRORLINE);
        }
        statusLabel.setText(model.getStatus());
        graphicPanel.resetZoom();
        graphicPanel.refresh();
    }

    private JPanel getPanel4() {
        JLabel labelD = new JLabel("Discriminant = ");
        JLabel labelX1 = new JLabel("             x1 = ");
        JLabel labelX2 = new JLabel("             x2 = ");
        fieldD = new JTextField(NUMBEROFCOLUMNS);
        fieldD.setEditable(false);
        fieldD.setBackground(FIELDCOLOR);
        fieldX1 = new JTextField(NUMBEROFCOLUMNS);
        fieldX1.setEditable(false);
        fieldX1.setBackground(FIELDCOLOR);
        fieldX2 = new JTextField(NUMBEROFCOLUMNS);
        fieldX2.setEditable(false);
        fieldX2.setBackground(FIELDCOLOR);
        JPanel panel = new JPanel();
//        panel.setBackground(Color.CYAN);
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.setPreferredSize(new Dimension(250, 90)); // for Y
        panel.setMaximumSize(new Dimension(250, 90)); // for X
        panel.add(labelD);
        panel.add(fieldD);
        panel.add(labelX1);
        panel.add(fieldX1);
        panel.add(labelX2);
        panel.add(fieldX2);
        return panel;
    }

    private JPanel getPanel2() {
        JLabel labelA = new JLabel("a =");
        JLabel labelB = new JLabel("b =");
        JLabel labelC = new JLabel("c =");
        
//        fieldA = new JTextField("", NUMBEROFCOLUMNS);
//        fieldB = new JTextField("", NUMBEROFCOLUMNS);
//        fieldC = new JTextField("", NUMBEROFCOLUMNS);
        
        fieldA = new JTextField("2", NUMBEROFCOLUMNS);
        fieldB = new JTextField("3", NUMBEROFCOLUMNS);
        fieldC = new JTextField("-2", NUMBEROFCOLUMNS);
        
//        fieldA = new JTextField("1", NUMBEROFCOLUMNS);
//        fieldB = new JTextField("5", NUMBEROFCOLUMNS);
//        fieldC = new JTextField("6", NUMBEROFCOLUMNS);
        
        JPanel panel = new JPanel();
//        panel.setBackground(Color.CYAN);
        panel.setLayout(new FlowLayout());
        panel.setPreferredSize(new Dimension(190, 90)); // for Y
        panel.setMaximumSize(new Dimension(190, 90)); // for X
        panel.add(labelA);
        panel.add(fieldA);
        panel.add(labelB);
        panel.add(fieldB);
        panel.add(labelC);
        panel.add(fieldC);
        return panel;
    }
}