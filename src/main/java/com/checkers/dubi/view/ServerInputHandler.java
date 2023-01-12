package com.checkers.dubi.view;

import com.checkers.Main;
import javafx.application.Platform;
import javafx.scene.Scene;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ServerInputHandler implements Runnable{
    DataInputStream input;
    viewHandlingCommands view;
    static int height=0;
    static int width=0;
    static int tileSize=10;


    public ServerInputHandler(DataInputStream input, viewHandlingCommands view) {
        this.input=input;
        this.view=view;
    }

    public void run()
    {
        String receivedString;
        while (true)
        {
            try {

                receivedString = input.readUTF();
                String[] splitInput = receivedString.split(" ");

                switch (splitInput[0]) {
                    case "team" :
                        if(splitInput[1]=="0"){
                            //team white
                        } else{
                            //team black
                        }
                        break;
                    case "drawBoard" :
                        height=Integer.parseInt(splitInput[2]);
                        width=Integer.parseInt(splitInput[1]);
                        tileSize=Main.overallSize/height;
                            view.onDrawBoard(Integer.parseInt(splitInput[1]),Integer.parseInt(splitInput[2]));

                        System.out.println(height+ " : " + width + " " + tileSize);




                        break;
                    case "selected" :
                        view.onSelected(Integer.parseInt(splitInput[1]),Integer.parseInt(splitInput[2]));
                        break;
                    case "potential" :
                        view.onPotential(Integer.parseInt(splitInput[1]),Integer.parseInt(splitInput[2]));
                        break;
                    case "clearHighlights" :
                        view.onClearHighlights();
                        break;
                    case "addPawn" :
                       // System.out.println("add");
                        view.onAddPawn(Integer.parseInt(splitInput[1]),Integer.parseInt(splitInput[2]),splitInput[3].trim(), splitInput[4].trim());
                        break;
                    case "removePawn" :
                       // System.out.println("remove");
                        view.onRemovePawn(Integer.parseInt(splitInput[1]),Integer.parseInt(splitInput[2]));
                        break;
                    case "gameFinished" :
                        view.onGameFinished(Integer.parseInt(splitInput[1]));
                        break;

                    default:
                        System.out.println("Invalid move");
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }
    public static int returnHeight(){
        return height;
    }
    public static int returnWidth(){
        return width;
    }

    public static int returnTileSize(){
        return tileSize;
    }

    public interface viewHandlingCommands{
        void onTeam(int value);
        void onDrawBoard(int height, int width) throws InterruptedException;
        void onSelected(int x, int y);
        void onPotential(int x, int y);
        void onClearHighlights();
        void onAddPawn(int x, int y, String type, String color) throws InterruptedException;
        void onRemovePawn(int x, int y) throws InterruptedException;
        void onGameFinished(int result) throws InterruptedException;

    }


}
