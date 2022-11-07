package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.MainGame.stillObjects;

public class Brick extends Entity {
    private int timeToDie = 50;

    public Brick(int x, int y, Image img) {
        super(x, y, img);
        this.collisionBox = new Rectangle(x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE, img.getWidth(), img.getHeight());
    }

    @Override
    public void update() {
        if (!isAlive) {
            if (timeToDie > 0) {
                this.setImg(Sprite.movingSprite(Sprite.brick_exploded2,
                        Sprite.brick_exploded1, Sprite.brick_exploded, timeToDie, 60).getFxImage());
                timeToDie--;
            } else {
                BombermanGame.map[yUnit][xUnit] = "+";
                this.setTimeToDie(0);
                //stillObjects.remove(this);
            }
        }
    }

}

