package package1;

public enum ColorSchemesImplemented {
    DARK("Dark Scheme"),
    GRAY("Gray Scheme"),
    LIGHT("Light Scheme"),
    GIRLS("Girl's Scheme");

    private final String schemeName;
    
    private ColorSchemesImplemented(String schemeName) {
        this.schemeName = schemeName;
    }

    public static ColorSchemesImplemented valueOfLabel(String s) {
        for (ColorSchemesImplemented cs : values()) {
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
