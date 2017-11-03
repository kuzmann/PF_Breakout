package pf.game.breakout;

import javafx.scene.paint.Color;

import java.util.ArrayList;

class BrickGrid extends Brick {

    static ArrayList<Brick> bricks = new ArrayList<>();

    BrickGrid(){
        createBrickGrid();
    }
    private static void createBrickGrid (){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                Brick brick = new Brick();
                brick.setX(i*(brick.getWidth()+5)+(brick.getWidth()/2+5));
                brick.setY(j*(brick.getHeight()+5)+(brick.getHeight()/2+5));
                if(j<=1)
                    brick.setFill(Color.RED);
                else if(j > 1 && j <= 3)
                    brick.setFill(Color.DARKORANGE);
                else if(j > 3 && j <= 5)
                    brick.setFill(Color.GREEN);
                else brick.setFill(Color.YELLOW);

                brick.setEffect(GraphicStyles.getLightFX());
                BreakoutMain.gameArea.getChildren().add(brick);
                bricks.add(brick);
            }
        }
    }
}
