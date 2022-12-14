package com.checkers.bartolini.checkersModel;

import static com.checkers.bartolini.checkersModel.Field.Direction.*;

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
        Field[][] board = new Field[boardHeight][boardWidth];
        for (int h = 0; h < boardHeight; h++) {
            for (int w = 0; w < boardWidth; w++) {
                board[h][w] = new Field();
            }
        }

        for (int h = 0; h < boardHeight; h++) {
            for (int w = 0; w < boardWidth; w++) {
                if (h == 0) {
                    board[h][w].setNeighbor(null, NORTH);
                } else if (h == (boardHeight - 1)) {
                    board[h][w].setNeighbor(null, SOUTH);
                } else {
                    board[h][w].setNeighbor(board[h - 1][w], NORTH);
                    board[h][w].setNeighbor(board[h + 1][w], SOUTH);
                }
                if (w == 0) {
                    board[h][w].setNeighbor(null, WEST);
                } else if (w == (boardWidth - 1)) {
                    board[h][w].setNeighbor(null, EAST);
                } else {
                    board[h][w].setNeighbor(board[h][w - 1], WEST);
                    board[h][w].setNeighbor(board[h][w + 1], EAST);
                }
            }
        }

        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardWidth; j++) {
                if (i < rowsOfPawns) {
                    if ((i + j) % 2 == 1) {
                        board[i][j].setPawn(new NormalPawn(Pawn.Team.BLACK));
                    }
                } else if (i > (boardHeight - rowsOfPawns - 1)) {
                    if ((i + j) % 2 == 1) {
                        board[i][j].setPawn(new NormalPawn(Pawn.Team.WHITE));
                    }
                }
            }
        }

        return new Board(board,boardHeight,boardWidth);
    }
}

