package com.checkers.dubi.view;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ServerCheckersView implements ServerInputHandler.viewHandlingCommands{
    Board board;
    int height;
    int width;
    Color color1;
    Color color2;
    Pane root;
    int tileSize;
    Stage stage;



    public ServerCheckersView(int height, int width,Color color1,Color color2, Pane root, int tileSize, Stage stage){

        this.height=height;
        this.width=width;
        this.color1=color1;
        this.color2=color2;
        this.root=root;
        this.tileSize=tileSize;
        this.stage = stage;
    }

    @Override
    public void onTeam(int value) {
        board.team(value);
    }
    @Override
    public void onDrawBoard(int height, int width) {
        board = new Board(height, width,color1,color2,root,tileSize, stage);
        board.drawBoard();
    }

    @Override
    public void onSelected(int x, int y) {
        board.highlightFieldSelectedPawn(x, y);
    }

    @Override
    public void onPotential(int x, int y) {
        board.highlightFieldPossibleMove(x,y);
    }

    @Override
    public void onClearHighlights() {
        board.removeAllHighlights(height,width);
    }

    @Override
    public void onAddPawn(int x, int y, String type, String color) {
        board.addPawn(x,y,type,color);
    }

    @Override
    public void onRemovePawn(int x, int y) {
        board.removePawn(x,y);
    }

    @Override
    public void onGameFinished(int result) {
        board.gameFinished(result);
    }
}
