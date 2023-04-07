package Gamestates;

import Entidades.Jugador;
import Inputs.Statemethods;
import Juego.Juego;
import static Juego.Juego.SCALA;
import Juego.PanelJuego;
import Niveles.NivelManager;
import Utils.CargarGuardar;
import static Utils.Constantes.Enviroment.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Playing extends State implements Statemethods {

    private Jugador jugador;
    private NivelManager nivelmanager;
    
    private JTextField name;
    private BufferedImage backgroundImg, objFondo, obj2Fondo;
    private int[] obj2FondoPos;
    private Random rnd = new Random();
    
    //constructor 
    public Playing(Juego juego, PanelJuego panel) {
        super(juego);
        iniciClases();
        name=panel.getJTextField();
        
        //fondo
        backgroundImg = CargarGuardar.GetSpriteAtlas(CargarGuardar.JUEGO_FONDO_IMAGEN);
        objFondo = CargarGuardar.GetSpriteAtlas(CargarGuardar.OBJ_DELFONDO_IMG);
        obj2Fondo = CargarGuardar.GetSpriteAtlas(CargarGuardar.OBJ2_DELFONDO_IMG);
        obj2FondoPos = new int[8];
        for(int i=0; i<obj2FondoPos.length;i++)
            obj2FondoPos[i] = (int)(30*Juego.SCALA)+rnd.nextInt((int)(120*Juego.SCALA)) ; //empieza en 70 y dos da un valor random hasta 150
    }

    private void iniciClases() {
        nivelmanager = new NivelManager(juego);
        jugador = new Jugador((50),(920-36-20-47),(int)(76*SCALA),(int)(76*SCALA)); 
        jugador.loadNvlData(nivelmanager.getNivelReciente().getNivelData());
    }
    public void windowFocuseLost(){
        jugador.resetDirBoolean();
    }

    public Jugador getJugador(){
        return jugador;
    }

    @Override
    public void update() {
        name.setVisible(false);
        nivelmanager.update();
        jugador.update();
    }

    @Override
    public void draw(Graphics g) {
        //fondo
        g.drawImage(backgroundImg, 0, 0, Juego.JUEGO_WIDTH, Juego.JUEGO_HEIGHT, null);
        
        //drawObjetosFondo(g);
        
        nivelmanager.draw(g);
        jugador.render(g);
    }
    
    private void drawObjetosFondo(Graphics g) {
        //dibujando objetos que acompanan el fondo 
        //ciclo para que aparezcan a lo largo de la pantalla 
        
        for(int i =0; i<3; i++)
            g.drawImage(objFondo, (i*OBJ_DELFONDO_WIDTH), (int)(204*Juego.SCALA), OBJ_DELFONDO_WIDTH, OBJ_DELFONDO_HEIGHT, null);
        
        for(int i=0; i<obj2FondoPos.length;i++)
            g.drawImage(obj2Fondo, OBJ2_DELFONDO_WIDTH *4 *i, obj2FondoPos[i], OBJ2_DELFONDO_WIDTH, OBJ2_DELFONDO_WIDTH, null);
    
        
    }
    
    @Override
    public void mousePressed(MouseEvent e) {

    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_W:   //asegurando que con cada tecla se acabe el movimiento
                jugador.setJump(false); //salto 
                break;
            case KeyEvent.VK_A:
                jugador.setLeft(false);
                break;
            case KeyEvent.VK_D:
                jugador.setRight(false);
                break;
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_W:   
                jugador.setJump(true); //salto
                break;
            case KeyEvent.VK_A:
                jugador.setLeft(true);
                break;
            case KeyEvent.VK_D:
                jugador.setRight(true);
                break;
        }
    }

    
}
