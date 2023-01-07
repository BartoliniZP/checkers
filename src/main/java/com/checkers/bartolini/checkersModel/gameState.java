package com.checkers.bartolini.checkersModel;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing checkers game instance that is also responsible for handling user's field click
 * not implemented promoting to queen on last lane
 * example standard 8x8 checkers instance:     gameState standardGame = new gameState(new StandardWinCondition(),new BestTakeObligatory(),new StandardCheckersBoardBuilder(8,8,3,new NormalPawnFactory()),new NormalPawnFactory());
 */
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
    private OnGettingToLastRank onGettingToLastRank;

    public gameState(winCondition conditionOfWin, PermittedMovesRules moveRestrictions, boardBuilder boardBuild, PawnFactory pawnGenerator, OnGettingToLastRank whatToDo) {
        this.board = boardBuild.createBoard();
        this.winConditionChecker = conditionOfWin;
        this.whoToMove = Pawn.Team.WHITE;
        this.moveRestrictions = moveRestrictions;
        this.queenCreator = pawnGenerator;
        this.moveHistory = new ArrayList<>();
        this.currentlyPossibleMoves = this.moveRestrictions.getPermittedMoves(board, whoToMove);
        this.currentlySelectedField = null;
        this.isGameFinished = this.winConditionChecker.checkForGameFinish(board);
        this.view = null;
        this.onGettingToLastRank = whatToDo;

    }

    public Pawn.Team getWhoToMove() {
        synchronized(this) {
            return whoToMove;
        }
    }

    /**
     * gameView interface is a class which instance is informed by gameState about changes in game like move, finish of game, possible moves from user's selected field
     */
    public interface gameView {
        /**
         * Shall draw board on screen
         *
         * @param b board to draw
         */
        void drawBoard(Board b);

        /**
         * Shall inform that game has finished (Eg. pop-up)
         *
         * @param whoWon informs who win using gameState enum
         */
        void gameFinish(winCondition.gameState whoWon);

        /**
         * Shall unhighlight passed field
         *
         * @param field to unhighlight
         */
        void unhighlightField(Pair<Integer, Integer> field);

        /**
         * Shall unhighlight all field
         */
        void unhighlightAllFields();

        /**
         * Shall highlight passed field as if it is field with pawn selected by user to move
         *
         * @param field to highlight
         */
        void selectedField(Pair<Integer, Integer> field);

        /**
         * Shall highlight passed field as if it is possible move for user
         *
         * @param move field to highlight
         */
        void highlightPossibleMove(Pair<Integer, Integer> move);

        /**
         * Shall add pawn with passed Texture on screen
         *
         * @param pawnTexture pawnTexture
         * @param field       where to add pawn
         */
        void addPawn(TextureWrapper pawnTexture, Pair<Integer, Integer> field);

        /**
         * Shall remove pawn on passed position
         *
         * @param field position
         */
        void removePawn(Pair<Integer, Integer> field);
    }

    /**
     * Function to set view informed by this class about changes in game
     *
     * @param view view to set
     */
    public void setView(gameView view) {
        synchronized(this) {
            this.view = view;
            if (view != null) view.drawBoard(board);
        }
    }

    /**
     * Interpreted as resign of current player to move
     */
    public void resign() {
        synchronized(this) {
            if (isGameFinished != winCondition.gameState.noWinner) return;
            if (whoToMove == Pawn.Team.WHITE) {
                isGameFinished = winCondition.gameState.BlackWin;
            } else {
                isGameFinished = winCondition.gameState.WhiteWin;
            }
            if (view != null) view.gameFinish(isGameFinished);
        }
    }

    /**
     * This function shall be called by controller when user presses mouse on some field on board
     *
     * @param field coordinates of clicked field
     */
    public void fieldClicked(Pair<Integer, Integer> field) {
        synchronized(this) {
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
                        unselectCurrentField();
                        applyMove(potentialMove);

                    }
                }
            }
        }

    }

    /**
     * Undo last move
     */
    public void undo() {
        synchronized(this) {
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
    }


    protected void applyMove(Move move) {
        synchronized(this) {
            if (isGameFinished != winCondition.gameState.noWinner) return;
            try {
                move.applyMove();
                moveHistory.add(move);
                informView(move, true);
                boolean promoted = onGettingToLastRank.checkLastRanks(board);
                if (promoted) {
                    refreshLastRanksOnView();
                }
                if (whoToMove == Pawn.Team.WHITE) whoToMove = Pawn.Team.BLACK;
                else whoToMove = Pawn.Team.WHITE;
                currentlyPossibleMoves = moveRestrictions.getPermittedMoves(board, whoToMove);
                isGameFinished = winConditionChecker.checkForGameFinish(board);
                informViewAboutResult();
            } catch (IncorrectMoveException e) {

            }
        }

    }

    protected void refreshLastRanksOnView() {
        synchronized(this) {
            if (view == null) return;
            for (int j = 0; j < board.getWidth(); j++) {
                Field fieldToRefresh = board.getFieldAtPos(0, j);
                if (fieldToRefresh.containsPawn()) {
                    view.removePawn(new Pair<>(0, j));
                    view.addPawn(fieldToRefresh.getPawnOnField().getPawnTexture(), new Pair<>(0, j));
                }
                fieldToRefresh = board.getFieldAtPos(board.getHeight() - 1, j);
                if (fieldToRefresh.containsPawn()) {
                    view.removePawn(new Pair<>(board.getHeight() - 1, j));
                    view.addPawn(fieldToRefresh.getPawnOnField().getPawnTexture(), new Pair<>(board.getHeight() - 1, j));
                }
            }
        }
    }

    private void unselectCurrentField() {
        synchronized(this) {
            if (currentlySelectedField == null) return;
            if (view != null) {
                view.unhighlightField(convertFieldToPair(currentlySelectedField));
                view.unhighlightAllFields();
            }
            currentlySelectedField = null;
        }
    }

    private Move checkIfMoveIsPossible(Field from, Field to) {
        synchronized(this) {
            for (Move move : currentlyPossibleMoves) {
                if (move.getStart() == from && move.getDestination() == to) {
                    return move;
                }
            }
            return null;
        }
    }

    private void informView(Move move, boolean apply) { //called only after applying move //boolean apply: true for apply, false for redo
        synchronized(this) {
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
    }

    private Pair<Integer, Integer> convertFieldToPair(Field field) {
        synchronized(this) {
            if (field == null) return null;
            return new Pair<>(field.getRow(), field.getColumn());
        }
    }

    private void informViewAboutResult() {
        synchronized(this) {
            if (view == null) return;
            if (isGameFinished != winCondition.gameState.noWinner) {
                view.gameFinish(isGameFinished);
            }
        }
    }

    private List<Move> findCurrentlyPossibleMovesFrom(Field f) {
        synchronized(this) {
            List<Move> toReturn = new ArrayList<>();
            for (Move move : currentlyPossibleMoves) {
                if (move.getStart() == f) {
                    toReturn.add(move);
                }
            }
            return toReturn;
        }
    }

    private void selectPawnToMoveByCurrentPlayer(Pair<Integer, Integer> field) {
        synchronized(this) {
            Field fi = board.getFieldAtPos(field.getKey(), field.getValue());
            if (fi.containsPawn()) {
                if (fi.getPawnOnField().getTeam() == whoToMove) {
                    List<Move> potentialMoves = findCurrentlyPossibleMovesFrom(fi);
                    if (!potentialMoves.isEmpty()) {
                        currentlySelectedField = fi;
                        if (view == null) return;
                        view.selectedField(convertFieldToPair(fi));
                        for (Move move : potentialMoves) {
                            view.highlightPossibleMove(convertFieldToPair(move.getDestination()));
                        }
                    }
                }
            }
        }
    }



}
