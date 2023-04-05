package Gamestates;

import Inputs.Statemethods;
import Juego.Juego;
import Juego.VentanaJuego;
import Juego.PanelJuego;
import Utils.CargarGuardar;
import ui.MenuButtons;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Seleccion extends State implements Statemethods {
    private MenuButtons[] buttons=new MenuButtons[2];
    private JTextField name;

    private BufferedImage backgroundImg,fireboyImg,watergirlImg;
    public Seleccion(Juego juego, PanelJuego panel){
        super(juego);
        loadBackground();
        loadCharacters();
        loadButtons();
        name=panel.getJTextField();
    }
    private void loadBackground() {
        backgroundImg = CargarGuardar.GetSpriteAtlas(CargarGuardar.MENU_BACKGROUND);
    }
    private void loadButtons(){
        buttons[0]=new MenuButtons(250,(int)(500*Juego.SCALA),1,Gamestate.SALIR);
        buttons[1]=new MenuButtons(950,(int)(500*Juego.SCALA),2,Gamestate.PLAYING);

    }
    private void loadCharacters(){
        fireboyImg=CargarGuardar.GetSpriteAtlas(CargarGuardar.FIREBOY_IMAGE);
        watergirlImg=CargarGuardar.GetSpriteAtlas(CargarGuardar.WATERGIRL_IMAGE);
    }
    @Override
    public void update() {
        for (MenuButtons mb : buttons) {
            mb.update();
        }
        name.setVisible(true);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg,0,0,Juego.JUEGO_WIDTH,Juego.JUEGO_HEIGHT,null);
        g.drawImage(fireboyImg,200,150,300,300,null);
        g.drawImage(watergirlImg,700,150,300,300,null);
        for (MenuButtons mb : buttons) {
            mb.draw(g);
        }
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
    private void resetButtons() {
        for (MenuButtons mb : buttons) {
            mb.resetBools();
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
    public void keyPressed(KeyEvent e) {
    }
}
