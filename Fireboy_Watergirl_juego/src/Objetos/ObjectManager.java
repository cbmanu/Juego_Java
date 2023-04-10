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

public class ObjectManager {
    //atributos
    private Playing playing;
    private BufferedImage[][] gemasImg;
    private BufferedImage plataformaImg;
    private Plataforma[] plataforma=new Plataforma[2];
    private Gemas[] gemas= new Gemas[16];//
    private Agua[] agua = new Agua[3];
    private Lava[] lava = new Lava[3];
    private BufferedImage lavaImg, aguaImg;
    public int puntos =0, punto =0;


    //constructor
    public ObjectManager(Playing playing){
        this.playing = playing;
        cargarImg();
    }



//    public void checkObjetoHit(Rectangle.Float colecciona){//esto sera luego para los botones de los elevadores
//
//    }

    //
    public void cargarObjetos(Nivel nivelUno){
        gemas = nivelUno.getGemas();
        plataforma=nivelUno.getPlataforma();
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
        plataformaImg=CargarGuardar.GetSpriteAtlas(CargarGuardar.PLATAFORMA_P);
        lavaImg = CargarGuardar.GetSpriteAtlas(CargarGuardar.LAVA_ATLAS);
        //imagenes de agua
        aguaImg = CargarGuardar.GetSpriteAtlas(CargarGuardar.AGUA_ATLAS);
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
        drawPlataforma(g);
        drawLava(g,xnvOffset);
        drawAgua(g,xnvOffset);
    }
    private void drawLava(Graphics g, int xnvOffset) {
        for(Lava l: lava){
            //System.out.println("imagen lava " +xnvOffset);
            g.drawImage(lavaImg, (int)(l.getHitbox().x - xnvOffset),
                    (int)(l.getHitbox().y ), LAVA_WIDTH, LAVA_HEIGHT, null);}
    }

    private void drawAgua(Graphics g, int xnvOffset){
        for(Agua a: agua){
            //System.out.println("imagen lava " +xnvOffset);
            g.drawImage(aguaImg, (int)(a.getHitbox().x - xnvOffset),
                    (int)(a.getHitbox().y ), AGUA_WIDTH, AGUA_HEIGHT, null);}
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

    private void drawPlataforma(Graphics g) {
        for(Plataforma  p: plataforma){
            g.drawImage(plataformaImg,0,440,96,12,null);
        }
    }


    public void checkLavaTouch(Rectangle.Float hitbox,int selected,Jugador jugador){
        for(Lava l: lava){
            if(selected==1){
                if(hitbox.intersects(l.getHitbox())){
                    System.out.println("LAVA LAVA"); //no esta entrando aqui no esta creando la colision
                    jugador.muerte();
                }
            }
        }
    }

    //metodo para cuando el jugador toque el agua
    public void checkAguaTouch(Rectangle.Float hitbox,int selected,Jugador jugador){
        if(selected==0){
        for(Agua a: agua){
                if(hitbox.intersects(a.getHitbox())){
                    System.out.println("AGUA AGUA"); //no esta entrando aqui no esta creando la colision
                    jugador.muerte();}
            }
        }
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

    public void aplicarEfectoJugador(Gemas p ,int selected){//depdende del metodo anteriro este me lleva los puntajes
        if(p.getObjType() == 0&& selected==0){
            new Sound();
            p.setActive(false);
            puntos += FIRE_GEM_VALOR; //se supone da los puntos del agua
            System.out.println("puntos gema roja "+puntos);
        }
        else if(p.getObjType() == 1&& selected==1){
            new Sound();
            //esto lo agregue yo para que dibuje la gema de fuego
            p.setActive(false);
            punto += WATER_GEM_VALOR;
            System.out.println("puntos gema azul "+punto);
            //playing.getJugador().puntosGemas(WATER_GEM_VALOR); //esta en jugador el metodo
        }

    }

    public void checkPlataformaTouch(Rectangle2D.Float hitbox) {
    }


}
