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
    
    }


    
}
