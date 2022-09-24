package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.Collision;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class BombermanGame extends Application {
    
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    
    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // bomberman init
        Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };

        // Them scene vao stage
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
                Entity object;
                String type = myReader.next();

                if (type.equals("#")) {

                    object = new Wall(j, i, Sprite.wall.getFxImage());

                } else if (type.equals("*")) {

                    object = new Brick(j, i, Sprite.brick.getFxImage());

                } else if (type.equals("x")) {

                    //Đặt cỏ ở dưới
                    Entity layer = new Grass(j, i, Sprite.grass.getFxImage());
                    stillObjects.add(layer);

                    object = new Portal(j, i, Sprite.portal.getFxImage());

                } else {

                    object = new Grass(j, i, Sprite.grass.getFxImage());

                }

                stillObjects.add(object);
            }
        }

        myReader.close();
    }

    public void update() {
        entities.forEach(entity -> {
            entity.update();
        });
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
