package de.pfbeuth.game.breakout;

import javafx.scene.image.Image;
import static de.pfbeuth.game.breakout.Breakout.left;
import static de.pfbeuth.game.breakout.Breakout.right;

class Paddle extends AnimatedGameObject {

    //Singleton Design Pattern
 //   private static Paddle paddle = new Paddle(SVGdata, xLocation, yLocation, sprites);

    /*private*/
    public Paddle (String SVGdata, double xLocation, double yLocation, Image... sprites){
        super(SVGdata, xLocation, yLocation, sprites);
    }

 //   static Paddle getInstance(){
 //       return paddle;    }

    @Override
    public void update() {
        if(left) {
            positionX -= 20;
            System.out.println("left key");
        }
       // spriteImage.setTranslateX(positionX);
        if(right) {
            positionX = positionX + 20;
            System.out.println("right key");
        }
        spriteImage.setTranslateX(positionX);
    }
   @Override
    boolean collision(GameObject object){
        return false;
    }
}
