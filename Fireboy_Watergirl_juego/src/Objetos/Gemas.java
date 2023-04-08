package Objetos;

import java.awt.Graphics;

public class Gemas extends JuegoObjeto{
    
    //constructor
    public Gemas(int x, int y, int objType) {
        super(x, y, objType);
        doAnimation = true;
        iniHitbox(16,12);
        xDrawOffset = (int)(0*Juego.Juego.SCALA);
        yDrawOffset = (int)(0*Juego.Juego.SCALA);
    }
    
    public void update(){
        actualizarAniTick();
    }
    
}
