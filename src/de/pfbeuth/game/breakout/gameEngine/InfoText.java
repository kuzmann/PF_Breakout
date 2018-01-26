package de.pfbeuth.game.breakout.gameEngine;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * This class defines the style of Text Strings,
 * it inherits from the JavaFX class Text.
 */

class InfoText extends Text {
    /** ------ CONSTRUCTOR ------ */
    InfoText (){
        setVisible(false);
        setFont(new Font("arial", 18));
        setWrappingWidth(200);
        setFill(Color.WHITE);
        setTextAlignment(TextAlignment.CENTER);
    }
}