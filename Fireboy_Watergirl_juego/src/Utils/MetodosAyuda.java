package Utils;

import java.awt.geom.Rectangle2D;

public class MetodosAyuda {
    
    //metodos 
    public static boolean puedeMover(float x, float y, float width, float height, int[][] nvData){
        
        //puntos de choque, primero se tomaran las esquinas en diagonal izquierda arriba/derecha abajo
        if(!esSolido(x,y,nvData))                       //esquina superior izquierda
            if(!esSolido(x+width, y+height, nvData))   //esquina inferior derecha superior izquierda
                if(!esSolido(x+width, y, nvData))      //esquina superior derecha
                    if(!esSolido(x, y+height, nvData)) //esquina inferior izquierda
                        //if(!esSolido(x, (y+height)/2, nvData))//
                           // if(!esSolido(x+width, (y+height)/2, nvData))//
                                return true;
        
        return false;
    }
    
    private static boolean esSolido(float x, float y, int[][] nvData){
        //chequea los limites 
        if(x<0 || x>= Juego.Juego.JUEGO_WIDTH){
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
}