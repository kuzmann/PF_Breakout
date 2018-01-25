package de.pfbeuth.game.breakout.gameEngine;
import javafx.scene.image.Image;
import static de.pfbeuth.game.breakout.gameEngine.Breakout.WIDTH;
import static de.pfbeuth.game.breakout.gameEngine.Breakout.HEIGHT;


/*
 * This class defines and control the object paddle
 * this class inherits from the class AnimatedGameObject
 */
class Paddle extends AnimatedGameObject {
    private Breakout breakout;  //creates context to Breakout-Class
    /** Initial position of the paddle */
    private final double PADDLE_INIT_X_POS = 0;
    private final double PADDLE_INIT_Y_POS = HEIGHT*0.40;
    //TODO: Was wird hier festgelegt?
    /**     */
    private static final double PADDLE_DIM_X = 200/2;
    private static final double PADDLE_DIM_Y = 12.5;
    //TODO: Was wird hier genau festgelegt?
    /**    */
    private static final double RIGHT_SCREEN_BOUNDARY = WIDTH/2 - PADDLE_DIM_X/2;
    private static final double LEFT_SCREEN_BOUNDARY = -(WIDTH/2 - PADDLE_DIM_X/2);
    /** ------ CONSTRUCTOR ------ */
    protected Paddle (Breakout iPaddle, String SVGdata, double xLocation, double yLocation, Image... sprites){
        super(SVGdata, xLocation, yLocation, sprites);
        breakout = iPaddle;
    }

    /** start class methods*/
    @Override
    public void update() {
        setXYPosition();
        setScreenBoundaries();
        translatePaddle();
    }

    /** get control information to navigate the paddle with arrow keys*/
    private void setXYPosition(){
        //check, if user pressed left arrow key
        if(breakout.getController().isLeft()) {
            positionX -= velocityX;
        }
        //check, if user pressed right arrow key
        if(breakout.getController().isRight()) {
            positionX += velocityX;
        }
    }
    //TODO: Tommy, kannst du diese Methode beschreiben?
    /**  */
    private void setScreenBoundaries(){
        if(positionX >= RIGHT_SCREEN_BOUNDARY){
            positionX = RIGHT_SCREEN_BOUNDARY;
        }
        if(positionX <= LEFT_SCREEN_BOUNDARY){
            positionX = LEFT_SCREEN_BOUNDARY;
        }
    }
    //TODO: Tommy, kannst du diese Methode beschreiben?
    /**  */
    private void translatePaddle() {
        spriteImage.setTranslateX(positionX);
        spriteImage.setTranslateY(positionY);
    }

    //TODO: Tommy, bitte den Kommentar überprüfen
    /** if a new game or level starts, paddle will set to the initial position */
    void resetState(){
        setVelocityX(15);
        this.positionX = PADDLE_INIT_X_POS;
        this.positionY = PADDLE_INIT_Y_POS;
        spriteImage.setTranslateX(PADDLE_INIT_X_POS);
        spriteImage.setTranslateY(PADDLE_INIT_Y_POS);
    }
    /** ------ SETTER ------ */
    public void setPaddleToBack(){
        spriteImage.toBack();
    }
    public void setPaddleToFrotn(){
        spriteImage.toFront();
    }
}

