package de.pfbeuth.game.breakout.controller;

import de.pfbeuth.game.breakout.gameEngine.Breakout;
import javafx.scene.input.KeyCode;

public class Controller {

    public boolean left;
    public boolean right;
    public boolean up;
    public boolean down;
    public Breakout breakout;

    public Controller (Breakout ibreakout){
        this.breakout = ibreakout;
    }

    //eventhandling for gameobjects
    public void createSceneEventHandling(){
        breakout.scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case LEFT: left = true;
                    break;
                case RIGHT: right = true;
                    break;
                case UP: up = true;
                    break;
                case DOWN: down = true;
                    break;
                case A: left = true;
                    break;
                case D: right = true;
                    break;
                case NUMPAD4: left = true;
                    break;
                case NUMPAD6: right = true;
                    break;
            }
        });

        breakout.scene.setOnKeyReleased(e -> {
            switch(e.getCode()) {
                case LEFT: left = false;
                    break;
                case RIGHT: right = false;
                    break;
                case UP: up = false;
                    break;
                case DOWN: down = false;
                    break;
                case A: left = false;
                    break;
                case D: right = false;
                    break;
                case NUMPAD4: left = false;
                    break;
                case NUMPAD6: right = false;
                    break;
                case ESCAPE: breakout.gameIsPausedEvents();
                    break;
                case ENTER: breakout.gameIsOnEvents();
                    break;
            }
        });
    }



    //Getter for key values
    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

}
