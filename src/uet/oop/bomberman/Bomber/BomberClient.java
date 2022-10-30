package uet.oop.bomberman.Bomber;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.MainGame.*;

public class BomberClient extends Bomber {
    public BomberClient(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    protected void move() {

        if (network.getLine() != null) {
            switch (network.getLine()) {
                case "UP":
                    direction = "up";
                    speed_y = -1;
                    speed_x = 0;
                    break;
                case "DOWN":
                    direction = "down";
                    speed_y = 1;
                    speed_x = 0;
                    break;
                case "LEFT":
                    direction = "left";
                    speed_y = 0;
                    speed_x = -1;
                    break;
                case "RIGHT":
                    direction = "right";
                    speed_y = 0;
                    speed_x = 1;
                    break;
                case "STOP":
                    step = 0;
                    stepCount = 0;
                    loadAnimation();
                    direction = "stay";
                    setxUnit(xUnit);
                    setyUnit(yUnit);
                    break;
                case "BOMB":
                    int a = x + 8;
                    int b = y + 8;
                    xUnit = a/32;
                    yUnit = b/32;
                    bombs.add(new Bomb(yUnit, xUnit, Sprite.bomb.getFxImage()));
                    break;
            }
        }
    }
}
