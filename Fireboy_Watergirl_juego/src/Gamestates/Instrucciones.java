package Gamestates;

import Inputs.Statemethods;
import Juego.Juego;
import Utils.CargarGuardar;
import ui.MenuButtons;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Instrucciones extends State implements Statemethods {
    private BufferedImage backgroundImg;

    public Instrucciones(Juego juego){
        super(juego);
        loadBackground();
    }
    @Override
    public void update() {
    }
    private void loadBackground() {
        backgroundImg = CargarGuardar.GetSpriteAtlas(CargarGuardar.MENU_BACKGROUND);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg,0,0,Juego.JUEGO_WIDTH,Juego.JUEGO_HEIGHT,null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }
}
