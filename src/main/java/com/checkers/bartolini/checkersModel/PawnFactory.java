package com.checkers.bartolini.checkersModel;

import javafx.scene.Node;

public interface PawnFactory {
    Pawn getPawn(Pawn.Team team);  //nie działa Node


    Pawn getQueen(Pawn.Team team);
}
