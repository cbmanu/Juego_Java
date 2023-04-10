package Gamestates;

import Inputs.Statemethods;
import Juego.Juego;
import Juego.PanelJuego;
import Utils.CargarGuardar;
import ui.Characters;
import ui.MenuButtons;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Seleccion extends State implements Statemethods {
    private MenuButtons[] buttons=new MenuButtons[2];
    private Characters[] character=new Characters[2];
    private PanelJuego panel=juego.getPanelJuego();
    private JLabel time=juego.getPanelJuego().getTime();
    public int selected=1;
    public int timeSecs=0;

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
        timeSecs=0;
        panel.getNameLabel().setVisible(true);
            panel.getNameField().setVisible(true);
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
    Timer timer = new Timer(1000,new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if(timeSecs%60<10){
                String seconds=timeSecs/60+":"+"0"+timeSecs%60;
                time.setText(seconds);
            }else {
                String seconds=timeSecs/60+":"+timeSecs%60;
                time.setText(seconds);
            }
            timeSecs++;
        }
    });

    @Override
    public void mouseReleased(MouseEvent e) {
        for (int i = 0; i <buttons.length; i++) {
            if (isIn(e, buttons[i])) {
                if(buttons[0].isMousePressed()){
                    buttons[0].applyGamestate();
                    panel.getNameLabel().setVisible(false);
                    panel.getNameField().setVisible(false);
                }
                if(buttons[1].isMousePressed()){
                    if(panel.getNameField().getText().equals("")){
                        System.out.println("no hay nombre");
                    } else if(!panel.getNameField().getText().equals("")&&(character[0].isMousePressed()||character[1].isMousePressed())){
                        buttons[i].applyGamestate();
                        if(Gamestate.state==Gamestate.PLAYING){
                            if(character[0].isMousePressed()){
                                selected=0;
                            }else {
                                selected=1;
                            }
                            panel.getNameWindow().setText("Jugador: "+panel.getNameField().getText());
                            panel.getNameLabel().setVisible(false);
                            panel.getNameField().setVisible(false);
                            panel.getTime().setVisible(true);
                            timer.start();
                        }
                        break;
                    }else {
                        System.out.println("No hay personaje seleccionado");
                    }
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
    public int getSelected(){
        return selected;
    }
    public Timer getTimer(){
        return timer;
    }

}
