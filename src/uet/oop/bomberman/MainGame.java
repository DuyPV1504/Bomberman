package uet.oop.bomberman;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import uet.oop.bomberman.Socket.Network;
import uet.oop.bomberman.entities.Entity;

import java.util.ArrayList;
import java.util.List;

public abstract class MainGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static Scene scene;
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static String[][] map = new String[HEIGHT][WIDTH];
    public static AudioClip explosionSound;
    public static Network networkBomber;

    @Override
    public abstract void start(Stage stage) throws Exception;

}
