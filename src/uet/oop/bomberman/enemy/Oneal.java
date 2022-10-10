package uet.oop.bomberman.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;


public class Oneal extends Enemies {

    public Oneal(int x, int y, Image img) {
        super(x, y, img);
        setSprite();
    }

    @Override
    public void setSprite() {
        left1 = Sprite.oneal_left1;
        left2 = Sprite.oneal_left2;
        left3 = Sprite.oneal_left3;
        right1 = Sprite.oneal_right1;
        right2 = Sprite.oneal_right2;
        right3 = Sprite.oneal_right3;
        dead1 = Sprite.oneal_dead;
        dead2 = Sprite.mob_dead1;
        dead3 = Sprite.mob_dead2;
        dead4 = Sprite.mob_dead3;
        symbol = "2";
    }

}

