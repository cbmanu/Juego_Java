package Inputs;

import Gamestates.Gamestate;
import Juego.PanelJuego;
import java.awt.event.*;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener{
    //atributos
    private PanelJuego paneljuego;
    
    //constructor
    public MouseInput(PanelJuego paneljuego){
        this.paneljuego = paneljuego;
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
       switch (Gamestate.state){
           case PLAYING:
               paneljuego.getJuego().getPlaying().mouseClicked(me);
               break;
           default:
               break;
       }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        switch (Gamestate.state){
            case PLAYING:
                paneljuego.getJuego().getPlaying().mousePressed(me);
                break;
            case MENU:
                paneljuego.getJuego().getMenu().mousePressed(me);
                break;
            case SELECCION:
                paneljuego.getJuego().getSeleccion().mousePressed(me);
                break;
            case INSTRUCCIONES:
                paneljuego.getJuego().getInstrucciones().mousePressed(me);
                break;
            default:
                break;
        }
         
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        switch (Gamestate.state){
            case PLAYING:
                paneljuego.getJuego().getPlaying().mouseReleased(me);
                break;
            case MENU:
                paneljuego.getJuego().getMenu().mouseReleased(me);
                break;
            case SELECCION:
                paneljuego.getJuego().getSeleccion().mouseReleased(me);
                break;
            case INSTRUCCIONES:
                paneljuego.getJuego().getInstrucciones().mouseReleased(me);
                break;
            default:
                break;
        }
         
    }

    @Override
    public void mouseEntered(MouseEvent me) {
         
    }

    @Override
    public void mouseExited(MouseEvent me) {
         
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        switch (Gamestate.state){
            case PLAYING:
                paneljuego.getJuego().getPlaying().mouseMoved(me);
                break;
            case MENU:
                paneljuego.getJuego().getMenu().mouseMoved(me);
                break;
            case SELECCION:
                paneljuego.getJuego().getSeleccion().mouseMoved(me);
                break;
            case INSTRUCCIONES:
                paneljuego.getJuego().getInstrucciones().mouseMoved(me);
                break;
            default:
                break;
        }
     
    }
    
}
