package uet.oop.bomberman.Bomber;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.scene;

public class BomberServer extends Bomber {
    public BomberServer(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    protected void move() {
        BombermanGame.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent Keyevent) {
                this.handleEvent(Keyevent);
            }

            private void handleEvent(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case UP: {
                        //y -= Sprite.SCALED_SIZE;
                        speed_y = -1;
                        speed_x = 0;
                        direction = "up";
                        //setyUnit(yUnit - 1);
                        break;
                    }
                    case DOWN: {
                        //y += Sprite.SCALED_SIZE;
                        speed_y = 1;
                        speed_x = 0;
                        direction = "down";
                        //setyUnit(yUnit + 1);
                        break;
                    }
                    case LEFT: {
                        //x -= Sprite.SCALED_SIZE;
                        speed_x = -1;
                        speed_y = 0;
                        direction = "left";
                        //setxUnit(xUnit - 1);
                        break;
                    }
                    case RIGHT: {
                        //x += Sprite.SCALED_SIZE;
                        speed_x = 1;
                        speed_y = 0;
                        direction = "right";
                        //setxUnit(xUnit + 1);
                        break;
                    }
                    case SPACE: {
                        int a = x + 8;
                        int b = y + 8;
                        xUnit = a/32;
                        yUnit = b/32;
                        bombs.add(new Bomb(yUnit, xUnit, Sprite.bomb.getFxImage()));
                    }
                    default:
                        break;
                    // currStt = Bomber.IDLE;

                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                step = 0;
                stepCount = 0;
                loadAnimation();
                direction = "stay";
                setxUnit(xUnit);
                setyUnit(yUnit);
            }
        });
    }
}
