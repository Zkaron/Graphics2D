package Testing.Figures;

import Core.MyJFrame;
import Figures.AbstractCircle;
import Figures.PolarCoordsCircle;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Erik on 7/6/2017.
 */
public class PolarCoordsCircleTest extends MyJFrame {
    private final String FRAME_TITLE = "Polar Coords Circle";
    private JPanel panel;
    private BufferedImage buffImage;
    private AbstractCircle circle;

    public PolarCoordsCircleTest() {
        setTitle(FRAME_TITLE);
        setSize(700, 700);
        panel = new JPanel();
        add(panel);
        this.setVisible(true);  //is set visible again because a new element has been added

        buffImage = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g2 = buffImage.createGraphics();
        g2.setColor(panel.getBackground());
        g2.fillRect(0, 0, panel.getWidth(), panel.getHeight());

        circle = new PolarCoordsCircle(panel, g2);
    }

    public void drawSomethingCool() {
        circle.drawCircle(new Point(300, 300), new Point(100, 100));
        circle.drawCircle(new Point(300, 300), new Point(50, 50));
        circle.drawCircle(new Point(300, 300), new Point(200, 200));
        circle.drawCircle(new Point(300, 300), new Point(250, 250));
        panel.getGraphics().drawImage(buffImage, 0, 0, panel);
    }

    public static void main(String[] args) {
        PolarCoordsCircleTest test = new PolarCoordsCircleTest();
        test.drawSomethingCool();
    }
}
