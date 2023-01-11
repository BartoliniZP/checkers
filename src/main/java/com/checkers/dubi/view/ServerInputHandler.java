package com.checkers.dubi.view;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ServerInputHandler implements Runnable{
    DataInputStream input;
    viewHandlingCommands view;


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
                            view.onDrawBoard(Integer.parseInt(splitInput[1]),Integer.parseInt(splitInput[2]));
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
                        view.onAddPawn(Integer.parseInt(splitInput[1]),Integer.parseInt(splitInput[2]),splitInput[3], splitInput[4]);
                        break;
                    case "removePawn" :
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
            }
        }


    }

    public interface viewHandlingCommands{
        void onTeam(int value);
        void onDrawBoard(int height, int width);
        void onSelected(int x, int y);
        void onPotential(int x, int y);
        void onClearHighlights();
        void onAddPawn(int x, int y, String type, String color);
        void onRemovePawn(int x, int y);
        void onGameFinished(int result);

    }


}
