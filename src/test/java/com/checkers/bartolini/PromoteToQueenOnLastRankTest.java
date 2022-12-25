package com.checkers.bartolini;

import com.checkers.bartolini.checkersModel.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PromoteToQueenOnLastRankTest {

    @Test
    void promotesToQueenOnGettingToLastRank() {
        StandardCheckersBoardBuilder builder = new StandardCheckersBoardBuilder(8, 8, 0, new NormalPawnFactory()); //standard builder creates pawns that cannot move backwards
        Board board = builder.createBoard();
        PermittedMovesRules rules = new AnyTakeObligatory();
        OnGettingToLastRank queenPromoter = new PromoteToQueenOnLastRank(new NormalPawnFactory());
        board.getFieldAtPos(2, 2).setPawn(new Pawn(Pawn.Team.WHITE, new NormalPawnPossibleMovesChecker(false, true), new TextureWrapper()));
        board.getFieldAtPos(1, 1).setPawn(new Pawn(Pawn.Team.BLACK, new NormalPawnPossibleMovesChecker(false, true), new TextureWrapper()));
        List<Move> possibleMoves = rules.getPermittedMoves(board, Pawn.Team.WHITE);
        assertEquals(possibleMoves.size(),1);
        try {
            possibleMoves.get(0).applyMove();
            boolean promoted = queenPromoter.checkLastRanks(board);
            possibleMoves = rules.getPermittedMoves(board, Pawn.Team.WHITE);
            assertEquals(possibleMoves.size(),7);
            assertTrue(promoted);
        }
        catch(IncorrectMoveException e) {
            fail();
        }
    }
}
