package com.checkers;

import com.checkers.dubi.view.*;
import com.checkers.dubi.view.Board;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
/*
//<<<<<<< HEAD
    @Override
    public void start(Stage stage) throws IOException {
        gameState game = new gameState(new StandardWinCondition(),new BestTakeObligatory(),new StandardCheckersBoardBuilder(8,8,3,new NormalPawnFactory()),new NormalPawnFactory());
        System.out.println(game.getBoard().toString());
        game.fieldClicked(new Pair<>(5,2));
        game.fieldClicked(new Pair<>(4,3));

        game.fieldClicked(new Pair<>(2,1));
        game.fieldClicked(new Pair<>(3,2));

        game.fieldClicked(new Pair<>(4,3));
        game.fieldClicked(new Pair<>(2,1));

        game.fieldClicked(new Pair<>(1,0));
        game.fieldClicked(new Pair<>(3,2));

        game.fieldClicked(new Pair<>(4,0));
        game.fieldClicked(new Pair<>(5,0));
        game.fieldClicked(new Pair<>(5,4));
        game.fieldClicked(new Pair<>(4,3));

        game.fieldClicked(new Pair<>(2,7));
        game.fieldClicked(new Pair<>(3,6));

        game.fieldClicked(new Pair<>(3,2));
        game.fieldClicked(new Pair<>(4,1));

        game.fieldClicked(new Pair<>(3,2));
        game.fieldClicked(new Pair<>(5,4));



        if(game.getWhoToMove()== Pawn.Team.WHITE) {
            System.out.println("It works");
        }
        game.fieldClicked(new Pair<>(6,5));
        game.fieldClicked(new Pair<>(4,3));
        if(game.getWhoToMove()== Pawn.Team.BLACK) {
            System.out.println("It works 100%");
        }

        System.out.println(game.getBoard().toString());
        game.undo();
        game.undo();
        game.undo();
        game.undo();
        game.undo();
        game.undo();
        game.undo();
        System.out.println(game.getBoard().toString());
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();*/
//=======

    public static final int tileSize = 50;
    public static final double pawnScale = 0.4;

    public static final int margin = 100;
    public static final int width = 10;
    public static final int height = 10;
    public static final int layersOfPawns = 3;
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

     //   board.addPawn(3, 3, new QueenPawn(false));
      //  board.addPawn(3, 4, new QueenPawn(true));



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