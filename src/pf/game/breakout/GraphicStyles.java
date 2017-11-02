package pf.game.breakout;

import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;

class GraphicStyles {

    private static Lighting lightFX(){
        Light.Distant light = new Light.Distant();
        light.setAzimuth(-135.00);
        Lighting lighting = new Lighting();
        lighting.setLight(light);
        lighting.setSurfaceScale(5.0);
        return lighting;
    }
    private static DropShadow shadowFX(){
        DropShadow shadow = new DropShadow();
        shadow.setOffsetX(0.0);
        shadow.setOffsetY(15.0);
        shadow.setColor(Color.GREY);
        shadow.setInput(getLightFX());
        return shadow;
    }

    private static Reflection reflectionFX(){
        Reflection reflection = new Reflection();
        reflection.setTopOffset(0.0);
        reflection.setInput(getShadowFX());
        return reflection;
    }

    static Lighting getLightFX(){
        return lightFX();
    }
    static DropShadow getShadowFX(){
        return shadowFX();
    }
    static Reflection getReflFX(){
        return reflectionFX();
    }
}
