package com.checkers;

import com.checkers.bartolini.checkersModel.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Pair;

public class checkersView implements gameState.gameView {

    // todo konstruktor który ma przyjąć pozostałe rzeczy do zainicjsdbsjgfh boarda
    int height;
    int width;
    Stage primaryStage;
    int tileSize;
    public checkersView(int height, int width, Stage stage, int tileSize){
        this.height=height;
        this.width=width;
        this.primaryStage=stage;
        this.tileSize = tileSize;

    }

    com.checkers.dubi.view.Board board;
    @Override
    public void drawBoard(Board b) {

        board.drawBoard(b.getHeight(), b.getWidth());
    }

    @Override
    public void gameFinish(winCondition.gameState whoWon) {
        TilePane tilePane = new TilePane();
        Label label = new Label();
        if(whoWon == winCondition.gameState.WhiteWin){
            label.setText("White won!");
        } else  if(whoWon == winCondition.gameState.BlackWin){
            label.setText("Black won!");
        } else if(whoWon == winCondition.gameState.InvalidState) {
            label.setText("Invalid state!");
        }

            Popup popup = new Popup();

        label.setStyle("-fx-background-color: yellow;");
        popup.getContent().add(label);

        label.setMinWidth(100);
        label.setMinHeight(80);

        popup.show(primaryStage); //?????????????????/ todo przekazać stage i zapisać stage jako zmienną i się odnieść
    }

    @Override
    public void unhighlightField(Pair<Integer, Integer> field) {
        double xCursor = field.getKey()*tileSize+0.1;
        double yCursor = field.getValue()*tileSize+0.1;
        board.removehighlightField(xCursor, yCursor);
    }

    @Override
    public void unhighlightAllFields() {
        for(int i=0;i< height;i++){
            for (int j=0;j<width;j++){
                board.removehighlightField(i*tileSize+0.1, j*tileSize+0.1);
            }
        }
    }

    @Override
    public void selectedField(Pair<Integer, Integer> field) {
        double xCursor = field.getKey()*tileSize+0.1;
        double yCursor = field.getValue()*tileSize+0.1;
        board.highlightFieldSelectedPawn(xCursor, yCursor);
    }

    @Override
    public void highlightPossibleMove(Pair<Integer, Integer> move) {
        double xCursor = move.getKey()*tileSize+0.1;
        double yCursor = move.getValue()*tileSize+0.1;
        board.highlightFieldPossibleMove(xCursor, yCursor);

    }

    @Override
    public void addPawn(TextureWrapper pawnTexture, Pair<Integer, Integer> field) {
        // todo key 1 wart, v 2
        //  todo addpawn moje
        //   board.addPawn(field.getKey(),field.getValue(),pawnTexture.getTexture());  //cannot convert
    }

    @Override
    public void removePawn(Pair<Integer, Integer> field) {
        //remove z mojej klasy? z twojej? jak się za to zabrać?
        // todo z mojej
        double xCursor = field.getKey()*tileSize+0.1;
        double yCursor = field.getValue()*tileSize+0.1;
        board.removePawn(xCursor,yCursor);
    }
}
