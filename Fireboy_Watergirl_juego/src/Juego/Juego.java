package Juego;
import Gamestates.*;
import Gamestates.Menu;
import Gamestates.Seleccion;

import java.awt.*;


public class Juego implements Runnable{
    //atributos
    private VentanaJuego ventanajuego;
    private PanelJuego paneljuego;
    private Thread juegoThread;
    private final int FPS_SET = 120;
    private final int UPS_SET= 120;
    private Playing playing;
    private Menu menu;
    private Seleccion seleccion;
    private Instrucciones instrucciones;
    
    public final static int TILES_DEFAULT_SIZE= 32;
    public final static float SCALA = 1.25f; //1.5f
    public final static int TILES_IN_WIDTH = 30;//26 27//26
    public final static int TILES_IN_HEIGHT = 23;//14 18// 16
    public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALA);
    public final static int JUEGO_WIDTH = TILES_SIZE *TILES_IN_WIDTH;
    public final static int JUEGO_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
    
    //constructor
    public Juego(){
        iniciClases();
        paneljuego = new PanelJuego(this);
        ventanajuego = new VentanaJuego(paneljuego);
        paneljuego.requestFocus();
        seleccion=new Seleccion(this,paneljuego);
        playing=new Playing(this,paneljuego);
        InicioLoop();
    }



    private void iniciClases() {
        menu=new Menu(this);
        instrucciones=new Instrucciones(this);
    }

    private void InicioLoop(){
        juegoThread = new Thread(this);
        juegoThread.start();
    }

    private void actualizar() {
       switch (Gamestate.state){
           case MENU:
               menu.update();
               break;
           case PLAYING:
               playing.update();
               break;
           case INSTRUCCIONES:
               break;
           case SELECCION:
               seleccion.update();
               break;
           case SALIR:
               System.exit(0);
               break;
           default:
               break;
       }
    }
    
    public void render(Graphics g){
        switch (Gamestate.state){
            case MENU:
                menu.draw(g);
                break;
            case PLAYING:
                playing.draw(g);
                break;
            case SELECCION:
                seleccion.draw(g);
            default:
                break;
        }
    }
    
    @Override
    public void run() {
        double tiempoPorSegundo= 1000000000.0/FPS_SET;
        double tiempoDeCarga= 1000000000.0/UPS_SET;
        
        
        long tiempoAnterior = System.nanoTime();
        
        int frames = 0;
        int updates = 0;
        
        long lastCheck = System.currentTimeMillis();
        
        double deltaU = 0;
        double deltaF =0;
        
        while(true){
            
            long currenTime = System.nanoTime();
            
            deltaU+=(currenTime-tiempoAnterior)/tiempoDeCarga;
            deltaF+=(currenTime-tiempoAnterior)/tiempoPorSegundo;
            tiempoAnterior = currenTime;
            
            if(deltaU>=1){
                actualizar();
                updates++;
                deltaU--;
            }
            
            if(deltaF >=1){
                paneljuego.repaint();
                frames++;
                deltaF--;
            }
            
        if(System.currentTimeMillis()-lastCheck >= 1000){
            lastCheck = System.currentTimeMillis();
            System.out.println("FPS "+ frames+ " |UPS: "+updates);
            frames = 0;
            updates = 0;
        }
        }
    }
     
    public void windowFocuseLost(){
        if(Gamestate.state ==Gamestate.PLAYING){
            playing.getJugador().resetDirBoolean();
        }
    }

    public Menu getMenu(){
      return menu;
    }

    public Playing getPlaying() {
        return playing;
    }
    public Seleccion getSeleccion() {
        return seleccion;
    }
    public Instrucciones getInstrucciones() {
        return instrucciones;
    }



}
