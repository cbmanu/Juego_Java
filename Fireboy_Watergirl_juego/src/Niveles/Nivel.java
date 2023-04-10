package Niveles;

import Objetos.Agua;
import Objetos.Gemas;
import Objetos.Lava;
import Utils.MetodosAyuda;
import java.awt.image.BufferedImage;


public class Nivel {
    //atributos 
    private BufferedImage img;
    private int[][] nvData;
    private Gemas[] gemas = new Gemas[16];
    private Agua[] agua = new Agua[3];
    private Lava[] lava = new Lava[3];
    
    
    //constructor 
    public Nivel(int[][] nvData, BufferedImage img){
        this.nvData = nvData;
        this.img = img;
        crearGemas();
        crearLava(); //esto esta aca 
        crearAgua();

    }
    public Nivel(BufferedImage img){//constructor no necesito por ahora 
        this.img = img;
        crearGemas();
    }
    
     public void crearGemas() {
        gemas = MetodosAyuda.getGemas(img);
     }
     
     private void crearLava() {
        lava = MetodosAyuda.getLava(img);
    }
    
    private void crearAgua() {
        agua = MetodosAyuda.getAgua(img);
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
    
    public Lava[] getLava(){
        return lava;
    }
    public Agua[] getAgua(){
        return agua;
    }
    
}
