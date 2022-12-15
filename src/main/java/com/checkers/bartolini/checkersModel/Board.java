package com.checkers.bartolini.checkersModel;

public class Board {

    Field[][] board;
    private int height;
    private int width;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Board(Field[][] board, int height, int width) {
        this.board = board;
        this.height = height;
        this.width = width;
    }

    public Field getFieldAtPos(int row, int col) {
       if(row<0 || col<0 || row>=height || col >= width) throw new IllegalArgumentException();
        return board[row][col];
    }

    @Override
    public String toString() {
        String ret = "";
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                ret = ret.concat(board[i][j].toString());
            }
            ret = ret.concat("\n");
        }
        return ret;
    }
}
