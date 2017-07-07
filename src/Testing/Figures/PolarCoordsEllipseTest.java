package Testing.Figures;

import Core.MyJFrame;
import Figures.AbstractCircle;
import Figures.PolarCoordsEllipse;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Erik on 7/6/2017.
 */
public class PolarCoordsEllipseTest extends MyJFrame {
    private final String FRAME_TITLE = "Midpoint Circle";
    private JPanel panel;
    private BufferedImage buffImage;
    private AbstractCircle circle;

    public PolarCoordsEllipseTest() {
        setTitle(FRAME_TITLE);
        setSize(700, 700);
        panel = new JPanel();
        add(panel);
        this.setVisible(true);  //is set visible again because a new element has been added

        buffImage = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g2 = buffImage.createGraphics();
        g2.setColor(panel.getBackground());
        g2.fillRect(0, 0, panel.getWidth(), panel.getHeight());

        circle = new PolarCoordsEllipse(panel, g2);
    }

    public void drawSomethingCool() {
        circle.drawCircle(new Point(200, 300), new Point(100, 200));
        circle.drawCircle(new Point(200, 300), new Point(50, 200));
        circle.drawCircle(new Point(200, 300), new Point(200, 200));
        circle.drawCircle(new Point(200, 300), new Point(200, 100));
        circle.drawCircle(new Point(200, 300), new Point(150, 200));
        panel.getGraphics().drawImage(buffImage, 0, 0, panel);
    }

    public static void main(String[] args) {
        PolarCoordsEllipseTest test = new PolarCoordsEllipseTest();
        test.drawSomethingCool();
    }
}
