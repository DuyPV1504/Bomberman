package uet.oop.bomberman.enemy;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.BombermanGameClient;
import uet.oop.bomberman.graphics.Sprite;

import java.io.IOException;

public abstract class EnemyClient extends Enemies {
    private int animate = 0;
    private boolean isInsideBlock = true;
    private int speed = 1;

    //Sprite
    protected Sprite left1;
    protected Sprite left2;
    protected Sprite left3;
    protected Sprite right1;
    protected Sprite right2;
    protected Sprite right3;
    protected Sprite dead1;
    protected Sprite dead2;
    protected Sprite dead3;
    protected Sprite dead4;

    //Symbol
    protected String symbol;

    public EnemyClient(int x, int y, Image img) {
        super(x, y, img);
        this.collisionBox = new Rectangle(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);

    }

    /**
     * Update collision box to oneal position.
     */
    private void collisionUpdate() {
        this.collisionBox.setX(x);
        this.collisionBox.setY(y);
    }

    @Override
    public void update() {
        if (isAlive) {
            //Di chuyển

            //Update collsion box và animation
            collisionUpdate();
            animate++;
        } else {
            if (timeToDie > 0) {
                timeToDie--;
                this.setImg(Sprite.movingSprite(dead4, dead3, dead2, dead1, timeToDie, 60).getFxImage());
                BombermanGameClient.map[yUnit][xUnit] = "+";
            } else {
                this.setImg(null);
            }
        }

    }

    protected abstract void setSprite();
}
