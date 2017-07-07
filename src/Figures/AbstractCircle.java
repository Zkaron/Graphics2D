package Figures;

import Core.Pixel;
import Lines.AbstractLine;

import java.awt.*;

/**
 * Created by erik on 4/25/17.
 */
public abstract class AbstractCircle extends AbstractLine {
    protected Pixel pixel;

    public AbstractCircle() {
        pixel = new Pixel();
    }

    public void drawCircle(Point pc, Point radius) {
        drawingMethod(pc, radius);
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
}
