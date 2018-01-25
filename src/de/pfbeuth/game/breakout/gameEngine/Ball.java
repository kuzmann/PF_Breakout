package de.pfbeuth.game.breakout.gameEngine;

import de.pfbeuth.game.breakout.gamelogic.ScoreCounter;
import javafx.scene.image.Image;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import static de.pfbeuth.game.breakout.gameEngine.Breakout.HEIGHT;
import static de.pfbeuth.game.breakout.gameEngine.Breakout.WIDTH;

public class Ball extends AnimatedGameObject {
    private Breakout breakout;  //creates context to Breakout-Class
    private final double BALL_INIT_X_POS = 0;
    private final double BALL_INIT_Y_POS = HEIGHT/3;
    private static final double BALL_RADIUS = 50/4; //TODO get rid of magic number; 50 = size of ball.png in px
    private static final double RIGHT_SCREEN_BOUNDARY = WIDTH/2 - BALL_RADIUS/4;
    private static final double LEFT_SCREEN_BOUNDARY = -(WIDTH/2 - BALL_RADIUS/4);
    private static final double TOP_SCREEN_BOUNDARY = -(HEIGHT/2 - BALL_RADIUS/4);
    private static final double BOTTOM_SCREEN_BOUNDARY = HEIGHT/2 + BALL_RADIUS/4;
    private boolean up = true;
    private boolean right = true;
    private boolean ballIsDead;
    private Brick destroyedBrick;
    private boolean levelAccomplished = false;
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
            if(collision(collisionObject) && collisionObject instanceof Paddle) {
                ballPaddleCollision();
            }
        }
    }

    @Override
    boolean collision(GameObject object){
        boolean collisionDetect = false;
        /* ------ two step collision detection ------ */
        if(breakout.getBall().spriteImage.getBoundsInParent().intersects(object.getSpriteImage().getBoundsInParent())) {
            Shape intersection = SVGPath.intersect(breakout.getBall().getSpriteCollisionBound(), object.getSpriteCollisionBound());
            if(intersection.getBoundsInLocal().getWidth() != -1){
                collisionDetect = true;
            }
        }
        if (collisionDetect){
            if (!(object instanceof Paddle) && !(object instanceof Ball)) {
                breakout.getSpriteManager().removeCurrentObjects(object);
                breakout.getSpriteManager().addToRemovedObjects(object);
              //  breakout.getRoot().getChildren().remove(object.getSpriteImage());
            }
            return true;
        }
        return false;
    }
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

	private void setXYPosition(){
        if (up) {
            positionY -= velocityY;
        } else {
            positionY += velocityY;
        }

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
	private void translateBall () {
        spriteImage.setTranslateX(positionX);
        spriteImage.setTranslateY(positionY);
    }
	private void brickCollision(){
		up = !up;
		right = !right;
	}
	private void ballPaddleCollision(){
        up = true;
    }
	protected void resetState(){
        up = true;
        right = true;
        this.positionX = BALL_INIT_X_POS;
        this.positionY = BALL_INIT_Y_POS;
        spriteImage.setTranslateX(BALL_INIT_X_POS);
        spriteImage.setTranslateY(BALL_INIT_Y_POS);
    }
	private boolean  checkLevelFinished(){
		//TODO um die Methode is.Empty() nutzen zu können, müsste man aus der Arrayliste zwei Elemente (Paddle, Ball) erntfernen. Deshlab ist vergleichswert bei int = 2
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
