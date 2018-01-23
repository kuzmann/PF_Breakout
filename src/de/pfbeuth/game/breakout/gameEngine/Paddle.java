package de.pfbeuth.game.breakout.gameEngine;
import javafx.scene.image.Image;
import static de.pfbeuth.game.breakout.gameEngine.Breakout.WIDTH;
import static de.pfbeuth.game.breakout.gameEngine.Breakout.HEIGHT;

class Paddle extends AnimatedGameObject {
    private Breakout breakout;  //creates context to Breakout-Class
    private final double PADDLE_INIT_X_POS = 0;
    private final double PADDLE_INIT_Y_POS = HEIGHT*0.40;
    private static final double PADDLE_DIM_X = 200/2;
    private static final double PADDLE_DIM_Y = 12.5;
    private static final double RIGHT_SCREEN_BOUNDARY = WIDTH/2 - PADDLE_DIM_X/2;
    private static final double LEFT_SCREEN_BOUNDARY = -(WIDTH/2 - PADDLE_DIM_X/2);
    /** ------ CONSTRUCTOR ------ */
    protected Paddle (Breakout iPaddle, String SVGdata, double xLocation, double yLocation, Image... sprites){
        super(SVGdata, xLocation, yLocation, sprites);
        breakout = iPaddle;
    }
    @Override
    public void update() {
        setXYPosition();
        setScreenBoundaries();
        translatePaddle();
    }
    private void setXYPosition(){
        if(breakout.getController().isLeft()) {
            positionX -= velocityX;
        }
        if(breakout.getController().isRight()) {
            positionX += velocityX;
        }
    }
    private void setScreenBoundaries(){
        if(positionX >= RIGHT_SCREEN_BOUNDARY){
            positionX = RIGHT_SCREEN_BOUNDARY;
        }
        if(positionX <= LEFT_SCREEN_BOUNDARY){
            positionX = LEFT_SCREEN_BOUNDARY;
        }
    }
    private void translatePaddle() {
        spriteImage.setTranslateX(positionX);
        spriteImage.setTranslateY(positionY);
    }
    void resetState(){
        setVelocityX(15);
        this.positionX = PADDLE_INIT_X_POS;
        this.positionY = PADDLE_INIT_Y_POS;
        spriteImage.setTranslateX(PADDLE_INIT_X_POS);
        spriteImage.setTranslateY(PADDLE_INIT_Y_POS);
    }
}

