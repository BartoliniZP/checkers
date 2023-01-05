package com.checkers;

import com.checkers.bartolini.checkersModel.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Pair;

public class checkersView implements gameState.gameView {

    // todo konstruktor który ma przyjąć pozostałe rzeczy do zainicjsdbsjgfh boarda

    Pane root;
    Stage stage;
    int tileSize;
    com.checkers.dubi.view.Board board;
    Color color1;
    Color color2;
    public checkersView( Pane root, int tileSize, Color color1, Color color2, Stage stage){
        this.tileSize = tileSize;
        this.color1 = color1;
        this.color2 = color2;
        this.root = root;
        this.stage = stage;
    }


    @Override
    public void drawBoard(Board b) {
        board = new com.checkers.dubi.view.Board(b.getHeight(),b.getWidth(), color1, color2,root,tileSize);
        board.drawBoard();
        for(int i=0;i<b.getHeight();i++) {
            for (int j = 0; j < b.getWidth(); j++) {
                if(b.getFieldAtPos(i,j).containsPawn()){
                    board.addPawn(i,j,b.getFieldAtPos(i,j).getPawnOnField().getPawnTexture().getTexture());
                }
            }
        }
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

        popup.show(stage); //?????????????????/ todo przekazać stage i zapisać stage jako zmienną i się odnieść
    }

    @Override
    public void unhighlightField(Pair<Integer, Integer> field) {
        double xCursor = field.getKey()*tileSize+0.1;
        double yCursor = field.getValue()*tileSize+0.1;
        board.removehighlightField(xCursor, yCursor);
    }

    @Override
    public void unhighlightAllFields() {
        for(int i=0;i< board.getHeight();i++){
            for (int j=0;j<board.getWidth();j++){
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
           board.addPawn(field.getKey(),field.getValue(),pawnTexture.getTexture());  //cannot convert
    }

    @Override
    public void removePawn(Pair<Integer, Integer> field) {
        double xCursor = field.getKey()*tileSize+0.1;
        double yCursor = field.getValue()*tileSize+0.1;
        board.removePawn(xCursor,yCursor);
    }
}
