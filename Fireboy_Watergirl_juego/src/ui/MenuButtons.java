package ui;

import Gamestates.Gamestate;
import Gamestates.State;
import Utils.CargarGuardar;

import java.awt.image.BufferedImage;

public class MenuButtons {
    private int xPos;
    private int yPos;
    private int rowIndex;
    private BufferedImage[] imgs;
    private Gamestate state;
    MenuButtons(int xPos, int yPos,int rowIndex, Gamestate state){
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        loadImgs();
    }

    private void loadImgs() {
        imgs = new BufferedImage[2];
        BufferedImage temp = CargarGuardar.GetSpriteAtlas(CargarGuardar.FIREBOY_IMAGE);
        
    }

}
