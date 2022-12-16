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
    public List<Field> getPossibleTakes(Board board, int row, int col) throws InvalidAlgorithmParameterException {
        if (!board.getFieldAtPos(row, col).containsPawn()) {
            throw new InvalidAlgorithmParameterException();
        }
        Pawn.Team team = board.getFieldAtPos(row, col).getPawnOnField().getTeam();
        ArrayList<Field> toReturn = new ArrayList<>();
        Field potentialCapture;
        Field potentialMoveAfterCapture;
        if (team == Pawn.Team.WHITE || (team == Pawn.Team.BLACK && CanTakeBackwards())) {
            potentialCapture = getLeftUpperField(board,row,col);
            if(potentialCapture!=null) {
                potentialMoveAfterCapture = getLeftUpperField(board, potentialCapture.getRow(), potentialCapture.getColumn());
                if (potentialCapture.containsPawn() && potentialCapture.getPawnOnField().getTeam() != team && potentialMoveAfterCapture != null && !potentialMoveAfterCapture.containsPawn()) {
                    toReturn.add(potentialMoveAfterCapture);
                }
            }
            potentialCapture = getRightUpperField(board,row,col);
            if(potentialCapture!=null) {
                potentialMoveAfterCapture = getRightUpperField(board, potentialCapture.getRow(), potentialCapture.getColumn());
                if ( potentialCapture.containsPawn() && potentialCapture.getPawnOnField().getTeam() != team && potentialMoveAfterCapture != null && !potentialMoveAfterCapture.containsPawn()) {
                    toReturn.add(potentialMoveAfterCapture);
                }
            }
        }
        if (team == Pawn.Team.BLACK || (team == Pawn.Team.WHITE && CanTakeBackwards())) {
            potentialCapture = getLeftLowerField(board,row,col);
            if(potentialCapture!=null) {
                potentialMoveAfterCapture = getLeftLowerField(board, potentialCapture.getRow(), potentialCapture.getColumn());
                if (potentialCapture.containsPawn() && potentialCapture.getPawnOnField().getTeam() != team && potentialMoveAfterCapture != null && !potentialMoveAfterCapture.containsPawn()) {
                    toReturn.add(potentialMoveAfterCapture);
                }
            }
            potentialCapture = getRightLowerField(board,row,col);
            if(potentialCapture!=null) {
                potentialMoveAfterCapture = getRightLowerField(board, potentialCapture.getRow(), potentialCapture.getColumn());
                if (potentialCapture.containsPawn() && potentialCapture.getPawnOnField().getTeam() != team && potentialMoveAfterCapture != null && !potentialMoveAfterCapture.containsPawn()) {
                    toReturn.add(potentialMoveAfterCapture);
                }
            }
        }
        return toReturn;
    }




    @Override
    public List<Field> getPossibleMoves(Board board, int row, int column) throws InvalidAlgorithmParameterException {
        if (!board.getFieldAtPos(row, column).containsPawn()) {
            throw new InvalidAlgorithmParameterException();
        }
        Pawn.Team team = board.getFieldAtPos(row, column).getPawnOnField().getTeam();
        ArrayList<Field> toReturn = new ArrayList<>();
        Field potentialMove;
        if (team == Pawn.Team.WHITE || (team == Pawn.Team.BLACK && CanMoveBackwards())) {
            potentialMove = getLeftUpperField(board, row, column);
            if (potentialMove != null && !potentialMove.containsPawn()) {
                toReturn.add(potentialMove);
            }
            potentialMove = getRightUpperField(board, row, column);
            if (potentialMove != null && !potentialMove.containsPawn()) {
                toReturn.add(potentialMove);
            }

        }
        if (team == Pawn.Team.BLACK || (team == Pawn.Team.WHITE && CanMoveBackwards())) {
            potentialMove = getLeftLowerField(board, row, column);
            if (potentialMove != null && !potentialMove.containsPawn()) {
                toReturn.add(potentialMove);
            }
            potentialMove = getRightLowerField(board, row, column);
            if (potentialMove != null && !potentialMove.containsPawn()) {
                toReturn.add(potentialMove);
            }
        }
        return toReturn;
    }

    public NormalPawnPossibleMovesChecker(boolean canMoveBackwards, boolean canTakeBackwards) {
        super(canMoveBackwards, canTakeBackwards);
    }
}
