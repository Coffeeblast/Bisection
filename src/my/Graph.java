package my;

import java.awt.*;

public class Graph extends Plotter {
    private FunctionCalculator fc;

    public Graph(double minX, double maxX, double minY, double maxY, int pixSizeX, int pixSizeY, FunctionCalculator fc){
        super(minX, maxX, minY, maxY, pixSizeX, pixSizeY);
        this.fc = fc;
    }

    public void plotFunction(){
        double x_old = 0.0;
        double y_old = fc.f(0);
        double y;
        double step = rangeX / 100000;
        for (double x = 0; x <= maxX; x += step){
            y = fc.f(x);
            drawLine(x_old, y_old, x, y);
            x_old = x;
            y_old = y;
        }
    }

    public void paintComponent(Graphics g) {
        g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        g2.setColor(Color.white);
        g2.fillRect(0, 0, pixSizeX - 1, pixSizeY - 1);
        plot();
    }

    public void plot(){
        setScale(minX, maxX, minY, maxY);

        setColor(Color.LIGHT_GRAY);
        drawGrid(5, 5);

        setColor(Color.black);
        drawAxis();

        drawText("x", maxX - rangeX/20.0, 0.0 + rangeY/40.0);
        drawText("f(x)", 0.0 + rangeX/40.0, maxY - rangeY/20.0);

        setColor(Color.red);
        plotFunction();

        setColor(Color.black);
    }
}
