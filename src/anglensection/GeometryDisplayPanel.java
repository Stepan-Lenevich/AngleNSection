/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anglensection;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.util.List;
import javax.swing.JPanel;

public class GeometryDisplayPanel extends JPanel {
    
    private double coeff;
    private int xCoord = Java2D.JFRAME_DIMENSIONS/2;
    private int yCoord = Java2D.JFRAME_DIMENSIONS/2;
    Graphics g;
    Graphics2D graph2;
    GeometryEngine geom;
    
    List<Shape> lines;
    List<Shape> innerTriangle;
    List<Shape> outerTriangle;
    
    public GeometryDisplayPanel(double coeff){
        this.coeff=coeff;
    }
    

    public void paint(Graphics g) {
        graph2 = (Graphics2D) g;
        
                super.paint(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 500, 500);
        
        graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        geom = new GeometryEngine(coeff, xCoord, yCoord);         
        refreshFigure();    
    }
    
    void update(double coeff) {
        removeAll();
        this.coeff=coeff;
        revalidate();
        
    }
    
    public void setCoeff(double coeff) {
        this.coeff = coeff;
    }
    
        void setCoordinates(int x, int y) {
            xCoord = x;
            yCoord = y;
    }
    
    private void refreshFigure() {
        outerTriangle = geom.getOuterTriangle();       
        graph2.setPaint(Color.BLACK);
        for(Shape s : outerTriangle)graph2.draw(s);
        
        
        lines = geom.getNsectrissi();
        graph2.setPaint(Color.BLUE);
        for(Shape s : lines)graph2.draw(s);
        
        
        innerTriangle = geom.getInnerTriangle();       
        graph2.setPaint(Color.BLACK);
        for(Shape s : innerTriangle)graph2.draw(s);
    }
 }
