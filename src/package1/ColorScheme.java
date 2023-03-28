package package1;

import java.awt.Color;

public class ColorScheme {
    private int colorScheme;
    private Color background;
    private Color coordinateSystem;
    private Color graphic;
    private Color rootCycle;
    private Color border;

    public ColorScheme(int i) {
        colorScheme = i;
        setColorScheme(colorScheme);
    }

    public ColorScheme() {
        colorScheme = 0; // default
        setColorScheme(colorScheme);
    }

    public void setColorScheme(int colorScheme) {
        this.colorScheme = colorScheme;
        switch (this.colorScheme) {
        case 0:
            background = Color.BLACK;
            coordinateSystem = Color.WHITE;
            graphic = Color.CYAN;
            rootCycle = new Color(255, 255, 0, 200); // yellow, partially transparent
            border = Color.WHITE;
            break;
        case 1:
            background = Color.WHITE;
            coordinateSystem = Color.BLACK;
            graphic = Color.BLUE;
            rootCycle = new Color(255, 0, 0, 200); // red, partially transparent
            border = Color.BLACK;
            break;
        case 2:
//            background = new Color(238, 238, 238);
            background = new Color(218, 218, 218);
            coordinateSystem = Color.BLACK;
            graphic = Color.YELLOW;
            rootCycle = new Color(0, 0, 255, 200); // cyan, partially transparent
            border = Color.BLACK;
            break;
        default:
            System.out.println("error");
        }
    }

    public Color getBackgroundColor() {
        return background;
    }

    public Color getCoordinateSystemColor() {
        return coordinateSystem;
    }

    public Color getGraphicColor() {
        return graphic;
    }

    public Color getRootCycleColor() {
        return rootCycle;
    }

    public Color getBorderColor() {
        return border;
    }
//    public static void main(String[] a) {
//        ColorScheme myColorScheme = new ColorScheme(1);
//        myColorScheme.setColorScheme(0);
//        System.out.println(myColorScheme.getGraphicColor());
//        myColorScheme.setColorScheme(1);
//        System.out.println(myColorScheme.getGraphicColor());
//        
//        myColorScheme.setColorScheme(0);
//        System.out.println(myColorScheme.getGraphicColor());
//    }
}
