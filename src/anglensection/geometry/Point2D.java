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
public class Point2D {
    private double x;
    private double y;
    
    public Point2D(double x, double y){
        this.x = x;
        this.y = y;        
    }
    
    
    public double getSlopeAngle(Point2D p) {
        return Math.atan2(p.getY() - getY(), p.getX() - getX());
    }
    
    public void setPoint2D(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public void movePoint(double x, double y){
        this.x+=x;
        this.y+=y;
    }
    
    public boolean isSamePoint(Point2D p){
        if (x==p.getX() && y==p.getY()){
            return true;
        }
        return false;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    double getDistanceToOrigin() {
        return Math.pow((x*x+y*y), 0.5);
    }
    
    
    public String toString(){
        return "x: "+x+" y "+y;
    }
    
}
