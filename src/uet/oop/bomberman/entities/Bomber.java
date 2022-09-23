package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

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

    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    @Override
    public void update() {
        collisionUpdate();
    }
}
