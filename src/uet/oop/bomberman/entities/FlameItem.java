package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.graphics.Sprite;

public class FlameItem extends Entity {


    private  boolean isReceived = false;

    private int COLLISION_BOX_WIDTH = 26;
    private int COLLISION_BOX_HEIGHT = 24;

    public FlameItem(int x, int y, Image img) {
        super(x, y, img);
        this.collisionBox = new Rectangle(x * Sprite.SCALED_SIZE + 2, y * Sprite.SCALED_SIZE + 4,
                COLLISION_BOX_WIDTH, COLLISION_BOX_HEIGHT);
    }

    public void setReceived(boolean received) {
        isReceived = received;
    }

    @Override
    public void update() {
        if(isReceived) {
            setAlive(false);
            this.img = null;
        }
    }
}
