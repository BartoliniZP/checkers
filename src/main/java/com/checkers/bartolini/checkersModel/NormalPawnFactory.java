package com.checkers.bartolini.checkersModel;

import com.checkers.Main;
import com.checkers.dubi.view.NormalPawn;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class NormalPawnFactory implements PawnFactory {



    int tileSize = Main.tileSize;
    double pawnScale = Main.pawnScale;
    /*
    int tileSize;
    double pawnScale;
    public NormalPawnFactory(int tileSize, double pawnScale){ //37 błędów
        this.tileSize=tileSize;
        this.pawnScale=pawnScale;
    }

    NormalPawnFactory nPF = new NormalPawnFactory(Main.tileSize, Main.pawnScale);*/

    @Override
    public Pawn getPawn(Pawn.Team team) {
        if(team== Pawn.Team.WHITE) {
            com.checkers.dubi.view.NormalPawn p = new com.checkers.dubi.view.NormalPawn(true);
            return new Pawn(team,new NormalPawnPossibleMovesChecker(false,true),new TextureWrapper(p));
        }
        else {
            com.checkers.dubi.view.NormalPawn p = new com.checkers.dubi.view.NormalPawn(false);
            return new Pawn(team,new NormalPawnPossibleMovesChecker(false,true),new TextureWrapper(p));
        }
    }

    @Override
    public Pawn getQueen(Pawn.Team team) {

        if(team== Pawn.Team.WHITE) {
            com.checkers.dubi.view.QueenPawn p = new com.checkers.dubi.view.QueenPawn(true);
            return new Pawn(team, new QueenPawnPossibleMovesChecker(true,true),new TextureWrapper(p));
        }
        else {
            com.checkers.dubi.view.QueenPawn p = new com.checkers.dubi.view.QueenPawn(false);
            return new Pawn(team, new QueenPawnPossibleMovesChecker(true,true),new TextureWrapper(p));
        }
     }
}
