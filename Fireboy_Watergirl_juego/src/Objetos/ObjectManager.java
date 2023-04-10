package Objetos;

import Entidades.Jugador;
import Gamestates.Playing;
import Niveles.Nivel;
import Utils.CargarGuardar;
import Utils.Sound;

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
    private BufferedImage plataformaImg;
    private  Plataforma[] plataforma=new Plataforma[2];


    private Gemas[] gemas= new Gemas[16];//
    int puntos =0, punto =0;


    //constructor
    public ObjectManager(Playing playing){
        this.playing = playing;
        cargarImgGemas();
        cargarImgPlataforma();
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
    public void checkPlataformaTouch(Rectangle.Float hitbox) {
        for (Plataforma p : plataforma) {
            if(hitbox.intersects(p.hitbox)){
            }
        }
    }


    public void aplicarEfectoJugador(Gemas p, int selected){//depdende del metodo anteriro este me lleva los puntajes
        if(p.getObjType() == 0&&selected==0){
            puntos += FIRE_GEM_VALOR; //se supone da los puntos del agua
            p.setActive(false);
            System.out.println("puntos gema roja "+puntos);
            new Sound();
        }
        else if(p.getObjType() == 1&&selected==1){
            punto += WATER_GEM_VALOR;
            p.setActive(false);
            System.out.println("puntos gema azul "+punto);
            new Sound();

        }

    }

    public void checkObjetoHit(Rectangle.Float colecciona){//esto sera luego para los botones de los elevadores 

    }

    //
    public void cargarObjetos(Nivel nivelUno){
        gemas = nivelUno.getGemas();
        plataforma = nivelUno.getPlataforma();
    }

    private void cargarImgGemas() {
        BufferedImage gemasSprite = CargarGuardar.GetSpriteAtlas(CargarGuardar.GEMA_ATLAS);
        gemasImg = new BufferedImage[2][5];

        for(int j= 0; j<gemasImg.length;j++){
            for(int i=0;i<gemasImg[j].length;i++){
                gemasImg[j][i] = gemasSprite.getSubimage(16*i, 12*j, 16,12);
            }
        }
    }
    public void cargarImgPlataforma(){
         plataformaImg = CargarGuardar.GetSpriteAtlas(CargarGuardar.PLATAFORMA_P);
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
        drawPuentes(g);
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
    private void drawPuentes(Graphics g){
        g.drawImage(plataformaImg,40,630,plataformaImg.getWidth(),plataformaImg.getHeight(),null);
    }

}
