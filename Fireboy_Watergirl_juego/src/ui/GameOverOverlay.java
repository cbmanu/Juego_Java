package ui;

import Gamestates.Gamestate;
import Gamestates.Playing;
import Objetos.ObjectManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOverOverlay {
    //atributos
    private Playing playing;
    
    //constructor
    public GameOverOverlay(Playing playing){//estara en la clase playing
        this.playing = playing;
    }
    
    public void draw(Graphics g){
        g.setColor(new Color(0,0,0,200)); ///negro trasnparente
        g.fillRect(0, 0, Juego.Juego.JUEGO_WIDTH, Juego.Juego.JUEGO_HEIGHT);
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.BOLD, 40));
        g.drawString("GAME OVER", (Juego.Juego.JUEGO_WIDTH/2-100), 300);
        g.drawString("Presione ENTER para volver al menu", (Juego.Juego.JUEGO_WIDTH/2-350), 450);
        g.drawString("Presione ESPACIO para volver a intentar", (Juego.Juego.JUEGO_WIDTH/2-350), 550);

    }
    
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            Gamestate.state = Gamestate.MENU;
            playing.resetAll();
        }if(e.getKeyCode() == KeyEvent.VK_SPACE){
            playing.resetAll();
        }
    }
}
