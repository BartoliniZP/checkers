package com.checkers;

import com.checkers.dubi.view.ServerCheckersView;
import com.checkers.dubi.view.ServerInputHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main extends Application {

    public static final int tileSize = 80;
    public static final double pawnScale = 0.4;
    public static final Color color1 =  Color.ANTIQUEWHITE;
    public static final Color color2 =   Color.DARKGREEN;

    public static final int height = 10;

    public static final int width = 10;

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        /*

        gameState standardGame = new gameState(new StandardWinCondition(),new AnyTakeObligatory(),new StandardCheckersBoardBuilder(height,width,layersOfPawns,new NormalPawnFactory()),new NormalPawnFactory(), new PromoteToQueenOnLastRank(new NormalPawnFactory()));

        primaryStage.setTitle("Checkers Offline BartoliniZP & Dubi v1.0");
        Pane root = new Pane();
        Scene scene = new Scene(root, tileSize*height, tileSize*width);
        
        checkersView gameView = new checkersView(root,tileSize,Color.ANTIQUEWHITE, Color.DARKGREEN,primaryStage);
        standardGame.setView(gameView);

        root.setOnMouseClicked(e->
                {
                    if (e.getButton() == MouseButton.PRIMARY) {
                        double clickX = e.getX();
                        double clickY = e.getY();
                        int x = (int)clickX/tileSize;
                        int y = (int)clickY/tileSize;
                        standardGame.fieldClicked(new Pair<>(y,x));
                    }
                });


        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        */
        System.out.println("test");
        try {
            //todo kolorki do przekazania i obiekt checkersview i inputhandler
            Scanner scanner = new Scanner(System.in);
            System.out.print("IP: ");
            String IP_serwera = scanner.next();
            System.out.print("Port: ");
            int port = Integer.parseInt(scanner.next());
            Socket server = new Socket(IP_serwera, port);

            primaryStage.setTitle("Checkers BartoliniZP & Dubi v1.0");
            Pane root = new Pane();
            Scene scene = new Scene(root, width*tileSize, height*tileSize);

            ServerCheckersView gameView = new ServerCheckersView(height,width,color1,color2,root,tileSize,primaryStage);

            DataOutputStream output = new DataOutputStream(server.getOutputStream());
            DataInputStream input = new DataInputStream(server.getInputStream());

            //ServerInputHandler handler = new ServerInputHandler(input,gameView);

            Thread serverReader = new Thread(new ServerInputHandler(input,gameView));
            serverReader.start();




                root.setOnMouseClicked(e->
                {
                    if (e.getButton() == MouseButton.PRIMARY) {
                        double clickX = e.getX();
                        double clickY = e.getY();
                        int x = (int)clickX/tileSize;
                        int y = (int)clickY/tileSize;
                        String spacebar = " ";
                        String result = "mouseClick ";
                        result = result.concat(String.valueOf(x));
                        result = result.concat(spacebar);
                        result = result.concat(String.valueOf(y));
                        try {
                            output.writeUTF(result);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
                primaryStage.setScene(scene);
                primaryStage.setResizable(false);
                primaryStage.show();




        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
        public static void main(String[] args) {
        launch();
    }
}