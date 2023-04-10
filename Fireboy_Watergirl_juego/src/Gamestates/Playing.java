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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Playing extends State implements Statemethods {

    private Jugador fireboy;
    private Jugador watergirl;

    private NivelManager nivelmanager;
    private ObjectManager objectManager;
    private int xnvOffset;
    private int leftBorde = (int)(0.2*Juego.JUEGO_WIDTH);
    private int rightBorde = (int)(0.8*Juego.JUEGO_HEIGHT);
    private int maxLvOffsetX;
    
    private int selected=0;
    private BufferedImage backgroundImg, objFondo, obj2Fondo;
    private int[] obj2FondoPos;

    private Random rnd = new Random();
    
    //constructor 
    public Playing(Juego juego) {
        super(juego);
        iniciClases();
        fireboy = new Jugador((50),(920-36-20-47),(int)(76*SCALA),(int)(76*SCALA),0,this);
        watergirl = new Jugador((100),(920-36-20-47),(int)(76*SCALA),(int)(76*SCALA),1,this);
        fireboy.loadNvlData(nivelmanager.getNivelReciente().getNivelData());
        watergirl.loadNvlData(nivelmanager.getNivelReciente().getNivelData());
        //fondo
        backgroundImg = CargarGuardar.GetSpriteAtlas(CargarGuardar.JUEGO_FONDO_IMAGEN);
        cargarIniciarNivel();
    }
    public void cargarOtroNivel(){
        nivelmanager.cargarOtroNivel();
    }
    public void cargarIniciarNivel(){//called to load the objects
        objectManager.cargarObjetos(nivelmanager.getNivelReciente());
    }

    private void iniciClases() {
        nivelmanager = new NivelManager(juego);
        objectManager = new ObjectManager(this);
        

    }
//    public void windowFocuseLost(){
//        jugador.resetDirBoolean();
//    }

//    public Jugador getJugador(){
//        return jugador;
//    }
    public void calcularPersonaje(Graphics g){
        if(juego.getSeleccion().getSelected()==0){
            fireboy.render(g);
        }else {
            watergirl.render(g);
        }
    }

    @Override
    public void update() {
        nivelmanager.update();
        objectManager.update();
        fireboy.update();
        watergirl.update();
    }

    @Override
    public void draw(Graphics g) {
        //fondo
        g.drawImage(backgroundImg, 0, 0, Juego.JUEGO_WIDTH, Juego.JUEGO_HEIGHT, null);
        
        
        //drawObjetosFondo(g);
        calcularPersonaje(g);

        nivelmanager.draw(g);
        objectManager.draw(g, xnvOffset);
    }
    
  /*  private void drawObjetosFondo(Graphics g) {
        //dibujando objetos que acompanan el fondo 
        //ciclo para que aparezcan a lo largo de la pantalla 
        
        for(int i =0; i<3; i++)
            g.drawImage(objFondo, (i*OBJ_DELFONDO_WIDTH), (int)(204*Juego.SCALA), OBJ_DELFONDO_WIDTH, OBJ_DELFONDO_HEIGHT, null);
        
        for(int i=0; i<obj2FondoPos.length;i++)
            g.drawImage(obj2Fondo, OBJ2_DELFONDO_WIDTH *4 *i, obj2FondoPos[i], OBJ2_DELFONDO_WIDTH, OBJ2_DELFONDO_WIDTH, null);
    
        
    }*/


    
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
                watergirl.setJump(false); //salto
                break;
            case KeyEvent.VK_A:
                watergirl.setLeft(false);
                break;
            case KeyEvent.VK_D:
                watergirl.setRight(false);
                break;
            case KeyEvent.VK_UP:
                fireboy.setJump(false);
                break;
            case KeyEvent.VK_LEFT:
                fireboy.setLeft(false);
                break;
            case KeyEvent.VK_RIGHT:
                fireboy.setRight(false);
                break;
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_W:
                watergirl.setJump(true); //salto
                break;
            case KeyEvent.VK_A:
                watergirl.setLeft(true);
                break;
            case KeyEvent.VK_D:
                watergirl.setRight(true);
                break;
            case KeyEvent.VK_UP:
                fireboy.setJump(true);
                break;
            case KeyEvent.VK_LEFT:
                fireboy.setLeft(true);
                break;
            case KeyEvent.VK_RIGHT:
                fireboy.setRight(true);
                break;
        }
    }

    //getters setters
    public ObjectManager getObjectManager(){
        return objectManager;
    }

    //esto es para el jugador
    public void checkGemaTouched(Rectangle2D.Float hitbox){
        objectManager.checkObjetoTouch(hitbox,juego.getSeleccion().getSelected()); //revisar si es el mismo touch
    }
    
}
