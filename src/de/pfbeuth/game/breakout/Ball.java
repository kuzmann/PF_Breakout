package de.pfbeuth.game.breakout;
import javafx.scene.image.Image;
class Ball extends AnimatedGameObject {
    Breakout breakout;
    public Ball(Breakout iBall, String SVGdata, double xLocation, double yLocation, Image... sprites) {
        super(SVGdata, xLocation, yLocation, sprites);
        breakout = iBall;
    }
    @Override
    public void update(){
        checkCollision();
    }
    public void checkCollision(){
        for (int i = 0; i < breakout.getSpriteManager().getCurrentObjects().size(); i++) {
            GameObject collisionObject = breakout.getSpriteManager().getCurrentObjects().get(i);
            collide(collisionObject);
        }
    }
    @Override
    public boolean collide(GameObject object){
        return false;
    }

 /*
    void move()
    {
        boolean brickHit = false;
        for(Brick b : BrickGrid.bricks) {
            if (b.intersects(super.getBoundsInLocal())) {
                this.xSpeed = -this.xSpeed;
                this.ySpeed = -this.ySpeed;
                b.destroyBrick();
                BrickGrid.bricks.remove(b);
                brickHit = true;
            }
         if (brickHit){
             AudioClip destroySound = new AudioClip("assets/Quick_Impact_07.wav");
             destroySound.play();
         }

        }
     }
*/

}