package package1;

import java.util.ArrayList;
import java.util.List;

public class RunSolver {
    public static void main(String[] args) {
        IModel model = new Model();
        IView graphicView = new GraphicView3(model);
        IView consoleView = new ConsoleView(model);
        List<IView> views = new ArrayList<IView>();
        views.add(graphicView);
        views.add(consoleView);
        IInputView input = (IInputView) graphicView;
        new Controller(model, views, input);
    }
}