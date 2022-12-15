package com.checkers.dubi.view;

import com.checkers.Main;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Pawn extends Circle{
    public Pawn(double a, double b, boolean isWhite, boolean isQueen) {
        setCenterX(Main.margin / 2 + (a + 0.5) * Main.tileSize);
        setCenterY(Main.margin / 2 + (b + 0.5) * Main.tileSize);
        setRadius(Main.tileSize * 0.4);
        if (isWhite == true) {
            setFill(Color.WHITE);
        } else {
            setFill(Color.BLACK);
        }
        if (isQueen == true) {
            setStroke(Color.BLUE);
            setStrokeWidth(8);
        }
    }

    public static void removePawn(double x, double y, Pane root) {
        ObservableList<Node> list = root.getChildren();
        for (int i = 0; i < list.size(); i++) {
            Node n = list.get(i);
            if (n instanceof Circle && n.contains(x, y)) {
                root.getChildren().remove(n);
                break;
            }
        }
    }

    public static void addPawn(double x1, double y1, Pane root){
        ObservableList<Node> list = root.getChildren();
        for (int i = 0; i < list.size(); i++) {
            Node n = list.get(i);
            if (n instanceof Rectangle && n.contains(x1, y1)) {
                double x=(((Rectangle) n).getX()-Main.margin/2)/Main.tileSize;
                double y=(((Rectangle) n).getY()-Main.margin/2)/Main.tileSize;
                Pawn pawn=new Pawn(x,y,false,false);
                root.getChildren().add(pawn);
                System.out.println(x+" "+y+" "+ ((Rectangle) n).getX());
            }
        }
    }

    public static void movePawn(double x1, double y1, double x2, double y2, Pane root){

    }

}
