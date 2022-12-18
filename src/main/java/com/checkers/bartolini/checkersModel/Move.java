package com.checkers.bartolini.checkersModel;

public class Move {
    private Field from;
    private Field to;
    private Field captureRemoveAtThisPosition;
    private Pawn capturedPawnCopy;
    private  Move child;

    public Move(Field from, Field to, Field captured) {
        this.child=null;
        this.capturedPawnCopy=null;
        this.from = from;
        this.to = to;
        this.captureRemoveAtThisPosition = captured;
    }

    public int getMoveSize() {
        if (child != null) {
            return 1 + child.getMoveSize();
        } else {
            return 1;
        }
    }

    public Move getFollowingMove() {
        return child;
    }

    public void setFollowingMove(Move followingMove) {
        if (this.child == null) {
            this.child = followingMove;
        } else {
            this.child.setFollowingMove(followingMove);
        }
    }

    public Field getStart() {
        return from;
    }

    public Field getDestination() {
        if (child == null) {
            return this.to;
        } else {
            return child.getDestination();
        }
    }

    public Field getCaptured() {
        return captureRemoveAtThisPosition;
    }

    public void applyMove() throws IncorrectMoveException {
        if (!from.containsPawn()) throw new IncorrectMoveException("Field to start move does not contain pawn");
        if (to.containsPawn()) throw new IncorrectMoveException("Move's destination field is not empty");
        if (captureRemoveAtThisPosition != null && !captureRemoveAtThisPosition.containsPawn())
            throw new IncorrectMoveException("Field to capture is empty");
        to.setPawn(from.getPawnOnField());
        from.removePawn();
        if (captureRemoveAtThisPosition != null) {
            capturedPawnCopy = captureRemoveAtThisPosition.getPawnOnField();
            captureRemoveAtThisPosition.removePawn();
        }
        if (child != null) child.applyMove();
    }

    public void undoMove() throws IncorrectMoveException {
        if (child != null) child.undoMove();
        if (from.containsPawn())
            throw new IncorrectMoveException("Unable to undo move: move's start point is not empty");
        if (!to.containsPawn()) throw new IncorrectMoveException("Unable to undo move: Original destination is empty");
        from.setPawn(to.getPawnOnField());
        to.removePawn();
        if (captureRemoveAtThisPosition != null && captureRemoveAtThisPosition.containsPawn())
            throw new IncorrectMoveException("Unable to undo move: Captured pawn field is not empty");
        if (captureRemoveAtThisPosition != null) captureRemoveAtThisPosition.setPawn(capturedPawnCopy);

    }

    public static Field translateField(Board onThisBoard, Field field) {
        if (field == null) return null;
        return onThisBoard.getFieldAtPos(field.getRow(), field.getColumn());
    }

    /**
     * Function to create move applicable on passed board that do same things as passed move
     *
     * @param OnThisBoard board onto move shall be applicated
     * @param move        move to translate onto passed board
     * @return move applicable on passed board
     */
    public static Move translateMove(Board OnThisBoard, Move move) {
        if (move == null) return null;
        Move toReturn = new Move(translateField(OnThisBoard, move.from), translateField(OnThisBoard, move.to), translateField(OnThisBoard, move.captureRemoveAtThisPosition));
       // toReturn.capturedPawnCopy = new Pawn(move.capturedPawnCopy);
        toReturn.setFollowingMove(translateMove(OnThisBoard, move.child));
        return toReturn;
    }

    public Move(Move toCopy) {
        this.from = toCopy.from;
        this.to = toCopy.to;
        this.captureRemoveAtThisPosition = toCopy.captureRemoveAtThisPosition;
        this.capturedPawnCopy = toCopy.capturedPawnCopy;
        if(toCopy.child!=null)
        this.child = new Move(toCopy.child);
    }

    public Move clone() {

        return new Move(this);
    }

    @Override
    public String toString() {
        String toReturn = "";
        toReturn = toReturn.concat(Integer.toString(from.getRow()));
        toReturn = toReturn.concat(Integer.toString(from.getColumn()));
        toReturn = toReturn.concat(" ");
        if(captureRemoveAtThisPosition!=null) {
            toReturn = toReturn.concat(Integer.toString(captureRemoveAtThisPosition.getRow()));
            toReturn = toReturn.concat(Integer.toString(captureRemoveAtThisPosition.getColumn()));
            toReturn = toReturn.concat(" ");
        }

        if(child!=null) {
            toReturn = toReturn.concat(child.toString());
        }
        else {
            toReturn = toReturn.concat(Integer.toString(to.getRow()));
            toReturn = toReturn.concat(Integer.toString(to.getColumn()));
            toReturn = toReturn.concat("/");
        }
        return toReturn;
    }
}
