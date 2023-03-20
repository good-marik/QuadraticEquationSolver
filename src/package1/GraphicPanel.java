package package1;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class GraphicPanel extends JPanel {
    private static final int OFFSET = 5;
    private static final int TICKSIZE = 10;
    private static final int FONTSIZE = 14;
    private static final int DISTANCEBETWEENTICKS = 60;
    private static final int ROOTCYCLEDIAMETER = 8;
    private static final int XLABELOFFSET = 12;
    private static final int BUTTONWIDTH = 30;
    private static final int BUTTONHEIGHT = 30;
    private static final int BUTTONOFFSETX = 10;
    private static final int BUTTONOFFSETY = 10;
    private static final int BUTTONOFFSETY2 = BUTTONOFFSETY + 35;
    private static final int BUTTONOFFSETY3 = BUTTONOFFSETY2 + 35;
    private static final double ZOOMFACTORMULTIPLIER = 1.5;
    private int yLabelOffset = 12;
    private Color backgroundColor = Color.BLACK;
    private Color coordinateSystemColor = Color.WHITE;
    private Color graphicColor = Color.CYAN;
    private Color rootCycleColor = new Color(255, 255, 0, 200); // yellow, partially transparent
    private int graphicLineWidth = 2;
    private IModel model;
    private double xCenter;
    private double yCenter;
    private double xStart;
    private double yStart;
    private double scalingFactor;
    private double step;
    private int positionOfYAxis;
    private int positionOfXAxis;
    private int textOffset;
    private Font rotatedFont;
    private Font font;
    private FontMetrics metrics;
    private JButton zoomPlus;
    private JButton zoomMinus;
    private JButton zoomReset;
    private boolean mouseIn;
    private Point lastMousePosition;

    public GraphicPanel(IModel model) {
        this.model = model;
        setBackground(backgroundColor);
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        this.setLayout(null);
        font = new Font(null, Font.PLAIN, FONTSIZE);
        metrics = getFontMetrics(font);
        textOffset = metrics.getAscent() / 4; // instead of "/2" - strange, but working better
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(Math.toRadians(90), 0, 0);
        rotatedFont = font.deriveFont(affineTransform);
        zoomPlus = new JButton("+");
        zoomPlus.setMargin(new Insets(0, 0, 0, 0));
        zoomPlus.setSize(BUTTONWIDTH, BUTTONHEIGHT);
        zoomPlus.setLocation(BUTTONOFFSETX, BUTTONOFFSETY);
        zoomPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setZoomFactor(ZOOMFACTORMULTIPLIER);
            }
        });
        this.add(zoomPlus);
        zoomMinus = new JButton("–");
        zoomMinus.setMargin(new Insets(0, 0, 0, 0));
        zoomMinus.setSize(BUTTONWIDTH, BUTTONHEIGHT);
        zoomMinus.setLocation(BUTTONOFFSETX, BUTTONOFFSETY3);
        zoomMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setZoomFactor(1 / ZOOMFACTORMULTIPLIER);
            }
        });
        this.add(zoomMinus);
        zoomReset = new JButton("O");
        zoomReset.setMargin(new Insets(0, 0, 0, 0));
        zoomReset.setSize(BUTTONWIDTH, BUTTONHEIGHT);
        zoomReset.setLocation(BUTTONOFFSETX, BUTTONOFFSETY2);
        zoomReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetZoom();
                refresh();
            }
        });
        this.add(zoomReset);
        this.addMouseListener(new MouseAdapter() {
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
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (mouseIn) {
//                    System.out.print(scalingFactor + ": " + xCenter + " ");
                    Point currentMousePosition = getMousePosition();
                    if (currentMousePosition != null) {
                        xCenter += (lastMousePosition.x - currentMousePosition.x) / scalingFactor;
                        // System.out.println(currentMousePosition.x + " " + xCenter);
                        yCenter += (currentMousePosition.y - lastMousePosition.y) / scalingFactor;
                        lastMousePosition = currentMousePosition;
                        refresh();
                    } else {
                        System.out.println("getMousePosition() returns null!");
                    }
                }
            }
        });
    }

    public void setZoomFactor(double multiplier) {
        scalingFactor = scalingFactor * multiplier;
        refresh();
    }

    public void refresh() {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (model.rootsExist()) {
            g2.setColor(coordinateSystemColor);
            initializeParametersForTransformations();
            drawXAxis(g2);
            drawYAxis(g2);
            drawTicks(g2, "x");
            drawTicks(g2, "y");
            drawParabola(g2);
            drawRoots(g2);
        }
    }

    private void drawRoots(Graphics2D g2) {
        g2.setColor(rootCycleColor);
        g2.setStroke(new BasicStroke(graphicLineWidth));
        int x = getPanelXCoordinate(model.getValue(4)) - ROOTCYCLEDIAMETER / 2;
        int y = getPanelYCoordinate(0.0) - ROOTCYCLEDIAMETER / 2;
        int border = OFFSET + ROOTCYCLEDIAMETER;
        if ((x > OFFSET) && (x < getWidth() - border) && (y > OFFSET) && (y < getHeight() - border)) {
            g2.draw(new Ellipse2D.Double(x, y, ROOTCYCLEDIAMETER, ROOTCYCLEDIAMETER));
        }
        x = getPanelXCoordinate(model.getValue(5)) - ROOTCYCLEDIAMETER / 2;
        if ((x > OFFSET) && (x < getWidth() - border) && (y > OFFSET) && (y < getHeight() - border)) {
        g2.draw(new Ellipse2D.Double(x, y, ROOTCYCLEDIAMETER, ROOTCYCLEDIAMETER));
        }
    }

    private BigDecimal getRoundedBigDecimal(double x) {
        double multiplicationFactor = (x > 0) ? 1 : -1;
        double positiveNumber = Math.abs(x);
        int scale = 0;
        while (positiveNumber >= 10) {
            positiveNumber = positiveNumber / 10;
            multiplicationFactor = multiplicationFactor * 10;
        }
        while (positiveNumber < 1) {
            positiveNumber = positiveNumber * 10;
            multiplicationFactor = multiplicationFactor / 10;
            scale++;
        }
        BigDecimal multiplicationFactorBD = BigDecimal.valueOf(multiplicationFactor);
        BigDecimal numberBD = BigDecimal.valueOf(positiveNumber);
        int numberRoundedInt = numberBD.setScale(0, RoundingMode.HALF_UP).intValueExact();
        switch (numberRoundedInt) {
        case 3:
            numberRoundedInt = 2;
            break;
        case 4:
            numberRoundedInt = 5;
            break;
        case 6:
            numberRoundedInt = 5;
            break;
        case 7:
            numberRoundedInt = 5;
            break;
        case 8:
            numberRoundedInt = 10; // no break on purpose!
        case 9:
            numberRoundedInt = 10; // no break on purpose!
        case 10:
            scale--; // adjusting scaling of the number!
        }
        BigDecimal numberRoundedBD = BigDecimal.valueOf(numberRoundedInt);
        BigDecimal resultBD = numberRoundedBD.multiply(multiplicationFactorBD);
        return resultBD.setScale(scale, RoundingMode.HALF_UP);
    }

    private boolean isInteger(BigDecimal bd) {
        return (bd.stripTrailingZeros().scale() <= 0);
    }

    private void drawTicks(Graphics2D g2, String axis) {
        int axisLength;
        double startPosition;
        if (axis.equals("x")) {
            axisLength = getWidth();
            startPosition = xStart;
        } else {
            axisLength = getHeight();
            startPosition = yStart;
        }
        double stepDouble = DISTANCEBETWEENTICKS / scalingFactor;
        BigDecimal roundedStepBD = getRoundedBigDecimal(stepDouble);
        int scale = roundedStepBD.scale();
        double numberOfTicksDouble = axisLength / (roundedStepBD.doubleValue() * scalingFactor) + 1;
        int numberOfTicks = (int) Math.round(numberOfTicksDouble);
        long multiplicityFactor = Math.round(roundedStepBD.doubleValue() * Math.pow(10, scale));
        // multiplicityFactor = 1, 2, 5 or x10^n
        long startRoundedUnscaled = multiplicityFactor
                * Math.round(startPosition * Math.pow(10, scale) / multiplicityFactor);
        BigDecimal startRoundedBD = BigDecimal.valueOf(startRoundedUnscaled, scale);
        for (int i = 0; i <= numberOfTicks; i++) {
            BigDecimal iBD = BigDecimal.valueOf(i);
            double position = ((roundedStepBD.multiply(iBD)).add(startRoundedBD)).doubleValue();
            if (axis.equals("x")) {
                drawXTick(g2, position, isInteger(roundedStepBD) && isInteger(startRoundedBD));
            } else {
                drawYTick(g2, position, isInteger(roundedStepBD) && isInteger(startRoundedBD));
            }
        }
    }

    private void drawXTick(Graphics2D g2, double d, boolean withoutDecimals) {
        String s = (withoutDecimals) ? String.format("%.0f", d) : String.valueOf(d);
        int panelX = getPanelXCoordinate(d);
        int border = OFFSET + textOffset;
        g2.setFont(rotatedFont);
        if ((panelX >= border) && (panelX <= (getWidth() - border)) && (d != 0)) {
            g2.drawLine(panelX, positionOfXAxis - TICKSIZE / 2, panelX, positionOfXAxis + TICKSIZE / 2);
            String sModified = (s.charAt(0) == '-') ? s : ("-" + s);
            int sLength = metrics.stringWidth(s);
            int sModifiedLength = metrics.stringWidth(sModified);
            int position;
            if ((positionOfXAxis + XLABELOFFSET + sModifiedLength) > (getHeight() - OFFSET)) {
                position = positionOfXAxis - XLABELOFFSET - sLength;
            } else {
                position = positionOfXAxis + XLABELOFFSET;
            }
            g2.drawString(s, panelX - textOffset, position);
        }
    }

    private void drawYTick(Graphics2D g2, double d, boolean withoutDecimals) {
        String s = (withoutDecimals) ? String.format("%.0f", d) : String.valueOf(d);
        int panelY = getPanelYCoordinate(d);
        int border = OFFSET + textOffset;
        g2.setFont(font);
        if ((panelY >= border) && (panelY <= (getHeight() - border)) && (d != 0)) {
            g2.drawLine(positionOfYAxis - TICKSIZE / 2, panelY, positionOfYAxis + TICKSIZE / 2, panelY);
            String sModified = (s.charAt(0) == '-') ? s : ("-" + s);
            int sLength = metrics.stringWidth(s);
            int sModifiedLength = metrics.stringWidth(sModified);
            int position;
            if ((positionOfYAxis + yLabelOffset + sModifiedLength) > (getWidth() - OFFSET)) {
                position = positionOfYAxis - yLabelOffset - sLength;
            } else {
                position = positionOfYAxis + yLabelOffset;
            }
            g2.drawString(s, position, panelY + textOffset);
        }
    }

    private void drawParabola(Graphics2D g2) {
        List<Point> parabola = new ArrayList<Point>();
        boolean drawingStarted = false;
        int previousX = 0;
        int previousY = 0;
        parabola.clear();
        for (int i = 0; i <= getWidth(); i++) {
            double x = xStart + step * i;
            double y = model.getValue(0) * x * x + model.getValue(1) * x + model.getValue(2);
            parabola.add(new Point(getPanelXCoordinate(x), getPanelYCoordinate(y)));
        }
        g2.setColor(graphicColor);
        g2.setStroke(new BasicStroke(graphicLineWidth));
        for (Point point : parabola) {
            if (point.x < OFFSET || point.x > (getWidth() - OFFSET) || point.y < OFFSET
                    || point.y > (getHeight() - OFFSET)) {
                drawingStarted = false;
            } else {
                if (drawingStarted) {
                    g2.drawLine(previousX, previousY, point.x, point.y);
                } else {
                    drawingStarted = true;
                }
                previousX = point.x;
                previousY = point.y;
            }
        }
    }

    private int getPanelXCoordinate(double x) {
        return (int) Math.round(scalingFactor * (x - xStart));
    }

    private int getPanelYCoordinate(double y) {
        return (int) Math.round(scalingFactor * (-y + yStart) + getHeight());
    }

    private void drawYAxis(Graphics2D g2) {
        int position = getPanelXCoordinate(0.0);
        int border = OFFSET + TICKSIZE / 2 + 1;
        if (position < border) {
            position = border;
        } else if (position > (getWidth() - border)) {
            position = getWidth() - border;
        }
        positionOfYAxis = position;
        g2.drawLine(positionOfYAxis, OFFSET, positionOfYAxis, getHeight() - OFFSET);
    }

    private void drawXAxis(Graphics2D g2) {
        int position = getPanelYCoordinate(0.0);
        int border = OFFSET + TICKSIZE / 2 + 1;
        if (position < border) {
            position = border;
        } else if (position > (getHeight() - border)) {
            position = getHeight() - border;
        }
        positionOfXAxis = position;
        g2.drawLine(OFFSET, positionOfXAxis, getWidth() - OFFSET, positionOfXAxis);
    }

    private void initializeParametersForTransformations() {
        double xEnd;
        xStart = xCenter - getWidth() / 2 / scalingFactor;
        xEnd = xCenter + getWidth() / 2 / scalingFactor;
        yStart = yCenter - getHeight() / 2 / scalingFactor;
        step = (xEnd - xStart) / getWidth();
    }

    public void resetZoom() {
        double rootSmall;
        double rootLarge;
        double xLength;
        rootSmall = Math.min(model.getValue(4), model.getValue(5));
        rootLarge = Math.max(model.getValue(4), model.getValue(5));
        xCenter = (rootSmall + rootLarge) / 2;
        xLength = (rootSmall == rootLarge) ? 11 : (rootLarge - rootSmall) * 3; // 11 and 3 can be adjusted
        scalingFactor = getWidth() / xLength;
        yCenter = 0.0;
    }
}
