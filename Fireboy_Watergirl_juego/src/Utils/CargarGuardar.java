package Utils;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import Juego.*;
import java.awt.Color;

public class CargarGuardar {
    //atributos
    public static final String JUGADOR_ATLAS = "FireSprites.png";
    public static final String NIVEL_ATLAS = "outside_sprites.png";
    public static final String NIVEL_ONE_DATA = "level_o_data.png";
    public static final String FIREBOY_IMAGE="fireboy.jpg";
    public static final String WATERGIRL_IMAGE="watergirl.jpg";
    public static final String PRESENTATION_IMAGE="fireboy-watergirl.jpg";


    //metodo trycatch 
    public static BufferedImage GetSpriteAtlas(String nombreArch){
        BufferedImage img = null;
         InputStream is = CargarGuardar.class.getResourceAsStream("res/" + nombreArch);
       
        try {
           img = ImageIO.read(is); 
        }catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try{
                is.close();
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }    
        return img;    
    }
    
    //dibujo/diseño del nivel
    public static int[][] GetNivelData(){
        int[][] nvData= new int[Juego.TILES_IN_HEIGHT][Juego.TILES_IN_WIDTH];
        BufferedImage img = GetSpriteAtlas(NIVEL_ONE_DATA);
        
        for(int j = 0;j<img.getHeight(); j++)
            for(int i =0; i<img.getWidth(); i++){
                
                Color color = new Color(img.getRGB(i, j));
                int valor = color.getRed();
                
                if(valor >= 48)
                    valor = 0;
                
                nvData[j][i] =  valor;
            }
        return nvData;
    }
    
    
}
