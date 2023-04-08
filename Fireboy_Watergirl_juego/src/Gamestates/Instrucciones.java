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
    private BufferedImage wasdImg;
    private BufferedImage arrowsImg;
    private MenuButtons[] buttons=new MenuButtons[2];



    public Instrucciones(Juego juego){
        super(juego);
        loadInstrucciones();
        loadButtons();
    }
    @Override
    public void update() {
        for (MenuButtons mb:buttons){
            mb.update();
        }
    }
    private void loadInstrucciones() {
        backgroundImg = CargarGuardar.GetSpriteAtlas(CargarGuardar.MENU_BACKGROUND);
        wasdImg=CargarGuardar.GetSpriteAtlas(CargarGuardar.WASD);
        arrowsImg=CargarGuardar.GetSpriteAtlas(CargarGuardar.ARROWS);

    }
    private void loadButtons() {
        buttons[0]=new MenuButtons(300,(int)(550*Juego.SCALA),1,Gamestate.MENU);
        buttons[1]=new MenuButtons(900,(int)(550*Juego.SCALA),2,Gamestate.SELECCION);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg,0,0,Juego.JUEGO_WIDTH,Juego.JUEGO_HEIGHT,null);
        g.drawImage(wasdImg,44,0,512,512,null);
        g.drawImage(arrowsImg,644,0,512,512,null);
        g.setFont(new Font("TimesRoman", Font.BOLD, 40));
        g.setColor(Color.BLUE);
        g.drawString("Para Watergirl",190,50);
        g.setColor(Color.red);
        g.drawString("Para Fireboy",800,50);

        g.setColor(Color.BLACK);
        g.drawString("W: Saltar",44,562);
        g.drawString("A: Moverse a la izquierda",44,612);
        g.drawString("D: Moverse a la derecha",44,672);

        g.drawString("⬆: Saltar",644,562);
        g.drawString("⬅: Moverse a la izquierda",644,612);
        g.drawString("➡ : Moverse a la derecha",644,672);
        for (MenuButtons mb:buttons){
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
    public void keyPressed(KeyEvent e) {

    }
}
