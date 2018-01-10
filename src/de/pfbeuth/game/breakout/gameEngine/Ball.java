package de.pfbeuth.game.breakout.gameEngine;
import javafx.scene.image.Image;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import static de.pfbeuth.game.breakout.gameEngine.Breakout.HEIGHT;
import static de.pfbeuth.game.breakout.gameEngine.Breakout.WIDTH;

class Ball extends AnimatedGameObject {
    private Breakout breakout;  //creates context to Breakout-Class
    private final double BALL_INIT_X_POS = 0;
    private final double BALL_INIT_Y_POS = HEIGHT*0.38;
    private static final double BALL_RADIUS = 50/4; //TODO get rid of magic number; 50 = size of ball.png in px
    private static final double RIGHT_SCREEN_BOUNDARY = WIDTH/2 - BALL_RADIUS;
    private static final double LEFT_SCREEN_BOUNDARY = -(WIDTH/2 - BALL_RADIUS);
    private static final double BOTTOM_SCREEN_BOUNDARY = +(HEIGHT/2 - BALL_RADIUS);
    private static final double TOP_SCREEN_BOUNDARY = -(HEIGHT/2 - BALL_RADIUS);

    protected Ball(Breakout iBall, String SVGdata, double xLocation, double yLocation, Image... sprites) {
        super(SVGdata, xLocation, yLocation, sprites);
        breakout = iBall;
   }

    @Override
    void update(){
        setXYPosition(-2);
        setScreenBoundaries();
        translateBall();
        checkCollision();
    }
    private void checkCollision(){
        for (int i = 0; i < breakout.getSpriteManager().getCurrentObjects().size(); i++) {
            GameObject collisionObject = breakout.getSpriteManager().getCurrentObjects().get(i);
            collision(collisionObject);
            if (collision(collisionObject) && collisionObject instanceof Brick){
                ((Brick) collisionObject).destroyBrick();
            }
        }
    }

    @Override
    boolean collision(GameObject object){
        boolean collisionDetect = false;
        //two step collision detection
        if(breakout.getBall().spriteImage.getBoundsInParent().intersects(object.getSpriteImage().getBoundsInParent()))
        {
            Shape intersection = SVGPath.intersect(breakout.getBall().getSpriteCollisionBound(), object.getSpriteCollisionBound());
            if(intersection.getBoundsInLocal().getWidth() != -1){
                collisionDetect = true;
            }
        }
        if (collisionDetect){
            breakout.getSpriteManager().addToRemovedObjects(object);
            //breakout.getRoot().getChildren().remove(object.getSpriteImage()); //TODO Fix disappearance of ball and paddle objects
            breakout.getSpriteManager().resetRemovedObjects();
            return true;
        }
        return false;
    }

    //set XY coordinates when arrow keys are used for gameobject control
    private void setXYPosition(double velocity){
        this.velocityX = velocity;
        this.velocityY = velocity;

        this.positionX =  5;
        this.positionY -= velocityY;


//        if(breakout.controller.isLeft()) {
//            positionX -= velocityX;
//        }
//        if(breakout.controller.isRight()) {
//            positionX += velocityX;
//        }
//        if(breakout.controller.isUp()) {
//            positionY -= velocityY;
//        }
//        if(breakout.controller.isDown()) {
//            positionY += velocityY;
//        }

//        if(positionX >= RIGHT_SCREEN_BOUNDARY) {
//            positionX -= velocityX;
//        }
//        if(positionX <= LEFT_SCREEN_BOUNDARY) {
//            positionX += velocityX;
//        }
//        if(positionY >= TOP_SCREEN_BOUNDARY) {
//            positionY -= velocityY;
//        }
//        if(positionY <= BOTTOM_SCREEN_BOUNDARY) {
//            positionY += velocityY;
//        }
    }
    private void setScreenBoundaries(){
        if(positionX >= RIGHT_SCREEN_BOUNDARY) {
            this.positionX -= velocityX;
        }
        if(positionX <= LEFT_SCREEN_BOUNDARY) {
           this.positionX += velocityX;
        }
        if(positionY <= TOP_SCREEN_BOUNDARY) {
            positionY = 0;
        }
        if(positionY >= BOTTOM_SCREEN_BOUNDARY) {
            isDead();
        }
    }

    public boolean isDead(){
        return true;
    }

    private void translateBall () {
        spriteImage.setTranslateX(positionX);
        spriteImage.setTranslateY(positionY);
    }

    void resetState(){
        this.positionX = BALL_INIT_X_POS;
        this.positionY = BALL_INIT_Y_POS;
        spriteImage.setTranslateX(BALL_INIT_X_POS);
        spriteImage.setTranslateY(BALL_INIT_Y_POS);
    }

}