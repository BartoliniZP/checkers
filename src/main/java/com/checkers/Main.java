package com.checkers;

import com.checkers.dubi.view.boardDraw;
import com.checkers.dubi.view.pawnsDraw;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumnBase;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    public static final int tileSize = 70;

    public static final int margin = 100;
    public static final int width = 8;
    public static final int height = 8;
    public static final int layersOfPawns = 3;
    @Override
    public void start(Stage primaryStage) {


        primaryStage.setTitle("Hello World");
        Pane root = new Pane();
        Scene scene = new Scene(root, tileSize*width+margin, tileSize*height+margin);

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

        root.getChildren().addAll(addTale, addPawns);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}