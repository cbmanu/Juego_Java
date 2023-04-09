package Juego;
import Inputs.*;
import static Juego.Juego.*;

import java.awt.*;

import javax.swing.*;

public class PanelJuego extends JComponent{
    //atributos
    private MouseInput mouseinput;
    private Juego juego;
    private JLabel time,name,nameWindow;
    private JTextField nameField;


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

        time=new JLabel("0:00");
        time.setBounds(45,25,80,80);
        time.setFont(new Font("HELVETICA",Font.BOLD,35));
        time.setForeground(Color.WHITE);
        time.setVisible(false);
        add(time);

        nameWindow=new JLabel();
        nameWindow.setBounds(450,-20,300,80);
        nameWindow.setFont(new Font("HELVETICA",Font.BOLD,25));
        nameWindow.setForeground(Color.WHITE);
        nameWindow.setHorizontalAlignment(JTextField.CENTER);
        nameWindow.setVisible(true);
        add(nameWindow);

        nameField=new JTextField();
        nameField.setBounds(450,670,300,100);
        nameField.setFont(new Font("HELVETICA",Font.BOLD,35));
        nameField.setHorizontalAlignment(JTextField.CENTER);
        nameField.setForeground(Color.BLACK);
        nameField.setVisible(false);
        add(nameField);

        name=new JLabel("Introduzca su nombre");
        name.setBounds(460,600,300,100);
        name.setFont(new Font("HELVETICA",Font.BOLD,25));
        name.setForeground(Color.BLACK);
        name.setVisible(false);
        add(name);

    }
    
    //size window 
    private void setTamPanel() {
       Dimension tamano = new Dimension(JUEGO_WIDTH, JUEGO_HEIGHT);
       setPreferredSize(tamano);
       System.out.println("size: " +JUEGO_WIDTH +" : "+ JUEGO_HEIGHT);
    }
    
    
    public void updateJuego(){
        
    }
    public JLabel getTime(){
        return time;
    }
    public JTextField getNameField(){
        return nameField;
    }
    public JLabel getNameLabel(){
        return name;
    }
    public JLabel getNameWindow(){
        return nameWindow;
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
