package Objetos;


import java.awt.*;

public class Lava extends JuegoObjeto{
    //atributos
    
    
    //constructor
    public Lava(int x, int y, int objType) {
        super(x, y, objType);
        
        iniHitbox(32,32);

        xDrawOffset = 0;
        yDrawOffset = (int)(Juego.Juego.SCALA*32);
        hitbox.y += yDrawOffset;//iniciarlo correctamente en el fondo del suelo
        
    }
    
}
