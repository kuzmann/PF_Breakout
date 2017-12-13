package de.pfbeuth.game.breakout;
import javafx.scene.image.Image;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import static de.pfbeuth.game.breakout.Breakout.HEIGHT;
import static de.pfbeuth.game.breakout.Breakout.WIDTH;

class Ball extends AnimatedGameObject {
    private Breakout breakout;  //creates context to Breakout-Class
    private static final double BALL_RADIUS = 50/4; //TODO get rid of magic number; 50 = size of ball.png in px
    private static final double RIGHT_SCREEN_BOUNDARY = WIDTH/2 - BALL_RADIUS;
    private static final double LEFT_SCREEN_BOUNDARY = -(WIDTH/2 - BALL_RADIUS);
    private static final double BOTTOM_SCREEN_BOUNDARY = -(HEIGHT/2 - BALL_RADIUS);
    private static final double TOP_SCREEN_BOUNDARY = (HEIGHT/2 - BALL_RADIUS);

    protected Ball(Breakout iBall, String SVGdata, double xLocation, double yLocation, Image... sprites) {
        super(SVGdata, xLocation, yLocation, sprites);
        breakout = iBall;
    }

    @Override
    public void update(){
        setXYPosition(8);
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
    //set XY coordinates when arrow keys are used for gameobject control
    private void setXYPosition(double velocity){
        this.velocityX = velocity;
        this.velocityY = velocity;
        if(breakout.isLeft()) {
            positionX -= velocityX;
        }
        if(breakout.isRight()) {
            positionX += velocityX;
        }
        if(breakout.isUp()) {
            positionY -= velocityY;
        }
        if(breakout.isDown()) {
            positionY += velocityY;
        }
    }
    private void setScreenBoundaries(){
        if(positionX >= RIGHT_SCREEN_BOUNDARY){
            positionX = RIGHT_SCREEN_BOUNDARY;
        }
        if(positionX <= LEFT_SCREEN_BOUNDARY){
            positionX = LEFT_SCREEN_BOUNDARY;
        }
        if(positionY <= BOTTOM_SCREEN_BOUNDARY){
            positionY = BOTTOM_SCREEN_BOUNDARY;
        }
        if(positionY >= TOP_SCREEN_BOUNDARY){
            positionY = TOP_SCREEN_BOUNDARY;
        }
    }
    private void translateBall () {
        spriteImage.setTranslateX(positionX);
        spriteImage.setTranslateY(positionY);
    }
    @Override
    public boolean collision(GameObject object){
        boolean collisionDetect = false;
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
}