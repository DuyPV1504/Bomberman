package uet.oop.bomberman;

//import com.sun.deploy.security.JarSignature;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import uet.oop.bomberman.Bomber.Bomber;
import uet.oop.bomberman.Bomber.BomberClient;
import uet.oop.bomberman.Bomber.BomberServer;
import uet.oop.bomberman.enemy.Balloon;
import uet.oop.bomberman.enemy.Enemies;
import uet.oop.bomberman.enemy.Oneal;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.NetworkServer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static uet.oop.bomberman.Bomber.Bomber.bombs;

public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static Scene scene;
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static String[][] map = new String[HEIGHT][WIDTH];
    public static AudioClip explosionSound;
    public static NetworkServer network;

    private GraphicsContext gc;
    private Canvas canvas;
    private MediaPlayer mediaPlayer;
    @FXML
    Button startButton;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Menu.fxml")));
        stage.setTitle("Bomberman");
        stage.setScene(new Scene(root, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT));
        stage.show();
    }

    public void startGame() throws Exception {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        scene = new Scene(root);

        // Tao socket
        network = new NetworkServer();

        // bomberman init
        Entity bomberman = new BomberServer(6, 7, Sprite.player_right.getFxImage());
        Entity bombermanClient = new BomberClient(11, 29, Sprite.player_right.getFxImage());
        entities.add(bomberman);
        entities.add(bombermanClient);

        // Music
        Media bgMusic;

        try {
            bgMusic = new Media(new File("res\\sound\\Abstraction - Patreon Goal Reward Loops\\Patreon Goal Reward Loops - Track 05.wav").toURI().toString());
            mediaPlayer = new MediaPlayer(bgMusic);
            explosionSound = new AudioClip(new File("res\\sound\\clip audio\\Bomberman SFX (3).wav").toURI().toString());

        } catch (Exception e) {
            System.out.println("Error with playing sound.");
            e.printStackTrace();
        }

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();

                //Listen to client
                try {
                    network.handleClient();
                    System.out.println(network.getClientLine());
                } catch (IOException ignored) {
                    /* Client haven't response yet */
                }

            }
        };



        // Them scene vao stage
        Stage stage = (Stage) startButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

        timer.start();

        createMap();
    }

    public void createMap() throws IOException {
        //đọc file level.txt
        File level1 = new File("res\\levels\\Level1.txt");

        //Scanner for read interger and BufferedReader for read String
        Scanner myReader = new Scanner(level1);

        //Đọc dòng đầu tiên để lấy kích thước của mảng
        int level = myReader.nextInt();
        int row = myReader.nextInt();
        int column = myReader.nextInt();

        //Lấy map từ file
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                String type = myReader.next();

                if (type.equals("#")) {

                    //Tạo Wall
                    Entity object;
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                    stillObjects.add(object);
                } else if (type.equals("*")) {

                    //Đặt cỏ ở dưới
                    Entity layer = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(layer);

                    //Tạo Brick
                    Entity object;
                    object = new Brick(i, j, Sprite.brick.getFxImage());
                    stillObjects.add(object);
                } else if (type.equals("x")) {

                    //Đặt cỏ ở dưới
                    Entity layer = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(layer);

                    //Tạo Portal
                    Entity object;
                    object = new Portal(i, j, Sprite.portal.getFxImage());
                    stillObjects.add(object);
                } else if (type.equals("1")) {

                    //Đặt cỏ ở dưới
                    Entity layer = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(layer);

                    //Tạo enemy
                    Entity object;
                    object = new Balloon(i, j, Sprite.balloom_left1.getFxImage());
                    entities.add(object);
                } else if (type.equals("2")) {

                    //Đặt cỏ ở dưới
                    Entity layer = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(layer);

                    //Tạo enemy
                    Entity object;
                    object = new Oneal(i, j, Sprite.oneal_left1.getFxImage());
                    entities.add(object);
                } else if (type.equals("b")) {

                    //Đặt cỏ ở dưới
                    Entity layer = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(layer);

                    //Tạo bomb item
                     Entity object1;
                    object1 = new BombItem(i, j, Sprite.powerup_bombs.getFxImage());
                    stillObjects.add(object1);

                    //Tao brick
                    Entity object;
                    object = new Brick(i, j, Sprite.brick.getFxImage());
                    stillObjects.add(object);

                }  else if (type.equals("f")) {

                    //Đặt cỏ ở dưới
                    Entity layer = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(layer);

                    //Tạo flame item
                    Entity object;
                    object = new FlameItem(i, j, Sprite.powerup_flames.getFxImage());
                    stillObjects.add(object);

                    //Tao brick
                    Entity object1;
                    object1 = new Brick(i, j, Sprite.brick.getFxImage());
                    stillObjects.add(object1);

                }  else if (type.equals("s")) {

                    //Đặt cỏ ở dưới
                    Entity layer = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(layer);

                    //Tạo speed item
                    Entity object1;
                    object1 = new SpeedItem(i, j, Sprite.powerup_speed.getFxImage());
                    stillObjects.add(object1);

                    //Tao brick
                    Entity object;
                    object = new Brick(i, j, Sprite.brick.getFxImage());
                    stillObjects.add(object);

                } else if (type.equals("p")) {
                    //Đặt cỏ ở dưới
                    Entity layer = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(layer);

                    //Đặt bomberman ở vị trí này
                    /* entities.get(0).setX(j);
                    entities.get(0).setY(i); */

                } else {

                    //Tạo cỏ
                    Entity object;
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                }

                //Lưu map
                map[i][j] = type;
            }
        }

        //Bomb init
        //Entity bomb = new Bomb(5, 21, Sprite.bomb.getFxImage());
        //stillObjects.add(bomb);

        myReader.close();
    }

    public void update() {
        final Bomber[] bomber = new Bomber[1];
        entities.forEach(entity -> {
            entity.update();
            if (entity instanceof BomberServer) bomber[0] = (Bomber) entity;

            if (entity instanceof Enemies  && bomber[0] != null){
                if (entity.getyUnit() == bomber[0].getyUnit()
                        && entity.getxUnit() == bomber[0].getxUnit()){
                    bomber[0].setAlive(false);
                }
            }
        });
        entities.removeIf(entity -> entity.getTimeToDie() == 0);


        stillObjects.forEach(entity -> {
            entity.update();
            if (entity instanceof FlameItem  && bomber[0] != null){
                if (entity.getyUnit() == bomber[0].getyUnit()
                        && entity.getxUnit() == bomber[0].getxUnit()){
                    System.out.println("Flame");
                    ((FlameItem) entity).setReceived(true);
                    bomber[0].setBombRadius(bomber[0].getBombRadius() + 1);
                }
            }
        });
        stillObjects.removeIf(entity -> entity.getTimeToDie() == 0);
        if (bomber[0] != null) {
            bomber[0].bombs.forEach(bomb1 -> bomb1.setRadius(bomber[0].getBombRadius()));
        }

        for (Bomb bomb : bombs) {
            bomb.update();
        }
        bombs.removeIf(bomb -> bomb.isExploded());

        //Play bg music
        mediaPlayer.play();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        for (Bomb bomb : bombs) {
            bomb.render(gc);
        }
    }
}