package com.checkers.dubi.view;

import com.checkers.Main;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class pawnsDraw{
    public static void drawPawns(Group root){
        for(int i=0;i<8;i++){
            for(int j=0; j<8;j++){
                if((i+j)%2==1 && j<3){
                    Circle circle = new Circle();
                    circle.setRadius(25);
                    circle.setFill(Color.BLACK);
                    if(j%2==0){
                        circle.setCenterX(85+70*i);
                        circle.setCenterY(85+70*j);
                    } else{
                        circle.setCenterX(85+70*i);
                        circle.setCenterY(85+70*j);
                    }

                    root.getChildren().add(circle);
                }

                if((i+j)%2==0 &&j>4){
                    if((i+j)%2==0){
                        Circle circle = new Circle();
                        circle.setRadius(25);
                        circle.setFill(Color.WHITE);
                        if(j%2==0){
                            circle.setCenterX(155+70*i);
                            circle.setCenterY(85+70*j);
                        } else{
                            circle.setCenterX(15+70*i);
                            circle.setCenterY(85+70*j);
                        }

                        root.getChildren().add(circle);
                    }
                }


            }
        }
    }
}
