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
public class Triangle {

    private final double[] x=new double[3];
    private final double[] y=new double[3];
    
    
        public Triangle(Point2D a, Point2D b, Point2D c) {
        
        x[0]= a.getX();
        y[0]= a.getY();
        x[1]= b.getX();
        y[1]= b.getY();
        x[2]= c.getX();
        y[3]= c.getY();
    }


    public Triangle(double xA, double yA, double xB, double yB, double xC, double yC) {
        
        x[0]= xA;
        y[0]= yA;
        x[1]= xB;
        y[1]= yB;
        x[2]= xC;
        y[3]= yC;
    }


    public double[] getX() {
        return x;
    }

    public double[] getY() {
        return y;
    }    
            public static void p(double x, double y){
        System.out.println("c "+x+" y "+y+" angle "+180*Math.atan2(y,x)/Math.PI);
    }
}
