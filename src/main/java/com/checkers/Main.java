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

  //  public static final int margin = 100;
    public static final int width = 8;
    public static final int height = 8;
    public static final int layersOfPawns = 2;
   // public static final Color pawnColor1 = Color.BLACK;
    //public static final Color pawnColor2 = Color.WHITE;
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
                    if (e.getButton() == MouseButton.FORWARD) {
                        double clickX = e.getX();
                        double clickY = e.getY();
                        board.removePawn(clickX,clickY);
                        System.out.println(board.getFieldCoordinatesOnBoard(clickX, clickY)[0]+" "+board.getFieldCoordinatesOnBoard(clickX, clickY)[1]);
                    }
                    if (e.getButton() == MouseButton.PRIMARY){
                        double clickX = e.getX();
                        double clickY = e.getY();
                        board.highlightFieldSelectedPawn(clickX,clickY);
                        board.highlightFieldPossibleMove(clickX,clickY);
                    }
                    if (e.getButton() == MouseButton.SECONDARY){
                        double clickX = e.getX();
                        double clickY = e.getY();
                        board.removehighlightField(clickX,clickY);
                    }
                });





        /*
        Button addTale = new Button("AddTales");
        addTale.setOnAction( actionEvent ->
        {
            boardDraw.drawBoard(width,height,root);
        });


        Button addPawns = new Button("AddPawns");
        addPawns.setLayoutX(100);
        addPawns.setOnAction( actionEvent ->
        {
            pawnsDraw.drawPawns(width, height, root, layersOfPawns);

        });

        root.setOnMouseClicked(e->
        {
            if (e.getButton() == MouseButton.SECONDARY){
                double clickX = e.getX();
                double clickY = e.getY();
                Pawn.removePawn(clickX,clickY,root);
            }
            if (e.getButton() == MouseButton.PRIMARY){
                double clickX = e.getX();
                double clickY = e.getY();
                Pawn.addPawn(clickX,clickY,true,true,root);
            }
            if (e.getButton()==MouseButton.FORWARD){
                double clickX = e.getX();
                double clickY = e.getY();
                Highlight.highlightTile(clickX,clickY,root);
            }
            if (e.getButton()==MouseButton.BACK){
                double clickX = e.getX();
                double clickY = e.getY();
                Highlight.removeHighlight(clickX,clickY,root);
            }
            if(e.getButton()==MouseButton.MIDDLE){
                double clickX = e.getX();
                double clickY = e.getY();
                Pawn.movePawn(clickX,clickY, clickX-tileSize,clickY-tileSize,root);
            }
        });



        root.getChildren().addAll(addTale, addPawns);*/


        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }




    public static void main(String[] args) {
        launch();
    }
}