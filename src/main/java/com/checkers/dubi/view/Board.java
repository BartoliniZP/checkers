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
     int tileSize;
    Color highlightFieldSelectedPawnColor = Color.MEDIUMPURPLE;
    Color highlightFieldPossibleMoveColor = Color.PURPLE;

    public Board(int height, int width, Color color1, Color color2, Pane root, int tileSize) {
        this.height =height;
        this.width=width;
        this.color1=color1;
        this.color2=color2;
        this.root=root;
        this.tileSize=tileSize;
        this.pawns = new Node[width][height];
        this.fields = new Rectangle[width][height];
    }
    Node[][] pawns;
    Rectangle[][] fields;

    public void drawBoard(int height, int width){

        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                Rectangle rectangle = new Rectangle(i*tileSize,j*tileSize,tileSize,tileSize);
                if((i+j)%2==0){
                    rectangle.setFill(color1);
                } else{
                    rectangle.setFill(color2);
                }

                root.getChildren().add(rectangle);
                fields[i][j]=rectangle;
            }
        }


    }

    public int[] getFieldCoordinatesOnBoard(double x, double y){
        int[] coordinates = new int[2];
         //0 is x, 1 is y
        coordinates[0]=(int)x/tileSize;
        coordinates[1]=(int)y/tileSize;
        return coordinates;
    }

    public void addPawn(int xPos, int yPos, Pawn pawn){
       if(pawns[xPos][yPos]!=null){
            throw new IllegalArgumentException("Tile already taken");
        }
        boolean isWhite = pawn.getColor();
        Node p = pawn.getTexture();
        p.setTranslateX((xPos+0.5)*tileSize);
        p.setTranslateY((yPos+0.5)*tileSize);
        root.getChildren().add(p);
        pawns[xPos][yPos]=p;
    }

    public void removePawn(double xCursor, double yCursor){
        int x=getFieldCoordinatesOnBoard(xCursor, yCursor)[0];
        int y=getFieldCoordinatesOnBoard(xCursor, yCursor)[1];
        if(pawns[x][y]!=null){
            root.getChildren().remove(pawns[x][y]);
            pawns[x][y]=null;
        }

    }

    public void highlightFieldSelectedPawn(double xCursor, double yCursor) {
        int x=getFieldCoordinatesOnBoard(xCursor, yCursor)[0];
        int y=getFieldCoordinatesOnBoard(xCursor, yCursor)[1];

            fields[x][y].setFill(highlightFieldSelectedPawnColor );

    }

    public void highlightFieldPossibleMove(double xCursor, double yCursor){
        int x=getFieldCoordinatesOnBoard(xCursor, yCursor)[0];
        int y=getFieldCoordinatesOnBoard(xCursor, yCursor)[1];
            fields[x][y].setFill(highlightFieldPossibleMoveColor);

    }
    public void removehighlightField(double xCursor, double yCursor) {
        int x=getFieldCoordinatesOnBoard(xCursor, yCursor)[0];
        int y=getFieldCoordinatesOnBoard(xCursor, yCursor)[1];
        if((x+y)%2==0){
            fields[x][y].setFill(color1);
        } else{
            fields[x][y].setFill(color2);
        }
    }

}
