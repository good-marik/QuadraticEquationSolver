package tests;

import java.awt.*;

import javax.swing.*;

public class TestingResize extends JPanel {
    public TestingResize() {
        this.setLayout(null);
        JButton button = new JButton("press me");
        this.add(button);
        button.setLocation(0, 30);
        button.setSize(140, 40);
        System.out.println(button.getLocation().toString());
        Font font = new Font(null, Font.PLAIN, 14);
        FontMetrics metrics = getFontMetrics(font);
        System.out.println(metrics.stringWidth(" "));
        System.out.println(metrics.stringWidth("-"));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setMinimumSize(new Dimension(200, 200));
        frame.setLocationRelativeTo(null);
        JPanel panel = new TestingResize();
        frame.add(panel);
        frame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.drawLine(0, 0, getWidth(), getHeight());
    }
}
