package com.checkers;

import com.checkers.bartolini.checkersModel.*;
import com.checkers.server.ClientsInformer;
import com.checkers.server.clientInputHandler;
import com.checkers.server.standardInterpreterIntoGameState;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Main {

    public static gameState askUserForGameMode(Scanner input) {
        System.out.println(" ");
        System.out.println("0 - Competitive checkers: 10x10 board, best take mandatory, pawn can capture backwards, 3 pawn lines");
        System.out.println("1 - Classic checkers: Same as competitive but 8x8 board");
        System.out.println("2 - Anti-checkers 8x8 board");
        System.out.println("3 - Russian checkers: 8x8 board, any take mandatory, pawn can capture backwards, 3 pawn lines");
        System.out.print("Gamemode: ");
        int gameMode = input.nextInt();

        switch (gameMode) {
            case 0:
                return new gameState(new StandardWinCondition(), new BestTakeObligatory(), new StandardCheckersBoardBuilder(10, 10, 3, new NormalPawnFactory()), new NormalPawnFactory(), new PromoteToQueenOnLastRank(new NormalPawnFactory()));
            case 1:
                return new gameState(new StandardWinCondition(), new BestTakeObligatory(), new StandardCheckersBoardBuilder(8, 8, 3, new NormalPawnFactory()), new NormalPawnFactory(), new PromoteToQueenOnLastRank(new NormalPawnFactory()));
            case 2:
                return new gameState(new AntichessWinCondition(), new BestTakeObligatory(), new StandardCheckersBoardBuilder(8, 8, 3, new NormalPawnFactory()), new NormalPawnFactory(), new PromoteToQueenOnLastRank(new NormalPawnFactory()));
            case 3:
                return new gameState(new StandardWinCondition(), new AnyTakeObligatory(), new StandardCheckersBoardBuilder(8, 8, 3, new NormalPawnFactory()), new NormalPawnFactory(), new PromoteToQueenOnLastRank(new NormalPawnFactory()));
            default:
                return null;
        }
    }

    public static void main(String[] args) {


        Scanner stdin = new Scanner(System.in);
        System.out.println("Ekodiesel");
        System.out.print("Port: ");
        int port = stdin.nextInt();
        System.out.println(" ");

        gameState game = askUserForGameMode(stdin);

        try {

            ServerSocket server = new ServerSocket(port);
            System.out.println("Server started.");
            System.out.println("Waiting for first client...");
            Socket client1 = server.accept();
            System.out.println("First client accepted. Waiting for second client...");
            Socket client2 = server.accept();
            System.out.println("Second client accepted.");

            ClientsInformer informer = new ClientsInformer(client1,client2,game);
            assert game != null;
            game.setView(informer);
            clientInputHandler client1input = new clientInputHandler(client1,new standardInterpreterIntoGameState(game, Pawn.Team.WHITE));
            DataOutputStream forTeamInform = new DataOutputStream(client1.getOutputStream());
            forTeamInform.writeUTF("team 1");
            forTeamInform = new DataOutputStream(client2.getOutputStream());
            forTeamInform.writeUTF("team 0");
            clientInputHandler client2input = new clientInputHandler(client2,new standardInterpreterIntoGameState(game, Pawn.Team.BLACK));
            Thread client1thread = new Thread(client1input);
            Thread client2thread = new Thread(client2input);

            client1thread.start();
            client2thread.start();
            System.out.println(" ");
            System.out.println("Type 0 to force turn off server");
            while(stdin.nextInt()!=0) {

            }
        }
        catch (IOException e) {

        }
    }
}