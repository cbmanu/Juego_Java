package Niveles;

import Objetos.Gemas;
import Utils.MetodosAyuda;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Nivel {
    //atributos 
    private BufferedImage img;
    private MetodosAyuda ayuda;
    private int[][] nvData;
    //private ArrayList<Gemas> gema;
    private Gemas[] gemas = new Gemas[16];
    
    
    //constructor 
    public Nivel(int[][] nvData, BufferedImage img){
        this.nvData = nvData;
        this.img = img;
        crearGemas();
        
        //gemas[0] = new Gemas(400,300,0);
        //gemas[1] = new Gemas(300,300,1);
    }
    public Nivel(BufferedImage img){//constructor 
        this.img = img;
        crearGemas();
    }
    
     public void crearGemas() {
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
    public Gemas[] getGemas(){
       // System.out.println("getgemas "+gemas);
        return gemas;
    }
}
