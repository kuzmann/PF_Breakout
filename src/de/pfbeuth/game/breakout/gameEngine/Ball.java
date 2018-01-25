package de.pfbeuth.game.breakout.gameEngine;

import de.pfbeuth.game.breakout.gamelogic.ScoreCounter;
import javafx.scene.image.Image;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import static de.pfbeuth.game.breakout.gameEngine.Breakout.HEIGHT;
import static de.pfbeuth.game.breakout.gameEngine.Breakout.WIDTH;


/*
 * This class defines the object ball and checks the ball-brick-collision and the various states of the object ball
 * this class inherits from the class AnimatedGameObject
 *
 * @param up                check, if ball hits paddle returns true
 * @param right             check, if
 * @param ballIsDead        check, if ball miss the paddle and user loose a life
 * @param destroyedBrick
 * @param levelAccomplished check, if user destroyed all bricks, returns true
 *
 */
public class Ball extends AnimatedGameObject {
    private Breakout breakout;  //creates context to Breakout-Class

    private final double BALL_INIT_X_POS = 0;
    private final double BALL_INIT_Y_POS = HEIGHT/3;
    /** Radius of the ball */
    private static final double BALL_RADIUS = 50/4; //TODO get rid of magic number; 50 = size of ball.png in px
    /** screen boundary*/
    private static final double RIGHT_SCREEN_BOUNDARY = WIDTH/2 - BALL_RADIUS/4;
    private static final double LEFT_SCREEN_BOUNDARY = -(WIDTH/2 - BALL_RADIUS/4);
    private static final double TOP_SCREEN_BOUNDARY = -(HEIGHT/2 - BALL_RADIUS/4);
    private static final double BOTTOM_SCREEN_BOUNDARY = HEIGHT/2 + BALL_RADIUS/4;
    private boolean up = true;
    private boolean right = true;
    private boolean ballIsDead;
    private Brick destroyedBrick;
    private boolean levelAccomplished = false;
    /** Initial velocities of the ball */
    private final double INIT_BALL_VELOCITY = 10;

    protected Ball(Breakout iBall, String SVGdata, double xLocation, double yLocation, Image... sprites) {
        super(SVGdata, xLocation, yLocation, sprites);
        breakout = iBall;
   }

    @Override
    void update(){
        checkCollision();
        setXYPosition();
        setScreenBoundaries();
        translateBall();
    }
    private void checkCollision(){
        for (int i = 0; i < breakout.getSpriteManager().getCurrentObjects().size(); i++) {
            GameObject collisionObject = breakout.getSpriteManager().getCurrentObjects().get(i);
            collision(collisionObject);
            // Check if ball hit a brick
            if (collision(collisionObject) && collisionObject instanceof Brick){
                ((Brick) collisionObject).destroyBrick();
                brickCollision();
                destroyedBrick = (Brick) collisionObject;
                breakout.getSpriteManager().resetRemovedObjects();
                checkBrickHitColor();
                if(checkLevelFinished()){
                    breakout.getGameStates().levelFinishedEvents();
                }
            }
            // Check if ball hit the paddle
            if(collision(collisionObject) && collisionObject instanceof Paddle) {
                ballPaddleCollision();
            }
        }
    }

    /**
     * two step collision detection (ball hits brick)
     * A collision with brick also removes the brick
     * @return returns true if ball hit an game object,
     * returns false otherwise
     * */
    @Override
    boolean collision(GameObject object){
        boolean collisionDetect = false;
        /* ------ first step: is an object in the vicinity ------ */
        if(breakout.getBall().spriteImage.getBoundsInParent().intersects(object.getSpriteImage().getBoundsInParent())) {
            Shape intersection = SVGPath.intersect(breakout.getBall().getSpriteCollisionBound(), object.getSpriteCollisionBound());
            if(intersection.getBoundsInLocal().getWidth() != -1){
                collisionDetect = true;
            }
        }
        /* ------ second step: something was hit ------ */
        if (collisionDetect){
            // make sure the ball didn't hit paddle or ball object
            if (!(object instanceof Paddle) && !(object instanceof Ball)) {
                breakout.getSpriteManager().removeCurrentObjects(object);
                breakout.getSpriteManager().addToRemovedObjects(object);
              //  breakout.getRoot().getChildren().remove(object.getSpriteImage());
            }
            return true;
        }
        return false;
    }

    /** check, which brickcolor was hit and start counter method*/
    private void checkBrickHitColor() {
        if (getDestroyedBrick().spriteImage.getImage().equals(breakout.getBrickImageGreen())) {
            breakout.getScoreCounter().counter(ScoreCounter.BrickColor.GREEN);
        }
        if (getDestroyedBrick().spriteImage.getImage().equals(breakout.getBrickImageRed())) {
            breakout.getScoreCounter().counter(ScoreCounter.BrickColor.RED);
        }
        if (getDestroyedBrick().spriteImage.getImage().equals(breakout.getBrickImageOrange())) {
            breakout.getScoreCounter().counter(ScoreCounter.BrickColor.ORANGE);
        }
        if (getDestroyedBrick().spriteImage.getImage().equals(breakout.getBrickImageYellow())) {
            breakout.getScoreCounter().counter(ScoreCounter.BrickColor.YELLOW);
        }
    }

