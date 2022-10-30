package uet.oop.bomberman.Bomber;

import ActiveEntity.Control;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.MainGame;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;


import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.scene;
import static uet.oop.bomberman.graphics.Sprite.*;

public abstract class Bomber extends Entity {

    private List<Pair<Integer, Integer>> road = new ArrayList<>();

    private String[][] map = new String[BombermanGame.HEIGHT][BombermanGame.WIDTH];

    private int COLLISION_BOX_WIDTH = 15;
    private int COLLISION_BOX_HEIGHT = 28;

    protected String direction = "stay";
    protected int speed_x = 0;
    protected int speed_y = 0;

    private int bombRadius;
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
        this.bombRadius = 1;
    }

    protected abstract void move();

    public void setBombRadius(int bombRadius) {
        this.bombRadius = bombRadius;
    }

    public int getBombRadius() {
        return bombRadius;
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
                /* if (step == 3) {
                    img = Sprite.player_left_2.getFxImage();
                } */
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
                /*if (step == 3) {
                    img = Sprite.player_right_2.getFxImage();
                } */
                break;
            }
        }
    }

    @Override
    public void update() {
        if (isAlive) {
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
                y = y + speed_y;
                setxUnit(x / 32);
                setyUnit(y / 32);
                canMove = false;
            }

            if (!direction.equals("stay")) {
                stepCount++;
                loadAnimation();
            } else {
                stepCount = 0;
            }
            if (stepCount == 5) {
                if (step != 5) {
                    step++;
                } else step = 0;
                stepCount = 0;
            }
        } else {
            System.out.println("DEAD");
            if (timeToDie > 0) {
                timeToDie--;
                this.setImg(Sprite.movingSprite(player_dead3, player_dead3, player_dead2, player_dead1, timeToDie, 30).getFxImage());
                BombermanGame.map[yUnit][xUnit] = "+";
            } else {
                this.setImg(null);
            }
        }
    }
}