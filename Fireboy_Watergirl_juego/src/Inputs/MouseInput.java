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
           case MENU:
               paneljuego.getJuego().getMenu().mouseClicked(me);
               break;
           default:
               break;
       }
    }

    @Override
    public void mousePressed(MouseEvent me) {
         
    }

    @Override
    public void mouseReleased(MouseEvent me) {
         
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
     
    }
    
}
