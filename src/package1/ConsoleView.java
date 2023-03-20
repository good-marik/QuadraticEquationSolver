package package1;

public class ConsoleView implements IView {
    private IModel model;

    public ConsoleView(IModel model) {
        this.model = model;
    }

    @Override
    public void refresh() {
        double a = model.getValue(0);
        double b = model.getValue(1);
        double c = model.getValue(2);
        System.out.println(a + "x^2 + " + b + "x + " + c + " = 0");
        
        if (!model.getQuadratic()) {
            System.out.println(model.getStatus());
        } else {
            System.out.println("Discriminant D = " + model.getValue(3));
            if (!model.rootsExist()) {
                System.out.println(model.getStatus());
            } else {
                System.out.println("x1 = " + model.getValue(4));
                System.out.println("x2 = " + model.getValue(5));
            }
        }
        System.out.println();
    }
}
