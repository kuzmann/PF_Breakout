package de.pfbeuth.game.breakout.gameEngine;
import de.pfbeuth.game.breakout.gamelogic.ScoreCounter;
import javafx.scene.image.Image;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import java.util.Random;
import static de.pfbeuth.game.breakout.gameEngine.Breakout.HEIGHT;
import static de.pfbeuth.game.breakout.gameEngine.Breakout.WIDTH;

/**
 * This class defines the object ball and checks the ball-brick-collision and the various states of the object ball
 * this class inherits from the class AnimatedGameObject
 */

public class Ball extends AnimatedGameObject {
    private Breakout breakout;  //creates context to Breakout-Class
    private final double BALL_INIT_X_POS = 0;
    private final double BALL_INIT_Y_POS = HEIGHT/3;
    private Random rand = new Random();
    private final double INIT_BALL_X_VELOCITY = 4 + rand.nextInt(6);
    private final double INIT_BALL_Y_VELOCITY = 4 + rand.nextInt(6);
    private static final double BALL_RADIUS = 50/4; // 50 = size of ball.png in px
    private static final double RIGHT_SCREEN_BOUNDARY = WIDTH/2 - BALL_RADIUS/4;
    private static final double LEFT_SCREEN_BOUNDARY = -(WIDTH/2 - BALL_RADIUS/4);
    private static final double TOP_SCREEN_BOUNDARY = -(HEIGHT/2 - BALL_RADIUS/4);
    private static final double BOTTOM_SCREEN_BOUNDARY = HEIGHT/2 + BALL_RADIUS/4;
    private boolean up = true;
    private boolean right = true;
    private boolean ballIsDead;
    private Brick destroyedBrick;

    /** ------ CONSTRUCTOR ------ */
    protected Ball(Breakout iBall, String SVGdata, double xLocation, double yLocation, Image... sprites) {
        super(SVGdata, xLocation, yLocation, sprites);
        breakout = iBall;
    }
    /** Update() is called by GamePlayTimer and updates every method with 60fps */
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
     * two step collision detection (ball hits brick or paddle)
     * A collision with a brick also removes the brick
     * @return returns true if ball hit an game object,
     * returns false otherwise
     * */
    @Override
    boolean collision(GameObject object){
        boolean collisionDetect = false;
        /* ------ first step: is an object in the vicinity ------ */
        if(breakout.getBall().spriteImage.getBoundsInParent().intersects(object.getSpriteImage().getBoundsInParent())) {
        	/* ------ second step: SVG Collision Path gets checked------ */
            Shape intersection = SVGPath.intersect(breakout.getBall().getSpriteCollisionBound(), object.getSpriteCollisionBound());
            if(intersection.getBoundsInLocal().getWidth() != -1){
                collisionDetect = true;
            }
        }
        /* ------ third step: something was hit ------ */
        if (collisionDetect){
            // make sure the that only Brick objects will be deleted from the currentObjectsList
            if (!(object instanceof Paddle) && !(object instanceof Ball)) {
                breakout.getSpriteManager().removeCurrentObjects(object);
                breakout.getSpriteManager().addToRemovedObjects(object);
            }
            return true;
        }
        return false;
    }
    /* check, which brickcolor was hit and start ScoreCounter method */
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
    /* check, if ball moves in positive or negative X- or Y-direction */
    private void setXYPosition(){
	    if (up) {
            setPositionY(getPositionY() - velocityY);
        } else {
            setPositionY(getPositionY() + velocityY);
        }
        if(right){
            setPositionX(getPositionX() + velocityX);
        } else {
            setPositionX(getPositionX() - velocityX);
        }
    }
    /* defines screen boundaries */
	private void setScreenBoundaries(){
        if(getPositionX() >= RIGHT_SCREEN_BOUNDARY) {
           setPositionX(RIGHT_SCREEN_BOUNDARY - BALL_RADIUS);
           right = false;
           ballIsDead = false;
        }
        if(getPositionX() <= LEFT_SCREEN_BOUNDARY) {
            setPositionX(LEFT_SCREEN_BOUNDARY + BALL_RADIUS);
            right = true;
            ballIsDead = false;
        }
        if(getPositionY() <= TOP_SCREEN_BOUNDARY) {
            setPositionY(TOP_SCREEN_BOUNDARY + BALL_RADIUS);
            up = false;
            ballIsDead = false;
        }

        if(getPositionY() >= BOTTOM_SCREEN_BOUNDARY) {
            ballIsDead =  true;
            breakout.getGameStates().ballDied();
        }
    }
    /* translate X- and Y-Position */
	private void translateBall () {
        spriteImage.setTranslateX(getPositionX());
        spriteImage.setTranslateY(getPositionY());
    }
    /* if brick gets hit, ball movement is inverted  */
	private void brickCollision(){
		up = !up;
		right = !right;

        if (!right) {
            setVelocityX( getVelocityX()* 1.01);
            setVelocityY( getVelocityY()* 1.05);
        } else {
            setVelocityX( getVelocityX()* 0.99);
            setVelocityY( getVelocityY()* 0.95);
        }

        if (right) {
            setVelocityX( getVelocityX()* 1.01);
            setVelocityY( getVelocityY()* 1.05);
        } else {
            setVelocityX( getVelocityX()* 0.99);
            setVelocityY( getVelocityY()* 0.95);
        }
	}

    /* called when ball hits paddle */
	private void ballPaddleCollision(){
        up = true;
        if (breakout.getController().isLeft()) {
            if (!right) {
                setVelocityX( getVelocityX()* 1.01);
                setVelocityY( getVelocityY()* 1.05);
            } else {
                setVelocityX( getVelocityX()* 0.99);
                setVelocityY( getVelocityY()* 0.95);
            }
        }
        if (breakout.getController().isRight()) {
            if (right) {
                setVelocityX( getVelocityX()* 1.01);
                setVelocityY( getVelocityY()* 1.05);
            } else {
                setVelocityX( getVelocityX()* 0.99);
                setVelocityY( getVelocityY()* 0.95);
            }
        }
    }

    /**if a new game or level starts, ball will set to the initial position */
	void resetState(){
        up = true;
        right = true;
        setPositionX(BALL_INIT_X_POS);
        setPositionY(BALL_INIT_Y_POS);
        spriteImage.setTranslateX(BALL_INIT_X_POS);
        spriteImage.setTranslateY(BALL_INIT_Y_POS);
    }

    /* check if all bricks are destroyed and a level was successfully completed
     * @return returns true if user destroyed all bricks
     * returns false otherwise */
	private boolean checkLevelFinished(){
		/* You have to compare the size of the array with 2 because the List contains the bricks
		* and also paddle and ball object in the array. Paddle and Ball are
		* always the first two elements in the List */
		if (breakout.getSpriteManager().getCurrentObjects().size() == 2) {
			breakout.getLevel().setLevelAccomplished(true);
		}
		else {
		    breakout.getLevel().setLevelAccomplished(false);
		}
        return breakout.getLevel().isLevelAccomplished();
	}

    private Brick getDestroyedBrick(){
		return destroyedBrick;
	}

    /** resets X- and Y-Velocity to initial value*/
    void resetVelocity() {
        setVelocityX(INIT_BALL_X_VELOCITY);
        setVelocityY(INIT_BALL_Y_VELOCITY);
    }
    /** set Ball back in Z-Depth*/
    void setBallToBack(){
        spriteImage.toBack();
    }

    /* ------ GETTER ------ */
    /** @return true if ball hits bottom boundary*/
    boolean getBallIsDead(){
        return ballIsDead;
    }
}