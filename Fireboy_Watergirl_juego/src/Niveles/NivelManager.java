package Niveles;
import Juego.*;
import static Juego.Juego.TILES_SIZE;
import Utils.CargarGuardar;
import static Utils.CargarGuardar.NIVEL_ATLAS;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class NivelManager {
    //atributos
    private Juego juego;
    private BufferedImage[] spriteNivel;
    private Nivel nivelUno, nivels;
    
    //constructor
    public NivelManager(Juego juego){
        this.juego = juego;
        
       importOutsideSprites();
       nivelUno = new Nivel(CargarGuardar.GetNivelData(), CargarGuardar.GetSpriteAtlas(CargarGuardar.NIVEL_ONE_DATA));//revisar esto
      
    }
    //
    public void cargarOtroNivel(){
        juego.getPlaying().getObjectManager().cargarObjetos(nivelUno);
    }
    
    private void importOutsideSprites() {
        BufferedImage img = CargarGuardar.GetSpriteAtlas(CargarGuardar.NIVEL_ATLAS);//
        
        spriteNivel = new BufferedImage[48]; //10//48
        for(int j = 0; j<4; j++){
            for(int i=0; i<12;i++){
                int index = j*12 + i;
                spriteNivel [index] = img.getSubimage(i*32, j*32, 32, 32);
            }
        }
    }
    
    public void draw(Graphics g){
        
        for(int j=0; j<Juego.TILES_IN_HEIGHT; j++){
            for(int i= 0; i<Juego.TILES_IN_WIDTH; i++){
                int index = nivelUno.getSpriteIndex(i, j);
                g.drawImage(spriteNivel[index], TILES_SIZE*i, TILES_SIZE*j, TILES_SIZE,TILES_SIZE, null);
            }
        }
        //draw was here before the cicle 
    }
    
    public void update(){
    
    }

    public Nivel getNivelReciente(){
        return nivelUno; 
    }
    
}
