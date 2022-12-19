package com.checkers.bartolini.checkersModel;

import javafx.scene.Node;

public class NormalPawnFactory implements PawnFactory {
    @Override
    public Pawn getPawn(Pawn.Team team) { //todo texture wrapper
        return new Pawn(team,new NormalPawnPossibleMovesChecker(false,true),new TextureWrapper());
    }

    @Override
    public Pawn getQueen(Pawn.Team team) { //todo texture wrapper
        return new Pawn(team, new QueenPawnPossibleMovesChecker(true,true),new TextureWrapper());
    }
}
