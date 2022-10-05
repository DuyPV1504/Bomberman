package uet.oop.bomberman.enemy;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Enemies extends Entity {

    private List<Pair<Integer, Integer>> road = new ArrayList<>();
    private String[][] map = new String[BombermanGame.HEIGHT][BombermanGame.WIDTH];
    private int oldPlayerX;
    private int oldPlayerY;
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

    public Enemies(int x, int y, Image img) {
        super(x, y, img);
        this.collisionBox = new Rectangle(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        this.oldPlayerX = this.xUnit;
        this.oldPlayerY = this.yUnit;

    }

    /**
     * Update collision box to oneal position.
     */
    private void collisionUpdate() {
        this.collisionBox.setX(x);
        this.collisionBox.setY(y);
    }

    /**
     * 4 direction for moving.
     */
    private void moveLeft(int nextX) {

        if (x > nextX) {
            x -= speed;

            //Xác định enemy đang không ở trong block
            isInsideBlock = false;
        } else {

            //Update lại tọa độ trong mảng
            xUnit = road.get(road.size() - 1).getKey();
            yUnit = road.get(road.size() - 1).getValue();

            //Xóa điểm cuối trong đường đi
            road.remove(road.size() - 1);

            //Xác định enemy đang ở trong block
            isInsideBlock = true;

            //Update map
            BombermanGame.map[yUnit][xUnit] = "2";
            BombermanGame.map[yUnit][xUnit + 1] = "+";
        }


    }

    private void moveRight(int nextX) {
        if (x < nextX) {
            x += speed;

            //Xác định enemy đang không ở trong block
            isInsideBlock = false;
        } else {

            //Update lại tọa độ trong mảng
            xUnit = road.get(road.size() - 1).getKey();
            yUnit = road.get(road.size() - 1).getValue();

            //Xóa điểm đầu tiên trong danh sách
            road.remove(road.size() - 1);

            //Xác định enemy đang ở trong block
            isInsideBlock = true;

            //Update map
            BombermanGame.map[yUnit][xUnit] = "2";
            BombermanGame.map[yUnit][xUnit - 1] = "+";
        }
    }

    private void moveUp(int nextY) {
        if (y > nextY) {
            y -= speed;

            //Xác định enemy đang không ở trong block
            isInsideBlock = false;
        } else {

            //Update lại tọa độ trong mảng
            xUnit = road.get(road.size() - 1).getKey();
            yUnit = road.get(road.size() - 1).getValue();

            //Xóa điểm đầu tiên trong danh sách
            road.remove(road.size() - 1);

            //Xác định enemy đang ở trong block
            isInsideBlock = true;

            //Update map
            BombermanGame.map[yUnit][xUnit] = "2";
            BombermanGame.map[yUnit + 1][xUnit] = "+";
        }
    }

    private void moveDown(int nextY) {

        if (y < nextY) {
            y += speed;

            //Xác định enemy đang không ở trong block
            isInsideBlock = false;
        } else {

            //Update lại tọa độ trong mảng
            xUnit = road.get(road.size() - 1).getKey();
            yUnit = road.get(road.size() - 1).getValue();

            //Xóa điểm đầu tiên trong danh sách
            road.remove(road.size() - 1);

            //Xác định enemy đang ở trong block
            isInsideBlock = true;

            //Update map
            BombermanGame.map[yUnit][xUnit] = "2";
            BombermanGame.map[yUnit - 1][xUnit] = "+";
        }
    }


    /**
     * Pathfinding.
     */
    private boolean findRoad(int currX, int currY, int PlayerX, int PlayerY, String[][] map) {
        String[] obstacle = {"#","*","x","v"};

        //Nếu thấy player thì trả về true
        if (currX == PlayerX && currY == PlayerY) {
            return true;
        }

        //Deep copy map
        String[][] mapCopy = new String[BombermanGame.HEIGHT][BombermanGame.WIDTH];
        for (int i = 0; i < BombermanGame.HEIGHT; i++) {
            for (int j = 0; j < BombermanGame.WIDTH; j++) {
                mapCopy[i][j] = map[i][j];
            }
        }

        //Đánh dấu điểm hiện tại đã được duyệt
        map[currY][currX] = "v";

        //Tìm đường đi
        if (currX - 1 >= 0 && !Arrays.asList(obstacle).contains(map[currY][currX - 1])) {
            if (findRoad(currX - 1, currY, PlayerX, PlayerY, map)) {
                road.add(new Pair<>(currX - 1, currY));
                return true;
            }
        }

        if (currX + 1 < BombermanGame.WIDTH && !Arrays.asList(obstacle).contains(map[currY][currX + 1])) {
            if (findRoad(currX + 1, currY, PlayerX, PlayerY, map)) {
                road.add(new Pair<>(currX + 1, currY));
                return true;
            }
        }

        if (currY - 1 >= 0 && !Arrays.asList(obstacle).contains(map[currY - 1][currX])) {
            if (findRoad(currX, currY - 1, PlayerX, PlayerY, map)) {
                road.add(new Pair<>(currX, currY - 1));
                return true;
            }
        }

        if (currY + 1 < BombermanGame.HEIGHT && !Arrays.asList(obstacle).contains(map[currY + 1][currX])) {
            if (findRoad(currX, currY + 1, PlayerX, PlayerY, map)) {
                road.add(new Pair<>(currX, currY + 1));
                return true;
            }
        }

        //Nếu không tìm thấy đường đi thì trả về false
        return false;
    }

    /**
     * Tái hiện lại map.
     */
    private void createMap() {
        //Deep copy map
        for (int i = 0; i < BombermanGame.HEIGHT; i++) {
            for (int j = 0; j < BombermanGame.WIDTH; j++) {
                map[i][j] = BombermanGame.map[i][j];
            }
        }
    }

    @Override
    public void update() {
        if (isAlive) {

            //Tái hiện lại map
            createMap();

            //Tọa độ của player
            int PlayerX = BombermanGame.entities.get(0).getxUnit();
            int PlayerY = BombermanGame.entities.get(0).getyUnit();

            //Di chuyển
            if (road.size() > 0) {

                //Khôi phục lại map
                createMap();

                Pair<Integer, Integer> next = road.get(road.size() - 1);

                if (next.getKey() > xUnit) {

                    moveRight(next.getKey() * Sprite.SCALED_SIZE);
                    this.setImg(Sprite.movingSprite(right1, right2, right3, animate, 60).getFxImage());
                } else if (next.getKey() < xUnit) {

                    moveLeft(next.getKey() * Sprite.SCALED_SIZE);
                    this.setImg(Sprite.movingSprite(left1, left2, left3, animate, 60).getFxImage());
                } else if (next.getValue() > yUnit) {

                    moveDown(next.getValue() * Sprite.SCALED_SIZE);
                    this.setImg(Sprite.movingSprite(left1, left2, left3, animate, 60).getFxImage());
                } else if (next.getValue() < yUnit) {

                    moveUp(next.getValue() * Sprite.SCALED_SIZE);
                    this.setImg(Sprite.movingSprite(right1, right2, right3, animate, 60).getFxImage());
                }

            }


            //Nếu player chưa di chuyển thì không cần tìm đường đi
            if (PlayerX != oldPlayerX || PlayerY != oldPlayerY) {

                //Chỉ tìm khi ở trong block
                if (isInsideBlock) {

                    //Tìm đường đi
                    road.clear(); //Xóa đường đi cũ

                    if (findRoad(xUnit, yUnit, PlayerX, PlayerY, map)) {

                        //Cập nhật lại tọa độ của player
                        oldPlayerX = PlayerX;
                        oldPlayerY = PlayerY;
                    }
                }
            }

            //Update collsion box và animation
            collisionUpdate();
            animate++;
        } else {
            if (timeToDie > 0) {
                timeToDie--;
                this.setImg(Sprite.movingSprite(dead4, dead3, dead2, dead1, timeToDie, 60).getFxImage());
                BombermanGame.map[yUnit][xUnit] = "+";
            } else {
                this.setImg(null);
            }
        }

    }

    protected abstract void setSprite();

}