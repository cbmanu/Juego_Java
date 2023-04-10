package Objetos;

import Entidades.Jugador;
import Gamestates.Playing;
import Niveles.Nivel;
import Utils.CargarGuardar;
import static Utils.Constantes.ConstantesObjeto.*;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ObjectManager {
    //atributos
    private Playing playing;
    private BufferedImage[][] gemasImg;
    private Gemas[] gemas= new Gemas[16];//
    int puntos =0, punto =0;


    //constructor
    public ObjectManager(Playing playing){
        this.playing = playing;
        cargarImg();
    }

    public void checkObjetoTouch(Rectangle.Float hitbox,int selected){ //detecta si ha chocado, esta conectado con aplicarEfectoJugador
        for(Gemas p : gemas){
            if(p.isActive()){
                if(hitbox.intersects(p.getHitbox())){
                    //p.setActive(false);
                    aplicarEfectoJugador(p,selected);

                }
            }
        }

    }

    public void aplicarEfectoJugador(Gemas p, int selected){//depdende del metodo anteriro este me lleva los puntajes

        if(p.getObjType() == 0&&selected==0){
            puntos += FIRE_GEM_VALOR; //se supone da los puntos del agua
            p.setActive(false);
            System.out.println("puntos gema roja "+puntos);
        }
        else if(p.getObjType() == 1&&selected==1){
            punto += WATER_GEM_VALOR;
            p.setActive(false);
            System.out.println("puntos gema azul "+punto);

        }

    }

    public void checkObjetoHit(Rectangle.Float colecciona){//esto sera luego para los botones de los elevadores 

    }

    //
    public void cargarObjetos(Nivel nivelUno){
        gemas = nivelUno.getGemas();
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
        //System.out.println("gemas " +gemas);

        for(Gemas a : gemas){
            //System.out.println("valor de a "+a);
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
