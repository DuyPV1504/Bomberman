package uet.oop.bomberman.entities;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Oneal extends Entity {
    private List<Pair<Integer, Integer>> road = new ArrayList<>();

    public Oneal(int x, int y, Image img) {
        super(x, y, img);
        this.collisionBox = new Rectangle(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        road.add(new Pair<>(17, 2));
        road.add(new Pair<>(16, 2));
        road.add(new Pair<>(16, 3));
        road.add(new Pair<>(17, 3));
    }

    private void moveLeft(int nextX) {
        if (x > nextX) {
            x -= 1;
        } else {

            //Update lại tọa độ trong mảng
            xUnit = road.get(0).getKey();
            yUnit = road.get(0).getValue();

            //Xóa điểm đầu tiên trong danh sách
            road.remove(0);
        }
    }

    private void moveRight(int nextX) {
        if (x < nextX) {
            x += 1;
        } else {

            //Update lại tọa độ trong mảng
            xUnit = road.get(0).getKey();
            yUnit = road.get(0).getValue();

            //Xóa điểm đầu tiên trong danh sách
            road.remove(0);
        }
    }

    private void moveUp(int nextY) {
        if (y > nextY) {
            y -= 1;
        } else {

            //Update lại tọa độ trong mảng
            xUnit = road.get(0).getKey();
            yUnit = road.get(0).getValue();

            //Xóa điểm đầu tiên trong danh sách
            road.remove(0);
        }
    }

    private void moveDown(int nextY) {

        if (y < nextY) {
            y += 1;
        } else {

            //Update lại tọa độ trong mảng
            xUnit = road.get(0).getKey();
            yUnit = road.get(0).getValue();

            //Xóa điểm đầu tiên trong danh sách
            road.remove(0);
        }
    }

    @Override
    public void update() {

        if (road.size() > 0) {

            Pair<Integer, Integer> next = road.get(0);

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

    @Override
    public void update(List<Entity> entities, List<Entity> stillObjects) {

    }
}

