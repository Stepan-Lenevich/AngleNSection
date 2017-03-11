/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anglensection;

import java.awt.BorderLayout;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class DrawPanel extends JPanel implements ActionListener {

    int count;
    GeometryDisplayPanel geometryPanel;
    private JPanel bottom;
    private JSlider coefficientSelector;
    private boolean isTimerOn = true;
    private JButton timerOnOff;
    
    private JLabel currentValue;
    private int sliderSetting = 50;
    private double mouseX;
    private double mouseY;
    
    Timer timer;

    public DrawPanel() {
        count = 0;
        timer = new Timer(100, this);
        timer.start();
        init();
    }

    private void init() {

        setLayout(new BorderLayout());

        bottom = new JPanel();
        bottom.setLayout(new BorderLayout());
        
        timerOnOff = createTimerButton();
        

        coefficientSelector = getSlider();
        currentValue = new JLabel(Integer.toString(sliderSetting));
        bottom.add(timerOnOff, BorderLayout.EAST);
        bottom.add(coefficientSelector, BorderLayout.CENTER);
        bottom.add(currentValue, BorderLayout.WEST);
        double coeff = sliderSetting / 1000.0;
        geometryPanel = new GeometryDisplayPanel(coeff); 
        add(geometryPanel, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
        
           addMouseListener(new MouseAdapter() { 
          @Override
          public void mousePressed(MouseEvent me) { 
              
              
               mouseX = MouseInfo.getPointerInfo().getLocation().getX();
                mouseY = MouseInfo.getPointerInfo().getLocation().getY();
                
                
                geometryPanel.removeAll();
                geometryPanel.setCoordinates((int) mouseX, (int) mouseY);
                geometryPanel.repaint();
          } 
        }); 
        
        

    }

    public JSlider getSlider() {

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 1000, sliderSetting);
        slider.setMinorTickSpacing(10);
        slider.setMajorTickSpacing(50);

        slider.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                sliderSetting = ((JSlider) e.getSource()).getValue();
                double coeff = sliderSetting / 1000.0;

                geometryPanel.removeAll();
                geometryPanel.setCoeff(coeff);
                geometryPanel.repaint(); //drawing, but not erasing

                currentValue.setText(Integer.toString(sliderSetting));
            }

        });

        return slider;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == timer) {
            count += 1;
            count = count%1000;
 
           double coeff = count / 1000.0;
            geometryPanel.removeAll();
            geometryPanel.setCoeff(coeff);
            geometryPanel.repaint();
            
        }
    }

    private JButton createTimerButton() {
        String text = getButtonText();
        JButton timerOnOffButton = new JButton(text);
        timerOnOffButton.addActionListener(new ButtonListener());

        
        
     return timerOnOffButton;   
    }
    
    class ButtonListener implements ActionListener {

        ButtonListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getActionCommand().equals(getButtonText())) {
                isTimerOn = !isTimerOn;
                
                if(isTimerOn){
                   timer.start();
                }else{
                     timer.stop();
                }                
                timerOnOff.setText(getButtonText());
                
            }
        }
    }
    
    private String getButtonText(){
        return isTimerOn ? "Pause" : "Resume";
    }

}
