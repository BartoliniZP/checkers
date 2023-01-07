package com.checkers.bartolini.checkersModel;

import javafx.scene.text.Text;

public class TextureWrapper {
    String val;
    public TextureWrapper(String commandForClientPawnTypeIdentifier) {
this.val=commandForClientPawnTypeIdentifier;
    }
    public TextureWrapper(TextureWrapper toCopy) {

    }

    public TextureWrapper clone() {
     return new TextureWrapper(this);
    }

    @Override
    public String toString() {
        return val;
    }
}
