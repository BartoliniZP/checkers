package com.checkers.dubi.view;

import com.checkers.Main;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class pawnsDraw{
    public static void drawPawns(int a, int b, Pane root, int layers){
        for(int i=0;i<a;i++){
            for(int j=0; j<b;j++){
                Pawn pawn = null;
                if(j < layers && (i+j)%2==1){
                    pawn=new Pawn(i,j,false,false);
                    root.getChildren().add(pawn);
                }
                if(j >= a-layers && (i+j)%2==1){
                    pawn=new Pawn(i,j,true,false);
                    root.getChildren().add(pawn);
                }

            }
        }
    }
}

class Pawn extends Circle{
    public Pawn(int a, int b, boolean isWhite, boolean isQueen){
        setCenterX(Main.margin/2+(a+0.5)*Main.tileSize);
        setCenterY(Main.margin/2+(b+0.5)*Main.tileSize);
        setRadius(Main.tileSize*0.4);
        if(isWhite==true){ setFill(Color.WHITE );
        } else{ setFill(Color.BLACK );
        }
        if(isQueen==true){
            setStroke(Color.BLUE);
            setStrokeWidth(8);
        }
    }
}