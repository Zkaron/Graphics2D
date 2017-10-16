package Project;

import Core.MyJFrame;
import Figures.MidpointEllipse;
import Figures.Rectangle;
import Filling.FloodFill;
import Lines.AbstractLine;
import Lines.MidPointLine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main extends MyJFrame {

    private final String FRAME_TITLE = "Mountain";
    private JPanel panel;
    private BufferedImage offScreen;
    private int imageWidth;
    private int imageHeight;

    private Tree tree;
    private Mountain mountain;


    public Main() {
        setTitle(FRAME_TITLE);
        panel = new JPanel();
        add(panel);
        this.setVisible(true);  //is set visible again because a new element has been added
    }

    public void paint() {
        Dimension d = panel.getSize();
        if((offScreen == null) || (d.width != imageWidth)
                ||(d.height != imageHeight)) {
            if(d.width < 1 || d.height < 1) return;

            offScreen = new BufferedImage(panel.getWidth(),
                    panel.getHeight(), BufferedImage.TYPE_INT_RGB);
            imageWidth = d.width;
            imageHeight = d.height;

            tree = new Tree(panel, offScreen);
            mountain = new Mountain(panel, offScreen);
        }   //check if the screen hasn't been drawn or the window was resided

        Graphics offGraphics = offScreen.createGraphics();
        clear(offGraphics);
        tree.setGraphics(offGraphics);
        mountain.setGraphics(offGraphics);
        tree.drawTree(0, 0, 0.5, 0.5);
//        tree.drawTree(300, 10, 0.4, 0.4);
//        tree.drawTree(600, 10, 0.4, 0.4);
//        tree.drawTreeTop(300, 500, 0.5, 1);

        mountain.drawMountain(200, 0, 1, 1);
        Graphics panelGraphics = panel.getGraphics();
        panelGraphics.drawImage(offScreen, 0, 0, panel);
    }

    public void paint(Graphics g) {
        paint();
    }

    private void clear(Graphics g) {
        g.setColor(panel.getBackground());
        g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
    }

    public static void main(String[] args) {
        new Main();
    }
}
