package Gamestates;

import Juego.Juego;

public class State {
    protected Juego juego;
    public State(Juego juego) {
        this.juego = juego;
    }

    public Juego getJuego() {
        return juego;
    }
}
