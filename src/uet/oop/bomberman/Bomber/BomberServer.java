package uet.oop.bomberman.Bomber;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.graphics.Sprite;

import java.io.IOException;

import static uet.oop.bomberman.MainGame.scene;
import static uet.oop.bomberman.MainGame.networkBomber;

public class BomberServer extends Bomber {
    public BomberServer(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    protected void move() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent Keyevent) {
                try {
                    this.handleEvent(Keyevent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            private void handleEvent(KeyEvent keyEvent) throws IOException {
                switch (keyEvent.getCode()) {
                    case UP: {
                        //y -= Sprite.SCALED_SIZE;
                        speed_y = -1;
                        speed_x = 0;
                        direction = "up";
                        if (networkBomber != null) {
                            networkBomber.send("UP");
                        }
                        //setyUnit(yUnit - 1);
                        break;
                    }
                    case DOWN: {
                        //y += Sprite.SCALED_SIZE;
                        speed_y = 1;
                        speed_x = 0;
                        direction = "down";
                        if (networkBomber != null) {
                            networkBomber.send("DOWN");
                        }
                        //setyUnit(yUnit + 1);
                        break;
                    }
                    case LEFT: {
                        //x -= Sprite.SCALED_SIZE;
                        speed_x = -1;
                        speed_y = 0;
                        direction = "left";
                        if (networkBomber != null) {
                            networkBomber.send("LEFT");
                        }
                        //setxUnit(xUnit - 1);
                        break;
                    }
                    case RIGHT: {
                        //x += Sprite.SCALED_SIZE;
                        speed_x = 1;
                        speed_y = 0;
                        direction = "right";
                        if (networkBomber != null) {
                            networkBomber.send("RIGHT");
                        }
                        //setxUnit(xUnit + 1);
                        break;
                    }
                    case SPACE: {
                        int a = x + 8;
                        int b = y + 8;
                        xUnit = a/32;
                        yUnit = b/32;
                        bombs.add(new Bomb(yUnit, xUnit, Sprite.bomb.getFxImage()));
                        if (networkBomber != null) {
                            networkBomber.send("BOMB");
                        }
                    }
                    default:
                        break;
                    // currStt = Bomber.IDLE;

                }
            }
        });

        scene.setOnKeyReleased(e -> {
            step = 0;
            stepCount = 0;
            loadAnimation();
            direction = "stay";
            setxUnit(xUnit);
            setyUnit(yUnit);
            try {
                if (networkBomber != null) {
                    networkBomber.send("STOP");
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }
}
