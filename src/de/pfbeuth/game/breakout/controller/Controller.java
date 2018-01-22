package de.pfbeuth.game.breakout.controller;

import de.pfbeuth.game.breakout.gameEngine.Breakout;

public class Controller {
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    private Breakout breakout;

    public Controller (Breakout breakout){
        this.breakout = breakout;
    }

    /** Event Listener */
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
                case ESCAPE: breakout.getGameStates().pauseGameEvents();
                    break;
                case ENTER:
                    {
                        if(breakout.getGuiNodes().getStartButton().isVisible()) {
                            breakout.getGameStates().runGameEvents();
                            breakout.getGuiNodes().getLifeInfo().setText(breakout.getGuiNodes().getLIVES_INFO_TEXT() + breakout.getLife().getActualLife());

                            if (breakout.getGuiNodes().getStartButton().getText().equals(breakout.getGuiNodes().getPlayAgainText())) {
                                breakout.getGuiNodes().getStartButton().setText(breakout.getGuiNodes().getStartText());
                            }
                        }
                        if(breakout.getGuiNodes().getConfirmButton().isVisible() && breakout.getLife().getIsGameOver()){
                            breakout.getGuiNodes().confirmButtonEvents();
                        }
                    }
                    break;
            }
        });
    }

    /** GETTER */
    public boolean isLeft() {
        return left;
    }
    public boolean isRight() {
        return right;
    }
    //TODO Delete - only for test purposes
    public boolean isUp(){
        return up;
    }
    public boolean isDown(){
        return down;
    }
}
