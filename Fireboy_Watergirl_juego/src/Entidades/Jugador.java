package Entidades;
import Gamestates.Playing;
import Utils.CargarGuardar;
import static Utils.MetodosAyuda.*;
import static Utils.Constantes.ConstantJugador.*;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Jugador extends Entidad{
    //atributos
    private Playing playing;
    private BufferedImage[][] animaciones;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int jugadorAction = CORRER;
    private boolean movimiento = false, movback = false;
    private boolean left, up, right,down, jump;
    private float playerSpeed = 2.0f;
    private int[][] nvData;
    private float  xDrawOffset = 21 * Juego.Juego.SCALA;
    private float yDrawOffset = 4*Juego.Juego.SCALA;

    //atributos saltar /gravedad
    private float airSpeed = 0f;
    private float gravedad = 0.04f * Juego.Juego.SCALA; //the lower value the higher the jump
    private float jumpSpeed = -2.25f* Juego.Juego.SCALA;
    private float fallSpeedAfterColision=0.5f * Juego.Juego.SCALA;
    private boolean aire = false;
    int puntos =0;
    
    //variables para vivir
    private int currentHealth =1;
    
    //constructor
    public Jugador(float x, float y, int width, int height, int selected,Playing playing) {
        super(x, y,width, height);
        this.playing = playing;//
        this.nivelVida =1;
        cargaAnimaciones(selected);
        iniHitbox(x, y,38*Juego.Juego.SCALA, 50*Juego.Juego.SCALA);
    }

    public void update(){
        if(currentHealth == 0){
            playing.setGameOver(true);
            return;
        }
   
        actualizarPosicion();
        if(movimiento){//esto es para el check del object manager
            checkObjetoTouched(); //esto esta aqui
            checkLavaTouched();
            checkAguaTouched();
        }

        actualizarAniTick();
        setAnimacion();
    }

    //para la colicion de gemas que viene del objectmanager
    public void checkObjetoTouched(){
        playing.checkGemaTouched(hitbox);//esto viene del playing
    }
    
    private void checkLavaTouched() {//cuando toca la lava
        playing.checkLavaTouched(this); //esta en playing al final
    }
    
    private void checkAguaTouched() {
        playing.checkAguaTouched(this);
    }

    public void render(Graphics g) {
        g.drawImage(animaciones[jugadorAction][aniIndex], (int) (hitbox.x - (hitbox.getWidth() * 0.8)),
                (int) (hitbox.y - (hitbox.getHeight() * 0.45)), (int) hitbox.getWidth() * 2, (int) (hitbox.getHeight() * 1.6), null); // 128//w h 90
        //drawHitbox(g);
    }




    private void actualizarAniTick() {
        aniTick++;
        if (aniTick>=aniSpeed){
            aniTick = 0;
            aniIndex++;

            if (aniIndex>= GetCantidadSprites(jugadorAction)){
                aniIndex = 0;
            }
        }
    }


    private void setAnimacion() {

        int startAnimacion = jugadorAction;

        if(movimiento){
            jugadorAction = CORRER;

            if(aire){
                if (airSpeed<0)
                    jugadorAction = SALTAR;
            }else if(left){
                jugadorAction = CORRER2;
            }

        }
        else{
            jugadorAction = FIREPLAYER;
        }

        if(startAnimacion != jugadorAction){
            reiniciarAniTick();
        }
    }

    private void reiniciarAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void actualizarPosicion() {
        movimiento = false;

        if (jump)
            jump();
        if(!left && !right && !aire)
            return;

        float xVeloci = 0;
        if(left && !right)
            xVeloci -=playerSpeed;
        if(right && !left)
            xVeloci += playerSpeed;

        if(!aire){
            if(!EstaEnSuelo(hitbox, nvData)){
                aire = true;
            }
        }

        if(aire){
            if(puedeMover(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, nvData)){
                hitbox.y += airSpeed;
                airSpeed += gravedad;
                updateXPos(xVeloci);
            }else{
                //if we cannot move up or down
                hitbox.y = GetEntityYPosUnderAboveF(hitbox, airSpeed);
                if(airSpeed>0)
                    resetAire();
                else
                    airSpeed = fallSpeedAfterColision;
                updateXPos(xVeloci);
            }

        }else
            updateXPos(xVeloci);

        movimiento = true;
    }

    private void jump() {
        if(aire)
            return;
        aire = true;
        airSpeed = jumpSpeed;
    }

    private void resetAire() {
        aire = false;
        airSpeed=0;
    }
    
    //para vivir 
    public void changeHealth(int valor){
        currentHealth = valor;
        
        if(currentHealth == 0){
            //gameOver();
        }
    }

    private void updateXPos(float xVeloci) {
        if(puedeMover(hitbox.x+xVeloci,hitbox.y, hitbox.width, hitbox.height, nvData)){
            hitbox.x += xVeloci;
        }else{
            hitbox.x = GetEntityXPosNextToWall(hitbox,xVeloci);
        }

       }
    
    
    //para las gemas en objectmanager
    public void puntosGemas(int valor) {

        puntos +=valor;

        //System.out.println("recolecto gema azul " + puntos);

    }
    
    public void muerte() {//esto esta en el object manager
        currentHealth =0;
    }

    private void cargaAnimaciones(int selected) {

        if (selected==0) {
            BufferedImage img = CargarGuardar.GetSpriteAtlas(CargarGuardar.FIREBOY_ATLAS);

            animaciones = new BufferedImage[5][4];//as big as the sprites images have
            for (int j = 0; j<animaciones.length; j++){
                for (int i =0; i<animaciones[j].length; i++){
                    animaciones[j][i] = img.getSubimage(i*256, j*256, 220, 220);
                }
            }
        }else {
            BufferedImage img = CargarGuardar.GetSpriteAtlas(CargarGuardar.WATERGIRL_ATLAS);

            animaciones = new BufferedImage[5][4];//as big as the sprites images have
            for (int j = 0; j<animaciones.length; j++){
                for (int i =0; i<animaciones[j].length; i++){
                    animaciones[j][i] = img.getSubimage(i*256, j*256, 220, 220);
                }
            }
        }
    }

    public void loadNvlData(int[][] nvData){
        this.nvData = nvData;
        if(EstaEnSuelo(hitbox, nvData))
            aire =  true;
    }

    public void resetDirBoolean(){
        left = false;
        right = false;
        up = false;
        down = false;
    }
    //prueba
    public void setMovback(boolean movback){
        this.movback = movback;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setJump(boolean jump){
        this.jump =jump;
    }

    

    

    


}
