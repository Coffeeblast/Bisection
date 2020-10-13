package my;

public class CustomFunctionCalculator implements FunctionCalculator{
    double c, k, q;

    public CustomFunctionCalculator(double c, double k, double q){
        this.c = c;
        this.k = k;
        this.q = q;
    }

    @Override
    public double f(double x) {
        return c * x * Math.exp(k * x) + q;
    }
}
