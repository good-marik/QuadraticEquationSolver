package package1;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.*;

public class GraphicView3 extends JFrame implements IInputView {
    private final static String FRAMETITLE = "Quadratic Equation Solver, v0.5";
    private NummericPanel nummericPanel;
    private GraphicPanel graphicPanel;
    private IModel model;
    private MenuListener menuListener;
    private ColorScheme colorScheme;

    public GraphicView3(IModel model) {
        super(FRAMETITLE);
        this.model = model;
        colorScheme = new ColorScheme(0);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.orange);
        graphicPanel = new GraphicPanel(this.model, colorScheme);
        nummericPanel = new NummericPanel(this.model, graphicPanel, colorScheme);
        getContentPane().add(nummericPanel, BorderLayout.WEST);
        // VIP: "Default Button" for the whole JFrame!!!
        getRootPane().setDefaultButton(nummericPanel.getControlButton());
        getContentPane().add(graphicPanel, BorderLayout.CENTER);
        setMinimumSize(new Dimension(620, 360)); // (360, 340)
        this.addMenu();
        pack();
        getContentPane().validate();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addMenu() {
        JMenuBar menuLeiste = new JMenuBar();
        JMenu menuMenu = new JMenu("Menu");
        JMenu menuColorScheme = new JMenu("Color Scheme");
        JMenuItem itemDarkScheme = new JMenuItem("Dark Scheme");
        JMenuItem itemGrayScheme = new JMenuItem("Gray Scheme");
        JMenuItem itemLightScheme = new JMenuItem("Light Scheme");
        menuColorScheme.add(itemDarkScheme);
        menuColorScheme.add(itemGrayScheme);
        menuColorScheme.add(itemLightScheme);
        JMenuItem itemAbout = new JMenuItem("About");
        JMenuItem itemExit = new JMenuItem("Close Program");
        menuListener = new MenuListener();
        itemDarkScheme.addActionListener(menuListener);
        itemLightScheme.addActionListener(menuListener);
        itemGrayScheme.addActionListener(menuListener);
        itemAbout.addActionListener(menuListener);
        itemExit.addActionListener(menuListener);
        menuMenu.add(menuColorScheme);
        menuMenu.add(itemAbout);
        menuMenu.add(itemExit);
        menuLeiste.add(menuMenu);
        setJMenuBar(menuLeiste);
    }

    private class MenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            switch (s) {
            case "Dark Scheme":
                colorScheme.setColorScheme(0);
                break;
            case "Light Scheme":
                colorScheme.setColorScheme(1);
                break;
            case "Gray Scheme":
                colorScheme.setColorScheme(2);
                break;
            case "About":
                String aboutProgram = "<html><center>Quadratic Equation Solver, version 0.5"
                        + "<br>by Marat Khusniyarov<br>2023";
                JLabel label = new JLabel(aboutProgram);
                JLabel labelMail = new JLabel("<html><a href=\"\">marat.khusniyarov@gmail.com</a>");
                labelMail.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent me) {
                        try {
                            Desktop.getDesktop().mail(new URI(
                                    "mailto:marat.khusniyarov@gmail.com?subject=Quadratic%20Equation%20Solver"));
                        } catch (URISyntaxException | IOException ex) {
//                            System.out.println("error!");
                        }
                    }
                });
                label.setHorizontalAlignment(SwingConstants.CENTER);
                labelMail.setHorizontalAlignment(SwingConstants.CENTER);
                JLabel[] labels = new JLabel[] { label, labelMail };
                JOptionPane.showMessageDialog(null, labels, "About", JOptionPane.PLAIN_MESSAGE);
                break;
            case "Close Program":
                System.exit(0);
                break;
            }
        }
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
