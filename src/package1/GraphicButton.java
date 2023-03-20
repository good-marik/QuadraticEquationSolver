package package1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.*;

import javax.swing.JPanel;

public class GraphicButton extends MouseAdapter {
    private final String plus = "+";
    private final Color BUTTONCOLOR = Color.LIGHT_GRAY;
    private final Color SIGNCOLOR = Color.BLACK;
    private final int SIGNSIZE = 8;
    private final int BUTTONWIDTH = 30;
    private final int BUTTONHEIGHT = 40;
    private final int ROUNDINGDIAMETER = 5;
    private int xOffset;
    private int yOffset;
    private String label;
    private JPanel parentPanel;

    public GraphicButton(JPanel parentPanel, int xOffset, int yOffset, String label) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.label = label;
        this.parentPanel = parentPanel;
    }

    public void draw(Graphics2D g2) {
        int panelWidth = parentPanel.getWidth();
//        int panelHeight = parentPanel.getHeight();
//        System.out.println(parentPanel.toString());
        int x = panelWidth - (xOffset + BUTTONWIDTH);
        int y = yOffset;
        g2.setColor(BUTTONCOLOR);
        g2.fillRoundRect(x, y, BUTTONWIDTH, BUTTONHEIGHT, ROUNDINGDIAMETER, ROUNDINGDIAMETER);
        g2.setColor(SIGNCOLOR);
        g2.drawLine(x + (BUTTONWIDTH - SIGNSIZE) / 2, y + BUTTONHEIGHT / 2, x + (BUTTONWIDTH + SIGNSIZE) / 2,
                y + BUTTONHEIGHT / 2);
        if (label.equals(plus)) {
            g2.drawLine(x + BUTTONWIDTH / 2, y + (BUTTONHEIGHT - SIGNSIZE) / 2, x + BUTTONWIDTH / 2,
                    y + (BUTTONHEIGHT + SIGNSIZE) / 2);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("the button is clicked!");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("the button is clicked!");
    }
}
