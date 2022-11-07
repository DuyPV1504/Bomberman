package uet.oop.bomberman.Socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkServer extends Network {

    private static ServerSocket listener = null;
    private static Socket socketOfServer = null;

    public NetworkServer(int port) {
        try {
            listener = new ServerSocket(port);
            System.out.println("Server is running...");

            socketOfServer = listener.accept();
            socketOfServer.setSoTimeout(1); //listen timeout

            System.out.println("Client connected");
            is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
            os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        super.close();
        listener.close();
        socketOfServer.close();
    }

}
