package com.checkers.bartolini.checkersModel;

public class Field {

    public Field(int row, int column) {
        this.row = row;
        this.column = column;
        this.pawnOnField = null;
    }

    private int row;
    private int column;

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    private Pawn pawnOnField;

    public boolean containsPawn() {
        return (pawnOnField!=null);
    }
    public Pawn getPawnOnField() {
        return pawnOnField;
    }
    public void setPawn(Pawn pawn) {
        pawnOnField=pawn;
    }

    public void removePawn() {
        pawnOnField=null;
    }
    @Override
    public String toString() {
        if(this.containsPawn()) {
            return pawnOnField.toString();
        } else {
            return "E";
        }
    }
}
