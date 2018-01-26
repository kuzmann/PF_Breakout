package de.pfbeuth.game.breakout.controller;
import de.pfbeuth.game.breakout.gameEngine.Breakout;

/**
 * Event handling for scene control
 * */
public class Controller {
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    private Breakout breakout;

    /** ------ CONSTRUCTOR ------ */
    public Controller (Breakout breakout){
        this.breakout = breakout;
    }

    /** Scene event Listener */
    public void createSceneEventHandling(){
        breakout.getScene().setOnKeyPressed(e -> {
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
            }
        });
        breakout.getScene().setOnKeyReleased(e -> {
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
                case ESCAPE:
                    if(!breakout.getGameStates().isGamePaused()){
                        breakout.getGameStates().pauseGameEvents();
                    }
                    break;
                case ENTER:
                    {
                        if(!breakout.getGuiNodes().getStartButton().isDisable()) {
                            breakout.getGameStates().runGameEvents();
                            breakout.getGuiNodes().getLifeInfo().setText(breakout.getGuiNodes().getLIVES_INFO_TEXT() + breakout.getLife().getActualLife());
                        }
                        if(breakout.getGuiNodes().getConfirmButton().isVisible()){
                            breakout.getGuiNodes().confirmButtonEvents();
                        }
                    }
                    break;
            }
        });
    }
    /** ------ GETTER ------ */
    /** @return true if Left-Arrow-Key or A-Key is pressed, false if key is released */
    public boolean isLeft() {
        return left;
    }
    /** @return true if Right-Arrow-Key or D-Key is pressed, false if key is released */
    public boolean isRight() {
        return right;
    }
    /* unused method is kept for testing purposes */
    public boolean isUp(){
        return up;
    }
    /* unused method is kept for testing purposes */
    public boolean isDown(){
        return down;
    }
}
