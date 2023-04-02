
package Juego;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.*;

public class VentanaJuego {
    //atributos
    private JFrame frame;
    
    //constructor
    public VentanaJuego(PanelJuego paneljuego){
        frame = new JFrame();
        
        //frame.setSize(772, 563);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(paneljuego);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.addWindowFocusListener(new WindowFocusListener(){
                @Override
             public void windowGainedFocus(WindowEvent we) {
                 paneljuego.getJuego().windowFocuseLost();
                         
            }

                @Override
             public void windowLostFocus(WindowEvent we) {
                 
            }
        
        });
    }

    
}
