package com.checkers.dubi.view;

import com.checkers.Main;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public abstract class Pawn {

        private boolean isWhite;

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }

    boolean getColor(){
            return isWhite;
        }
        abstract Node getTexture();


}

