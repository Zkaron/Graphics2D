package Testing.Lines;

import Core.MyJFrame;
import Lines.AbstractLine;
import Lines.MidPointLine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Erik on 7/6/2017.
 */
public class MidpointLineTest extends MyJFrame {
    private final String FRAME_TITLE = "Midpoint Line";
    private JPanel panel;
    private BufferedImage buffImage;
    private AbstractLine line;

    public MidpointLineTest() {
        setTitle(FRAME_TITLE);
        panel = new JPanel();
        add(panel);
        this.setVisible(true);  //is set visible again because a new element has been added

        buffImage = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g2 = buffImage.createGraphics();
        g2.setColor(panel.getBackground());
        g2.fillRect(0, 0, panel.getWidth(), panel.getHeight());

        line = new MidPointLine(panel, g2);
    }

    public void drawSomethingCool() {
        line.drawLine(new Point(100, 50), new Point(50, 100));
        line.drawLine(new Point(150, 50), new Point(200, 100));
        line.drawLine(new Point(100, 200),new Point( 50, 200));
        line.drawLine(new Point(150, 200),new Point( 200, 200));
        line.drawLine(new Point(100, 300),new Point( 100, 350));
        line.drawLine(new Point(200, 350),new Point( 200, 300));
        panel.getGraphics().drawImage(buffImage, 0, 0, panel);
}

    public static void main(String[] args) {
        MidpointLineTest test = new MidpointLineTest();
        test.drawSomethingCool();
    }
}
