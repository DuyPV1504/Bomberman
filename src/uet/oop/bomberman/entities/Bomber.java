package uet.oop.bomberman.entities;

import ActiveEntity.Control;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;


import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.scene;

public class Bomber extends Entity {

    private List<Pair<Integer, Integer>> road = new ArrayList<>();

    private String[][] map = new String[BombermanGame.HEIGHT][BombermanGame.WIDTH];

    private int COLLISION_BOX_WIDTH = 15;
    private int COLLISION_BOX_HEIGHT = 28;

    private String direction = "stay";
    private int speed_x = 0;
    private int speed_y = 0;
    /*public static final int IDLE = 0;
    public static final int DOWN = 1;
    public static final int UP = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;
    public static final int DEAD = 5;
    private int currStt = Bomber.IDLE;

     */

    private boolean canMove = false;

    public static final List<Bomb> bombs = new ArrayList<>();

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        this.collisionBox = new Rectangle(x + 3, y + 2, COLLISION_BOX_WIDTH, COLLISION_BOX_HEIGHT);
    }

    public Bomber(int xUnit, int yUnit, Image img, String direction, int step, int stepCount) {
        super(xUnit, yUnit, img, direction, step, stepCount);
    }

    private void move() {
        BombermanGame.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent Keyevent) {
                this.handleEvent(Keyevent);
            }

            private void handleEvent(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case UP: {
                        //y -= Sprite.SCALED_SIZE;
                        speed_y = -1;
                        speed_x = 0;
                        direction = "up";
                        //setyUnit(yUnit - 1);
                        break;
                    }
                    case DOWN: {
                        //y += Sprite.SCALED_SIZE;
                        speed_y = 1;
                        speed_x = 0;
                        direction = "down";
                        //setyUnit(yUnit + 1);
                        break;
                    }
                    case LEFT: {
                        //x -= Sprite.SCALED_SIZE;
                        speed_x = -1;
                        speed_y = 0;
                        direction = "left";
                        //setxUnit(xUnit - 1);
                        break;
                    }
                    case RIGHT: {
                        //x += Sprite.SCALED_SIZE;
                        speed_x = 1;
                        speed_y = 0;
                        direction = "right";
                        //setxUnit(xUnit + 1);
                        break;
                    }
                    case SPACE: {
                        bombs.add(new Bomb(yUnit, xUnit, Sprite.bomb.getFxImage()));
                    }
                    default:
                        break;
                    // currStt = Bomber.IDLE;

                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                step = 0;
                stepCount = 0;
                loadAnimation();
                direction = "stay";
                setxUnit(xUnit);
                setyUnit(yUnit);
            }
        });
    }

    private void collisionUpdate() {
        this.collisionBox.setX(x + 3);
        this.collisionBox.setY(y + 2);
    }

    private void createMap() {
        //Deep copy map
        for (int i = 0; i < BombermanGame.HEIGHT; i++) {
            for (int j = 0; j < BombermanGame.WIDTH; j++) {
                map[i][j] = BombermanGame.map[i][j];
            }
        }
    }

    public void loadAnimation() {
        switch (direction) {
            case "up": {
                if (step == 0) {
                    img = Sprite.player_up.getFxImage();
                }
                if (step == 1) {
                    img = Sprite.player_up_1.getFxImage();
                }
                if (step == 2) {
                    img = Sprite.player_up.getFxImage();
                }
                if (step == 3) {
                    img = Sprite.player_up_2.getFxImage();
                }
                break;
            }
            case "down": {
                if (step == 0) {
                    img = Sprite.player_down.getFxImage();
                }
                if (step == 1) {
                    img = Sprite.player_down_1.getFxImage();
                }
                if (step == 2) {
                    img = Sprite.player_down.getFxImage();
                }
                if (step == 3) {
                    img = Sprite.player_down_2.getFxImage();
                }
                break;
            }
            case "left": {
                if (step == 0) {
                    img = Sprite.player_left.getFxImage();
                }
                if (step == 1) {
                    img = Sprite.player_left_1.getFxImage();
                }
                if (step == 2) {
                    img = Sprite.player_left.getFxImage();
                }
                if (step == 3) {
                    img = Sprite.player_left_2.getFxImage();
                }
                break;
            }
            case "right": {
                if (step == 0) {
                    img = Sprite.player_right.getFxImage();
                }
                if (step == 1) {
                    img = Sprite.player_right_1.getFxImage();
                }
                if (step == 2) {
                    img = Sprite.player_right.getFxImage();
                }
                if (step == 3) {
                    img = Sprite.player_right_2.getFxImage();
                }
                break;
            }
        }
    }

    @Override
    public void update() {

        collisionUpdate();
        move();
        switch (direction) {
            case "up": {
                if (Control.collisionUp(this.x, this.y))
                    canMove = true;
                break;
            }
            case "down": {
                if (Control.collisionDown(this.x, this.y))
                    canMove = true;
                break;
            }
            case "left": {
                if (Control.collisionLeft(this.x, this.y))
                    canMove = true;
                break;
            }
            case "right": {
                if (Control.collisionRight(this.x, this.y))
                    canMove = true;
                break;
            }
            default:
                break;
        }

        if (canMove) {
            x = x + speed_x;
            setxUnit(x / 32);
            y = y + speed_y;
            setyUnit(y / 32);
            canMove = false;
        }

        if (!direction.equals("stay")) {
            stepCount++;
            loadAnimation();
        }
        else {
            stepCount = 0;
        }
        if (stepCount == 3) {
            if (step != 3) {
                step++;
            }
            else step = 0;
            stepCount = 0;
        }
    }
}