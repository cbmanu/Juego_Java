package Juego;
import Inputs.*;
import static Juego.Juego.*;

import java.awt.*;

import javax.swing.*;

public class PanelJuego extends JComponent{
    //atributos
    private MouseInput mouseinput;
    private Juego juego;
    private JTextField name;

    //constructor
    public PanelJuego(Juego juego){
        mouseinput = new MouseInput(this);
        this.juego=juego;
        //ventana
        setTamPanel();
        
        //eventos
        addKeyListener(new TecladoInput(this));
        addMouseListener(mouseinput);
        addMouseMotionListener(mouseinput);
        name=new JTextField("Coloca Tu Nombre");
        name.setFont(new Font("Helvetica",Font.BOLD,30));
        name.setHorizontalAlignment(SwingConstants.CENTER);
        name.setForeground(Color.black);
        name.setBounds(450,650,300,100);
        name.setVisible(false);
        this.add(name);

    }
    
    //size window 
    private void setTamPanel() {
       Dimension tamano = new Dimension(JUEGO_WIDTH, JUEGO_HEIGHT);
       setPreferredSize(tamano);
       System.out.println("size: " +JUEGO_WIDTH +" : "+ JUEGO_HEIGHT);
    }
    
    
    public void updateJuego(){
        
    }
    public JTextField getJTextField(){
        return name;
    }
    //dibujar componentes
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        juego.render(g);
    }

    public Juego getJuego(){
        return juego;
    }
  

}
