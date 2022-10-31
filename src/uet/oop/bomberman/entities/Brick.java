 package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity {
    public Brick(int x, int y, Image img) {
        super(x, y, img);
        this.collisionBox = new Rectangle(x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE, img.getWidth(), img.getHeight());
    }

    @Override
    public void update() {
        if (!isAlive) {
            if (timeToDie > -10) {
                this.setImg(Sprite.movingSprite(Sprite.brick_exploded2,
                        Sprite.brick_exploded1, Sprite.brick_exploded, timeToDie, 60).getFxImage());
                timeToDie--;
                BombermanGame.map[yUnit][xUnit] = "+";
            }
        }
    }

}

