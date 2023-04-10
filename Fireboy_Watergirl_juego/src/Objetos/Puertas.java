package Objetos;

public class Puertas extends JuegoObjeto{
    //atributos
    
    
    //constructor
    public Puertas(int x, int y, int objType) {
        super(x, y, objType);
        
        iniHitbox(64,64);
        xDrawOffset = 0;
        yDrawOffset = (int)(Juego.Juego.SCALA*64);
        hitbox.y += yDrawOffset;
        
    }
    
    
}
