package package1;

import java.awt.*;

import javax.swing.JFrame;

public class GraphicView3 extends JFrame implements IInputView {
    private final static String FRAMETITLE = "Quadratic Equation Solver, v0.4";
    private NummericPanel nummericPanel;
    private GraphicPanel graphicPanel;
    private IModel model;

    public GraphicView3(IModel model) {
        super(FRAMETITLE);
        this.model = model;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.orange);
        graphicPanel = new GraphicPanel(this.model);
        nummericPanel = new NummericPanel(this.model, graphicPanel);
        getContentPane().add(nummericPanel, BorderLayout.WEST);
        // VIP: "Default Button" for the whole JFrame!!!
        getRootPane().setDefaultButton(nummericPanel.getControlButton());
        getContentPane().add(graphicPanel, BorderLayout.CENTER);
        setMinimumSize(new Dimension(620, 340)); // (360, 340)
        pack();
        getContentPane().validate();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void refresh() {
//        System.out.println("Frame: " + getSize());
//        System.out.println("Graphic Window: " + graphicPanel.getSize());
        nummericPanel.refresh();
//        graphicPanel.refresh();
    }

    @Override
    public void setButtonListener(IButtonListener ibl) {
        nummericPanel.setButtonListener(ibl);
    }
}
