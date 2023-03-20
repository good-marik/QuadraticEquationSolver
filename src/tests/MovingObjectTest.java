package tests;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MovingObjectTest extends JPanel {
    private static final int SIZE = 70;
    static Point center = new Point(0, 0);
    boolean mouseIn;
    Point lastMousePosition;

    public MovingObjectTest() {
        super();
        setBackground(Color.YELLOW);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastMousePosition = getMousePosition();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouseIn = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mouseIn = true;
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (mouseIn) {
                    Point currentMousePosition = getMousePosition();
                    center.x += currentMousePosition.x - lastMousePosition.x;
                    center.y += currentMousePosition.y - lastMousePosition.y;
                    lastMousePosition = currentMousePosition;
                    repaint();
                }
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.BLUE);
        g2.fillOval(center.x - SIZE / 2, center.y - SIZE / 2, SIZE, SIZE);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 400));
        frame.setBackground(Color.RED);
        JPanel panel = new MovingObjectTest();
        frame.add(panel);
        frame.pack();
        center.setLocation(panel.getWidth() / 2, panel.getHeight() / 2);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
