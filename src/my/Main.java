package my;

public class Main {
    public static void main(String[] args){
        double minX = -1.0;
        double maxX = 9.0;
        double minY = -1.0;
        double maxY = 9.0;

        FunctionCalculator fc = new CustomFunctionCalculator(1.0, 1.0, -5.0);

        new App(minX, maxX, minY, maxY, fc);
    }
}
