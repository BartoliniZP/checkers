package com.checkers;

import com.checkers.bartolini.checkersModel.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;

public class Main extends Application {

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
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}