package uet.oop.bomberman.entities;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public class Bomber extends Entity {

    private int COLLISION_BOX_WIDTH = 15;
    private int COLLISION_BOX_HEIGHT = 28;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
        this.collisionBox = new Rectangle(x + 3, y + 2, COLLISION_BOX_WIDTH, COLLISION_BOX_HEIGHT);
    }

    private void move() {
        BombermanGame.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override

            public void handle(KeyEvent event) {this.handleEvent(event);}
            private void handleEvent(KeyEvent event) {

                switch(event.getCode()) {

                    case UP: {
                        y += Sprite.SCALED_SIZE;
                        break;
                    }
                    case DOWN: {
                        y -= Sprite.SCALED_SIZE;
                        break;
                    }
                    case LEFT: {
                        x = x - Sprite.SCALED_SIZE;
                        break;
                    }
                    case RIGHT: {
                        x = x + Sprite.SCALED_SIZE;
                        break;
                    }
                    default:
                        break;
                }
            }
        });
    }


    private void collisionUpdate() {
        this.collisionBox.setX(x + 3);
        this.collisionBox.setY(y + 2);
    }

    @Override
    public void update() {
        collisionUpdate();
        move();
    }

    @Override
    public void update(List<Entity> entities ,List<Entity> stillObjects) {

    }
}


