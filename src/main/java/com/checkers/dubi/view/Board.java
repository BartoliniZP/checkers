package com.checkers.dubi.view;

import com.checkers.Main;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Board {
     int height;
     int width;
     Color color1;
     Color color2;
     Pane root;
     int tileSize;
    Color highlightFieldSelectedPawnColor = Color.MEDIUMPURPLE;
    Color highlightFieldPossibleMoveColor = Color.PURPLE;
    Stage stage;

    public Board(int height, int width, Color color1, Color color2, Pane root, int tileSize, Stage stage) {
        this.height =height;
        this.width=width;
        this.color1=color1;
        this.color2=color2;
        this.root=root;
        this.tileSize=tileSize;
        this.pawns = new Node[height][width];
        this.fields = new Rectangle[height][width];
        this.stage=stage;
    }
    Node[][] pawns;
    Rectangle[][] fields;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void drawBoard() throws InterruptedException {

        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                int iCopy = i;
                int jCopy=j;
                Rectangle rectangle = new Rectangle(i*tileSize,j*tileSize,tileSize,tileSize);
                if((i+j)%2==0){
                    rectangle.setFill(color1);
                } else{
                    rectangle.setFill(color2);
                }
                Runnable task = () -> {
                    Platform.runLater(() -> {
                        root.getChildren().add(rectangle);
                        fields[iCopy][jCopy]=rectangle;
                    });
                };
                Thread t = new Thread(task);
                t.start();
                t.join();


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

    /*public void addPawn(int xPos, int yPos, Pawn pawn){
       if(pawns[yPos][xPos]!=null){
            throw new IllegalArgumentException("Tile already taken");
        }
        boolean isWhite = pawn.getColor();
        Node p = pawn.getTexture();
        p.setTranslateX((yPos+0.5)*tileSize);
        p.setTranslateY((xPos+0.5)*tileSize);
        root.getChildren().add(p);
        pawns[yPos][xPos]=p;
    }*/
    public void addPawn(int x, int y, String type, String color) throws InterruptedException {

        /*if(pawns[y][x]!=null){
            throw new IllegalArgumentException("Tile already taken");
        }*/
        boolean isWhite;
        if(color.equals("W")){
            isWhite = true;
        } else{
            isWhite = false;
        }
        Node p;
        if(type.equals("Q")){
            QueenPawn qp = new QueenPawn(isWhite);
            p = qp.getTexture();
        } else{
            NormalPawn np = new NormalPawn(isWhite);
            p = np.getTexture();
        }

        p.setTranslateX((y+0.5)*tileSize);
        p.setTranslateY((x+0.5)*tileSize);
        Runnable task = () -> {
            Platform.runLater(() -> {
                root.getChildren().add(p);
                pawns[y][x]=p;
            });
        };
        Thread t = new Thread(task);
        t.start();
        t.join();



    }

    /*public void removePawn(double xCursor, double yCursor){
        int x=getFieldCoordinatesOnBoard(xCursor, yCursor)[0];
        int y=getFieldCoordinatesOnBoard(xCursor, yCursor)[1];
        if(pawns[y][x]!=null){
            root.getChildren().remove(pawns[y][x]);
            pawns[y][x]=null;
        }

    }*/
    public void removePawn(int x, int y) throws InterruptedException {

        //System.out.println("removed " + x +" " +y);
        if(pawns[y][x]!=null){
            Runnable task = () -> {
                Platform.runLater(() -> {
                    root.getChildren().remove(pawns[y][x]);
                    pawns[y][x]=null;
                });
            };
            Thread t = new Thread(task);
            t.start();
            t.join();


        }
    }

   /* public void highlightFieldSelectedPawn(double xCursor, double yCursor) {
        int x=getFieldCoordinatesOnBoard(xCursor, yCursor)[0];
        int y=getFieldCoordinatesOnBoard(xCursor, yCursor)[1];

            fields[y][x].setFill(highlightFieldSelectedPawnColor );

    }*/
   public void highlightFieldSelectedPawn(int x, int y){
       fields[y][x].setFill(highlightFieldSelectedPawnColor );
   }
/*
    public void highlightFieldPossibleMove(double xCursor, double yCursor){
        int x=getFieldCoordinatesOnBoard(xCursor, yCursor)[0];
        int y=getFieldCoordinatesOnBoard(xCursor, yCursor)[1];
            fields[y][x].setFill(highlightFieldPossibleMoveColor);

    }*/

    public void highlightFieldPossibleMove(int x, int y){
        fields[y][x].setFill(highlightFieldPossibleMoveColor);
    }


    /*public void removeHighlightField(double xCursor, double yCursor) {
        int x=getFieldCoordinatesOnBoard(xCursor, yCursor)[0];
        int y=getFieldCoordinatesOnBoard(xCursor, yCursor)[1];
        if((x+y)%2==0){
            fields[y][x].setFill(color1);
        } else{
            fields[y][x].setFill(color2);
        }
    }*/

    public void removeHighlightField(int x, int y){
        if((x+y)%2==0){
            fields[y][x].setFill(color1);
        } else{
            fields[y][x].setFill(color2);
        }
    }

    public void removeAllHighlights(){
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++) {
                removeHighlightField(i,j);
                }
            }
    }

    public void gameFinished(int result) throws InterruptedException {
        TilePane tilePane = new TilePane();
        Label label = new Label();
        if(result == 1){
            label.setText("White won!");
        } else  if(result == 0){
            label.setText("Black won!");
        } else {
            label.setText("Invalid state!");
        }



        label.setMinWidth(100);
        label.setMinHeight(100);
        Runnable task = () -> {
            Platform.runLater(() -> {
                if(ServerInputHandler.team==0) {
                    Rotate rotate = new Rotate();
                    rotate.setPivotX(Main.overallSize/2);
                    rotate.setPivotY(Main.overallSize/2);
                    root.getTransforms().addAll(rotate);
                    rotate.setAngle(180);
                }


                label.setTextFill(Color.YELLOW);
                label.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(5.0), Insets.EMPTY)));
                label.setTextAlignment(TextAlignment.CENTER);
                label.setMinWidth(200);
                label.setMinHeight(100);
                label.setMaxWidth(200);
                label.setMaxHeight(100);
                label.setLayoutX(root.getLayoutX()+Main.overallSize/2-100);
                label.setLayoutY(root.getLayoutY()+Main.overallSize/2-50);
                label.setFont(new Font("Arial", 40));
                root.getChildren().add(label);
            });
        };
        Thread t = new Thread(task);
        t.start();
        t.join();

    }

    public void team(int value) throws InterruptedException {
        Runnable task = () -> {
            Platform.runLater(() -> {
                if(value==0){
                    Rotate rotate = new Rotate();
                    rotate.setPivotX(Main.overallSize/2);
                    rotate.setPivotY(Main.overallSize/2);
                    root.getTransforms().addAll(rotate);
                    rotate.setAngle(180);
                }
            });

        };
        Thread t = new Thread(task);
        t.start();
        t.join();


    }
}
