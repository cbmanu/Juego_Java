package Inputs;
import Gamestates.Gamestate;
import Juego.PanelJuego;
import java.awt.event.*;

public class TecladoInput implements KeyListener {
    //atributos
    private PanelJuego paneljuego;
    
    //constructor 
    public TecladoInput(PanelJuego paneljuego){
        this.paneljuego = paneljuego;
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }
    @Override
    public void keyReleased(KeyEvent ke) {
        switch (Gamestate.state){
            case PLAYING:
                paneljuego.getJuego().getPlaying().keyReleased(ke);
                break;
            case MENU:
                paneljuego.getJuego().getMenu().keyReleased(ke);
                break;
            default:
                break;
        }
        
        switch(ke.getKeyCode()){
            case KeyEvent.VK_W:   //asegurando que con cada tecla se acabe el movimiento
                paneljuego.getJuego().getJugador().setJump(false); //salto 
                break;
            case KeyEvent.VK_A:
                paneljuego.getJuego().getJugador().setLeft(false);
                break;
            case KeyEvent.VK_D:
                paneljuego.getJuego().getJugador().setRight(false);
                break;
            case KeyEvent.VK_S:
                paneljuego.getJuego().getJugador().setDown(false);
                break;
            
        }
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        switch (Gamestate.state){
            case PLAYING:
                paneljuego.getJuego().getPlaying().keyPressed(ke);
                break;
            case MENU:
                paneljuego.getJuego().getMenu().keyPressed(ke);
                break;
            default:
                break;
        }
        
        switch(ke.getKeyCode()){
            case KeyEvent.VK_W:   //asegurando que con cada tecla se acabe el movimiento
                paneljuego.getJuego().getJugador().setJump(true); //salto
                break;
            case KeyEvent.VK_A:
                paneljuego.getJuego().getJugador().setLeft(true);
                break;
            case KeyEvent.VK_D:
                paneljuego.getJuego().getJugador().setRight(true);
                break;
            case KeyEvent.VK_S:
                paneljuego.getJuego().getJugador().setDown(true);
                break;
        }
    }


    
}
