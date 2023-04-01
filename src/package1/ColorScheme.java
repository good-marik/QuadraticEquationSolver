package package1;

import java.awt.Color;

public class ColorScheme {
    private ColorSchemes colorScheme;
    private Color background;
    private Color coordinateSystem;
    private Color graphic;
    private Color rootCycle;
    private Color border;

    public ColorScheme(ColorSchemes csc) {
        colorScheme = csc;
        set(colorScheme);
    }

    public ColorScheme() {
        colorScheme = ColorSchemes.DARK; // default
        set(colorScheme);
    }

    public void set(ColorSchemes colorScheme) {
        this.colorScheme = colorScheme;
        switch (this.colorScheme) {
        case DARK:
            background = Color.BLACK;
            coordinateSystem = Color.WHITE;
            graphic = Color.CYAN;
            rootCycle = new Color(255, 255, 0, 200); // yellow, partially transparent
            border = Color.WHITE;
            break;
        case LIGHT:
            background = Color.WHITE;
            coordinateSystem = Color.BLACK;
            graphic = new Color(0,162,0);
            rootCycle = new Color(255, 0, 0, 200); // red, partially transparent
            border = Color.BLACK;
            break;
        case GRAY:
//            background = new Color(238, 238, 238);
            background = new Color(218, 218, 218);
            coordinateSystem = Color.BLACK;
            graphic = new Color(51, 187, 255);
            rootCycle = Color.RED; // cyan, partially transparent
            border = Color.BLACK;
            break;
        default:
            System.out.println("error: unknown color!");
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
