package com.checkers.dubi.view;

import com.checkers.Main;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class QueenPawn extends Pawn{

    Circle circle =null;
    @Override
    Node getTexture () {
        return circle;
    }
    public QueenPawn(boolean isWhite){
        circle = new Circle();
        if(isWhite){
            circle.setFill(Color.WHITE);
        } else{
            circle.setFill(Color.BLACK);
        }
        circle.setRadius(Main.tileSize * Main.pawnSkale);
        circle.setStrokeWidth(7.0);
        circle.setStroke(Color.BLUE);
    }

}
