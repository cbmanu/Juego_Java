package Objetos;

public class ContenedorJuego extends JuegoObjeto{
    
    //constructor
    public ContenedorJuego(int x, int y, int objType) {
        super(x, y, objType);
        
    }
    
    //esta clase es para las cajas que se deben arrastrar
    //no esta terminada observar esto para los puentes elevadores
    public void update(){
        if(doAnimation){
            actualizarAniTick();
        }
    }
    
}
