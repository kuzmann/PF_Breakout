package de.pfbeuth.game.breakout.gameEngine;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class InfoText extends Text {

    InfoText (){
        setVisible(false);
        setFont(new Font("arial", 18));
        setWrappingWidth(200);
        setFill(Color.WHITE);
        setTextAlignment(TextAlignment.CENTER);
    }
}