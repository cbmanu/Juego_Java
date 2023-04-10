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
import ui.GameOverOverlay;

public class Playing extends State implements Statemethods {

    private Jugador fireboy;
    private Jugador watergirl;
    private JLabel time=juego.getPanelJuego().getTime();

    private NivelManager nivelmanager;
    private ObjectManager objectManager;
    private GameOverOverlay gameOverOverlay;
    private int xnvOffset;
    private int leftBorde = (int)(0.2*Juego.JUEGO_WIDTH);
    private int rightBorde = (int)(0.8*Juego.JUEGO_HEIGHT);
    private int maxLvOffsetX;
    
    private int selected=0;
    private BufferedImage backgroundImg;
    private boolean gameOver;
    private boolean jugadorMuriendo;


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
        gameOverOverlay = new GameOverOverlay(this);

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
        if(gameOver){
//            gameOverOverlay.update();
        } else if (jugadorMuriendo) {
            fireboy.update();
            watergirl.update();
        }else {
            nivelmanager.update();
            objectManager.update();
            fireboy.update();
            watergirl.update();
        }


    }

    @Override
    public void draw(Graphics g) {
        //fondo
        g.drawImage(backgroundImg, 0, 0, Juego.JUEGO_WIDTH, Juego.JUEGO_HEIGHT, null);
        
        calcularPersonaje(g);

        nivelmanager.draw(g);
        objectManager.draw(g, xnvOffset);
        
        if(gameOver)
            gameOverOverlay.draw(g);
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
        if(!gameOver){
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
        }}

    }

    @Override
    public void keyPressed(KeyEvent e) {
    
        if(gameOver)
            gameOverOverlay.keyPressed(e);
        else{
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
        } }
    }

    public void resetAll(){
        watergirl.resetAll();
        fireboy.resetAll();
        time.setText("");
        juego.getSeleccion().getTimer().stop();
        jugadorMuriendo=false;
        gameOver=false;

    }


    public void setGameOver(boolean gameOver) {//invoca en el jugador
        this.gameOver = gameOver;
    }

    //getters setters
    public ObjectManager getObjectManager(){
        return objectManager;
    }

    //esto es para el jugador cuando toca un objeto 
    public void checkGemaTouched(Rectangle2D.Float hitbox){
        objectManager.checkObjetoTouch(hitbox,juego.getSeleccion().getSelected());
        objectManager.checkObjetoTouch(hitbox,juego.getSeleccion().getSelected()); //revisar si es el mismo touch
//revisar si es el mismo touch
    }
    public void checkPlataformaTouched(Rectangle2D.Float hitbox){
        objectManager.checkPlataformaTouch(hitbox);
    }

    public void checkLavaTouched(Rectangle2D.Float hitbox) {//esta en jugador
        objectManager.checkLavaTouch(hitbox,juego.getSeleccion().getSelected(),watergirl);
    }

    public void checkAguaTouched(Rectangle2D.Float hitbox) {
        objectManager.checkAguaTouch(hitbox,juego.getSeleccion().getSelected(),fireboy);
    }
    
    public void checkPuertaTouched(Rectangle2D.Float hitbox) {
        objectManager.checkPuertaTouch(hitbox,juego.getSeleccion().getSelected(),watergirl);
    }

    public void setJugadorMuriendo(boolean jugadorMuriendo) {
        this.jugadorMuriendo=jugadorMuriendo;
    }

    
}
