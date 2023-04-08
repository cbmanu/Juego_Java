package Gamestates;

import Inputs.Statemethods;
import Juego.Juego;
import Utils.CargarGuardar;
import ui.MenuButtons;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends State implements Statemethods {
    private MenuButtons[] buttons=new MenuButtons[3];
    private BufferedImage backgroundImg,presentationImg;
    public Menu(Juego juego) {
        super(juego);
        loadPresentation();
        loadBackground();
        loadButtons();
    }
    private void loadPresentation() {
        presentationImg=CargarGuardar.GetSpriteAtlas(CargarGuardar.PRESENTATION_IMAGE);
    }
    private void loadBackground() {
        backgroundImg = CargarGuardar.GetSpriteAtlas(CargarGuardar.MENU_BACKGROUND);
    }

    private void loadButtons() {
        buttons[0]=new MenuButtons(1000,(int)(500*Juego.SCALA),2,Gamestate.SELECCION);
        buttons[1]=new MenuButtons(600,(int)(500*Juego.SCALA),0,Gamestate.INSTRUCCIONES);
        buttons[2]=new MenuButtons(200,(int)(500*Juego.SCALA),1,Gamestate.SALIR);
    }

    @Override
    public void update() {
        for (MenuButtons mb:buttons){
            mb.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg,0,0,Juego.JUEGO_WIDTH,Juego.JUEGO_HEIGHT,null);
        g.drawImage(presentationImg,344,50,512,512,null);
        for (MenuButtons mb:buttons)
            mb.draw(g);
    }

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
        resetButtons();
    }

    private void resetButtons() {
        for (MenuButtons mb : buttons) {
            mb.resetBools();
        }
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
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }
}
