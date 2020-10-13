package my;

public class Bisection {
    private FunctionCalculator fc;

    public Bisection(FunctionCalculator fc){
        this.fc = fc;
    }

    public RootObject findRoot(double a, double b, double err){
        if (fc.f(a) * fc.f(b) > 0.0) return null;

        double root = (a + b) / 2.0;
        int numberOfSteps = 0;

        while (b - a > err){
                root = (a + b) / 2.0;
                numberOfSteps++;
                if(fc.f(a) * fc.f(root) > 0.0){
                    a = root;}
                else{
                    b = root;}
        }

        RootObject result = new RootObject();
        result.setRoot(root);
        result.setNumberOfSteps(numberOfSteps);
        return result;
    }
}
