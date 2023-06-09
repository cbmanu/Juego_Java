
package Utils;

import Juego.Juego;

public class Constantes {
        //atributos
        public static final int ANI_SPEED = 25;
        
        //para las gemas y algunos objetos 
    public static class ConstantesObjeto{
        public static final int WATER_GEM =0;
        public static final int FIRE_GEM =1;
        public static final int AGUA =3;
        public static final int LAVA =4;
        
        //puertas
        public static final int PUERTA_F =5;
        public static final int PUERTA_A =6;
 
        public static final int WATER_GEM_VALOR = 1;
        public static final int FIRE_GEM_VALOR = 1;
        
        public static final int GEM_WIDTH_DEFAULT = 16;
        public static final int GEM_HEIGHT_DEFAULT = 24;
        public static final int GEM_WIDTH =(int)(Juego.SCALA*GEM_WIDTH_DEFAULT);
        public static final int GEM_HEIGHT =(int)(Juego.SCALA*GEM_HEIGHT_DEFAULT);
        
        public static final int LAVA_WIDTH_DEFAULT = 32;
        public static final int LAVA_HEIGHT_DEFAULT = 32;
        public static final int LAVA_WIDTH =(int)(Juego.SCALA*LAVA_WIDTH_DEFAULT);
        public static final int LAVA_HEIGHT =(int)(Juego.SCALA*LAVA_HEIGHT_DEFAULT);
        
        public static final int AGUA_WIDTH_DEFAULT = 32;
        public static final int AGUA_HEIGHT_DEFAULT = 32;
        public static final int AGUA_WIDTH =(int)(Juego.SCALA*AGUA_WIDTH_DEFAULT);
        public static final int AGUA_HEIGHT =(int)(Juego.SCALA*AGUA_HEIGHT_DEFAULT);
        
        public static final int PUERTAF_WIDTH_DEFAULT = 64;
        public static final int PUERTAF_HEIGHT_DEFAULT = 64;
        public static final int PUERTAF_WIDTH =(int)(Juego.SCALA*PUERTAF_WIDTH_DEFAULT);
        public static final int PUERTAF_HEIGHT =(int)(Juego.SCALA*PUERTAF_HEIGHT_DEFAULT);
        
        public static final int PUERTAA_WIDTH_DEFAULT = 64;
        public static final int PUERTAA_HEIGHT_DEFAULT = 64;
        public static final int PUERTAA_WIDTH =(int)(Juego.SCALA*PUERTAA_WIDTH_DEFAULT);
        public static final int PUERTAA_HEIGHT =(int)(Juego.SCALA*PUERTAA_HEIGHT_DEFAULT);
        
        public static int GetSpriteAmount(int object_type){
            switch(object_type){
                case FIRE_GEM:
                case WATER_GEM:
                    return 5;
                
            }
            
            return 1;
        }
    }
    
    //para las imagenes que acompanan el fondo
    public static class Enviroment{
        public static final int OBJ_DELFONDO_WIDTH_DEFAULT =448;
        public static final int OBJ_DELFONDO_HEIGHT_DEFAULT =101;
        public static final int OBJ2_DELFONDO_WIDTH_DEFAULT =74;
        public static final int OBJ2_DELFONDO_HEIGHT_DEFAULT =24;
        
        //actual size
        public static final int OBJ_DELFONDO_WIDTH =(int)(OBJ_DELFONDO_WIDTH_DEFAULT *Juego.SCALA);
        public static final int OBJ_DELFONDO_HEIGHT =(int)(OBJ_DELFONDO_HEIGHT_DEFAULT *Juego.SCALA);
        
        public static final int OBJ2_DELFONDO_WIDTH =(int)(OBJ2_DELFONDO_WIDTH_DEFAULT *Juego.SCALA);
        public static final int OBJ2_DELFONDO_HEIGHT =(int)(OBJ2_DELFONDO_HEIGHT_DEFAULT *Juego.SCALA);
    }
    
    public static class UI{
        public static class MenuButtons{
            public static final int B_WIDTH_DEFAULT=260;
            public static final int B_HEIGHT_DEFAULT=167;
            public static final int B_WIDTH=(int)(B_WIDTH_DEFAULT + Juego.SCALA);
            public static final int B_HEIGHT=(int)(B_HEIGHT_DEFAULT + Juego.SCALA);

        }
        public static class Seleccion{
            public static final int WIDTH_DEFAULT=300;
            public static final int HEIGHT_DEFAULT=350;
            public static final int WIDTH=(int)(WIDTH_DEFAULT + Juego.SCALA);
            public static final int HEIGHT=(int)(HEIGHT_DEFAULT + Juego.SCALA);


        }
    }
    
    public static class Direcciones{
        public static final int LEFT =0;
        public static final int UP =1;
        public static final int RIGHT =2;
        public static final int DOWN =3;
        
    }
    
    public static class ConstantJugador{
        public static final int CORRER = 0; 
        public static final int SALTAR = 1;
        public static final int CORRER2 = 2;
        public static final int DYE = 3;
        public static final int FIREPLAYER = 4;
        public static final int PUSH =6;
        
        
        
        public static int GetCantidadSprites(int action_jugador){
            
            switch(action_jugador){
                //we will return how many sprites does this animation contains
                case CORRER:
                case SALTAR:    
                case CORRER2:
                    return 3;
                case DYE : 
                    return 4;
                case FIREPLAYER:
                    return 3;
                case PUSH:
                    return 3;
                default:
                    return 1;
            }
        
        }
    }
}
