package com.checkers.bartolini.checkersModel;

import javafx.scene.Node;
import javafx.scene.text.Text;

public class TextureWrapper {
    private Node texture;
    public TextureWrapper(Node pawnTexture) {
        this.texture=pawnTexture;

    }
    public Node getTexture () {
        return this.texture;
    };
    public TextureWrapper(TextureWrapper toCopy) {

    }

    public TextureWrapper clone() {
     return new TextureWrapper(this);
    }
}
