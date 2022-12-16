package com.checkers.bartolini.checkersModel;

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.List;

public class NormalPawnPossibleMovesChecker extends PossibleMovesChecker {
    @Override
    public int getLongestTake(Field position) {
        //TODO implement
        return 0;
    }



    @Override
    public List<Move> getPossibleTakes(Board board, int row, int col) throws InvalidAlgorithmParameterException {
        if (!board.getFieldAtPos(row, col).containsPawn()) {
            throw new InvalidAlgorithmParameterException();
        }
        Pawn.Team team = board.getFieldAtPos(row, col).getPawnOnField().getTeam();
        ArrayList<Move> toReturn = new ArrayList<>();
        Field potentialCapture;
        Field potentialMoveAfterCapture;
        if (team == Pawn.Team.WHITE || (team == Pawn.Team.BLACK && CanTakeBackwards())) {
            potentialCapture = getLeftUpperField(board,row,col);
            if(potentialCapture!=null) {
                potentialMoveAfterCapture = getLeftUpperField(board, potentialCapture.getRow(), potentialCapture.getColumn());
                if (potentialCapture.containsPawn() && potentialCapture.getPawnOnField().getTeam() != team && potentialMoveAfterCapture != null && !potentialMoveAfterCapture.containsPawn()) {
                    toReturn.add(new Move(board.getFieldAtPos(row,col),potentialMoveAfterCapture,potentialCapture));
                }
            }
            potentialCapture = getRightUpperField(board,row,col);
            if(potentialCapture!=null) {
                potentialMoveAfterCapture = getRightUpperField(board, potentialCapture.getRow(), potentialCapture.getColumn());
                if ( potentialCapture.containsPawn() && potentialCapture.getPawnOnField().getTeam() != team && potentialMoveAfterCapture != null && !potentialMoveAfterCapture.containsPawn()) {
                    toReturn.add(new Move(board.getFieldAtPos(row,col),potentialMoveAfterCapture,potentialCapture));
                }
            }
        }
        if (team == Pawn.Team.BLACK || (team == Pawn.Team.WHITE && CanTakeBackwards())) {
            potentialCapture = getLeftLowerField(board,row,col);
            if(potentialCapture!=null) {
                potentialMoveAfterCapture = getLeftLowerField(board, potentialCapture.getRow(), potentialCapture.getColumn());
                if (potentialCapture.containsPawn() && potentialCapture.getPawnOnField().getTeam() != team && potentialMoveAfterCapture != null && !potentialMoveAfterCapture.containsPawn()) {
                    toReturn.add(new Move(board.getFieldAtPos(row,col),potentialMoveAfterCapture,potentialCapture));
                }
            }
            potentialCapture = getRightLowerField(board,row,col);
            if(potentialCapture!=null) {
                potentialMoveAfterCapture = getRightLowerField(board, potentialCapture.getRow(), potentialCapture.getColumn());
                if (potentialCapture.containsPawn() && potentialCapture.getPawnOnField().getTeam() != team && potentialMoveAfterCapture != null && !potentialMoveAfterCapture.containsPawn()) {
                    toReturn.add(new Move(board.getFieldAtPos(row,col),potentialMoveAfterCapture,potentialCapture));
                }
            }
        }
        return toReturn;
    }




    @Override
    public List<Move> getPossibleMoves(Board board, int row, int column) throws InvalidAlgorithmParameterException {
        if (!board.getFieldAtPos(row, column).containsPawn()) {
            throw new InvalidAlgorithmParameterException();
        }
        Pawn.Team team = board.getFieldAtPos(row, column).getPawnOnField().getTeam();
        ArrayList<Move> toReturn = new ArrayList<>();
        Field potentialMove;
        if (team == Pawn.Team.WHITE || (team == Pawn.Team.BLACK && CanMoveBackwards())) {
            potentialMove = getLeftUpperField(board, row, column);
            if (potentialMove != null && !potentialMove.containsPawn()) {
                toReturn.add(new Move(board.getFieldAtPos(row,column),potentialMove,null));
            }
            potentialMove = getRightUpperField(board, row, column);
            if (potentialMove != null && !potentialMove.containsPawn()) {
                toReturn.add(new Move(board.getFieldAtPos(row,column),potentialMove,null));
            }

        }
        if (team == Pawn.Team.BLACK || (team == Pawn.Team.WHITE && CanMoveBackwards())) {
            potentialMove = getLeftLowerField(board, row, column);
            if (potentialMove != null && !potentialMove.containsPawn()) {
                toReturn.add(new Move(board.getFieldAtPos(row,column),potentialMove,null));
            }
            potentialMove = getRightLowerField(board, row, column);
            if (potentialMove != null && !potentialMove.containsPawn()) {
                toReturn.add(new Move(board.getFieldAtPos(row,column),potentialMove,null));
            }
        }
        return toReturn;
    }

    public NormalPawnPossibleMovesChecker(boolean canMoveBackwards, boolean canTakeBackwards) {
        super(canMoveBackwards, canTakeBackwards);
    }
}
