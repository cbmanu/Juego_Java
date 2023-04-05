package Gamestates;

import Juego.Juego;
import Utils.Constantes;
import ui.MenuButtons;

import java.awt.event.MouseEvent;

public class State {
    protected Juego juego;
    public State(Juego juego) {
        this.juego = juego;
    }
    public boolean isIn(MouseEvent e, MenuButtons mb){
        return mb.getBounds().contains(e.getX(),e.getY());
    }

    public Juego getJuego() {
        return juego;
    }
}
