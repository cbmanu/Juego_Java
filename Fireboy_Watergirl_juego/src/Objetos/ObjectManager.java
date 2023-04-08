package Objetos;

import Gamestates.Playing;
import Niveles.Nivel;
import Utils.CargarGuardar;
import static Utils.Constantes.ConstantesObjeto.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ObjectManager {
    //atributos
    private Playing playing;
    private BufferedImage[][] gemasImg;
    private Gemas[] gemas= new Gemas[2];//
    
    
    //constructor
    public ObjectManager(Playing playing){
        this.playing = playing;
        cargarImg();
        
        gemas[0] = new Gemas(400,300,0);
        gemas[1] = new Gemas(300,300,1);
       
        //gemas.add(new Gemas(400,300,1));
    }
    
    private void cargarImg() {
        BufferedImage gemasSprite = CargarGuardar.GetSpriteAtlas(CargarGuardar.GEMA_ATLAS);
        gemasImg = new BufferedImage[2][5];
        
        for(int j= 0; j<gemasImg.length;j++){
            for(int i=0;i<gemasImg[j].length;i++){
                gemasImg[j][i] = gemasSprite.getSubimage(16*i, 12*j, 16,12);
            }
        }
    }
    
    public void update(){
        for(Gemas a : gemas){
            if(a.isActive())
                a.update();
        }
    }
    
    public void draw(Graphics g, int xnvOffset){
        drawGemas(g,xnvOffset);
    }

    
    
    
    private void drawGemas(Graphics g, int xnvOffset) {
        for(Gemas a : gemas){
            if(a.isActive()){
                int type =0;
                    if(a.getObjType() == FIRE_GEM)
                        type =1;
                    
                    g.drawImage(gemasImg[type][a.getAniIndex()], 
                           (int)(a.getHitbox().x-a.getxDrawOffset()-xnvOffset), 
                            (int)(a.getHitbox().y -a.getyDrawOffset()), 
                            GEM_WIDTH, 
                            GEM_HEIGHT, null);
                        }
            
            
        }
    }
    
}
