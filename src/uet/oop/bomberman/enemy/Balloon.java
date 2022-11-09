 package uet.oop.bomberman.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.enemy.Enemies;
import uet.oop.bomberman.graphics.Sprite;

 public class Balloon extends Enemies {
    public Balloon(int x, int y, Image img) {
        super(x, y, img);
        setSprite();
    }

     @Override
     protected void setSprite() {
        left1 = Sprite.balloom_left1;
        left2 = Sprite.balloom_left2;
        left3 = Sprite.balloom_left3;
        right1 = Sprite.balloom_right1;
        right2 = Sprite.balloom_right2;
        right3 = Sprite.balloom_right3;
        dead1 = Sprite.balloom_dead;
        dead2 = Sprite.mob_dead1;
        dead3 = Sprite.mob_dead2;
        dead4 = Sprite.mob_dead3;
        symbol = "1";
     }
 }

