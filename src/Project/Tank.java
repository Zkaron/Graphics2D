package Project;

import Core.MyJFrame;
import Figures.*;
import Figures.Rectangle;
import Filling.FloodFill;
import Lines.AbstractLine;
import Lines.MidPointLine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Erik on 9/21/2017.
 *
 * Necesario agregar correccion en cuandro a relleno de figuras en caso de escalación asi como
 * escalacion de circulos
 */
public class Tank extends MyJFrame {
    private final String FRAME_TITLE = "Midpoint Line";
    private JPanel panel;
    private BufferedImage buffImage;
    private AbstractLine line;
    private AbstractLine rect;
    private AbstractCircle circle;
    private FloodFill fill;

    public Tank() {
        setTitle(FRAME_TITLE);
        panel = new JPanel();
        add(panel);
        this.setVisible(true);  //is set visible again because a new element has been added

        buffImage = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g2 = buffImage.createGraphics();
        g2.setColor(panel.getBackground());
        g2.fillRect(0, 0, panel.getWidth(), panel.getHeight());

        line = new MidPointLine(panel, g2);
        rect = new Rectangle(panel, g2);
        circle = new MidpointEllipse(panel, g2);
        fill = new FloodFill(panel, buffImage, g2);
    }

    public void drawFrontIdleTank(double x, double y) {
        rect.setColor(Color.BLACK);
        rect.setScale(x, y);
        rect.drawRectangle(new Point(150, 80), new Point(300, 150 ));   //upper cabin
        rect.drawRectangle(new Point(100, 150), new Point(350, 250));   //main cabin
        rect.drawRectangle(new Point(100, 250), new Point(150, 300));   //left wheel
        rect.drawRectangle(new Point(300, 250), new Point(350, 300));   //right wheel

        circle.setScale(x, y);
        circle.setColor(Color.BLACK);
        circle.drawCircle(new Point(225, 115), new Point(20 , 20 ));      //outer gun
        circle.drawCircle(new Point(225, 115), new Point(15 , 15 ));      //inner gun

//        fillTank(x, y);
        panel.getGraphics().drawImage(buffImage, 0, 0, panel);
    }

    public void fillTank(double x, double y) {
        fill.getPixel().setPixelColor(new Color(12, 114, 60));
        fill.fill(new Point(155, 81));      //upper cabin
        fill.fill(new Point(101, 151));     //main cabin

        fill.getPixel().setPixelColor(Color.BLACK);
        fill.fill(new Point(101, 251));     //left wheel
        fill.fill(new Point(301, 251));     //right wheel

        fill.fill(new Point(243, 115));     //gun
        panel.getGraphics().drawImage(buffImage, 0, 0, panel);
    }

    public static void main(String[] args) {
        Tank tk = new Tank();
        tk.drawFrontIdleTank(0.5, 0.5);
    }
}
