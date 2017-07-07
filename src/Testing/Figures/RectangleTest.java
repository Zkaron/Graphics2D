package Testing.Figures;

import Core.MyJFrame;
import Figures.Rectangle;
import Lines.AbstractLine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Erik on 7/6/2017.
 */
public class RectangleTest extends MyJFrame {
    private final String FRAME_TITLE = "Rectangle";
    private JPanel panel;
    private BufferedImage buffImage;
    private Rectangle rect;

    public RectangleTest() {
        setTitle(FRAME_TITLE);
        panel = new JPanel();
        add(panel);
        this.setVisible(true);  //is set visible again because a new element has been added

        buffImage = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g2 = buffImage.createGraphics();
        g2.setColor(panel.getBackground());
        g2.fillRect(0, 0, panel.getWidth(), panel.getHeight());

        rect = new Rectangle(panel, g2);
    }

    public void drawSomethingCool() {
        rect.drawRectangle(new Point(150, 50), new Point(200, 100));
        rect.drawRectangle(new Point(30, 50), new Point(150, 400));
        rect.drawRectangle(new Point(80, 60), new Point(60, 240));
        panel.getGraphics().drawImage(buffImage, 0, 0, panel);
    }

    public static void main(String[] args) {
        RectangleTest test = new RectangleTest();
        test.drawSomethingCool();
    }
}
