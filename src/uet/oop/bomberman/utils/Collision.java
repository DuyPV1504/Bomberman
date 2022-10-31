 package uet.oop.bomberman.utils;

import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

 public class Collision {
     public static boolean isCollide(Entity e1, Entity e2) {
         if (e1.getCollisionBox().getBoundsInParent().intersects(e2.getCollisionBox().getBoundsInParent())) {
             return true;
         }
         return false;
     }

     public static boolean bombKillEnemiesLeft(Bomb bomb, Entity e, int i) {
         Rectangle bombRect = new Rectangle(bomb.getX(), bomb.getY(), Sprite.SCALED_SIZE * (i + 1), Sprite.SCALED_SIZE);
         if (bombRect.getBoundsInParent().intersects(e.getCollisionBox().getBoundsInParent())) {
             return true;
         }
         return false;
     }

     public static boolean bombKillEnemiesRight(Bomb bomb, Entity e, int i) {
         Rectangle bombRect = new Rectangle(bomb.getX() - Sprite.SCALED_SIZE * i, bomb.getY(), Sprite.SCALED_SIZE * (i + 1), Sprite.SCALED_SIZE);
         if (bombRect.getBoundsInParent().intersects(e.getCollisionBox().getBoundsInParent())) {
             return true;
         }
         return false;
     }

     public static boolean bombKillEnemiesUp(Bomb bomb, Entity e, int i) {
        Rectangle bombRect = new Rectangle(bomb.getX(), bomb.getY() - Sprite.SCALED_SIZE * i, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE * (i + 1));
        if (bombRect.getBoundsInParent().intersects(e.getCollisionBox().getBoundsInParent())) {
            return true;
        }
        return false;
     }

     public static boolean bombKillEnemiesDown(Bomb bomb, Entity e, int i) {
        Rectangle bombRect = new Rectangle(bomb.getX(), bomb.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE * (i + 1));
        if (bombRect.getBoundsInParent().intersects(e.getCollisionBox().getBoundsInParent())) {
            return true;
        }
        return false;
     }
 }