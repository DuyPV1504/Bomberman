package uet.oop.bomberman.Socket;

import java.io.*;

public abstract class Network {
    protected BufferedReader is;
    protected BufferedWriter os;
    private String line;

    public String getLine() {
        return line;
    }

    public void send(String line) throws IOException {
        os.write(line);
        os.newLine();
        os.flush();
    }

    public void handle() throws IOException {
        line = is.readLine();
    }
}
