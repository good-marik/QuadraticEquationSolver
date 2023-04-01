package tests;

import package1.ColorSchemes;

public class TestingEnum {
    public static void main(String[] args) {
//        String s;
        ColorSchemes csc;
//        s = "Dark Scheme";
        csc = ColorSchemes.valueOfLabel(ColorSchemes.DARK.getName());
        
//        csc = ColorSchemeConstants.DARK;
        switch (csc) {
        case DARK:
            System.out.println(csc.getName());
            break;
        default:
            System.out.println("something wrong");
            break;
        }
    }
}
