package tests;

import package1.ColorSchemesImplemented;

public class TestingEnum {
    public static void main(String[] args) {
//        String s;
        ColorSchemesImplemented csc;
//        s = "Dark Scheme";
        csc = ColorSchemesImplemented.valueOfLabel(ColorSchemesImplemented.DARK.getName());
        
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
