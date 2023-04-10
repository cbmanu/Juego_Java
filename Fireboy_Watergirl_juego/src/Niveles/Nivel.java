package Niveles;

import Objetos.Agua;
import Objetos.Gemas;
import Objetos.Lava;
import Objetos.Plataforma;
import Objetos.Puertas;
import Utils.MetodosAyuda;
import java.awt.image.BufferedImage;


public class Nivel {
    //atributos 
    private BufferedImage img;
    private MetodosAyuda ayuda;
    private int[][] nvData;
    //private ArrayList<Gemas> gema;
    private Gemas[] gemas = new Gemas[16];
    private Plataforma[] plataforma = new Plataforma[2];
    private Agua[] agua = new Agua[3];
    private Lava[] lava = new Lava[3];
    private Puertas puertas,puertaAgua;

    //constructor 
    public Nivel(int[][] nvData, BufferedImage img){
        this.nvData = nvData;
        this.img = img;
        crearObjetos();
        crearLava(); //esto esta aca
        crearAgua();
        crearPuertaFuego();
        crearPuertaAgua();
    }
    public Nivel(BufferedImage img){//constructor 
        this.img = img;
        crearObjetos();
    }
    private void crearLava() {
        lava = MetodosAyuda.getLava(img);
    }

    private void crearAgua() {
        agua = MetodosAyuda.getAgua(img);
    }
    
     public void crearObjetos() {
         plataforma=MetodosAyuda.getPlataforma();
         gemas = MetodosAyuda.getGemas(img);
     }
     
     private void crearPuertaFuego() {
         puertas = MetodosAyuda.getPuertaFuego(img);
     }
    private void crearPuertaAgua() {
        puertaAgua = MetodosAyuda.getPuertaFuego(img);
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
        return gemas;
    }
    public Plataforma[] getPlataforma(){
        return plataforma;
    }
    public Lava[] getLava(){
        return lava;
    }
    public Agua[] getAgua(){
        return agua;
    }
    
    public Puertas getPuertaF(){
        return puertas;
    }

    public Puertas getPuertaW() {
        return puertaAgua;
    }
}
