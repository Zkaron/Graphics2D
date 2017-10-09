package Figures;

import Core.Pixel;
import Lines.AbstractLine;
import Transformations.Scale;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by erik on 4/25/17.
 */
public abstract class AbstractCircle extends AbstractLine {
    protected Pixel pixel;
    protected Point pc;
    protected Point radius;

    public AbstractCircle() {
        pixel = new Pixel();
    }

    public void drawCircle(Point pc, Point radius) {
        this.pc = new Point(pc);
        this.radius = new Point(radius);
        scale();
        drawingMethod();
    }

    public void setColor(Color color) {
        pixel.setPixelColor(color);
    }

    protected void drawWithWidth(int xc, int yc) {
        for(int i = 0; i < lineWidth; i++) {
            xc = i % 2 == 0 ? xc + i : xc - i;
            yc = i % 2 == 0 ? yc + i : yc - i;
            pixel.drawPixel(xc, yc);
        }
    }

    @Override
    protected void scale() {
        LinkedList<Point> scaledPoints;
        Scale scale = new Scale();
        scaledPoints = scale.scale(pc, radius, scaleWidth, scaleHeight);
        pc = scaledPoints.getFirst();
        radius = scaledPoints.getLast();
    }
}
