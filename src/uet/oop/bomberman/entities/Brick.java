package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public class Brick extends Entity {
    public Brick(int x, int y, Image img) {
        super(x, y, img);
        this.collisionBox = new Rectangle(x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE, img.getWidth(), img.getHeight());
    }

    @Override
    public void update() {

    }

    @Override
    public void update(List<Entity> entities, List<Entity> stillObjects) {

    }
}
