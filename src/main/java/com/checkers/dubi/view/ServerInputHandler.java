package com.checkers.dubi.view;

import com.checkers.Main;
import javafx.scene.layout.Pane;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ServerInputHandler implements Runnable{
    DataInputStream input;
    DataOutputStream output;
    viewHandlingCommands view;
    static int height=0;
    static int width=0;
    static int tileSize=10;
    static int team;
    static Pane root;



    public ServerInputHandler(DataInputStream input, viewHandlingCommands view, Pane root,DataOutputStream output) {
        this.input=input;
        this.view=view;
        this.root=root;
        this.output=output;
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
                        if(splitInput[1].equals("1")){
                            //team white
                            team=1;
                        } else{
                            team=0;
                            //team black
                        }
                        view.onTeam(team);
                        break;
                    case "drawBoard" :
                        height=Integer.parseInt(splitInput[2]);
                        width=Integer.parseInt(splitInput[1]);
                        tileSize=Main.overallSize/height;
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



                for(int n =0;n<height;n++) {
                    for (int m = 0; m < width; m++) {
                        int x = n;
                        int y = m;
                        String spacebar = " ";
                        String result = "mouseClick ";
                        result = result.concat(String.valueOf(y));
                        result = result.concat(spacebar);
                        result = result.concat(String.valueOf(x));
                        String resultOriginal = result;
                        output.writeUTF(result);
                        if(splitInput[0].equals("potential")){
                            int w =Integer.parseInt(splitInput[1]);
                            int r = Integer.parseInt(splitInput[2]);
                            result = "mouseClick ";
                            result = result.concat(String.valueOf(w));
                            result = result.concat(spacebar);
                            result = result.concat(String.valueOf(r));
                            output.writeUTF(resultOriginal);
                            output.writeUTF(result);
                        }

                        }
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
        void onTeam(int value) throws InterruptedException;
        void onDrawBoard(int height, int width) throws InterruptedException;
        void onSelected(int x, int y);
        void onPotential(int x, int y);
        void onClearHighlights();
        void onAddPawn(int x, int y, String type, String color) throws InterruptedException;
        void onRemovePawn(int x, int y) throws InterruptedException;
        void onGameFinished(int result) throws InterruptedException;

    }


}
