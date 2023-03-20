package package1;

public interface IModel {
    
    void setValue(int i, double x);

    double getValue(int i);

    void setStatus(String s);
    
    String getStatus();

    void setQuadratic(boolean b);
    
    boolean getQuadratic();

    void setRootsExist(boolean b);
    
    boolean rootsExist();

    String modelToString();
    
    String fieldToString(int i);
}
