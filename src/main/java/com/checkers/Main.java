package com.checkers;

import com.checkers.dubi.view.ServerCheckersView;
import com.checkers.dubi.view.ServerInputHandler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Pair;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main extends Application {

    public static int tileSize;
    public static final double pawnScale = 0.4;
    public static final Color color1 =  Color.ANTIQUEWHITE;
    public static final Color color2 =   Color.DARKGREEN;

    public static int height = 10;

    public static int width = 10;
    public static int overallSize =720;

    @Override
    public void start(Stage primaryStage) throws InterruptedException {

        System.out.println("test");
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("IP: ");
            String IP_serwera = scanner.next();
            System.out.print("Port: ");
            int port = Integer.parseInt(scanner.next());
            Socket server = new Socket(IP_serwera, port);


            Pane root = new Pane();


            ServerCheckersView gameView = new ServerCheckersView(color1,color2,root,primaryStage);

            DataOutputStream output = new DataOutputStream(server.getOutputStream());
            DataInputStream input = new DataInputStream(server.getInputStream());

            //ServerInputHandler handler = new ServerInputHandler(input,gameView);


            Thread serverReader = new Thread(new ServerInputHandler(input,gameView,root,output));
            serverReader.start();

            primaryStage.setTitle("Checkers BartoliniZP & Dubi v1.0");
            Scene scene = new Scene(root, overallSize, overallSize);





            primaryStage.setScene(scene);
            primaryStage.setMaxHeight(overallSize+37);
            primaryStage.setMinHeight(overallSize+37);
            primaryStage.setMaxWidth(overallSize+14);
            primaryStage.setMinWidth(overallSize+14);

            primaryStage.setOnCloseRequest(e -> {
                Platform.exit();
                System.exit(0);
            });
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