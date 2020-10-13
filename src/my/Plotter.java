package my;

import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JComponent;

public abstract class Plotter extends JComponent {

    protected double minX;
    protected double maxX;
    protected double minY;
    protected double maxY;

    protected double rangeX;
    protected double rangeY;

    protected int pixSizeX;
    protected int pixSizeY;

    Graphics2D g2;

    public Plotter(double minX, double maxX, double minY, double maxY, int pixSizeX, int pixSizeY){
        setScale(minX, maxX, minY, maxY);
        this.pixSizeX = pixSizeX;
        this.pixSizeY = pixSizeY;
    }

    public int getPixSizeX(){
        return pixSizeX;
    }

    public int getPixSizeY(){
        return pixSizeY;
    }

    public void setScale(double minX, double maxX, double minY, double maxY){
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;

        rangeX = maxX - minX;
        rangeY = maxY - minY;
    }

    private int getPixX(double x){
        return (int)((x - minX) * ((double)(pixSizeX - 1)) / rangeX);
    }

    private int getPixY(double y){
        return pixSizeY - 1 - (int)((y - minY) * ((double)(pixSizeY - 1)) / rangeY);
    }

    public void drawLine(double x1, double y1, double x2, double y2){
        g2.drawLine(getPixX(x1), getPixY(y1), getPixX(x2), getPixY(y2));
    }

    public void drawAxis(){
        drawLine(minX, 0.0, maxX, 0.0);
        drawLine(0.0, minY, 0.0, maxY);
    }

    public double optimalGridStep(double range, int numberOfLines){
        double n = java.lang.Math.ceil(java.lang.Math.log10(range)) - 2.0;
        double suggestedStep = java.lang.Math.pow(10.0, n);
        return suggestedStep * java.lang.Math.floor(range / (((double)numberOfLines) * suggestedStep));
    }

    public double optimalStart(double step, double min){
        return java.lang.Math.ceil(min / step);
    }

    public void drawGrid(int xGridLines, int yGridLines){
        double xStep = optimalGridStep(rangeX, xGridLines);
        double yStep = optimalGridStep(rangeY, yGridLines);

        double xStart = optimalStart(xStep, minX);
        double yStart = optimalStart(yStep, minY);

        for(double x = xStart; x <= maxX; x += xStep){
            drawText(Double.toString(x), x, minY);
            drawLine(x, minY, x, maxY);
        }

        for(double y = yStart; y <= maxY; y += yStep){
            drawText(Double.toString(y), minX, y);
            drawLine(minX, y, maxX, y);
        }
    }

    public void setColor(Color color){
        g2.setColor(color);
    }

    public void drawText(String text, double x, double y){
        g2.drawString(text, getPixX(x), getPixY(y));
    }
}