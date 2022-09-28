package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    //Tọa độ X thực tế trong mảng
    protected int xUnit;

    //Tọa độ Y thực tế trong mảng
    protected int yUnit;

    protected String direction = "idle";

    protected int step = 0;
    protected int stepCount = 0;

    protected Image img;

    //Collision box
    protected Rectangle collisionBox;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int yUnit, int xUnit, Image img) {
        this.xUnit = xUnit;
        this.yUnit = yUnit;
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public Entity(int xUnit, int yUnit, Image img, String direction, int step, int stepCount) {
        this.direction = direction;
        this.step = step;
        this.stepCount = stepCount;
    }


    public int getxUnit() {
        return xUnit;
    }

    public void setxUnit(int xUnit) {
        this.xUnit = xUnit;
    }

    public int getyUnit() {
        return yUnit;
    }

    public void setyUnit(int yUnit) {
        this.yUnit = yUnit;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x * Sprite.SCALED_SIZE;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y * Sprite.SCALED_SIZE;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public Rectangle getCollisionBox() {
        return collisionBox;
    }

    public void setCollisionBox(Rectangle collisionBox) {
        this.collisionBox = collisionBox;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }
    public abstract void update();

}
