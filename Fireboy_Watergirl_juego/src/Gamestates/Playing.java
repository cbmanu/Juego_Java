package Gamestates;

import Entidades.Jugador;
import Inputs.Statemethods;
import Juego.Juego;
import static Juego.Juego.SCALA;
import Juego.PanelJuego;
import Niveles.NivelManager;
import Objetos.ObjectManager;
import Utils.CargarGuardar;
import static Utils.Constantes.Enviroment.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Playing extends State implements Statemethods {

    private Jugador jugador;
    private NivelManager nivelmanager;
    private ObjectManager objectManager;
    
    private int xnvOffset;
    private int leftBorde = (int)(0.2*Juego.JUEGO_WIDTH);
    private int rightBorde = (int)(0.8*Juego.JUEGO_HEIGHT);
    private int maxLvOffsetX;
    
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
        
        //objetos
        cargarIniciarNivel();
    }
    ///
    public void cargarOtroNivel(){
        nivelmanager.cargarOtroNivel();
    }
    
    public void cargarIniciarNivel(){//called to load the objects
        objectManager.cargarObjetos(nivelmanager.getNivelReciente());
    }
    
    private void iniciClases() {
        nivelmanager = new NivelManager(juego);
        objectManager = new ObjectManager(this);
        
        jugador = new Jugador((50),(920-36-20-47),(int)(76*SCALA),(int)(76*SCALA),this); //lo modifique por el constructor de jugador
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
        objectManager.update();
        jugador.update();
    }

    @Override
    public void draw(Graphics g) {
        //fondo
        g.drawImage(backgroundImg, 0, 0, Juego.JUEGO_WIDTH, Juego.JUEGO_HEIGHT, null);
        
        nivelmanager.draw(g);
        jugador.render(g);
        objectManager.draw(g, xnvOffset);
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

    //getters setters
    public ObjectManager getObjectManager(){
        return objectManager;
    }
    
    //esto es para el jugador
    public void checkGemaTouched(Rectangle2D.Float hitbox){
        objectManager.checkObjetoTouch(hitbox); //revisar si es el mismo touch
    }
    
}
