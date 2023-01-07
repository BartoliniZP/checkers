package com.checkers.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class simpleClientForTests {

    public static void main(String[] args) {
        System.out.println("test");
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Port: ");
            int port = Integer.parseInt(input.readLine());
            Socket server = new Socket("127.0.0.1", port);
            DataOutputStream  output = new DataOutputStream(server.getOutputStream());
            readServerInput reader = new readServerInput(server);
            Thread serverReader = new Thread(reader);
            serverReader.start();

            while(true) {
                try {
                    String read = input.readLine();
                    output.writeUTF(read);
                } catch (IOException i) {
                    System.out.println(i);
                }
            }


        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
