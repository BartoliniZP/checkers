package com.checkers;

import com.checkers.bartolini.checkersModel.Board;
import com.checkers.bartolini.checkersModel.Field;
import com.checkers.bartolini.checkersModel.NormalPawnFactory;
import com.checkers.bartolini.checkersModel.StandardCheckersBoardBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(8,8,3, new NormalPawnFactory());
        Board board  = builder.createBoard();
        System.out.println(board.toString());
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