/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anglensection.geometry;

import java.awt.geom.Line2D;

/**
 *
 * @author stepan
 */
public class Line {

    private double distance;
    private double angle;

    public Line(Point2D p1, Point2D p2) {

        try {
            if (p1.equals(p2)) {
                throw new IllegalArgumentException("Two lints are identical:\n " + p1.getX() + " " + p1.getY());
            } else {
                
                LineSegment line1 =  new LineSegment(p1, p2);

                if (isGoingThroughOrigin(line1)) {
                    defineLineOnOrigin(p1);
                } else {
                    defineLineOffOrigin(line1);
                }
            }

        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
        }

    }

    public Line(double x1, double y1, double x2, double y2) {
        this(new Point2D(x1, y1),new Point2D(x2,y2));
    }
    
    public Line(Line2D l){        
        this(new Point2D(l.getX1(),l.getY1()),new Point2D(l.getX2(),l.getY2()));
    }
    
    public Point2D getIntersection(Line l){
        
        
        Double a = Math.cos(getAngle());
        Double b = Math.sin(getAngle());

        Double c = getDistance();
        
        Double d = Math.cos(l.getAngle());       
        Double e = Math.sin(l.getAngle());

        Double f = l.getDistance();
        
        double x =(c*e-f*b)/(a*e-d*b);
        double y =(c*d-a*f)/(b*d-a*e); 
            
      return new Point2D(x, y);
    }
    
    
    
    

    public Line(double x1, double y1, double alpha) {
        this(new Point2D(x1, y1), new Point2D(x1+ Math.cos(alpha), y1+Math.sin(alpha)));
    }

    private boolean isGoingThroughOrigin(LineSegment l) {
        return 0==l.getX1()*l.getY2()-l.getX2()*l.getY1();
    }

    private void defineLineOnOrigin(Point2D p) {
        distance =0;
        if(p.getX()==0){
            angle =0;
        }else{
            angle = Math.atan(p.getY()/p.getX())+Math.PI/2;
        }
    }

    private void defineLineOffOrigin(LineSegment ls1) {
        double deltaX=ls1.getX2()-ls1.getX1();
        double deltaY=ls1.getY2()-ls1.getY1();  
        
        double lineSlope = Math.atan2(deltaY, deltaX )+Math.PI/2; 
        //System.out.println("Slope is " + lineSlope*180/Math.PI);
        
        LineSegment ls2 = new LineSegment(0, 0, Math.cos(lineSlope), Math.sin(lineSlope));
        Point2D intersection = calculateLineIntersection(ls1, ls2);
        

        
        angle = Math.atan2(intersection.getY(), intersection.getX());
        
        
        distance = intersection.getDistanceToOrigin();
        
        
        
    }

    private Point2D calculateLineIntersection(LineSegment ls1, LineSegment ls2) { 
        double x = 0;
        double y = 0;
        double angle1 =  ls1.getSlopeAngle();
        double angle2 =  ls2.getSlopeAngle();
        
        
        
        if (checkIfVertical(angle1)){
            x=(ls1.getX1()+ls1.getX2())/2;
            y = ls2.getY2() + Math.tan(angle2)*(ls1.getX1()-ls2.getX2());
            return new Point2D(x, y);
        }
            
        if (checkIfVertical(angle2)){
            x=ls2.getX2();
            y = ls1.getY1() + Math.tan(angle1)*(ls2.getX2()-ls1.getX1());
            return new Point2D(x, y);
        }
        double k1 = Math.tan(angle1);
        double k2 = Math.tan(angle2);
        
        x = (ls2.getY2()-ls1.getY1()+k1*ls1.getX1()-k2*ls2.getX2())/(k1-k2);
        
        y = ls1.getY1()+(x-ls1.getX1())*k1;
        
        return new Point2D(x, y);
        
    }
    public boolean checkIfVertical(double v){
        if(Math.abs(v-Math.PI/2)<0.001) return true;
        if(Math.abs(v-3*Math.PI/2)<0.001) return true;
        if(Math.abs(v+3*Math.PI/2)<0.001) return true;
        if(Math.abs(v+Math.PI/2)<0.001) return true;
        return false;
        
        
    }
    public String toString(){
        return "Distance "+getDistance()+" angle "+getAngle()*180/Math.PI;
    }

    /**
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * @return the angle
     */
    public double getAngle() {
        return angle;
    }


}
