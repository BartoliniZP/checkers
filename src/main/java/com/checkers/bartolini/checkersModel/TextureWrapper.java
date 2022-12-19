package com.checkers.bartolini.checkersModel;

import javafx.scene.Node;
import javafx.scene.text.Text;

public class TextureWrapper {
//TODO implement
    Node pawnTexture;
    public TextureWrapper() {
        this.pawnTexture=pawnTexture;

    }
    public Node getTexture () {
        return pawnTexture;
    };
    public TextureWrapper(TextureWrapper toCopy) {

    }

    public TextureWrapper clone() {
     return new TextureWrapper(this);
    }
}
