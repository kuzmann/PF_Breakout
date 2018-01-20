package de.pfbeuth.game.breakout.gameEngine;

import java.util.Random;
import static de.pfbeuth.game.breakout.gameEngine.Breakout.HEIGHT;
import static de.pfbeuth.game.breakout.gameEngine.Breakout.WIDTH;

public class BrickGrid {

    private Breakout breakout;
    private Brick brick;

    BrickGrid (Breakout breakout){
        this.breakout = breakout;
    }

    public void createLevelOneGrid (){
        // standard Brick Grid
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                if (j <= 1) breakout.setBrickImage(breakout.getBrickImageRed());
                else if (j >= 2 && j < 4) breakout.setBrickImage(breakout.getBrickImageOrange());
                else if (j >= 4 && j < 6) breakout.setBrickImage(breakout.getBrickImageYellow());
                else breakout.setBrickImage(breakout.getBrickImageGreen());
                createBrick();
                translateXY(i, j);
                addBricksToScene();
            }
        }
    }

    public void createLevelTwoGrid() {
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
                }
            }
        }
    }

    public void createLevelThreeGrid(){
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
                }
            }
        }
    }

    public void createLevelFourGrid() {
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
                }
            }
        }
    }

    public void createLevelFiveGrid(){
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
                    }
                }
            }
    }

    private void addBricksToScene(){
        breakout.getRoot().getChildren().add(brick.spriteImage);
        breakout.getBrickGridList().add(brick);
    }
    private void createBrick(){
        brick = new Brick(breakout, "M.5,3.91V28.66c0,3.75,1.37,4.62,4.62,4.62H84c2.25,0,3.44-.75,3.44-3.44s-.08-22.06,0-26.19C87.5.45,88.07.5,84.29.5H3.5C.25.5.5,3.91.5,3.91Z", 0, 0, breakout.getBrickImage());
    }
    private void translateXY(int i, int j) {
        brick.spriteImage.setTranslateX(-WIDTH / 2 + i * (breakout.getBrickImage().getRequestedWidth() + 2) + (breakout.getBrickImage().getRequestedWidth() / 2) + 1);
        brick.spriteImage.setTranslateY(-HEIGHT / 2 + j * (breakout.getBrickImage().getRequestedHeight() + 2.5) + (breakout.getBrickImage().getRequestedHeight() / 2 + 50));
    }
}
