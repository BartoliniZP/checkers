package com.checkers.dubi.view;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class boardDraw {
    public static void drawBoard(int a, int b, Group root){
        for(int i=0;i<a;i++){
            for(int j=0;j<b;j++){
                Rectangle rectangle = new Rectangle();
                if((i+j)%2 ==0){
                    rectangle.setFill(Color.RED);
                } else{
                    rectangle.setFill(Color.GREEN);
                }
                rectangle.setHeight(70);
                rectangle.setWidth(70);
                rectangle.setY(50+70*i);
                rectangle.setX(50+70*j);
                root.getChildren().add(rectangle);
            }
        }
    }

}
