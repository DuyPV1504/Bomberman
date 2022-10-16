 package uet.oop.bomberman.utils;

import uet.oop.bomberman.entities.Entity;

 public class Collision {
    public static boolean isCollide(Entity e1, Entity e2) {
        if (e1.getCollisionBox().getBoundsInParent().intersects(e2.getCollisionBox().getBoundsInParent())) {
            return true;
        }


        return false;
    }
}
