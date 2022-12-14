package com.checkers;

import com.checkers.dubi.view.boardDraw;
import com.checkers.dubi.view.pawnsDraw;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World");
        Group root = new Group();
        Scene scene = new Scene(root, 700, 700);

        Button addTale = new Button("AddTale");

        addTale.setOnAction( actionEvent ->
        {
            boardDraw.drawBoard(8,8, root);
        });


        Button addPawns = new Button("AddPawns");
        addPawns.setLayoutX(100);
        addPawns.setOnAction( actionEvent ->
        {
            pawnsDraw.drawPawns(root);

        });

        root.getChildren().addAll(addTale, addPawns);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}