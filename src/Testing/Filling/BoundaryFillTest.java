package Testing.Filling;

import Core.MyJFrame;
import Filling.BoundaryFill;
import Lines.AbstractLine;
import Lines.BresenhamLine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * Created by Erik on 7/6/2017.
 */
public class BoundaryFillTest extends MyJFrame {
    private JPanel panel;
    private BufferedImage buffImage;
    private Point clickedPoint;
    private BoundaryFill boundaryFill;

    private Figures.Rectangle rect;
    private AbstractLine line;

    /**
     * Creates the Frame, panel and initializes variables
     */
    public BoundaryFillTest() {
        panel = new JPanel();
        add(panel);
        this.setVisible(true);  //is set visible again because a new element is added

        buffImage = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g2 = buffImage.createGraphics();
        g2.setColor(panel.getBackground());
        g2.fillRect(0, 0, panel.getWidth(), panel.getHeight());

        boundaryFill = new BoundaryFill(panel, buffImage, g2);

        rect = new Figures.Rectangle(panel, g2);
        line = new BresenhamLine(panel, g2);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                clickedPoint = new Point(e.getX(), e.getY());
                boundaryFill.fill(clickedPoint);
                panel.getGraphics().drawImage(buffImage, 0, 0, panel);
            }
        });
    }

    /**
     * Draws shapes (a triangle and two rectangles) to test the filling algorithm
     */
    public void drawTestFigures() {
        rect.drawRectangle(new Point(50, 50), new Point(100, 100));
        line.drawLine(new Point(100, 100), new Point(80, 140));
        line.drawLine(new Point(80, 140), new Point(120, 100));
        line.drawLine(new Point(100, 100), new Point(120, 100));
        rect.drawRectangle(new Point(150, 150), new Point(400, 400));
        rect.drawRectangle(new Point(250, 150), new Point(300, 300));
        panel.getGraphics().drawImage(buffImage, 0, 0, panel);
    }

    public static void main(String[] args) {
        BoundaryFillTest test = new BoundaryFillTest();
        test.drawTestFigures();
    }
}
