package uet.oop.bomberman.Socket;

import java.io.*;
import java.net.Socket;

public class NetworkClient extends Network {
    public NetworkClient(int port) {
        try {
            Socket socketOfClient = new Socket("localhost", port);
            socketOfClient.setSoTimeout(1); //listen timeout
            System.out.println("Connected to server");
            os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
            is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
