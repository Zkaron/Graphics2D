package Testing.Figures;

import Core.MyJFrame;
import Figures.AbstractCircle;
import Figures.BasicCircle;
import Lines.AbstractLine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Erik on 7/6/2017.
 */
public class BasicCircleTest extends MyJFrame {
    private final String FRAME_TITLE = "Basic Circle";
    private JPanel panel;
    private BufferedImage buffImage;
    private AbstractCircle circle;

    public BasicCircleTest() {
        setTitle(FRAME_TITLE);
        panel = new JPanel();
        add(panel);
        this.setVisible(true);  //is set visible again because a new element has been added

        buffImage = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g2 = buffImage.createGraphics();
        g2.setColor(panel.getBackground());
        g2.fillRect(0, 0, panel.getWidth(), panel.getHeight());

        circle = new BasicCircle(panel, g2);
    }

    public void drawSomethingCool() {
        circle.drawCircle(new Point(200, 200), new Point(100, 100));
        panel.getGraphics().drawImage(buffImage, 0, 0, panel);
    }

    public static void main(String[] args) {
        BasicCircleTest test = new BasicCircleTest();
        test.drawSomethingCool();
    }
}
