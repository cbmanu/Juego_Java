package Objetos;

public class Plataforma extends JuegoObjeto{
    public Plataforma(int x, int y, int objType) {
        super(x, y, objType);
        doAnimation = true;
        iniHitbox(96,12);
    }

}
