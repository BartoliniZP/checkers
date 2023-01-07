package com.checkers.server;

import com.checkers.bartolini.checkersModel.Pawn;
import com.checkers.bartolini.checkersModel.gameState;
import javafx.util.Pair;

public class standardInterpreterIntoGameState implements clientInputHandler.clientInputInterpreter {
    gameState gameReference;
    Pawn.Team clientsTeam;
    @Override
    public void interpret(String input) {
        String[] splitInput = input.split(" ");
        switch (splitInput[0]) {
            case "mouseClick":
                if (gameReference.getWhoToMove() == clientsTeam) {
                    gameReference.fieldClicked(new Pair<>(Integer.parseInt(splitInput[1]), Integer.parseInt(splitInput[2])));
                }
                break;
            case "resign":
                if (gameReference.getWhoToMove() == clientsTeam) {
                    gameReference.resign();
                }
                break;
            default:
                System.out.println("Client passed incorrect command: " + splitInput[0]);
                break;
        }
    }

    public standardInterpreterIntoGameState(gameState gameReference, Pawn.Team clientsTeam) {
        this.gameReference = gameReference;
        this.clientsTeam = clientsTeam;
    }
}
