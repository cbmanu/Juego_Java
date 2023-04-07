package Entidades;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public abstract class Entidad {
   
    protected float x,y; 
    protected int width, height;
    protected Rectangle2D.Float hitbox;
    
    public Entidad(float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    protected void drawHitbox(Graphics g){
        //for th burging hitnbxo
        g.setColor(Color.RED);
        g.drawRect((int)hitbox.x,(int) hitbox.y, (int)hitbox.width, (int)hitbox.height);
    }
    
    protected void iniHitbox(float x, float y, float width, float height) {
        hitbox = new Rectangle2D.Float(x,y,width, height);
    }
    
  /*  protected void actualizarHitbox(){//only one class or the class that extends can use it
        hitbox.x= (int)x;
        hitbox.y= (int)y;
    }*/
    
    public Rectangle2D.Float getHitbox(){
        return hitbox;
    }
}
