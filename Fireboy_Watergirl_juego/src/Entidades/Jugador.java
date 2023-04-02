package Entidades;
import Gamestates.Menu;
import Gamestates.Playing;
import Utils.CargarGuardar;
import static Utils.Constantes.ConstantJugador.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Jugador extends Entidad{
    //atributos
    private BufferedImage[][] animaciones;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int jugadorAction = CORRER;
   // private int jugadorDireccion = -1;
    private boolean movimiento = false, movback = false;
    private boolean left, up, right,down;
    private float playerSpeed = 2.0f;
    
    //constructor
    public Jugador(float x, float y) {
        super(x, y);
        cargaAnimaciones();
    }
    
    public void update(){
        actualizarPosicion();
        actualizarAniTick();
        setAnimacion();
    }
    
    public void render(Graphics g){
        g.drawImage(animaciones[jugadorAction][aniIndex], (int)x, (int) y,90,90, null); //128

    }
    
  
    
    
     private void actualizarAniTick() {
        aniTick++;
        if (aniTick>=aniSpeed){
            aniTick = 0;
            aniIndex++;
            
            if (aniIndex>= GetCantidadSprites(jugadorAction)){
                aniIndex = 0;
            }
        }
    }
    
    
    private void setAnimacion() {
        
        int startAnimacion = jugadorAction;
        
        if(movimiento){
            jugadorAction = CORRER;
            
            if(up){
                jugadorAction = SALTAR;
            }else if(left){
                jugadorAction = CORRER2;
            }
            
        }
        else{
            jugadorAction = FIREPLAYER;
        }
        
        if(startAnimacion != jugadorAction){
            reiniciarAniTick();
        }
    }
    
    private void reiniciarAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }
    
    private void actualizarPosicion() {
        movimiento = false;

        if(left && !right){
            x-=playerSpeed;
            movimiento = true;
        }
        else if(right && !left){
            x+= playerSpeed;
            movimiento = true;
        }
        
        if (up && !down){
            y-=playerSpeed;
            movimiento = true;
        }
        else if (down && !up){
            y+=playerSpeed;
            movimiento = true;
        }
    }
    
    
    private void cargaAnimaciones() {

         BufferedImage img = CargarGuardar.GetSpriteAtlas(CargarGuardar.JUGADOR_ATLAS);
            
           animaciones = new BufferedImage[5][4];//as big as the sprites images have 
            for (int j = 0; j<animaciones.length; j++){ 
                for (int i =0; i<animaciones[j].length; i++){
                    animaciones[j][i] = img.getSubimage(i*256, j*256, 220, 220); 
        }
       }
     
    }
    
    public void resetDirBoolean(){
        left = false;
        right = false;
        up = false;
        down = false;
    }
     //prueba
    public void setMovback(boolean movback){
        this.movback = movback;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }   
    
}
