package uet.oop.bomberman.utils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkClient {
    public static BufferedReader is;
    public static BufferedWriter os;

    public static void init() {
        try {
            Socket socketOfClient = new Socket("localhost", 9999);
            System.out.println("Connected to server");
            os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
            is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void send(String line) throws IOException {
        os.write(line);
        os.newLine();
        os.flush();
    }
}
