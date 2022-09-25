package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.graphics.Sprite;

public class Wall extends Entity {

    public Wall(int x, int y, Image img) {
        super(x, y, img);
        this.collisionBox = new Rectangle(x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE, img.getWidth(), img.getHeight());
    }

    @Override
    public void update() {

    }

}
