package ui;

import Utils.CargarGuardar;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Utils.Constantes.UI.MenuButtons.B_HEIGHT;
import static Utils.Constantes.UI.MenuButtons.B_WIDTH;
import static Utils.Constantes.UI.Seleccion.*;

public class Characters {
    private int xPos,yPos,rowIndex;
    private int xOffsetCenter=WIDTH/2;
    private Rectangle bounds;
    private boolean mouseOver,mousePressed,mouseReleased;
    private BufferedImage[] imgs;
    private int index;

    public Characters(int xPos, int yPos,int rowIndex){
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        loadImgs();
        initBounds();
    }

    private void initBounds() {
        bounds=new Rectangle(xPos-xOffsetCenter,yPos,WIDTH,HEIGHT);
    }

    private void loadImgs() {
        imgs = new BufferedImage[2];
        BufferedImage temp=CargarGuardar.GetSpriteAtlas(CargarGuardar.FIREBOY_AND_WATERGIRL);
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = temp.getSubimage(i* 300,rowIndex*HEIGHT_DEFAULT,WIDTH_DEFAULT,HEIGHT_DEFAULT);
        }
    }
    public void draw(Graphics g){
        g.drawImage(imgs[index],xPos-xOffsetCenter,yPos,WIDTH,HEIGHT,null);
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
    public void setMouseReleased(boolean mouseReleased) {
        this.mouseReleased = mouseReleased;
    }

    public boolean isMouseReleased() {
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

    public void resetBools(){
        mouseOver = false;
        mousePressed=false;
        mouseReleased = false;
    }



}

