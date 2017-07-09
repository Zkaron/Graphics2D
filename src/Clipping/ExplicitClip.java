package Clipping;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * Created by Erik on 7/7/2017.
 */
public class ExplicitClip {
    private final static byte TOTALLY_VISIBLE = 1;
    private final static byte TOTALLY_INVISIBLE = 2;
    private final static byte CLIPPED_LEFT = 3;
    private final static byte CLIPPED_RIGHT = 4;
    private final static byte CLIPPED_TOP = 5;
    private final static byte CLIPPED_BOTTOM = 6;

    private int left;
    private int right;
    private int top;
    private int bottom;

    private LinkedList<Point> points;

    public ExplicitClip(LinkedList<Point> pointVector) {
        points = pointVector;
    }

    public void setClipArea(Point p0, Point p1) {
        left = p0.getX() < p1.getX() ? (int)p0.getX() : (int)p1.getX();
        right = p0.getX() > p1.getX() ? (int)p0.getX() : (int)p1.getX();
        top = p0.getY() < p1.getY() ? (int)p0.getY() : (int)p1.getY();
        bottom = p0.getY() > p1.getY() ? (int)p0.getY() : (int)p1.getY();
    }

    public void clip() {
        for(int i = 0, j = 1; j < points.size(); i += 2, j += 2) {
            String[] regionCodes = new String[2];
            regionCodes[0] = getRegionCode(points.get(i));
            regionCodes[1] = getRegionCode(points.get(j));

            if(regionCodes[0].equals("0000") && regionCodes[1].equals("0000")) {
                System.out.println("Totally Visible");
                continue;
            }
                if(!getANDCodes(regionCodes[0], regionCodes[1]).equals("0000")) {
                System.out.println("Totally Invisible");
                points.get(i).setLocation(new Point(-1, -1));
                points.get(j).setLocation(new Point(-1, -1));
                continue;
            }

            double deltaX = points.get(j).getX() - points.get(i).getX();
            double deltaY = points.get(j).getY() - points.get(i).getY();
            double slope = 0;
            try {
                slope = deltaX != 0 ? deltaY / deltaX : 0;
            } catch (ArithmeticException e) {
                e.printStackTrace();
            }
            int x_i = -1, y_i = -1;

            // For the first Point
            if (regionCodes[0].charAt(3) == '1') { //left
                x_i = left;
                y_i = (int)points.get(i).getY() + (int)Math.round(slope * (left - points.get(i).getX()));
            }
            else if(regionCodes[0].charAt(2) == '1') {  //right
                x_i = right;
                y_i = (int)points.get(i).getY() + (int)Math.round(slope * (right - points.get(i).getX()));
            }
            else if(regionCodes[0].charAt(1) == '1') {  //top
                double xtmp = points.get(i).getX();
                double ytmo = points.get(i).getY();
                x_i = (int)points.get(i).getX() + (int)Math.round((top - points.get(i).getY()) / slope);
                y_i = top;
            }
            else if(regionCodes[0].charAt(0) == '1') {  //bottom
                x_i = (int)points.get(i).getX() + (int)Math.round((bottom - points.get(i).getY()) / slope);
                y_i = bottom;
            }

            int x_j = -1, y_j = -1;
            // For the second Point
            if (regionCodes[1].charAt(3) == '1') { //left
                x_j = left;
                y_j = (int)points.get(j).getY() + (int)Math.round(slope * (left - points.get(j).getX()));
            }
            else if(regionCodes[1].charAt(2) == '1') {  //right
                x_j = right;
                y_j = (int)points.get(j).getY() + (int)Math.round(slope * (right - points.get(j).getX()));
            }
            else if(regionCodes[1].charAt(1) == '1') {  //top
                x_j = (int)points.get(j).getX() + (int)Math.round((top - points.get(j).getY()) / slope);
                y_j = top;
            }
            else if(regionCodes[1].charAt(0) == '1') {  //bottom
                x_j = (int)points.get(j).getX() + (int)Math.round((bottom - points.get(j).getY()) / slope);
                y_j = bottom;
            }

            if(x_i != -1 || y_i != -1) {
                /*if((x_i > right || x_i < left) || (y_i < top || y_i > bottom)) {
                    //System.out.println("Totally Invisible");
                    points.get(i).setLocation(new Point(-1, -1));
                } else*/
                points.get(i).setLocation(new Point(x_i, y_i));
            }
            if(x_j != -1 || y_j != -1) {
                /*if((x_j > right || x_j < left) || (y_j < top || y_j > bottom)) {
                    points.get(j).setLocation(new Point(-1, -1));
                } else*/
                points.get(j).setLocation(new Point(x_j, y_j));
            }




        }
        /*double deltaX = (points.get(1).getX() - points.get(0).getX());
        double deltaY = points.get(1).getY() - points.get(0).getY();
        double slope = deltaY / deltaX;
        Point pointToClip = points.get(1);
        int x_i = 0, y_i = 0;
        //first left
        x_i = left;
        y_i = (int)pointToClip.getY() + (int)Math.round(slope * (left - pointToClip.getX()));

        //reassign points
        Point clippedPoint = new Point(x_i, y_i);
        points.get(1).setLocation(clippedPoint);
        //then top
        pointToClip = points.get(0);
        x_i = (int)pointToClip.getX() + (int)Math.round((top - pointToClip.getY()) / slope);
        y_i = top;

        clippedPoint = new Point(x_i, y_i);
        points.get(0).setLocation(clippedPoint);*/
    }

    private String getRegionCode(Point point) {
        StringBuilder regionCode = new StringBuilder("0000");
        if(point.getX() < left) {
            regionCode.setCharAt(3, '1');
        } else if(point.getX() > right){
            regionCode.setCharAt(2, '1');
        }
        if(point.getY() < top) {
            regionCode.setCharAt(1, '1');
        } else if(point.getY() > bottom){
            regionCode.setCharAt(0, '1');
        }
        return regionCode.toString();
    }

    private String getANDCodes(String code1, String code2) {
        StringBuilder regionCode = new StringBuilder("0000");
        for(int i = 0; i < 4; i++) {
            if(code1.charAt(i) == '1' && code2.charAt(i) == '1') {
                regionCode.setCharAt(i, '1');
            }
        }
        return regionCode.toString();
    }

}
