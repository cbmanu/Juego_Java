package ui;

import Gamestates.Playing;
import Utils.CargarGuardar;
import java.awt.image.BufferedImage;

public class NivelCompleto {//overllay
    //atributos
    private Playing playing;
    private MenuButtons salir, jugar;
    private BufferedImage img;
    private int bgX,bgY, bgW, bgH; //taman del letrero
    
    
    //constructor
    public  NivelCompleto(Playing playing){
        this.playing = playing;
        iniciarImg();
        iniciarBotones();
    }

    private void iniciarImg() {
        img = CargarGuardar.GetSpriteAtlas(CargarGuardar.COMPLETADO_IMG);
        
        //valores
        bgW = (int)(img.getWidth() * Juego.Juego.SCALA);
        bgH = (int)(img.getHeight() * Juego.Juego.SCALA);
        bgX = (Juego.Juego.JUEGO_WIDTH/2) -(bgW/2);//position
        bgY = (int)(75 * Juego.Juego.SCALA);//position 
    }

    private void iniciarBotones() {
        int salirX = (int)(330*Juego.Juego.SCALA);
        int jugarX = (int)(445 * Juego.Juego.SCALA);
        int y =(int)(195*Juego.Juego.SCALA);
        
    }
}
