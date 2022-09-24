package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class Bomber extends Entity {

    private int COLLISION_BOX_WIDTH = 15;
    private int COLLISION_BOX_HEIGHT = 28;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
        this.collisionBox = new Rectangle(x + 3, y + 2, COLLISION_BOX_WIDTH, COLLISION_BOX_HEIGHT);
    }

    private void collisionUpdate() {
        this.collisionBox.setX(x + 3);
        this.collisionBox.setY(y + 2);
    }

    @Override
    public void update() {
        collisionUpdate();
    }

    @Override
    public void update(List<Entity> entities ,List<Entity> stillObjects) {

    }

}
