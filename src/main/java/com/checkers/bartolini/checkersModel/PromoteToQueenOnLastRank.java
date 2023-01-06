package com.checkers.bartolini.checkersModel;

public class PromoteToQueenOnLastRank implements OnGettingToLastRank {
    private PawnFactory queenFactory;

    public PromoteToQueenOnLastRank(PawnFactory queenFactory) {
        this.queenFactory = queenFactory;
    }

    @Override
    public boolean checkLastRanks(Board boardToCheck) {
        boolean toReturn = false;
        for (int i = 0; i < boardToCheck.getWidth(); i++) {
            Field toCheck = boardToCheck.getFieldAtPos(0, i);
            if (toCheck.containsPawn()) {
                if (toCheck.getPawnOnField().getTeam() == Pawn.Team.WHITE) {
                    toCheck.removePawn();
                    toCheck.setPawn(queenFactory.getQueen(Pawn.Team.WHITE));
                    toReturn=true;
                }
            }
            toCheck = boardToCheck.getFieldAtPos(boardToCheck.getHeight() - 1, i);
            if (toCheck.containsPawn()) {
                if (toCheck.getPawnOnField().getTeam() == Pawn.Team.BLACK) {
                    toCheck.removePawn();
                    toCheck.setPawn(queenFactory.getQueen(Pawn.Team.BLACK));
                    toReturn=true;
                }
            }

        }
        return toReturn;
    }

}
