package Niveles;

import Objetos.Gemas;
import Utils.MetodosAyuda;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Nivel {
    //atributos 
    private BufferedImage img;
    private int[][] nvData;
    private ArrayList<Gemas> gemas;
    
    
    //constructor 
    public Nivel(int[][] nvData){
        this.nvData = nvData;
        
    }
    public Nivel(BufferedImage img){
        this.img = img;
        crearGemas();
    }
    
     private void crearGemas() {
        gemas = MetodosAyuda.getGemas(img);
     }
    
    //metodo para obtener la posicion especifica del index 
    public int getSpriteIndex(int x,int y){
        return nvData[y][x];
    }
    
    public int[][] getNivelData(){
        return nvData;
    }

   //getters setters
    public ArrayList<Gemas> getGemas(){
        return gemas;
    }
}
