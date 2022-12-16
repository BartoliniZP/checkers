package com.checkers.bartolini.checkersModel;

public class Move {
    Field from;
    Field to;
    Field captureRemoveAtThisPosition;
    Pawn capturedPawnCopy;
    Move child;

    public Move(Field from, Field to, Field captured) {
        this.from = from;
        this.to = to;
        this.captureRemoveAtThisPosition = captured;
    }

    public int getMoveSize() {
        if(child!=null) {
            return 1 +child.getMoveSize();
        } else {
            return 1;
        }
    }
    public Move getFollowingMove() {
        return child;
    }

    public void setFollowingMove(Move followingMove) {
        if(this.child==null) {
            this.child = followingMove;
        }
        else {
            this.child.setFollowingMove(followingMove);
        }
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
        if (!from.containsPawn()) throw new IncorrectMoveException("Field to start move contains pawn");
        if (to.containsPawn()) throw new IncorrectMoveException("Move's destination field is not empty");
        if (captureRemoveAtThisPosition != null && !captureRemoveAtThisPosition.containsPawn())
            throw new IncorrectMoveException("Field to capture is empty");
        to.setPawn(from.getPawnOnField());
        from.removePawn();
        if (captureRemoveAtThisPosition != null)  {
            capturedPawnCopy = captureRemoveAtThisPosition.getPawnOnField();
            captureRemoveAtThisPosition.removePawn();
        }
        if (child != null) child.applyMove();
    }

    public void undoMove() throws IncorrectMoveException {
        if (child != null) child.undoMove();
        if(from.containsPawn()) throw new IncorrectMoveException("Unable to undo move: move's start point is not empty");
        if(!to.containsPawn()) throw new IncorrectMoveException("Unable to undo move: Original destination is empty");
        from.setPawn(to.getPawnOnField());
        to.removePawn();
        if(captureRemoveAtThisPosition!=null && captureRemoveAtThisPosition.containsPawn()) throw new IncorrectMoveException("Unable to undo move: Captured pawn field is not empty");
        captureRemoveAtThisPosition.setPawn(capturedPawnCopy);

    }
}
