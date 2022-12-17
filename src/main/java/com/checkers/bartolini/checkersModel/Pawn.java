package com.checkers.bartolini.checkersModel;

import javafx.scene.text.Text;

public class Pawn {
    public enum Team {
        WHITE, BLACK
    }
    private Team team;
    private PossibleMovesChecker thisPawnMoves;
    private TextureWrapper thisPawnTexture;

    public Team getTeam() {
        return team;
    }

    public Pawn(Team team, PossibleMovesChecker thisPawnMoves, TextureWrapper thisPawnTexture) {
        this.team = team;
        this.thisPawnMoves = thisPawnMoves;
        this.thisPawnTexture = thisPawnTexture;
    }

    public Pawn(Pawn toCopy) {
        this.team = toCopy.team;
        this.thisPawnTexture = toCopy.thisPawnTexture.clone();
        this.thisPawnMoves = toCopy.thisPawnMoves.clone();
    }

    public Pawn clone() {
        return new Pawn(this);
    }
    public PossibleMovesChecker getPossibleMovesChecker() {
        return thisPawnMoves;
    }

    public TextureWrapper getPawnTexture() {
        return thisPawnTexture;
    }

    @Override
    public String toString() {
        return "P";
    }

}
