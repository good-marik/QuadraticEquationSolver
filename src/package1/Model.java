package package1;

public class Model implements IModel {
    private final static int MAXNUMBEROFPARAMETERS = 6; // can be adjusted, when necessary!
    private final static String EMPTYLINE = " ";
    private double[] coefficient = new double[MAXNUMBEROFPARAMETERS];
    private String status;
    private boolean rootsExist;
    private boolean quadratic;

    public Model() {
        setRootsExist(false);
        setQuadratic(false);
        setStatus(EMPTYLINE);
    }

    @Override
    public void setValue(int i, double x) {
        coefficient[i] = x;
    }

    @Override
    public double getValue(int i) {
        return coefficient[i];
    }

    @Override
    public String modelToString() {
        String outputString = "";
        for (int i = 0; i < coefficient.length; i++) {
            outputString = outputString  + coefficient[i] + "  ";
        }
        return outputString;
    }

    @Override
    public String fieldToString(int i) {
        return String.valueOf(coefficient[i]);
    }

    @Override
    public void setStatus(String s) {
        status = s;
    }
    
    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setQuadratic(boolean b) {
        quadratic = b;
    }

    @Override
    public void setRootsExist(boolean b) {
        rootsExist = b;
    }

    @Override
    public boolean getQuadratic() {
        return quadratic;
    }

    @Override
    public boolean rootsExist() {
        return rootsExist;
    }
}