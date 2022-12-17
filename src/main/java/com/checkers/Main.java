package com.checkers;

import com.checkers.dubi.view.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumnBase;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    public static final int tileSize = 70;
    public static final double pawnSkale = 0.4;

    public static final int margin = 100;
    public static final int width = 8;
    public static final int height = 8;
    public static final int layersOfPawns = 2;
    @Override
    public void start(Stage primaryStage) {


        primaryStage.setTitle("Hello World");
        Pane root = new Pane();
        Scene scene = new Scene(root, tileSize*height, tileSize*width);
        Board board = new Board(height,width, Color.RED, Color.GREEN,root,tileSize);
        board.drawBoard();


        for(int i=0;i<height;i++){
            for(int j=0; j<width;j++){
                if(j < layersOfPawns && (i+j)%2==1) {
                    board.addPawn(i,j, new NormalPawn(false));
                }
                if(j >= height-layersOfPawns && (i+j)%2==1){
                    board.addPawn(i,j, new NormalPawn(true));
                }

            }
        }

        board.addPawn(3, 3, new QueenPawn(false));
        board.addPawn(3, 4, new QueenPawn(true));



        root.setOnMouseClicked(e->
                {
                    if (e.getButton() == MouseButton.PRIMARY) {
                        double clickX = e.getX();
                        double clickY = e.getY();
                        board.removePawn(clickX,clickY);
                    }
                    if (e.getButton() == MouseButton.FORWARD){
                        double clickX = e.getX();
                        double clickY = e.getY();
                        board.highlightFieldPossibleMove(clickX,clickY);
                    }
                    if (e.getButton() == MouseButton.MIDDLE){
                        double clickX = e.getX();
                        double clickY = e.getY();
                        board.removehighlightField(clickX,clickY);
                    }
                    if (e.getButton() == MouseButton.BACK){
                        double clickX = e.getX();
                        double clickY = e.getY();
                        board.highlightFieldSelectedPawn(clickX,clickY);
                    }
                });



        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }




    public static void main(String[] args) {
        launch();
    }
}