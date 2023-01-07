package com.checkers.server;

import com.checkers.bartolini.checkersModel.*;
import javafx.util.Pair;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static java.lang.System.exit;

public class ClientsInformer implements gameState.gameView {

    gameState referenceToModel;
    DataOutputStream whitePlayer;
    DataOutputStream blackPlayer;

    public ClientsInformer(Socket whitePlayer, Socket blackPlayer, gameState referenceToModel) {
        try {
            this.whitePlayer = new DataOutputStream(whitePlayer.getOutputStream());
            this.blackPlayer = new DataOutputStream(blackPlayer.getOutputStream());
            this.referenceToModel = referenceToModel;
        } catch (Exception ex) {
            System.err.println("Fatal error during creation of informer");
            exit(-1);
        }
    }

    private void sendMsg(String msg, boolean sendToWhite, boolean sendToBlack) {
        try {
            if (sendToWhite) whitePlayer.writeUTF(msg);
            if (sendToBlack) blackPlayer.writeUTF(msg);
        } catch (IOException e) {
            System.err.println("Fatal error");
            exit(-1);
        }
    }


    @Override
    public void drawBoard(Board b) {
        String command = "drawBoard " + b.getHeight() + " " + b.getWidth();
        sendMsg(command,true,true);
        for(int i = 0;i<b.getHeight();i++) {
            for(int j = 0;j<b.getWidth();j++) {
                if(b.getFieldAtPos(i,j).containsPawn()) {
                    this.addPawn(b.getFieldAtPos(i,j).getPawnOnField().getPawnTexture(), new Pair<>(i,j));
                }
            }
        }
    }

    @Override
    public void gameFinish(winCondition.gameState whoWon) {
        if (whoWon == winCondition.gameState.WhiteWin) {
            String command = "gameFinished 1";
            sendMsg(command,true,true);
        } else if (whoWon == winCondition.gameState.BlackWin) {
            String command = "gameFinished 0";
            sendMsg(command,true,true);
        }
    }

    @Override
    public void unhighlightField(Pair<Integer, Integer> field) {
        //don't have to be implemented as it is called only with unhighlightAllFields
    }

    @Override
    public void unhighlightAllFields() {
        String command = "clearHighlights";
        if(referenceToModel.getWhoToMove()== Pawn.Team.WHITE) {
            sendMsg(command,true,false);
        } else {
            sendMsg(command,false,true);
        }
    }

    @Override
    public void selectedField(Pair<Integer, Integer> field) {
        String command = "selected " + field.getKey() + " " + field.getValue();
        if(referenceToModel.getWhoToMove()== Pawn.Team.WHITE) {
            sendMsg(command,true,false);
        } else {
            sendMsg(command,false,true);
        }
    }

    @Override
    public void highlightPossibleMove(Pair<Integer, Integer> move) {
        String command = "potential " + move.getKey() + " " + move.getValue();
        if(referenceToModel.getWhoToMove()== Pawn.Team.WHITE) {
            sendMsg(command,true,false);
        } else {
            sendMsg(command,false,true);
        }
    }

    @Override
    public void addPawn(TextureWrapper pawnTexture, Pair<Integer, Integer> field) {
        String command = "addPawn " + field.getKey() + " " + field.getValue() + " " + pawnTexture.toString();
        sendMsg(command,true,true);
    }

    @Override
    public void removePawn(Pair<Integer, Integer> field) {
        String command = "removePawn " + field.getKey() + " " + field.getValue();
        sendMsg(command,true,true);
    }
}
