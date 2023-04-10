package Objetos;

public class Agua extends JuegoObjeto{
    //atributos
    
    //constructor
    public Agua(int x, int y, int objType) {
        super(x, y, objType);
        
        iniHitbox(32,32);
        xDrawOffset = 0;
        yDrawOffset = (int)(Juego.Juego.SCALA*32);
        hitbox.y += yDrawOffset;//iniciarlo correctamente en el fondo del suelo
    }
    
}
