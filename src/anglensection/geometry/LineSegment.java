/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anglensection.geometry;

/**
 *
 * @author stepan
 */
public class LineSegment {

    private Point2D p1;
    private Point2D p2;

    public LineSegment(Point2D p1, Point2D p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public LineSegment(double x1, double y1, double x2, double y2) {
        this.p1 = new Point2D(x1, y1);
        this.p2 = new Point2D(x2, y2);
    }


    public double getSlopeAngle() {
        return Math.atan2(p2.getY() - p1.getY(), p2.getX() - p1.getX());
    }

    public Point2D getP1() {
        return p1;
    }

    public Point2D getP2() {
        return p2;
    }

    public double getX1() {
        return p1.getX();
    }

    public double getX2() {
        return p2.getX();
    }

    public double getY1() {
        return p1.getY();

    }

    public double getY2() {
        return p2.getY();

    }

}
