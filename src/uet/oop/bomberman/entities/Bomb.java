package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {
    private int timeToExplode = 180;
    private int timeAfterExplode = 20;
    private boolean exploded = false;
    private boolean allowToPassThru = true;
    private int radius = 1;
    private int animate = 0;

    public Bomb(int yUnit, int xUnit, Image img) {
        super(yUnit, xUnit, img);
    }

    public void update() {
        if (timeToExplode > 0) {
            timeToExplode--;
            this.setImg(Sprite.movingSprite(Sprite.bomb_2, Sprite.bomb_1, Sprite.bomb, timeToExplode, 60).getFxImage());

        } else {
            this.setImg(null);
            if (timeAfterExplode > 0) {
                timeAfterExplode--;
            } else {
                exploded = true;
            }
        }
    }

    public boolean isExploded() {
        return exploded;
    }

    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }

    public boolean isAllowToPassThru() {
        return allowToPassThru;
    }

    public void setAllowToPassThru(boolean allowToPassThru) {
        this.allowToPassThru = allowToPassThru;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}

