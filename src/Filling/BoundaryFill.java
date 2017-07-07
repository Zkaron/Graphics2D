package Filling;

import Core.Pixel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * Uses recursion to fill a shape, due to the nature of the java VM (virtual machine)
 * a linkedList (Stack) is used to simulate recursion and avoid VM overflowing
 */
public class BoundaryFill {
    private int clickedBgColor;
    private LinkedList<Point> recursiveStack;
    private BufferedImage buffImage;
    private int containerWidth, containerHeight;

    private Pixel pixel;

    public BoundaryFill(Container context, BufferedImage image, Graphics g2) {
        pixel = new Pixel();
        pixel.setContext(context);
        pixel.setGraphics(g2);
        buffImage = image;
        recursiveStack = new LinkedList<>();
        containerWidth = context.getWidth();
        containerHeight = context.getHeight();
    }

    /**
     * It just gets the color in RGB of the clicked point
     */
    private void initFillVariables(Point clickedPoint) {
        clickedBgColor = buffImage.getRGB((int)clickedPoint.getX(), (int)clickedPoint.getY());
    }

    /**
     * Fills recursively the area,  uses a Stack to save each recursive call
     * and compares the color of each pixel to know where to paint
     */
    public void fill(Point clickedPoint) {
        initFillVariables(clickedPoint);
        if(!(clickedBgColor == pixel.getPixelColor().getRGB())) {  //if is the same color, do nothing
            recursiveStack.push(clickedPoint);

            while (recursiveStack.size() != 0) {
                Point p = recursiveStack.pop();
                if (!(p.getX() >= containerWidth - 1 || p.getY() >= containerHeight - 1  //Check if the coordinate isn't out of bounds
                        || p.getX() <= 0 || p.getY() <= 0)) {
                    if (buffImage.getRGB((int) p.getX(), (int) p.getY() + 1) == clickedBgColor) {
                        recursiveStack.add(new Point((int) p.getX(), (int) p.getY() + 1));
                        pixel.drawPixel((int) p.getX(), (int) p.getY() + 1);
                    }
                    if (buffImage.getRGB((int) p.getX() + 1, (int) p.getY()) == clickedBgColor) {
                        recursiveStack.add(new Point((int) p.getX() + 1, (int) p.getY()));
                        pixel.drawPixel((int) p.getX() + 1, (int) p.getY());
                    }
                    if (buffImage.getRGB((int) p.getX(), (int) p.getY() - 1) == clickedBgColor) {
                        recursiveStack.add(new Point((int) p.getX(), (int) p.getY() - 1));
                        pixel.drawPixel((int) p.getX(), (int) p.getY() - 1);
                    }
                    if (buffImage.getRGB((int) p.getX() - 1, (int) p.getY()) == clickedBgColor) {
                        recursiveStack.add(new Point((int) p.getX() - 1, (int) p.getY()));
                        pixel.drawPixel((int) p.getX() - 1, (int) p.getY());
                    }
                }
            }
        } else {  //print that the same color was selected
            System.out.println("Selected same color, doing nothing");
        }
    }
}
