package com.checkers.dubi.view;

import com.checkers.Main;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Board {
     int height;
     int width;
     Color color1;
     Color color2;
     Pane root;
     double tileSize;


    public Board(int height, int width, Color color1, Color color2, Pane root, double tileSize) {
        this.height =height;
        this.width=width;
        this.color1=color1;
        this.color2=color2;
        this.root=root;
        this.tileSize=tileSize;
        this.pawns = new Pawn[height][width];
        this.fields = new Rectangle[height][width];
    }
    Pawn[][] pawns; //height-width nie działa, temp 100
    Rectangle[][] fields;  //height-width nie działa, temp 100

    public void drawBoard(){

        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                Rectangle rectangle = new Rectangle(i*tileSize,j*tileSize,tileSize,tileSize);
                if((i+j)%2==0){
                    rectangle.setFill(color1);
                } else{
                    rectangle.setFill(color2);
                }
                fields[i][j]=rectangle;
                root.getChildren().add(rectangle);
            }
        }


    }

    public double[] getFieldCoordinatesOnBoard(double x, double y){
        double[] coordinates = new double[2];
         //0 is x, 1 is y
        coordinates[0]=(int)x/50;
        coordinates[1]=(int)y/50;
        return coordinates;
    }

    public void addPawn(int xPos, int yPos, Pawn pawn){
       if(pawns[xPos][yPos]!=null){
            throw new IllegalArgumentException("Tile already taken");
        }
        boolean isWhite = pawn.getColor();
        Node p = pawn.getTexture();
        p.relocate((xPos)* Main.tileSize+((double)tileSize/10.0),(yPos)*Main.tileSize+((double)tileSize/10.0));
        root.getChildren().add(p);
        pawns[xPos][yPos]=pawn;
    }
}
