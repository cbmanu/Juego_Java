
package Utils;

import Juego.Juego;

public class Constantes {
    
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
