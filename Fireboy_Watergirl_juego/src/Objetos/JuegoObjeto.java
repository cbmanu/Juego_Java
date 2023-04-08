package Objetos;

import static Utils.Constantes.ANI_SPEED;
import static Utils.Constantes.ConstantesObjeto.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;


public class JuegoObjeto {
    //atributos
    protected int x,y, objType;
    protected Rectangle2D.Float hitbox;
    protected boolean doAnimation, active = true;
    protected int aniTick, aniIndex;
    protected int xDrawOffset, yDrawOffset;
    
    public JuegoObjeto(int x, int y, int objType){
        this.x = x;
        this.y = y;
        this.objType = objType;
    }
    
    protected void actualizarAniTick(){
        aniTick++;
        if(aniTick>=ANI_SPEED){
            aniTick = 0;
            aniIndex ++;
            if(aniIndex>= GetSpriteAmount(objType)){
                aniIndex = 0;
            }
        }
                
    }
    
    public void reiniciar(){
        aniIndex =0;
        aniTick = 0;
        active= true;
        
        doAnimation = true;
    }
    
    protected void iniHitbox(int width, int height) {
        hitbox = new Rectangle2D.Float(x,y,(int)(width* Juego.Juego.SCALA), (int)(height* Juego.Juego.SCALA));
    
    }
    
    public void drawHitbox(Graphics g, int xNvOffset){
        //for th burging hitnbxo
        g.setColor(Color.RED);
        g.drawRect((int)hitbox.x,(int) hitbox.y, (int)hitbox.width, (int)hitbox.height);
    }

    public int getObjType() {
        return objType;
    }

    public void setObjType(int objType) {
        this.objType = objType;
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active){
        this.active = active;
    }

    public int getxDrawOffset() {
        return xDrawOffset;
    }

    public int getyDrawOffset() {
        return yDrawOffset;
    }
    
    public int getAniIndex(){
        return aniIndex;
    }
    
}
