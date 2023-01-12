package com.checkers.dubi.view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ServerCheckersView implements ServerInputHandler.viewHandlingCommands{
    Board board;
   // int height;
   // int width;
    Color color1;
    Color color2;
    Pane root;
    int tileSize;


    //public ServerCheckersView(int height, int width,Color color1,Color color2, Pane root, int tileSize){
    public ServerCheckersView(Color color1,Color color2, Pane root){
       // this.height=height;
        //this.width=width;
        this.color1=color1;
        this.color2=color2;
        this.root=root;
    }

    @Override
    public void onTeam(int value) {
        board.team(value);
    }
    @Override
    public void onDrawBoard(int height, int width) throws InterruptedException {
        tileSize=ServerInputHandler.returnTileSize();
        board = new Board(height, width,color1,color2,root,tileSize);

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
        board.removeAllHighlights();
    }

    @Override
    public void onAddPawn(int x, int y, String type, String color) throws InterruptedException {
        board.addPawn(x,y,type,color);
    }

    @Override
    public void onRemovePawn(int x, int y) throws InterruptedException {
        board.removePawn(x,y);
    }

    @Override
    public void onGameFinished(int result) throws InterruptedException {
        board.gameFinished(result);
    }
}
