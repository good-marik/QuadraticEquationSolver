package package1;

public enum ColorSchemes {
    DARK("Dark Scheme"),
    GRAY("Gray Scheme"),
    LIGHT("Light Scheme"),
    GIRLS("Girl's Scheme");

    private final String schemeName;
    
    private ColorSchemes(String schemeName) {
        this.schemeName = schemeName;
    }

    public static ColorSchemes valueOfLabel(String s) {
        for (ColorSchemes cs : values()) {
            if (cs.schemeName.equals(s)) {
                return cs;
            }
        }
        return null;
    }
    
    public String getName() {
        return schemeName;
    }
}
