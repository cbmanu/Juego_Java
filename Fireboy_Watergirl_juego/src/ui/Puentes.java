package ui;

import Utils.CargarGuardar;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Puentes {
    protected int x,y;
    protected int width, height;
    protected Rectangle2D.Float hitbox;
    protected BufferedImage puenteImg;
    private Graphics g;

    public Puentes(int x,int y,int width,int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        loadImgs();
        iniHitbox(x,y,width,height);
        drawPuente(g);
        drawHitbox(g);
    }
    public void loadImgs(){
        puenteImg= CargarGuardar.GetSpriteAtlas(CargarGuardar.MENU_BUTTONS);
    }
    public void drawPuente(Graphics g){
        g.drawImage(puenteImg,x,y,width,height,null);
    }
    protected void drawHitbox(Graphics g){
        //for th burging hitnbxo
        g.setColor(Color.RED);
        g.drawRect((int)hitbox.x,(int) hitbox.y, (int)hitbox.width, (int)hitbox.height);
    }
    protected void iniHitbox(float x, float y, float width, float height) {
        hitbox = new Rectangle2D.Float(x,y,width, height);
    }
    public Rectangle2D.Float getHitbox(){
        return hitbox;
    }
}