    //TODO: Tommy, kannst du diese Methode beschreiben?
    /**  */
	private void setXYPosition(){
	    /** check, if ball hit paddle*/
        if (up) {
            positionY -= velocityY;
        } else {
            positionY += velocityY;
        }
        /** */
        if(right){
            positionX += velocityX;
        } else {
            positionX -= velocityX;
        }
       /* ------ !***FOR TESTING***! Control Ball with arrow keys ------ */
       /*if(breakout.getController().isLeft()) {
            positionX -= velocityX;
        }
        if(breakout.getController().isRight()) {
            positionX += velocityX;
        }
        if(breakout.getController().isUp()) {
            positionY -= velocityY;
        }
        if(breakout.getController().isDown()) {
            positionY += velocityY;
        }

        if(positionX >= RIGHT_SCREEN_BOUNDARY) {
            positionX -= velocityX;
        }
        if(positionX <= LEFT_SCREEN_BOUNDARY) {
            positionX += velocityX;
        }
        if(positionY >= TOP_SCREEN_BOUNDARY) {
            positionY -= velocityY;
        }
        if(positionY <= BOTTOM_SCREEN_BOUNDARY) {
            positionY += velocityY;
        }*/
    }

    /**
     * two step collision detection (ball hits brick)
     * @return returns true if ball hit an game object,
     * returns false otherwise
     * */

	private void setScreenBoundaries(){
        if(positionX >= RIGHT_SCREEN_BOUNDARY) {
           positionX = RIGHT_SCREEN_BOUNDARY - BALL_RADIUS;
           right = false;
           ballIsDead = false;
        }
        if(positionX <= LEFT_SCREEN_BOUNDARY) {
            positionX = LEFT_SCREEN_BOUNDARY + BALL_RADIUS;
            right = true;
            ballIsDead = false;
        }
        if(this.positionY <= TOP_SCREEN_BOUNDARY) {
            positionY = TOP_SCREEN_BOUNDARY + BALL_RADIUS ;
            up = false;
            ballIsDead = false;
        }

        if(this.positionY >= BOTTOM_SCREEN_BOUNDARY) {
            ballIsDead =  true;
            breakout.getGameStates().ballDied();
        }
    }

    //TODO: Tommy, was passiert hier genau?
    /** ----*/
	private void translateBall () {
        spriteImage.setTranslateX(positionX);
        spriteImage.setTranslateY(positionY);
    }
    //TODO: Tommy, was passiert hier genau?
    /**  */
	private void brickCollision(){
		up = !up;
		right = !right;
	}

    /**
     * If ball hits paddle, this method is called
     * @return returns true if ball hit the paddle,
     * */
	private void ballPaddleCollision(){
        up = true;
    }

    //TODO: Tommy, bitte den Kommentar überprüfen
    /** if a new game or level starts, ball will set to the initial position */
	protected void resetState(){
        up = true;
        right = true;
        this.positionX = BALL_INIT_X_POS;
        this.positionY = BALL_INIT_Y_POS;
        spriteImage.setTranslateX(BALL_INIT_X_POS);
        spriteImage.setTranslateY(BALL_INIT_Y_POS);
    }

    /**
     * this method check, if all brick are destroyed and a level was successfully completed
     * @return returns true if user destroyed all bricks
     * returns false otherwise
     * */
	private boolean  checkLevelFinished(){
		//you have to compare the size of the array with 2, because there are also paddle and ball object in the array besides the brick.
		if (breakout.getSpriteManager().getCurrentObjects().size() == 2) {
			breakout.getLevel().setLevelAccomplished(true);
		}
		else {
		    breakout.getLevel().setLevelAccomplished(false);
		}
        return breakout.getLevel().isLevelAccomplished();
	}

    /** ------ GETTER ------ */
    public boolean isRight() {
        return right;
    }
    public Brick getDestroyedBrick(){
		return destroyedBrick;
	}
    public boolean isLevelWon(){
        return levelAccomplished;
    }
    public boolean getBallIsDead(){
        return ballIsDead;
    }
    /** ------ SETTER ------ */
    public void setLevelAccomplished(Boolean levelAccomplished) {
         this.levelAccomplished = levelAccomplished;
    }

    public void resetVelocity(){
        setVelocityX(INIT_BALL_VELOCITY);
        setVelocityY(INIT_BALL_VELOCITY);
    }
    public void setBallToFront(){
        spriteImage.toFront();
    }
    public void setBallToBack(){
        spriteImage.toBack();
    }

}
