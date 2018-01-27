package de.pfbeuth.game.breakout.gameEngine;
import java.util.ArrayList;
import java.util.Random;
import static de.pfbeuth.game.breakout.gameEngine.Breakout.HEIGHT;
import static de.pfbeuth.game.breakout.gameEngine.Breakout.WIDTH;

/**
 * This class defines the different brickgrid configuration for each level
 */

class BrickGrid {
    private Breakout breakout;
    private Brick brick;
    private ArrayList<Brick> brickGridList;

    /** ------ CONSTRUCTOR ------ */
    BrickGrid (Breakout breakout){
        this.breakout = breakout;
        brickGridList = new ArrayList<>();
    }
    /** design for level 1 */
    void createLevelOneGrid (){
        //set the columns
        for (int i = 0; i < 10; i++) {
            //set the lines
            for (int j = 0; j < 8; j++) {
                // set which color is displayed in first line
                if (j <= 1) breakout.setBrickImage(breakout.getBrickImageRed());
                    // set which color is displayed in 2-4 line
                else if (j >= 2 && j < 4){
                    breakout.setBrickImage(breakout.getBrickImageOrange());}
                // set which color is displayed in 4-6 line
                else if (j >= 4 && j < 6) {
                    breakout.setBrickImage(breakout.getBrickImageYellow());
                }
                else {
                    breakout.setBrickImage(breakout.getBrickImageGreen());
                }
                createBrick();
                translateXY(i, j);
                addBricksToScene();
                addBricksToSpriteManager();
            }
        }
    }
    /** design for level 2 */
    void createLevelTwoGrid() {
        //only every second brick is rendered
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                if (j <= 1) breakout.setBrickImage(breakout.getBrickImageRed());
                else if (j >= 2 && j < 4) breakout.setBrickImage(breakout.getBrickImageOrange());
                else if (j >= 4 && j < 6) breakout.setBrickImage(breakout.getBrickImageYellow());
                else breakout.setBrickImage(breakout.getBrickImageGreen());
                createBrick();
                translateXY(i, j);
                if ((i % 2 == 0) && (j % 2 == 0) || (i % 2 != 0) && (j % 2 != 0)) {
                    addBricksToScene();
                    addBricksToSpriteManager();
                }
            }
        }
    }
    /** design for level 3 */
    void createLevelThreeGrid(){
        // random Bricks
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                if (j <= 1) breakout.setBrickImage(breakout.getBrickImageRed());
                else if (j >= 2 && j < 4) breakout.setBrickImage(breakout.getBrickImageOrange());
                else if (j >= 4 && j < 6) breakout.setBrickImage(breakout.getBrickImageYellow());
                else breakout.setBrickImage(breakout.getBrickImageGreen());
                createBrick();
                translateXY(i, j);
                Random rand = new Random();
                int r = rand.nextInt(10);
                if (r <= 5) {
                    addBricksToScene();
                    addBricksToSpriteManager();

                }
            }
        }
    }
    /** design for level 4 */
    void createLevelFourGrid() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                if (j <= 1) breakout.setBrickImage(breakout.getBrickImageRed());
                else if (j >= 2 && j < 4) breakout.setBrickImage(breakout.getBrickImageOrange());
                else if (j >= 4 && j < 6) breakout.setBrickImage(breakout.getBrickImageYellow());
                else breakout.setBrickImage(breakout.getBrickImageGreen());
                createBrick();
                translateXY(i, j);
                if (i < j) {
                    addBricksToScene();
                    addBricksToSpriteManager();

                }
            }
        }
    }
    /** design for level 5 */
    void createLevelFiveGrid(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                if (j <= 1) breakout.setBrickImage(breakout.getBrickImageRed());
                else if (j >= 2 && j < 4) breakout.setBrickImage(breakout.getBrickImageOrange());
                else if (j >= 4 && j < 6) breakout.setBrickImage(breakout.getBrickImageYellow());
                else breakout.setBrickImage(breakout.getBrickImageGreen());
                createBrick();
                translateXY(i, j);
                if(i>j) {
                    addBricksToScene();
                    addBricksToSpriteManager();
                }
            }
        }
    }
    private void addBricksToScene(){
        breakout.getRoot().getChildren().add(brick.spriteImage);
        brick.spriteImage.toFront();
        brickGridList.add(brick);
    }
    private void addBricksToSpriteManager(){
        for (Brick aBrickGrid : brickGridList) {
            brick = aBrickGrid;
            breakout.getSpriteManager().addCurrentObjects(brick);
        }
    }
    private void createBrick(){
        brick = new Brick("M0,0V18H47.91V0Z", 0, 0, breakout.getBrickImage());
    }
    /* translates every Brick to arrange the BrickGrid and adds space between each Brick */
    private void translateXY(int i, int j) {
        int xPadding = 2;
        float yPadding = 2.5f;
        int topPadding = 50;
        brick.spriteImage.setTranslateX(-WIDTH / 2 + i * (breakout.getBrickImage().getRequestedWidth() + xPadding) + (breakout.getBrickImage().getRequestedWidth() / 2) + xPadding/2);
        brick.spriteImage.setTranslateY(-HEIGHT / 2 + j * (breakout.getBrickImage().getRequestedHeight() + yPadding) + (breakout.getBrickImage().getRequestedHeight() / 2 + topPadding));
    }
    ArrayList<Brick> getBrickGridList() {
        return brickGridList;
    }
    /** delete all bricks from scene */
    void deleteAllBrickFromScene(){
        for (Brick aBrickGrid : brickGridList) {
            brick = aBrickGrid;
            breakout.getRoot().getChildren().remove(brick.spriteImage);
        }
    }
    /** set Bricks back in Z-Depth */
    void setBricksToBack(){
        for(Brick aBrick : brickGridList){
            aBrick.spriteImage.toBack();
        }
    }
}