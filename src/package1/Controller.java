package package1;

import java.util.List;

public class Controller implements IButtonListener{
    private static final String EMPTYLINE = " ";
    private IModel model;
    private List<IView> views;
//    private IInputView input;

    public Controller(IModel model, List<IView> views, IInputView input) {
        this.model = model;
        this.views = views;
//        this.input = input;
        input.setButtonListener(this);
    }

    private void solve() {
        double a = model.getValue(0);
        double b = model.getValue(1);
        double c = model.getValue(2);
        double discriminant = b * b - 4 * a * c;
        model.setValue(3, discriminant);
        
        if (a == 0) {
            model.setStatus("a = 0  =>  It's not a quadratic equation!  :)"); 
            model.setQuadratic(false);
            model.setRootsExist(false);
        }
         else if (discriminant < 0) {
             model.setStatus("Discriminant is negative! No solution in R space.");
             model.setQuadratic(true);
             model.setRootsExist(false);
        
        } else {
            model.setStatus(EMPTYLINE);
            model.setQuadratic(true);
            model.setRootsExist(true);
            double x1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            model.setValue(4, x1);
            double x2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            model.setValue(5, x2);
        }
        for (IView view : views) {
            view.refresh();
        }
    }

    @Override
    public void activated() {
        solve();
    }
}