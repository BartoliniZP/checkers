package com.checkers.server;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class readServerInput implements Runnable {
    Socket server;

    public readServerInput(Socket server) {
        this.server = server;
    }

    @Override
    public void run() {
        try {
            DataInputStream fromServer = new DataInputStream(
                    new BufferedInputStream(server.getInputStream()));

            while(true) {
                String msg = "Server: ";
                try
                {
                    msg = msg.concat(fromServer.readUTF());
                    System.out.println(msg);

                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
            }
        }
        catch(IOException e) {

        }
    }
}
