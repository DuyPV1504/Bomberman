 package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Oneal extends Entity {
    private List<Pair<Integer, Integer>> road = new ArrayList<>();
    private String[][] map = new String[BombermanGame.HEIGHT][BombermanGame.WIDTH];

    public Oneal(int x, int y, Image img) {
        super(x, y, img);
        this.collisionBox = new Rectangle(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }

    private void moveLeft(int nextX) {
        if (x > nextX) {
            x -= 1;
        } else {

            //Update lại tọa độ trong mảng
            xUnit = road.get(road.size() - 1).getKey();
            yUnit = road.get(road.size() - 1).getValue();

            //Xóa điểm cuối trong đường đi
            road.remove(road.size() - 1);
        }
    }

    private void moveRight(int nextX) {
        if (x < nextX) {
            x += 1;
        } else {

            //Update lại tọa độ trong mảng
            xUnit = road.get(road.size() - 1).getKey();
            yUnit = road.get(road.size() - 1).getValue();

            //Xóa điểm đầu tiên trong danh sách
            road.remove(road.size() - 1);
        }
    }

    private void moveUp(int nextY) {
        if (y > nextY) {
            y -= 1;
        } else {

            //Update lại tọa độ trong mảng
            xUnit = road.get(road.size() - 1).getKey();
            yUnit = road.get(road.size() - 1).getValue();

            //Xóa điểm đầu tiên trong danh sách
            road.remove(road.size() - 1);
        }
    }

    private void moveDown(int nextY) {

        if (y < nextY) {
            y += 1;
        } else {

            //Update lại tọa độ trong mảng
            xUnit = road.get(road.size() - 1).getKey();
            yUnit = road.get(road.size() - 1).getValue();

            //Xóa điểm đầu tiên trong danh sách
            road.remove(road.size() - 1);
        }
    }

    private boolean findRoad(int currX, int currY, int PlayerX, int PlayerY, String[][] map) {
        String[] obstacle = {"#","*","x","b","f","1","2","s","v"};

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

    @Override
    public void update() {

        //Tái hiện lại map
        createMap();

        //Tìm đường đi

        if (findRoad(xUnit, yUnit, 1, 1, map)) {

            //Nếu tìm thấy đường đi thì di chuyển
            if (road.size() > 0) {

                Pair<Integer, Integer> next = road.get(road.size() - 1);

                if (next.getKey() > xUnit) {

                    moveRight(next.getKey() * Sprite.SCALED_SIZE);

                } else if (next.getKey() < xUnit) {

                    moveLeft(next.getKey() * Sprite.SCALED_SIZE);

                } else if (next.getValue() > yUnit) {

                    moveDown(next.getValue() * Sprite.SCALED_SIZE);

                } else if (next.getValue() < yUnit) {

                    moveUp(next.getValue() * Sprite.SCALED_SIZE);

                }
            }
        }


    }

    private void createMap() {
        //Deep copy map
        for (int i = 0; i < BombermanGame.HEIGHT; i++) {
            for (int j = 0; j < BombermanGame.WIDTH; j++) {
                map[i][j] = BombermanGame.map[i][j];
            }
        }
    }

}

