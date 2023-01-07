package com.checkers;

import com.checkers.bartolini.checkersModel.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;

public class Main extends Application {

    public static final int tileSize = 80;
    public static final double pawnScale = 0.4;
    public static final int width = 8;
    public static final int height = 8;
    public static final int layersOfPawns = 3;

    @Override
    public void start(Stage primaryStage) throws InterruptedException {

        gameState standardGame = new gameState(new StandardWinCondition(),new AnyTakeObligatory(),new StandardCheckersBoardBuilder(height,width,layersOfPawns,new NormalPawnFactory()),new NormalPawnFactory(), new PromoteToQueenOnLastRank(new NormalPawnFactory()));

        primaryStage.setTitle("Checkers Offline BartoliniZP & Dubi v1.0");
        Pane root = new Pane();
        Scene scene = new Scene(root, tileSize*height, tileSize*width);
        
        checkersView gameView = new checkersView(root,tileSize,Color.ANTIQUEWHITE, Color.DARKGREEN,primaryStage);
        standardGame.setView(gameView);

        root.setOnMouseClicked(e->
                {
                    if (e.getButton() == MouseButton.PRIMARY) {
                        double clickX = e.getX();
                        double clickY = e.getY();
                        int x = (int)clickX/tileSize;
                        int y = (int)clickY/tileSize;
                        standardGame.fieldClicked(new Pair<>(y,x));
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