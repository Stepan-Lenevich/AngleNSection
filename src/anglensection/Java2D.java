/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anglensection;

/**
 *
 * @author stepan
 */
import javax.swing.JFrame;
import java.awt.*;

// By extending JFrame we have our applet
public class Java2D extends JFrame {
    public static int JFRAME_DIMENSIONS = 600;

    public static void main(String[] args) {
        
        new Java2D();

    }

    public Java2D() {

        this.setSize(JFRAME_DIMENSIONS+10, JFRAME_DIMENSIONS+50);

        this.setTitle("Drawing Shapes");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        this.add(new DrawPanel(), BorderLayout.CENTER);

        this.setVisible(true);

    }
}
