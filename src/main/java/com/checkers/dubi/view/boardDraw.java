package com.checkers.dubi.view;

import com.checkers.Main;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class boardDraw {

    public static void drawBoard(int a, int b, Pane root){
        for(int i=0;i<a;i++){
            for(int j=0;j<b;j++){
                Tile tile = new Tile(i,j,((i+j)%2==0));
                root.getChildren().add(tile);
            }
        }
    }
}

class Tile extends Rectangle{
    public Tile(int a, int b, boolean isRed){
       setWidth(Main.tileSize);
       setHeight(Main.tileSize);
        setX(Main.margin/2+a*Main.tileSize);
        setY(Main.margin/2+b*Main.tileSize);
        if(isRed==true){ setFill(Color.RED );
        } else{ setFill(Color.GREEN );
        }
    }
}