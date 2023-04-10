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
    private BufferedImage lavaImg, aguaImg;
    private Gemas[] gemas= new Gemas[16];//
    private Agua[] agua = new Agua[3];
    private Lava[] lava = new Lava[3];//
    int puntos =0, punto =0;
    
    
    //constructor
    public ObjectManager(Playing playing){
        this.playing = playing;
        cargarImg();

    }
    
    //metodo para cuando el jugador toque la lava
    public void checkLavaTouch(Jugador jugador){
        for(Lava l: lava){
            if(l.getHitbox().intersects(jugador.getHitbox())){
                System.out.println("LAVA LAVA"); //no esta entrando aqui no esta creando la colision 
                jugador.muerte();
            }
        }
    }
    
    //metodo para cuando el jugador toque el agua 
    public void checkAguaTouch(Jugador p){
        for(Agua a: agua){
            if(a.getHitbox().intersects(p.getHitbox())){
                p.muerte();
            }
        }
    }

    public void checkObjetoTouch(Rectangle2D.Float hitbox){ //detecta si ha chocado, esta conectado con aplicarEfectoJugador
        for(Gemas p : gemas){
            if(p.isActive()){
                if(hitbox.intersects(p.getHitbox())){
                    //p.setActive(false);
                    aplicarEfectoJugador(p);

                }
            }
        }

    }

    public void aplicarEfectoJugador(Gemas p ){//depdende del metodo anteriro este me lleva los puntajes 
        if(p.getObjType() == WATER_GEM){
            puntos += WATER_GEM_VALOR; //se supone da los puntos del agua
            p.setActive(false);
            System.out.println("puntos gema roja "+puntos);
        }
        else if(p.getObjType() == FIRE_GEM){
            p.setActive(true);
            punto += FIRE_GEM_VALOR;
            System.out.println("puntos gema azul "+puntos);
            //playing.getJugador().puntosGemas(WATER_GEM_VALOR); //esta en jugador el metodo
        }

    }

    public void checkObjetoHit(Rectangle.Float colecciona){//esto sera luego para los botones de los elevadores 

    }

    //
    public void cargarObjetos(Nivel nivelUno){
        gemas = nivelUno.getGemas();
        lava = nivelUno.getLava();
        agua = nivelUno.getAgua();
    }

    private void cargarImg() {
        BufferedImage gemasSprite = CargarGuardar.GetSpriteAtlas(CargarGuardar.GEMA_ATLAS);
        gemasImg = new BufferedImage[2][5];

        for(int j= 0; j<gemasImg.length;j++){
            for(int i=0;i<gemasImg[j].length;i++){
                gemasImg[j][i] = gemasSprite.getSubimage(16*i, 12*j, 16,12);
            }
        }
        
        //imagenes de la lava
        lavaImg = CargarGuardar.GetSpriteAtlas(CargarGuardar.LAVA_ATLAS);
        //imagenes de agua
        aguaImg = CargarGuardar.GetSpriteAtlas(CargarGuardar.AGUA_ATLAS);
    }

    public void update(){
        for(Gemas a : gemas){
            //System.out.println("valor de a "+a);
            if(a.isActive())
                a.update();
        }
    }

    public void draw(Graphics g, int xnvOffset){
        drawGemas(g,xnvOffset);
        drawLava(g,xnvOffset);
        drawAgua(g,xnvOffset);
    }

    private void drawLava(Graphics g, int xnvOffset) {
        for(Lava l: lava){
            //System.out.println("imagen lava " +xnvOffset);
            g.drawImage(lavaImg, (int)(l.getHitbox().x - xnvOffset), 
                        (int)(l.getHitbox().y - l.getyDrawOffset()), LAVA_WIDTH, LAVA_HEIGHT, null);}
    }
    
    private void drawAgua(Graphics g, int xnvOffset){
        for(Agua a: agua){
            //System.out.println("imagen lava " +xnvOffset);
            g.drawImage(aguaImg, (int)(a.getHitbox().x - xnvOffset), 
                        (int)(a.getHitbox().y - a.getyDrawOffset()), AGUA_WIDTH, AGUA_HEIGHT, null);}
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
