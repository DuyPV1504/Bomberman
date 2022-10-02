package ActiveEntity;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Control extends BombermanGame {


        public static boolean colliSionUp ( int _x, int _y){
            return createMap()[(int) ((_y) / Sprite.SCALED_SIZE)][((_x + 6) / Sprite.SCALED_SIZE)].equals("-")
                    && createMap(stillObjects)[(int) ((_y) / Sprite.SCALED_SIZE)][((_x + Sprite.SCALED_SIZE - 12) / Sprite.SCALED_SIZE)].equals("-");
        }

        public static boolean collisionDown ( int _x, int _y){
            return createMap(stillObjects)[(int) ((_y + Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE)][((_x + 6) / Sprite.SCALED_SIZE)].equals("-")
                    && createMap(stillObjects)[(int) ((_y + Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE)][(_x + Sprite.SCALED_SIZE - 12) / Sprite.SCALED_SIZE].equals("-");
        }

        public static boolean colliSionRight ( int _x, int _y){
            return createMap(stillObjects)[(int) (_y + 6) / Sprite.SCALED_SIZE][(int) ((_x + Sprite.SCALED_SIZE - 9) / Sprite.SCALED_SIZE)].equals("-")
                    && createMap(stillObjects)[(int) (_y + Sprite.SCALED_SIZE - 6) / Sprite.SCALED_SIZE][(int) ((_x + Sprite.SCALED_SIZE - 9) / Sprite.SCALED_SIZE)].equals("-");
        }

        public static boolean collisionLeft ( int _x, int _y){
            return createMap(stillObjects)[(int) (_y + 6) / Sprite.SCALED_SIZE][(int) ((_x) / Sprite.SCALED_SIZE)].equals("-")
                    && createMap(stillObjects)[(int) (_y + Sprite.SCALED_SIZE - 6) / Sprite.SCALED_SIZE][(int) ((_x) / Sprite.SCALED_SIZE)].equals("-");
        }
}



