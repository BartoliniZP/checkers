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
            return new Pawn(team,new NormalPawnPossibleMovesChecker(false,true),new TextureWrapper(new Circle(tileSize*pawnScale, Color.WHITE)));
        }
        else {
            return new Pawn(team,new NormalPawnPossibleMovesChecker(false,true),new TextureWrapper(new Circle(tileSize*pawnScale, Color.BLACK)));
        }
    }

    @Override
    public Pawn getQueen(Pawn.Team team) {
        Circle circle = new Circle(tileSize*pawnScale);
        circle.setStrokeWidth(7.0);
        circle.setStroke(Color.BLUE);
        if(team== Pawn.Team.WHITE) {
            circle.setFill(Color.WHITE);
            return new Pawn(team, new QueenPawnPossibleMovesChecker(true,true),new TextureWrapper(circle));
        }
        else {
            circle.setFill(Color.BLACK);
            return new Pawn(team, new QueenPawnPossibleMovesChecker(true,true),new TextureWrapper(circle));
        }
     }
}
