package uet.oop.bomberman;

//import com.sun.deploy.security.JarSignature;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import uet.oop.bomberman.Bomber.BomberClient;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import uet.oop.bomberman.Bomber.Bomber;
import uet.oop.bomberman.Bomber.BomberServer;
import uet.oop.bomberman.enemy.*;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.Socket.NetworkClient;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import static uet.oop.bomberman.Bomber.Bomber.bombs;

public class BombermanGameClient extends MainGame {

    private GraphicsContext gc;
    private Canvas canvas;
    private MediaPlayer mediaPlayer;
    private Stage stage;

    @FXML
    Button btnStart;

    public static void main(String[] args) {
        Application.launch(BombermanGameClient.class);
    }

    public void end(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("OverClient.fxml")));
        stage.setTitle("BombermanClient");
        stage.setScene(new Scene(root, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT));
        stage.show();
    }

    @Override
    public void start(Stage stageMulti) throws IOException {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        scene = new Scene(root);

        // Tao socket
        networkBomber = new NetworkClient(9999);

        // bomberman init
        Entity bomberman = new BomberClient(6, 7, Sprite.player_right.getFxImage());
        Entity bombermanClient = new BomberServer(1, 1, Sprite.player_right.getFxImage());
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
                update(this);

                //Listen to client
                try {
                    networkBomber.handle();
                } catch (IOException ignored) {
                    /* Server haven't response yet */
                }

            }
        };

        // Tao stage
        stage = stageMulti;

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();
        stage.setTitle("BombermanClient");

        timer.start();

        createMap();

    }

    public void startGame() throws IOException {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        scene = new Scene(root);

        // Tao socket
        networkBomber = new NetworkClient(9999);

        // bomberman init
        Entity bomberman = new BomberClient(6, 7, Sprite.player_right.getFxImage());
        Entity bombermanClient = new BomberServer(11, 29, Sprite.player_right.getFxImage());
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
                update(this);

                //Listen to client
                try {
                    networkBomber.handle();
                } catch (IOException ignored) {
                    /* Server haven't response yet */
                }

            }
        };

        // Tao stage
        stage = (Stage) btnStart.getScene().getWindow();

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();
        stage.setTitle("BombermanClient");

        timer.start();

        createMap();
    }

    public void createMap() throws IOException {
        //đọc file level.txt
        File level1 = new File("res\\levels\\LevelMultiplayer.txt");

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
                    map[i][j] = "#";
                } else if (type.equals("*")) {

                    //Đặt cỏ ở dưới
                    Entity layer = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(layer);

                    //Tạo Brick
                    Entity object;
                    object = new Brick(i, j, Sprite.brick.getFxImage());
                    stillObjects.add(object);
                    map[i][j] = "*";
                } else if (type.equals("x")) {

                    //Đặt cỏ ở dưới
                    Entity layer = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(layer);

                    //Tạo Portal
                    Entity object;
                    object = new Portal(i, j, Sprite.portal.getFxImage());
                    stillObjects.add(object);
                    map[i][j] = "x";
                } else if (type.equals("1")) {

                    //Đặt cỏ ở dưới
                    Entity layer = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(layer);

                    //Tạo enemy
                    Entity object;
                    object = new Balloon(i, j, Sprite.balloom_left1.getFxImage());
                    entities.add(object);
                    map[i][j] = "+";
                } else if (type.equals("2")) {

                    //Đặt cỏ ở dưới
                    Entity layer = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(layer);

                    //Tạo enemy
                    Entity object;
                    object = new Oneal(i, j, Sprite.oneal_left1.getFxImage());
                    entities.add(object);
                    map[i][j] = "+";
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
                    map[i][j] = "*";

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
                    map[i][j] = "*";

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
                    map[i][j] = "*";

                } else if (type.equals("p")) {
                    //Đặt cỏ ở dưới
                    Entity layer = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(layer);
                    map[i][j] = "+";

                    //Đặt bomberman ở vị trí này
                    /* entities.get(0).setX(j);
                    entities.get(0).setY(i); */

                } else {

                    //Tạo cỏ
                    Entity object;
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                    map[i][j] = "+";
                }

                //Lưu map
                //map[i][j] = type;
            }
        }

        //Bomb init
        //Entity bomb = new Bomb(5, 21, Sprite.bomb.getFxImage());
        //stillObjects.add(bomb);

        myReader.close();
    }

    public void update(AnimationTimer timer) {
        final Bomber[] bomber = new Bomber[2];
        entities.forEach(entity -> {
            entity.update();
            if (entity instanceof BomberServer) {
                bomber[0] = (Bomber) entity;
            } else if (entity instanceof BomberClient) {
                bomber[1] = (Bomber) entity;
            }

            if (entity instanceof Enemies  && bomber[0] != null) {
                if (entity.getyUnit() == bomber[0].getyUnit()
                        && entity.getxUnit() == bomber[0].getxUnit()){
                    bomber[0].setAlive(false);
                }
            }
            if (entity instanceof Enemies  && bomber[1] != null) {
                if (entity.getyUnit() == bomber[1].getyUnit()
                        && entity.getxUnit() == bomber[1].getxUnit()){
                    bomber[1].setAlive(false);
                }
            }
        });
        entities.removeIf(entity -> entity.getTimeToDie() == 0);

        for (Entity entity: stillObjects) {
            if (entity instanceof FlameItem  && bomber[0] != null) {
                if (entity.getyUnit() == bomber[0].getyUnit()
                        && entity.getxUnit() == bomber[0].getxUnit()
                        && !Objects.equals(map[entity.getyUnit()][entity.getxUnit()], "*")){
                    System.out.println("Flame");
                    ((FlameItem) entity).setReceived(true);
                    entity.setTimeToDie(0);
                    bomber[0].setBombRadius(bomber[0].getBombRadius() + 1);
                }
            }
            else if (entity instanceof BombItem  && bomber[0] != null) {
                if (entity.getyUnit() == bomber[0].getyUnit()
                        && entity.getxUnit() == bomber[0].getxUnit()
                        && !Objects.equals(map[entity.getyUnit()][entity.getxUnit()], "*")){
                    System.out.println("Bomb");
                    ((BombItem) entity).setReceived(true);
                    entity.setTimeToDie(0);
                    bomber[0].setBombRadius(bomber[0].getBombRadius() + 1);
                }
            }
            else if (entity instanceof SpeedItem  && bomber[0] != null) {
                if (entity.getyUnit() == bomber[0].getyUnit()
                        && entity.getxUnit() == bomber[0].getxUnit()
                        && !Objects.equals(map[entity.getyUnit()][entity.getxUnit()], "*")){
                    System.out.println("Speed");
                    ((SpeedItem) entity).setReceived(true);
                    entity.setTimeToDie(0);
                    bomber[0].setBombRadius(bomber[0].getBombRadius() + 1);
                }
            }
            entity.update();
        }
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

        //Game over
        //if dead single player

        if (bomber[0].getImg() == null || bomber[1].getImg() == null) {
            if (bombs.isEmpty()) {
                try {
                    entities.clear();
                    stillObjects.clear();
                    map = new String[HEIGHT][WIDTH];
                    timer.stop();
                    mediaPlayer.stop();
                    networkBomber.close();
                    end(stage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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