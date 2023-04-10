package ui;

import Gamestates.Gamestate;
import Gamestates.Playing;
import java.awt.Color;
import java.awt.Graphics;
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
        g.drawString("GAME OVER", (Juego.Juego.JUEGO_WIDTH/2), 300);
        g.drawString("Presione ENTER para volver al inicio", (Juego.Juego.JUEGO_WIDTH/2), 600);
    }
    
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            playing.resetAll();
            Gamestate.state = Gamestate.MENU;
        }
    }
}
