package Gamestates;

import Entidades.Jugador;
import Inputs.Statemethods;
import Juego.Juego;
import static Juego.Juego.SCALA;
import Juego.PanelJuego;
import Niveles.NivelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements Statemethods {

    private Jugador jugador;
    private NivelManager nivelmanager;
    private JTextField name;
    public Playing(Juego juego, PanelJuego panel) {
        super(juego);
        iniciClases();
        name=panel.getJTextField();
    }

    private void iniciClases() {
        jugador = new Jugador((50),(920-36-20-47),(int)(76*SCALA),(int)(76*SCALA));
        nivelmanager = new NivelManager(juego);
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
        jugador.update();
    }

    @Override
    public void draw(Graphics g) {
        nivelmanager.draw(g);
        jugador.render(g);
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
                jugador.setUp(false);
                break;
            case KeyEvent.VK_A:
                jugador.setLeft(false);
                break;
            case KeyEvent.VK_D:
                jugador.setRight(false);
                break;
            case KeyEvent.VK_S:
                jugador.setDown(false);
                break;
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_W:   //asegurando que con cada tecla se acabe el movimiento
                jugador.setUp(true);
                break;
            case KeyEvent.VK_A:
                jugador.setLeft(true);
                break;
            case KeyEvent.VK_D:
                jugador.setRight(true);
                break;
            case KeyEvent.VK_S:
                jugador.setDown(true);
                break;
        }
    }
}
