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
        return board[row][col];
    }
}
