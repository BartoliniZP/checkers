package com.checkers.bartolini.checkersModel;

public class StandardCheckersBoardBuilder implements boardBuilder {
    private int boardHeight;
    private int boardWidth;
    private int rowsOfPawns;

    public StandardCheckersBoardBuilder(int boardHeight, int boardWidth, int rowsOfPawns) {
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        this.rowsOfPawns = rowsOfPawns;
    }


    @Override
    public Board createBoard() {
        PawnFactory pawnFactory = new NormalPawnFactory();
        Field[][] board = new Field[boardHeight][boardWidth];
        for (int h = 0; h < boardHeight; h++) {
            for (int w = 0; w < boardWidth; w++) {
                board[h][w] = new Field(h,w);
            }
        }
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardWidth; j++) {
                if (i < rowsOfPawns) {
                    if ((i + j) % 2 == 1) {
                        board[i][j].setPawn(pawnFactory.getPawn(Pawn.Team.BLACK));
                    }
                } else if (i > (boardHeight - rowsOfPawns - 1)) {
                    if ((i + j) % 2 == 1) {
                        board[i][j].setPawn(pawnFactory.getPawn(Pawn.Team.WHITE));
                    }
                }
            }
        }

        return new Board(board,boardHeight,boardWidth);
    }
}

