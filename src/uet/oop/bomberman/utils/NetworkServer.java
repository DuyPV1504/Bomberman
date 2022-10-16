package uet.oop.bomberman.utils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkServer {

    public static BufferedReader is;
    public static BufferedWriter os;
    private static ServerSocket listener = null;
    private static Socket socketOfServer = null;
    private String clientLine;

    public NetworkServer() {
        try {
            listener = new ServerSocket(9999);
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

    public String getClientLine() {
        return clientLine;
    }

    public void handleClient() throws IOException {
        clientLine = is.readLine();
//        System.out.println(clientLine);
    }
}
