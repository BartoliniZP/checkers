package com.checkers.bartolini.checkersModel;

public class Move {
    Field from;
    Field to;
    Field captureRemoveAtThisPosition;

    public Move(Field from, Field to, Field captured) {
        this.from = from;
        this.to = to;
        this.captureRemoveAtThisPosition = captured;
    }

    public Field getStart() {
        return from;
    }

    public Field getDestination() {
        return to;
    }

    public Field getCaptured() {
        return captureRemoveAtThisPosition;
    }

    public void applyMove() throws IncorrectMoveException {
        if(!from.containsPawn()) throw new IncorrectMoveException("Field to start move contains pawn");
        if(to.containsPawn()) throw new IncorrectMoveException("Move's destination field is not empty");
        if(captureRemoveAtThisPosition!=null &&  !captureRemoveAtThisPosition.containsPawn()) throw new IncorrectMoveException("Field to capture is empty");
        to.setPawn(from.getPawnOnField());
        from.removePawn();
        captureRemoveAtThisPosition.removePawn();
    }
}
