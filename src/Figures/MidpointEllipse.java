package Figures;

import Core.MyJFrame;
import Core.Pixel;

import java.awt.*;

/**
 * Created by erik on 5/1/17.
 */
public class MidpointEllipse extends AbstractCircle {
    public MidpointEllipse(Container context, Graphics g2) {
        pixel.setContext(context);
        pixel.setGraphics(g2);
    }

    @Override
    public void drawingMethod(Point pc, Point radius) {
        int i = 0;
        int r1sq = (int) radius.getX() * (int) radius.getX();
        int r2sq = (int) radius.getY() * (int) radius.getY();

        int x = 0, y = (int) radius.getY();
        double px = 0, py = 2 * r1sq * y;

        //pixel.drawPixel(xc + x,yc + y);
        drawWithWidth((int) pc.getX() + x, (int) pc.getY() + y);
        drawWithWidth((int) pc.getX(), (int) pc.getY() - y);
        createMaskByType((int) radius.getX() > (int) radius.getY() ? (int) radius.getX() / 3 : (int) radius.getY() / 3);
        //region 1
        double p = r2sq - (r1sq * radius.getY()) + (0.25 * r1sq);
        while (px < py) {
            x++;
            px += 2 * r2sq;

            if (p < 0) {
                p += r2sq + px;
            } else {
                y--;
                py -= 2 * r1sq;
                p += r2sq + px - py;
            }
            if (i % mask.length == 0) {
                i = 0;
            }
            if (mask[i]) {
                drawWithWidth((int) pc.getX() + x, (int) pc.getY() + y);
                drawWithWidth((int) pc.getX() + x, (int) pc.getY() - y);
                drawWithWidth((int) pc.getX() - x, (int) pc.getY() + y);
                drawWithWidth((int) pc.getX() - x, (int) pc.getY() - y);
            }
            i = 0;
            //region 2
            p = r2sq * Math.pow((x + 0.5), 2) + r1sq * Math.pow((y - 1), 2) - r1sq * r2sq;
            while (y > 0) {
                y--;
                py -= 2 * r1sq;
                if (p > 0) {
                    p += r1sq - py;
                } else {
                    x++;
                    px += 2 * r2sq;
                    p += r1sq - py + px;
                }
                if (i % mask.length == 0) {
                    i = 0;
                }
                if (mask[i]) {
                    drawWithWidth((int) pc.getX() + x, (int) pc.getY() + y);
                    drawWithWidth((int) pc.getX() + x, (int) pc.getY() - y);
                    drawWithWidth((int) pc.getX() - x, (int) pc.getY() + y);
                    drawWithWidth((int) pc.getX() - x, (int) pc.getY() - y);
                }
                i++;
            }
        }
    }
}

