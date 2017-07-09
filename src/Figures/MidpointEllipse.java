package Figures;

import Core.MyJFrame;
import Core.Pixel;

import java.awt.*;

/**
 * Created by erik on 5/1/17.
 *
 * Algorithm used is in the following text:
 * Univ. Michoacana de San Nicolas de Hgo.
 * Facultad de Ingenieria Electrica
 * Notas de Graflcacion
 * Jise Antonio Camarena Ibarrola
 * Marzo de 2010
 */
public class MidpointEllipse extends AbstractCircle {
    public MidpointEllipse(Container context, Graphics g2) {
        pixel.setContext(context);
        pixel.setGraphics(g2);
    }

    @Override
    public void drawingMethod(Point pc, Point radius) {
        int x = 0;
        int y = (int) radius.getY();

        int r1sq = (int)radius.getX() * (int)radius.getX();
        int r2sq = (int)radius.getY() * (int)radius.getY();

        double p = r2sq - (radius.getY() * r1sq) +(0.25 * r1sq);

        while((2 * r2sq * x) <= (2 * r1sq * y)) {
            drawWithWidth((int)pc.getX() + x, (int)pc.getY() + y);
            drawWithWidth((int)pc.getX() + x, (int)pc.getY() - y);
            drawWithWidth((int)pc.getX() - x, (int)pc.getY() + y);
            drawWithWidth((int)pc.getX() - x, (int)pc.getY() - y);
            x++;
            if(p <= 0) {
                p +=(2 * x * r2sq) + r2sq;
            } else {
                y--;
                p +=(2 * x * r2sq) + r2sq - (2 * r1sq * y);
            }
        }
        p = r2sq * Math.pow(x + 0.5, 2)
                + r1sq * Math.pow(y - 1, 2) - r1sq * r2sq;
        while (y > 0) {
            y--;
            if(p <= 0) {
                x++;
                p += (2 * x * r2sq) - (2 * r1sq * y) + r1sq;
            } else {
                p -= (2 * y * r1sq) + r1sq;
            }
            drawWithWidth((int)pc.getX() + x, (int)pc.getY() + y);
            drawWithWidth((int)pc.getX() + x, (int)pc.getY() - y);
            drawWithWidth((int)pc.getX() - x, (int)pc.getY() + y);
            drawWithWidth((int)pc.getX() - x, (int)pc.getY() - y);
        }
    }
}

