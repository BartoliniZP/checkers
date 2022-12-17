package com.checkers.dubi.view;

import com.checkers.Main;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Highlight extends Rectangle {
    /*public Highlight(double a, double b){
        setWidth(Main.tileSize);
        setHeight(Main.tileSize);
        setX(Main.margin/2+a*Main.tileSize);
        setY(Main.margin/2+b*Main.tileSize);
        setFill(Color.PURPLE);
        setOpacity(0.8);
    }

    public static void highlightTile(double a, double b, Pane root){
        ObservableList<Node> list = root.getChildren();
        for (int i = 0; i < list.size(); i++) {
            Node n = list.get(i);
            if (n instanceof Rectangle && n.contains(a, b)) {
                double x=(((Rectangle) n).getX()-Main.margin/2)/Main.tileSize;
                double y=(((Rectangle) n).getY()-Main.margin/2)/Main.tileSize;
                Highlight highlight = new Highlight(x,y);
                root.getChildren().add(highlight);
                break;
            }
        }

    }

    public static void removeHighlight(double a, double b, Pane root){
        ObservableList<Node> list = root.getChildren();
        for (int i = 0; i < list.size(); i++) {
            Node n = list.get(i);
            if (n instanceof Rectangle && n.contains(a, b) && n.getOpacity()<1) {
                root.getChildren().remove(n);
                break;
            }
        }
    }*/
}
