package ui;

import Gamestates.Gamestate;
import Gamestates.State;
import static Utils.Constantes.UI.MenuButtons.*;
import Utils.CargarGuardar;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuButtons {
    private int xPos,index,yPos,rowIndex;
    private int xOffsetCenter=B_WIDTH/2;
    private Rectangle bounds;
    private boolean mouseOver,mousePressed;
    private BufferedImage[] imgs;
    private Gamestate state;
    public MenuButtons(int xPos, int yPos, int rowIndex, Gamestate state){
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        loadImgs();
        initBounds();
    }

    private void initBounds() {
        bounds=new Rectangle(xPos-xOffsetCenter,yPos,B_WIDTH,B_HEIGHT);
    }

    private void loadImgs() {
        imgs = new BufferedImage[2];
        BufferedImage temp=CargarGuardar.GetSpriteAtlas(CargarGuardar.MENU_BUTTONS);
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = temp.getSubimage(i* B_WIDTH_DEFAULT,rowIndex*B_HEIGHT_DEFAULT,B_WIDTH_DEFAULT,B_HEIGHT_DEFAULT);
        }
    }
    public void draw(Graphics g){
        g.drawImage(imgs[index],xPos-xOffsetCenter,yPos,B_WIDTH,B_HEIGHT,null);
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public void update() {
        index = 0;
        if(mouseOver){
            index=1;
        }
    }
    public Rectangle getBounds(){
        return bounds;
    }
    public void applyGamestate(){
        Gamestate.state=state;
    }
    public void resetBools(){
        mouseOver = false;
        mousePressed=false;
    }


}
