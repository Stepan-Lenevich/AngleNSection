/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anglensection;

import java.awt.Shape;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import anglensection.geometry.Line;
import anglensection.geometry.Point2D;

/**
 *
 * @author stepan
 */
public class GeometryEngine {

    private double fraction = 0.25;
    private double[] xArray = {10., Java2D.JFRAME_DIMENSIONS - 10., Java2D.JFRAME_DIMENSIONS / 2.};
    private double[] yArray = {10., 10., Java2D.JFRAME_DIMENSIONS - 40};

    List<Shape> triangle;
    List<Shape> lines;
    List<Shape> innerTriangle;
    List<Point2D> innerPoints;

    public GeometryEngine(double fraction, int x, int y) {
        this.fraction = fraction;
        xArray[2] = x;
        yArray[2] = y;
        triangle = new ArrayList<>();
        lines = new ArrayList<>();
        init();

    }

    private void polulateSides() {
        for (int i = 0; i < xArray.length; i += 1) {
            int j = (i + 1) % 3;
            triangle.add(new Line2D.Float((float) xArray[i], (float) yArray[i], (float) xArray[j], (float) yArray[j]));
        }
    }

    private void polulateAllQuad() {

        for (int i = 0; i < 3; i += 1) {
            int j = (i + 1) % 3;
            int k = (i + 2) % 3;

            populateNsects(i, j, k);
        }
    }

    private void populateNsects(int i, int j, int k) {

        double angleKJI1 = Math.atan2((yArray[j] - yArray[i]), (xArray[j] - xArray[i]));
        double angleKJI2 = Math.atan2((yArray[k] - yArray[i]), (xArray[k] - xArray[i]));

        Angle a = new Angle(angleKJI1, angleKJI2);
        double nsectrAngle1 = a.getNsectrissa(fraction);
        double nsectrAngle2 = a.getNsectrissa(1 - fraction);

        Line nsectLine1 = new Line(xArray[i], yArray[i], xArray[i] + 100 * Math.cos(nsectrAngle1), yArray[i] + 100 * Math.sin(nsectrAngle1));
        Line nsectLine2 = new Line(xArray[i], yArray[i], xArray[i] + 100 * Math.cos(nsectrAngle2), yArray[i] + 100 * Math.sin(nsectrAngle2));
        Line oppositeSide = new Line(xArray[j], yArray[j], xArray[k], yArray[k]);

        Point2D intersection1 = oppositeSide.getIntersection(nsectLine1);
        Point2D intersection2 = oppositeSide.getIntersection(nsectLine2);

        lines.add(new Line2D.Float((float) xArray[i], (float) yArray[i], (float) intersection1.getX(), (float) intersection1.getY()));
        lines.add(new Line2D.Float((float) xArray[i], (float) yArray[i], (float) intersection2.getX(), (float) intersection2.getY()));
    }

    private void populateInnerPoints() {

        innerPoints = new ArrayList<>();

        for (int i = 0; i < 3; i++) {

            Line l1 = new Line((Line2D) lines.get(i));
            Line l2 = new Line((Line2D) lines.get(i + 3));
            innerPoints.add(l1.getIntersection(l2));
        }

    }

    List<Shape> getNsectrissi() {
        return lines;
    }

    List<Shape> getOuterTriangle() {
        return triangle;
    }

    List<Shape> getInnerTriangle() {
        return innerTriangle;
    }

    private void populateInnerTriangle() {
        innerTriangle = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            for (int j = i + 1; j < 3; j++) {
                Shape l = new Line2D.Float((float) innerPoints.get(i).getX(), (float) innerPoints.get(i).getY(), (float) innerPoints.get(j).getX(), (float) innerPoints.get(j).getY());
                innerTriangle.add(l);
            }
        }
    }

    private void init() {
        polulateSides();
        polulateAllQuad();
        populateInnerPoints();
        populateInnerTriangle();
        System.out.println();
    }

}
