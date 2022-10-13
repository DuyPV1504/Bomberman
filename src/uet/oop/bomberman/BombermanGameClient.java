package uet.oop.bomberman;

//import com.sun.deploy.security.JarSignature;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.NetworkClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BombermanGameClient extends Application {
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static Scene scene;
    public static List<Entity> entities = new ArrayList<>();

    private GraphicsContext gc;
    private Canvas canvas;

    public static void main(String[] args) {
        Application.launch(BombermanGameClient.class);
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
        scene = new Scene(root);

        //Socket
        NetworkClient.init();

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W:
                    try {
                        NetworkClient.send("UP");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case S:

                    break;
                case A:

                    break;
                case D:

                    break;
                case ALT:

                    break;
            }
        });

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

    }

    public void update() {

    }

    public void render() {
        entities.forEach(entity -> {
            entity.render(gc);
        });
    }
}