package Figures;

import Lines.BresenhamLine;
import javax.swing.*;
import java.awt.*;

/**
 * Created by erik on 4/19/17.
 */
public class Rectangle {
    BresenhamLine line;

    public Rectangle(JPanel context) {
        line = new BresenhamLine(context);
    }

    public Rectangle(JPanel context, Graphics g2) {
        line = new BresenhamLine(context, g2);
    }

    public void drawRectangle(Point p0, Point p1) {
        line.drawLine(new Point((int)p0.getX(), (int)p0.getY()),
                new Point((int)p1.getX(), (int)p0.getY()));
        line.drawLine(new Point((int)p0.getX(), (int)p0.getY()), new Point((int)p0.getX(), (int)p1.getY()));
        line.drawLine(new Point((int)p1.getX(), (int)p0.getY()), new Point((int)p1.getX(), (int)p1.getY()));
        line.drawLine(new Point((int)p0.getX(), (int)p1.getY()), new Point((int)p1.getX(), (int)p1.getY()));
    }

}
