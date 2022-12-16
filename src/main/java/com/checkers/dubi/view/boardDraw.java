package com.checkers.dubi.view;

import com.checkers.Main;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class boardDraw {

    public static void drawBoard(int a, int b, Pane root){
        for(int i=0;i<a;i++){
            for(int j=0;j<b;j++){
                Tile tile = null;
                if((i+j)%2==0){
                    tile = new Tile(i,j, Tile.defaultColor1);
                } else{
                    tile = new Tile(i,j, Tile.defaultColor2);
                }
                root.getChildren().add(tile);
            }
        }
    }
}

class Tile extends Rectangle{
    public static Color defaultColor1 = Color.RED;
    public static Color defaultColor2 = Color.GREEN;
    public Tile(double a, double b, Color color){
       setWidth(Main.tileSize);
       setHeight(Main.tileSize);
        setX(Main.margin/2+a*Main.tileSize);
        setY(Main.margin/2+b*Main.tileSize);
        setFill(color);
    }
}