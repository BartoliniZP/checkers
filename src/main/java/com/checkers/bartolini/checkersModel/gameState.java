package com.checkers.bartolini.checkersModel;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class gameState {
    private Board board;
    private winCondition winConditionChecker;
    private Pawn.Team whoToMove;
    private PermittedMovesRules moveRestrictions;
    private PawnFactory queenCreator;
    private List<Move> moveHistory;
    private List<Move> currentlyPossibleMoves; //
    private Field currentlySelectedField;
    private winCondition.gameState isGameFinished;
    private gameView view;

    public gameState(winCondition conditionOfWin, PermittedMovesRules moveRestrictions, boardBuilder boardBuild, PawnFactory pawnGenerator) {
        this.board = boardBuild.createBoard();
        this.winConditionChecker = conditionOfWin;
        this.whoToMove = Pawn.Team.WHITE;
        this.moveRestrictions = moveRestrictions;
        this.queenCreator = pawnGenerator;
        this.moveHistory = new ArrayList<>();
        this.currentlyPossibleMoves = this.moveRestrictions.getPermittedMoves(board,whoToMove);
        this.currentlySelectedField = null;
        this.isGameFinished = this.winConditionChecker.checkForGameFinish(board);
        this.view = null;

    }

    public Pawn.Team getWhoToMove() {
        return whoToMove;
    }

    public interface gameView {
        void drawBoard(Board b);

        void gameFinish(winCondition.gameState whoWon);

        void unhighlightField(Pair<Integer, Integer> field);

        void unhighlightAllFields();

        void selectedField(Pair<Integer, Integer> field);

        void highlightPossibleMove(Pair<Integer, Integer> moves);

        void addPawn(TextureWrapper pawnTexture, Pair<Integer, Integer> field);

        void removePawn(Pair<Integer, Integer> field);
    }

    public void setView(gameView view) {
        this.view = view;
        if (view != null) view.drawBoard(board);
    }


    public void resign() {
        if (isGameFinished != winCondition.gameState.noWinner) return;
        if (whoToMove == Pawn.Team.WHITE) {
            isGameFinished = winCondition.gameState.BlackWin;
        } else {
            isGameFinished = winCondition.gameState.WhiteWin;
        }
        if (view != null) view.gameFinish(isGameFinished);
    }

    public Board getBoard() { //todo remove
        return board;
    }

    public void fieldClicked(Pair<Integer, Integer> field) {
        if (isGameFinished != winCondition.gameState.noWinner) return;
        if (currentlySelectedField == null) {
            selectPawnToMoveByCurrentPlayer(field);
            return;
        } else {
            Field fi = board.getFieldAtPos(field.getKey(), field.getValue());
            if (fi.containsPawn()) {
                unselectCurrentField();
                selectPawnToMoveByCurrentPlayer(field);
            } else {
                Move potentialMove = checkIfMoveIsPossible(currentlySelectedField, fi);
                if (potentialMove == null) {
                    unselectCurrentField();
                } else {
                    applyMove(potentialMove);
                    unselectCurrentField();
                }
            }
        }

    }

    public void undo() {
        if (isGameFinished != winCondition.gameState.noWinner) return;
        if (moveHistory.isEmpty()) return;
        Move moveToUndo = moveHistory.get(moveHistory.size() - 1);
        moveHistory.remove(moveHistory.size() - 1);
        try {
            moveToUndo.undoMove();
            informView(moveToUndo, false);
        } catch (IncorrectMoveException e) {
            //todo some critical exception
        }
    }


    protected void applyMove(Move move) {
        if(isGameFinished!= winCondition.gameState.noWinner) return;
        try {
            move.applyMove();
            moveHistory.add(move);
            informView(move, true);
            if (whoToMove == Pawn.Team.WHITE) whoToMove = Pawn.Team.BLACK;
            else whoToMove = Pawn.Team.WHITE;
            currentlyPossibleMoves = moveRestrictions.getPermittedMoves(board,whoToMove);
            isGameFinished = winConditionChecker.checkForGameFinish(board);
            informViewAboutResult();
        } catch (IncorrectMoveException e) {

        }

    }

    private void unselectCurrentField() {
        if (currentlySelectedField == null) return;
        currentlySelectedField = null;
        if(view==null) return;
        view.unhighlightField(convertFieldToPair(currentlySelectedField));
        view.unhighlightAllFields();
    }

    private Move checkIfMoveIsPossible(Field from, Field to) {
        for (Move move : currentlyPossibleMoves) {
            if (move.getStart() == from && move.getDestination() == to) {
                return move;
            }
        }
        return null;
    }

    private void informView(Move move, boolean apply) { //called only after applying move //boolean apply: true for apply, false for redo
        if (view == null) return;
        if (apply) {
            view.removePawn(convertFieldToPair(move.getStart()));
            view.addPawn(move.getDestination().getPawnOnField().getPawnTexture(), convertFieldToPair(move.getDestination()));
            if (move.getCaptured() != null) view.removePawn(convertFieldToPair(move.getCaptured()));
            Move followingMove = move.getFollowingMove();
            while (followingMove != null) {
                if (followingMove.getCaptured() != null)
                    view.removePawn(convertFieldToPair(followingMove.getCaptured()));
                followingMove = followingMove.getFollowingMove();
            }
        } else {
            view.addPawn(move.getStart().getPawnOnField().getPawnTexture(), convertFieldToPair(move.getStart()));
            view.removePawn(convertFieldToPair(move.getDestination()));
            if (move.getCaptured() != null)
                view.addPawn(move.getCaptured().getPawnOnField().getPawnTexture(), convertFieldToPair(move.getCaptured()));
            Move followingMove = move.getFollowingMove();
            while (followingMove != null) {
                if (followingMove.getCaptured() != null)
                    view.addPawn(move.getCaptured().getPawnOnField().getPawnTexture(), convertFieldToPair(followingMove.getCaptured()));
                followingMove = followingMove.getFollowingMove();
            }
        }
    }

    private Pair<Integer, Integer> convertFieldToPair(Field field) {
        if (field == null) return null;
        return new Pair<>(field.getRow(), field.getColumn());
    }

    private void informViewAboutResult() {
        if (view == null) return;
        if (isGameFinished != winCondition.gameState.noWinner) {
            view.gameFinish(isGameFinished);
        }
    }

    private List<Move> findCurrentlyPossibleMovesFrom(Field f) {
        List<Move> toReturn = new ArrayList<>();
        for (Move move : currentlyPossibleMoves) {
            if (move.getStart() == f) {
                toReturn.add(move);
            }
        }
        return toReturn;
    }

    private void selectPawnToMoveByCurrentPlayer(Pair<Integer, Integer> field) {
        Field fi = board.getFieldAtPos(field.getKey(), field.getValue());
        if (fi.containsPawn()) {
            if (fi.getPawnOnField().getTeam() == whoToMove) {
                List<Move> potentialMoves = findCurrentlyPossibleMovesFrom(fi);
                if (!potentialMoves.isEmpty()) {
                    currentlySelectedField = fi;
                    if(view==null) return;
                    view.selectedField(convertFieldToPair(fi));
                    for (Move move : potentialMoves) {
                        view.highlightPossibleMove(convertFieldToPair(move.getDestination()));
                    }
                }
            }
        }
    }


}
