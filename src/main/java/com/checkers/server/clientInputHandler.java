package com.checkers.server;

import com.checkers.bartolini.checkersModel.Pawn;
import com.checkers.bartolini.checkersModel.gameState;
import javafx.util.Pair;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import static java.lang.System.exit;

public class clientInputHandler implements Runnable {

    public interface clientInputInterpreter {
        void interpret(String input);
    }

    DataInputStream clientInput;
    clientInputInterpreter interpreter;



    public clientInputHandler(Socket client, clientInputInterpreter interpreter) {
        try {
            this.clientInput = new DataInputStream(new BufferedInputStream(client.getInputStream()));
            this.interpreter = interpreter;
        } catch (IOException e) {
            System.err.println("Fatal error during creation clientInputHandler");
            exit(-1);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String clientsInput = clientInput.readUTF();
                interpreter.interpret(clientsInput);
            } catch (Exception e) {
                System.err.println("Error during reading of client's Input");
            }
        }
    }


}
