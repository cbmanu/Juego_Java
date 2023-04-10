package Utils;

import Objetos.Agua;
import Objetos.Gemas;
import Objetos.Lava;
import Objetos.Plataforma;
import Objetos.Puertas;

import static Utils.Constantes.ConstantesObjeto.*;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class MetodosAyuda {

    //metodos
    public static boolean puedeMover(float x, float y, float width, float height, int[][] nvData){
        
        //puntos de choque, primero se tomaran las esquinas en diagonal izquierda arriba/derecha abajo
        if(!esSolido(x,y,nvData))                       //esquina superior izquierda
            if(!esSolido(x+width, y+height, nvData))   //esquina inferior derecha superior izquierda
                if(!esSolido(x+width, y, nvData))      //esquina superior derecha
                    if(!esSolido(x, y+height, nvData)) //esquina inferior izquierda
                        if(!esSolido(x, y+height/2, nvData))
                            if(!esSolido(x+width, y+height/2, nvData)) //esquina inferior izquierda
                                //if(!esSolido(x, (y+height)/2, nvData))//
                           // if(!esSolido(x+width, (y+height)/2, nvData))//
                                return true;

        return false;
    }
    
    private static boolean esSolido(float x, float y, int[][] nvData){
        int maxWidth = nvData[0].length * Juego.Juego.TILES_SIZE; //not really necessary, usar luego Juego.Juego.JUEGO_WIDTH
        //chequea los limites 
        if(x<0 || x>= maxWidth){ //Juego.Juego.JUEGO_WIDTH
            return true;
        }
        if(y<0 || y>= Juego.Juego.JUEGO_HEIGHT){
            return true;
        }
        
        float xIndex = x/Juego.Juego.TILES_SIZE;
        float yIndex = y/Juego.Juego.TILES_SIZE;
        
        int valor = nvData[(int) yIndex][(int) xIndex];
        
        if(valor >= 48 || valor<0 || valor!=11){
            return true;
        }
        
        return false;
    }
    
    public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xVeloci){
        //current tile the player is on
        int currentTile = (int)(hitbox.x/Juego.Juego.TILES_SIZE);
        
        if(xVeloci >0){
            //right
            int tileXPos = currentTile* Juego.Juego.TILES_SIZE;
            int xOffset = (int)(Juego.Juego.TILES_SIZE -7);//size of the player
            return tileXPos + xOffset -1;
        }else{
            //left
            return currentTile * Juego.Juego.TILES_SIZE;
        }
        
    }
    
    public static float GetEntityYPosUnderAboveF(Rectangle2D.Float hitbox, float airSpeed){
        //current tile the player is on
        int currentTile = (int)(hitbox.y/Juego.Juego.TILES_SIZE);
        if(airSpeed>0){
            //means we are falling 
            return hitbox.y;
        }else{
            //jumpnig
            return currentTile * Juego.Juego.TILES_SIZE;
        }
    }
    
    public static boolean EstaEnSuelo(Rectangle2D.Float hitbox, int[][] nvData){
        //check the pixel below bottonleft and bottonright
        if(!esSolido(hitbox.x, hitbox.y +hitbox.height +1, nvData)){
            if(!esSolido(hitbox.x+hitbox.width, hitbox.y +hitbox.height+1, nvData))
                return false; 
        }
        return true;
    }
//    public static boolean muere(Rectangle2D.Float hitbox, int[][]){
//        if()
//    }
    
    ///
    public static Gemas[] getGemas(BufferedImage img){
        Gemas[] lista = new Gemas[16];
        int contador =0;
      
        for(int j=0; j<img.getHeight(); j++ ){
            for(int i=0;i<img.getWidth();i++){
                //System.out.println(" img H "+img.getHeight()+" img W "+ img.getWidth());
                
                Color color = new Color(img.getRGB(i, j));
                int valor = color.getBlue();
                //System.out.println(" valor1 gema "+valor);
                if(valor == FIRE_GEM || valor == WATER_GEM){
                    lista[contador] = new Gemas(i*Juego.Juego.TILES_SIZE, j*Juego.Juego.TILES_SIZE, valor); 
                    contador++;
                } 
            }}
              return lista;  
    }
    public static Plataforma[] getPlataforma(){
        Plataforma[] list = new Plataforma[2];
        list[0]=new Plataforma(0,440,3);
        return list;
    }

    public static Lava[] getLava(BufferedImage img) {
        Lava[] lista = new Lava[3];
        int contador =0;
      
        for(int j=0; j<img.getHeight(); j++ ){
            for(int i=0;i<img.getWidth();i++){ 
                
                Color color = new Color(img.getRGB(i, j));
                int valor = color.getBlue();
                
                if(valor == LAVA){
                    lista[contador] = new Lava(i*Juego.Juego.TILES_SIZE, 850, valor);
                    contador++;
                } 
            }}
              return lista;
    }

    public static Agua[] getAgua(BufferedImage img) {
        Agua[] lista = new Agua[3];
        int contador =0;
      
        for(int j=0; j<img.getHeight(); j++ ){
            for(int i=0;i<img.getWidth();i++){ 
                
                Color color = new Color(img.getRGB(i, j));
                int valor = color.getBlue();
                if(valor == AGUA){
                    lista[contador] = new Agua(i*Juego.Juego.TILES_SIZE, 850, valor);
                    contador++;
                } 
            }}
              return lista;
    }

    public static Puertas getPuertaFuego(BufferedImage img) {
        Puertas lista = null;//REVISAR ESTO
      
        for(int j=0; j<img.getHeight(); j++ ){
            for(int i=0;i<img.getWidth();i++){ 
                
                Color color = new Color(img.getRGB(i, j));
                int valor = color.getBlue();
                
                if(valor == PUERTA_F){
                    lista = new Puertas(i*Juego.Juego.TILES_SIZE, 850, valor);
                    return lista;
                } 
            }}
              return lista;
    }
}
