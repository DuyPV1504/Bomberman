package uet.oop.bomberman.entities;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

import static uet.oop.bomberman.BombermanGame.scene;

public class Bomber extends Entity {

    private int COLLISION_BOX_WIDTH = 15;
    private int COLLISION_BOX_HEIGHT = 28;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
        this.collisionBox = new Rectangle(x + 3, y + 2, COLLISION_BOX_WIDTH, COLLISION_BOX_HEIGHT);
    }

    /* private void move() {
        BombermanGame.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent Keyevent) {
                this.handleEvent(Keyevent);
            }

            private void handleEvent(KeyEvent keyEvent) {
                switch (keyEvent.getCode()){
                    case UP: {
                        // currStt = Bomber.UP;
                        x -= Sprite.SCALED_SIZE;
                        break;
                    }
                    case DOWN: {
                        // currStt = Bomber.DOWN;
                        x += Sprite.SCALED_SIZE;
                        break;
                    }
                    case LEFT: {
                        // currStt = Bomber.LEFT;
                        y -= Sprite.SCALED_SIZE;
                        break;
                    }
                    case RIGHT: {
                        // currStt = Bomber.RIGHT;
                        y += Sprite.SCALED_SIZE;
                        break;
                    }
                    default:
                        break;
                        // currStt = Bomber.IDLE;

                }


            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                // currStt = Bomber.IDLE;
            }
        });
    }  */
    private void collisionUpdate() {
        this.collisionBox.setX(x + 3);
        this.collisionBox.setY(y + 2);
    }

    @Override
    public void update() {
        collisionUpdate();
        // move();
    }

}


