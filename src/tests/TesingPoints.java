package tests;

import java.awt.Point;

public class TesingPoints {
    public static void main(String arg[]) {
        Point p = new Point(2, 3);
        System.out.println(p);
        p.x = 1;
        System.out.println(p);
    }
    
}
