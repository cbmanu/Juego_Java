package Gamestates;

import Inputs.Statemethods;
import Juego.Juego;
import Juego.VentanaJuego;
import Juego.PanelJuego;
import Utils.CargarGuardar;
import ui.Characters;
import ui.MenuButtons;


import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Seleccion extends State implements Statemethods {
    private MenuButtons[] buttons=new MenuButtons[2];
    private Characters[] character=new Characters[2];
    private PanelJuego panel=juego.getPanelJuego();

    private BufferedImage backgroundImg;
    public Seleccion(Juego juego){
        super(juego);
        loadBackground();
        loadComponents();
    }
    private void loadBackground() {
        backgroundImg = CargarGuardar.GetSpriteAtlas(CargarGuardar.MENU_BACKGROUND);
    }
    private void loadComponents(){
        buttons[0]=new MenuButtons(250,(int)(500*Juego.SCALA),1,Gamestate.MENU);
        buttons[1]=new MenuButtons(950,(int)(500*Juego.SCALA),2,Gamestate.PLAYING);
        character[0]=new Characters(300,150,0);
        character[1]=new Characters(900,150,1);


    }

    @Override
    public void update() {
        for (MenuButtons mb : buttons) {
            mb.update();
        }
        for(Characters ch:character){
            ch.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg,0,0,Juego.JUEGO_WIDTH,Juego.JUEGO_HEIGHT,null);
        for (MenuButtons mb : buttons) {
            mb.draw(g);
        }
        for (Characters ch : character) {
            ch.draw(g);
    }}

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButtons mb:buttons){
            if (isIn(e,mb)) {
                mb.setMousePressed(true);
                break;
            }
        }

    }
    private void resetButtons() {
        for (MenuButtons mb : buttons) {
            mb.resetBools();
        }
    }
    private void resetSeleccionado(int i) {
        if (i==0){
            character[i+1].setMousePressed(false);
        }else {
            character[i-1].setMousePressed(false);
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButtons mb : buttons) {
            if (isIn(e, mb)) {
                if(mb.isMousePressed()){
                    mb.applyGamestate();
                    break;
                }
            }
        }
        for (int i = 0; i < character.length; i++) {
            if (isIn(e, character[i])) {
                resetSeleccionado(i);
                character[i].setMousePressed(true);
                break;
            }
        }
        resetButtons();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (MenuButtons mb:buttons){
            mb.setMouseOver(false);
        }
        for (MenuButtons mb:buttons){
            if(isIn(e,mb)){
                mb.setMouseOver(true);
                break;
            }
        }
        for(Characters ch:character){
            if(ch.isMousePressed()){
            }else {
                ch.setMouseOver(false);
            }
        }
        for (Characters ch:character){
                if(isIn(e,ch)){
                    ch.setMouseOver(true);
                    break;
                }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

}
