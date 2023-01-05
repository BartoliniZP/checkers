package com.checkers.bartolini.checkersModel;

import javafx.scene.Node;
import javafx.scene.text.Text;

public class TextureWrapper {
    private com.checkers.dubi.view.Pawn texture;
    public TextureWrapper(com.checkers.dubi.view.Pawn pawnTexture) {
        this.texture=pawnTexture;

    }
    public com.checkers.dubi.view.Pawn getTexture () {
        return this.texture;
    };
    public TextureWrapper(TextureWrapper toCopy) {

    }

    public TextureWrapper clone() {
     return new TextureWrapper(this);
    }
}
