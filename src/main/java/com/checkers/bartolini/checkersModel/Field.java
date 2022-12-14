package com.checkers.bartolini.checkersModel;

public class Field {
    public enum Direction {
        NORTH,WEST,SOUTH,EAST
    }

    public Field() {
        neighbors = new Field[4];
        pawnOnField = null;
    }

    private Field[] neighbors;
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

    public void setNeighbor(Field newNeighbor, Direction dir) {
        neighbors[dir.ordinal()]=newNeighbor;
    }
    public Field getNeighbor(Direction dir) {
       return neighbors[dir.ordinal()];
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
