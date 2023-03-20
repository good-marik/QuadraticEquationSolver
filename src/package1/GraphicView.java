package package1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GraphicView extends JFrame implements IInputView {
    private final static int NUMBEROFCOLUMNS = 13;
    private final static String ERRORLINE = " -- ";
    private IModel model;
    private IButtonListener myButtonListener;
    
    private JTextField fieldA;  
    private JTextField fieldB;
    private JTextField fieldC;
    private JTextField fieldD;
    private JTextField fieldX1;
    private JTextField fieldX2;
    private JLabel statusLabel;

    public GraphicView(IModel model) {
        super("Quadratic Equation Solver, ver. 0.1");
        this.model = model;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
//        getContentPane().setBackground(Color.orange);
//        setUndecorated(true);
//        setOpacity(0.8f);
        
        JLabel labelEq = new JLabel("ax^2 + bx + c = 0");

        JButton buttonSolve = new JButton("solve!");
        buttonSolve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonPressedInit();
            }
        });
        this.getRootPane().setDefaultButton(buttonSolve); // VIP: "Default Button" for the whole JFrame!!!
        
        statusLabel = new JLabel(model.getStatus());
        statusLabel.setForeground(Color.RED);
        
        JPanel panel1 = new JPanel();
//        panel1.setBackground(Color.YELLOW);
        panel1.add(labelEq);
        
        JPanel panel3 = new JPanel();
//        panel3.setBackground(Color.MAGENTA);
        panel3.add(buttonSolve);
        
        JPanel panel5 = new JPanel();
//        panel5.setBackground(Color.CYAN);
        panel5.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel5.add(statusLabel);
                
        JPanel panel2 = getPanel2();
        JPanel panel4 = getPanel4();
        
        Box  box = Box.createVerticalBox();
        box.add(Box.createGlue());
//        box.add(Box.createVerticalStrut(10));
        box.add(panel1);
        box.add(panel2);
        box.add(panel3);
        box.add(panel4);
//        box.add(panel5);
        
        add(box, BorderLayout.CENTER);
        add(panel5, BorderLayout.SOUTH);

        setSize(new Dimension(400, 400));
        setMinimumSize(new Dimension(316, 339));
        pack();
//        System.out.println(this.getSize());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void buttonPressedInit() {
        try {
            double d;
            d = Double.parseDouble(fieldA.getText());
            model.setValue(0, d);
            d = Double.parseDouble(fieldB.getText());
            model.setValue(1, d);
            d = Double.parseDouble(fieldC.getText());
            model.setValue(2, d);
            myButtonListener.activated();               // call the Controller!
        } catch (NumberFormatException e1) {
            model.setQuadratic(false);
            model.setRootsExist(false);
            model.setStatus("Proper numbers, please! Format example: 37.891");
            refresh();
        }
    }
    
    @Override
    public void setButtonListener(IButtonListener myButtonListener) {
        this.myButtonListener = myButtonListener;
    }
    
    @Override
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
    }
    
    private JPanel getPanel4() {
        JLabel labelD = new JLabel("Discriminant = ");
        JLabel labelX1 = new JLabel("x1 = ");
        JLabel labelX2 = new JLabel("x2 = ");

        fieldD = new JTextField(NUMBEROFCOLUMNS);
        fieldD.setEditable(false);
        fieldX1 = new JTextField(NUMBEROFCOLUMNS);
        fieldX1.setEditable(false);
        fieldX2 = new JTextField(NUMBEROFCOLUMNS);
        fieldX2.setEditable(false);
        
        JPanel panel = new JPanel();
//        panel.setBackground(Color.GREEN);
        GroupLayout groupLayout = new GroupLayout(panel);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);
        panel.setLayout(groupLayout);
        
        GroupLayout.SequentialGroup horizontalGroup = groupLayout.createSequentialGroup();
        GroupLayout.ParallelGroup hsub1 = groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING);
        hsub1.addComponent(labelD);
        hsub1.addComponent(labelX1);
        hsub1.addComponent(labelX2);
        GroupLayout.ParallelGroup hsub2 = groupLayout.createParallelGroup();
        hsub2.addComponent(fieldD);
        hsub2.addComponent(fieldX1);
        hsub2.addComponent(fieldX2);
        horizontalGroup.addGroup(hsub1);
        horizontalGroup.addGroup(hsub2);
        GroupLayout.SequentialGroup verticalGroup = groupLayout.createSequentialGroup();
        GroupLayout.ParallelGroup vsub1 = groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER);
        vsub1.addComponent(labelD);
        vsub1.addComponent(fieldD);
        GroupLayout.ParallelGroup vsub2 = groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER);
        vsub2.addComponent(labelX1);
        vsub2.addComponent(fieldX1);
        GroupLayout.ParallelGroup vsub3 = groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER);
        vsub3.addComponent(labelX2);
        vsub3.addComponent(fieldX2);
        verticalGroup.addGroup(vsub1);
        verticalGroup.addGroup(vsub2);
        verticalGroup.addGroup(vsub3);
        groupLayout.setHorizontalGroup(horizontalGroup);
        groupLayout.setVerticalGroup(verticalGroup);
        
        JPanel coveringPanel = new JPanel();
        coveringPanel.add(panel);
        return coveringPanel;
    }

    private JPanel getPanel2() {
        JLabel labelA = new JLabel("a =");
        JLabel labelB = new JLabel("b =");
        JLabel labelC = new JLabel("c =");
        
        fieldA = new JTextField(NUMBEROFCOLUMNS);
        fieldB = new JTextField(NUMBEROFCOLUMNS);
        fieldC = new JTextField(NUMBEROFCOLUMNS);
        
        JPanel panel = new JPanel();
//        panel.setBackground(Color.CYAN);
        GroupLayout groupLayout = new GroupLayout(panel);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);
        panel.setLayout(groupLayout);
        
        GroupLayout.SequentialGroup horizontalGroup = groupLayout.createSequentialGroup();
        GroupLayout.ParallelGroup hsub1 = groupLayout.createParallelGroup();
        hsub1.addComponent(labelA);
        hsub1.addComponent(labelB);
        hsub1.addComponent(labelC);
        GroupLayout.ParallelGroup hsub2 = groupLayout.createParallelGroup();
        hsub2.addComponent(fieldA);
        hsub2.addComponent(fieldB);
        hsub2.addComponent(fieldC);
        horizontalGroup.addGroup(hsub1);
        horizontalGroup.addGroup(hsub2);
        GroupLayout.SequentialGroup verticalGroup = groupLayout.createSequentialGroup();
        GroupLayout.ParallelGroup vsub1 = groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER);
        vsub1.addComponent(labelA);
        vsub1.addComponent(fieldA);
        GroupLayout.ParallelGroup vsub2 = groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER);
        vsub2.addComponent(labelB);
        vsub2.addComponent(fieldB);
        GroupLayout.ParallelGroup vsub3 = groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER);
        vsub3.addComponent(labelC);
        vsub3.addComponent(fieldC);
        verticalGroup.addGroup(vsub1);
        verticalGroup.addGroup(vsub2);
        verticalGroup.addGroup(vsub3);
        groupLayout.setHorizontalGroup(horizontalGroup);
        groupLayout.setVerticalGroup(verticalGroup);
        
        JPanel coveringPanel = new JPanel();
        coveringPanel.add(panel);
        return coveringPanel;
    }

}
