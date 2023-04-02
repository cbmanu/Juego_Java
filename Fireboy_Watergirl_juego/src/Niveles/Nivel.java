package Niveles;

public class Nivel {
    //atributos 
    private int[][] nvData;
    
    
    //constructor 
    public Nivel(int[][] nvData){
        this.nvData = nvData;
    }
    
    //metodo para obtener la posicion especifica del index 
    public int getSpriteIndex(int x,int y){
        return nvData[y][x];
    }
}
