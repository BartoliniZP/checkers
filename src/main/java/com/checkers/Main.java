package com.checkers;

import com.checkers.dubi.view.Highlight;
import com.checkers.dubi.view.Pawn;
import com.checkers.dubi.view.boardDraw;
import com.checkers.dubi.view.pawnsDraw;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumnBase;
import javafx.scene.input.MouseButton;
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
                Pawn.addPawn(clickX,clickY,root);
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
        });

        Button moveTest = new Button("MoveFirstTest");
        moveTest.setLayoutX(200);
        moveTest.setOnAction( actionEvent ->
        {
            Pawn.movePawn(1,0, 0,0,root);
        });

        root.getChildren().addAll(addTale, addPawns,moveTest);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }




    public static void main(String[] args) {
        launch();
    }
}