package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.Collision;

public class Bomb extends Entity {
    private int timeToExplode = 180;
    private int timeAfterExplode = 50;
    private int timeToAnimate = timeAfterExplode;
    private boolean exploded = false;
    private boolean allowToPassThru = true;
    private int radius = 1;
    private boolean hasKilledBrick = false;

    //Brick block explosion
    private boolean brickonLeft = false;
    private boolean brickonRight = false;
    private boolean brickonUp = false;
    private boolean brickonDown = false;
    private boolean brickonLeft1 = false;
    private boolean brickonRight1 = false;
    private boolean brickonUp1 = false;
    private boolean brickonDown1 = false;

    public Bomb(int yUnit, int xUnit, Image img) {
        super(yUnit, xUnit, img);
    }

    public boolean isExploded() {
        return exploded;
    }

    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }

    public boolean isAllowToPassThru() {
        return allowToPassThru;
    }

    public void setAllowToPassThru(boolean allowToPassThru) {
        this.allowToPassThru = allowToPassThru;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    private void kill() {
//        if (!hasKilledBrick) {
        outerLoop:
        for (int i = 0; i <= radius; i++) {
            for (Entity entity : BombermanGame.entities) {
//                    if (entity.getxUnit() <= this.getxUnit() + i && entity.getxUnit() > this.getxUnit()
//                            && entity.getyUnit() == this.getyUnit()) {
                if (Collision.bombKillEnemiesLeft(this, entity, i)) {
                    entity.setAlive(false);
                }
            }

            for (Entity entity : BombermanGame.stillObjects) {
                if (entity instanceof Brick || entity instanceof Wall) {
                    if (entity.getxUnit() <= this.getxUnit() + i && entity.getxUnit() > this.getxUnit()
                            && entity.getyUnit() == this.getyUnit()) {
                        if (i == 1) {
                            brickonRight = true;
                        }
                        if (i == 2) {
                            brickonRight1 = true;
                        }

                        entity.setAlive(false);
                        break outerLoop;
                    }
                }
            }
        }

        outerLoop:
        for (int i = 0; i <= radius; i++) {
            for (Entity entity : BombermanGame.entities) {
//                if (entity.getxUnit() >= this.getxUnit() - i && entity.getxUnit() < this.getxUnit()
//                        && entity.getyUnit() == this.getyUnit()) {
                if (Collision.bombKillEnemiesRight(this, entity, i)) {
                    entity.setAlive(false);
                }
            }

            for (Entity entity : BombermanGame.stillObjects) {
                if (entity instanceof Brick || entity instanceof Wall) {
                    if (entity.getxUnit() >= this.getxUnit() - i && entity.getxUnit() < this.getxUnit()
                            && entity.getyUnit() == this.getyUnit()) {
                        if (i == 1) {
                            brickonLeft = true;
                        }
                        if (i == 2) {
                            brickonLeft1 = true;
                        }


                        entity.setAlive(false);
                        break outerLoop;
                    }
                }
            }
        }

        outerLoop:
        for (int i = 0; i <= radius; i++) {
            for (Entity entity : BombermanGame.entities) {
//                if (entity.getyUnit() <= this.getyUnit() + i && entity.getyUnit() >= this.getyUnit()
//                        && entity.getxUnit() == this.getxUnit()) {
                if (Collision.bombKillEnemiesDown(this, entity, i)) {
                    entity.setAlive(false);
                }
            }

            for (Entity entity : BombermanGame.stillObjects) {
                if (entity instanceof Brick || entity instanceof Wall) {
                    if (entity.getyUnit() <= this.getyUnit() + i && entity.getyUnit() >= this.getyUnit()
                            && entity.getxUnit() == this.getxUnit()) {
                        if (i == 1) {
                            brickonDown = true;
                        }
                        if (i == 2) {
                            brickonDown1 = true;
                        }

                        entity.setAlive(false);
                        break outerLoop;
                    }
                }
            }
        }

        outerLoop:
        for (int i = 0; i <= radius; i++) {
            for (Entity entity : BombermanGame.entities) {
//                if (entity.getyUnit() >= this.getyUnit() - i && entity.getyUnit() < this.getyUnit()
//                        && entity.getxUnit() == this.getxUnit()) {
                if (Collision.bombKillEnemiesUp(this, entity, i)) {
                    entity.setAlive(false);
                }
            }

            for (Entity entity : BombermanGame.stillObjects) {
                if (entity instanceof Brick || entity instanceof Wall) {
                    if (entity.getyUnit() >= this.getyUnit() - i && entity.getyUnit() < this.getyUnit()
                            && entity.getxUnit() == this.getxUnit()) {
                        if (i == 1) {
                            brickonUp = true;
                        }
                        if (i == 2) {
                            brickonUp1 = true;
                        }

                        entity.setAlive(false);
                        break outerLoop;
                    }
                }
            }
        }
//            hasKilledBrick = true;
//        }
    }

    public void update() {

        //Limit the bomb's radius
        if (radius > 3) {
            radius = 3;
        }

        if (timeToExplode > 0) {
            timeToExplode--;
            this.setImg(Sprite.movingSprite(Sprite.bomb_2, Sprite.bomb_1, Sprite.bomb, timeToExplode, 60).getFxImage());

        } else {
            this.setImg(null);
            if (timeAfterExplode > 0) {
                timeAfterExplode--;
                kill();
                BombermanGame.explosionSound.play();
            } else {
                exploded = true;
                timeToDie = 0;
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if (img != null) {
            gc.drawImage(img, x, y);
        } else {

            //Draw explosion
            if (radius == 1) {
                if (!exploded) {

                    gc.drawImage(Sprite.movingSprite(
                            Sprite.bomb_exploded,
                            Sprite.bomb_exploded1,
                            Sprite.bomb_exploded2,
                            Sprite.bomb_exploded1,
                            Sprite.bomb_exploded,
                            timeAfterExplode, timeToAnimate).getFxImage(), x, y);

                }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             if (!BombermanGame.map[yUnit][xUnit - 1].equals("#") && !exploded) {

                    gc.drawImage(Sprite.movingSprite(
                            Sprite.explosion_horizontal_left_last,
                            Sprite.explosion_horizontal_left_last1,
                            Sprite.explosion_horizontal_left_last2,
                            Sprite.explosion_horizontal_left_last1,
                            Sprite.explosion_horizontal_left_last,
                            timeAfterExplode, timeToAnimate).getFxImage(), x - Sprite.SCALED_SIZE, y);

                }
                if (!BombermanGame.map[yUnit][xUnit + 1].equals("#") && !exploded) {

                    gc.drawImage(Sprite.movingSprite(
                            Sprite.explosion_horizontal_right_last,
                            Sprite.explosion_horizontal_right_last1,
                            Sprite.explosion_horizontal_right_last2,
                            Sprite.explosion_horizontal_right_last1,
                            Sprite.explosion_horizontal_right_last,
                            timeAfterExplode, timeToAnimate).getFxImage(), x + Sprite.SCALED_SIZE, y);

                }
                if (!BombermanGame.map[yUnit - 1][xUnit].equals("#") && !exploded) {

                    gc.drawImage(Sprite.movingSprite(
                            Sprite.explosion_vertical_top_last,
                            Sprite.explosion_vertical_top_last1,
                            Sprite.explosion_vertical_top_last2,
                            Sprite.explosion_vertical_top_last1,
                            Sprite.explosion_vertical_top_last,
                            timeAfterExplode, timeToAnimate).getFxImage(), x, y - Sprite.SCALED_SIZE);

                }
                if (!BombermanGame.map[yUnit + 1][xUnit].equals("#") && !exploded) {

                    gc.drawImage(Sprite.movingSprite(
                            Sprite.explosion_vertical_down_last,
                            Sprite.explosion_vertical_down_last1,
                            Sprite.explosion_vertical_down_last2,
                            Sprite.explosion_vertical_down_last1,
                            Sprite.explosion_vertical_down_last,
                            timeAfterExplode, timeToAnimate).getFxImage(), x, y + Sprite.SCALED_SIZE);

                }

            } else if (radius == 2) {
                if (!exploded) {
                    gc.drawImage(Sprite.movingSprite(
                            Sprite.bomb_exploded,
                            Sprite.bomb_exploded1,
                            Sprite.bomb_exploded2,
                            Sprite.bomb_exploded1,
                            Sprite.bomb_exploded,
                            timeAfterExplode, timeToAnimate).getFxImage(), x, y);
                }

                if(!BombermanGame.map[yUnit][xUnit - 1].equals("#") && !exploded) {
                    gc.drawImage(Sprite.movingSprite(
                            Sprite.explosion_horizontal,
                            Sprite.explosion_horizontal1,
                            Sprite.explosion_horizontal2,
                            Sprite.explosion_horizontal1,
                            Sprite.explosion_horizontal,
                            timeAfterExplode, timeToAnimate).getFxImage(), x - Sprite.SCALED_SIZE, y);

                    if(!BombermanGame.map[yUnit][xUnit - 2].equals("#") && !exploded && !brickonLeft) {
                        gc.drawImage(Sprite.movingSprite(
                                Sprite.explosion_horizontal_left_last,
                                Sprite.explosion_horizontal_left_last1,
                                Sprite.explosion_horizontal_left_last2,
                                Sprite.explosion_horizontal_left_last1,
                                Sprite.explosion_horizontal_left_last,
                                timeAfterExplode, timeToAnimate).getFxImage(), x - Sprite.SCALED_SIZE * 2, y);

                    }
                }

                if(!BombermanGame.map[yUnit][xUnit + 1].equals("#") && !exploded) {
                    gc.drawImage(Sprite.movingSprite(
                            Sprite.explosion_horizontal,
                            Sprite.explosion_horizontal1,
                            Sprite.explosion_horizontal2,
                            Sprite.explosion_horizontal1,
                            Sprite.explosion_horizontal,
                            timeAfterExplode, timeToAnimate).getFxImage(), x + Sprite.SCALED_SIZE, y);

                    if(!BombermanGame.map[yUnit][xUnit + 2].equals("#") && !exploded && !brickonRight) {
                        gc.drawImage(Sprite.movingSprite(
                                Sprite.explosion_horizontal_right_last,
                                Sprite.explosion_horizontal_right_last1,
                                Sprite.explosion_horizontal_right_last2,
                                Sprite.explosion_horizontal_right_last1,
                                Sprite.explosion_horizontal_right_last,
                                timeAfterExplode, timeToAnimate).getFxImage(), x + Sprite.SCALED_SIZE * 2, y);

                    }
                }

                if(!BombermanGame.map[yUnit - 1][xUnit].equals("#") && !exploded) {
                    gc.drawImage(Sprite.movingSprite(
                            Sprite.explosion_vertical,
                            Sprite.explosion_vertical1,
                            Sprite.explosion_vertical2,
                            Sprite.explosion_vertical1,
                            Sprite.explosion_vertical,
                            timeAfterExplode, timeToAnimate).getFxImage(), x, y - Sprite.SCALED_SIZE);

                    if(!BombermanGame.map[yUnit - 2][xUnit].equals("#") && !exploded && !brickonUp) {
                        gc.drawImage(Sprite.movingSprite(
                                Sprite.explosion_vertical_top_last,
                                Sprite.explosion_vertical_top_last1,
                                Sprite.explosion_vertical_top_last2,
                                Sprite.explosion_vertical_top_last1,
                                Sprite.explosion_vertical_top_last,
                                timeAfterExplode, timeToAnimate).getFxImage(), x, y - Sprite.SCALED_SIZE * 2);

                    }
                }

                if(!BombermanGame.map[yUnit + 1][xUnit].equals("#") && !exploded) {
                    gc.drawImage(Sprite.movingSprite(
                            Sprite.explosion_vertical,
                            Sprite.explosion_vertical1,
                            Sprite.explosion_vertical2,
                            Sprite.explosion_vertical1,
                            Sprite.explosion_vertical,
                            timeAfterExplode, timeToAnimate).getFxImage(), x, y + Sprite.SCALED_SIZE);

                    if(!BombermanGame.map[yUnit + 2][xUnit].equals("#") && !exploded && !brickonDown) {
                        gc.drawImage(Sprite.movingSprite(
                                Sprite.explosion_vertical_down_last,
                                Sprite.explosion_vertical_down_last1,
                                Sprite.explosion_vertical_down_last2,
                                Sprite.explosion_vertical_down_last1,
                                Sprite.explosion_vertical_down_last,
                                timeAfterExplode, timeToAnimate).getFxImage(), x, y + Sprite.SCALED_SIZE * 2);

                    }
                }

            } else if (radius == 3) {
                if (!exploded) {
                    gc.drawImage(Sprite.movingSprite(
                            Sprite.bomb_exploded,
                            Sprite.bomb_exploded1,
                            Sprite.bomb_exploded2,
                            Sprite.bomb_exploded1,
                            Sprite.bomb_exploded,
                            timeAfterExplode, timeToAnimate).getFxImage(), x, y);

                }
                if (!BombermanGame.map[yUnit][xUnit - 1].equals("#") && !exploded) {
                    gc.drawImage(Sprite.movingSprite(
                            Sprite.explosion_horizontal,
                            Sprite.explosion_horizontal1,
                            Sprite.explosion_horizontal2,
                            Sprite.explosion_horizontal1,
                            Sprite.explosion_horizontal,
                            timeAfterExplode, timeToAnimate).getFxImage(), x - Sprite.SCALED_SIZE, y);

                    if (!BombermanGame.map[yUnit][xUnit - 2].equals("#") && !exploded && !brickonLeft) {
                        gc.drawImage(Sprite.movingSprite(
                                Sprite.explosion_horizontal,
                                Sprite.explosion_horizontal1,
                                Sprite.explosion_horizontal2,
                                Sprite.explosion_horizontal1,
                                Sprite.explosion_horizontal,
                                timeAfterExplode, timeToAnimate).getFxImage(), x - Sprite.SCALED_SIZE * 2, y);

                        if (!BombermanGame.map[yUnit][xUnit - 3].equals("#") && !exploded && !brickonLeft1) {
                            gc.drawImage(Sprite.movingSprite(
                                    Sprite.explosion_horizontal_left_last,
                                    Sprite.explosion_horizontal_left_last1,
                                    Sprite.explosion_horizontal_left_last2,
                                    Sprite.explosion_horizontal_left_last1,
                                    Sprite.explosion_horizontal_left_last,
                                    timeAfterExplode, timeToAnimate).getFxImage(), x - Sprite.SCALED_SIZE * 3, y);

                        }
                    }
                }
                if (!BombermanGame.map[yUnit][xUnit + 1].equals("#") && !exploded) {
                    gc.drawImage(Sprite.movingSprite(
                            Sprite.explosion_horizontal,
                            Sprite.explosion_horizontal1,
                            Sprite.explosion_horizontal2,
                            Sprite.explosion_horizontal1,
                            Sprite.explosion_horizontal,
                            timeAfterExplode, timeToAnimate).getFxImage(), x + Sprite.SCALED_SIZE, y);

                    if (!BombermanGame.map[yUnit][xUnit + 2].equals("#") && !exploded && !brickonRight) {
                        gc.drawImage(Sprite.movingSprite(
                                Sprite.explosion_horizontal,
                                Sprite.explosion_horizontal1,
                                Sprite.explosion_horizontal2,
                                Sprite.explosion_horizontal1,
                                Sprite.explosion_horizontal,
                                timeAfterExplode, timeToAnimate).getFxImage(), x + Sprite.SCALED_SIZE * 2, y);

                        if (!BombermanGame.map[yUnit][xUnit + 3].equals("#") && !exploded && !brickonRight1) {
                            gc.drawImage(Sprite.movingSprite(
                                    Sprite.explosion_horizontal_right_last,
                                    Sprite.explosion_horizontal_right_last1,
                                    Sprite.explosion_horizontal_right_last2,
                                    Sprite.explosion_horizontal_right_last1,
                                    Sprite.explosion_horizontal_right_last,
                                    timeAfterExplode, timeToAnimate).getFxImage(), x + Sprite.SCALED_SIZE * 3, y);

                        }
                    }
                }
                if (!BombermanGame.map[yUnit - 1][xUnit].equals("#") && !exploded) {
                    gc.drawImage(Sprite.movingSprite(
                            Sprite.explosion_vertical,
                            Sprite.explosion_vertical1,
                            Sprite.explosion_vertical2,
                            Sprite.explosion_vertical1,
                            Sprite.explosion_vertical,
                            timeAfterExplode, timeToAnimate).getFxImage(), x, y - Sprite.SCALED_SIZE);

                    if (!BombermanGame.map[yUnit - 2][xUnit].equals("#") && !exploded && !brickonUp) {
                        gc.drawImage(Sprite.movingSprite(
                                Sprite.explosion_vertical,
                                Sprite.explosion_vertical1,
                                Sprite.explosion_vertical2,
                                Sprite.explosion_vertical1,
                                Sprite.explosion_vertical,
                                timeAfterExplode, timeToAnimate).getFxImage(), x, y - Sprite.SCALED_SIZE * 2);

                        if (!BombermanGame.map[yUnit - 3][xUnit].equals("#") && !exploded && !brickonUp1) {
                            gc.drawImage(Sprite.movingSprite(
                                    Sprite.explosion_vertical_top_last,
                                    Sprite.explosion_vertical_top_last1,
                                    Sprite.explosion_vertical_top_last2,
                                    Sprite.explosion_vertical_top_last1,
                                    Sprite.explosion_vertical_top_last,
                                    timeAfterExplode, timeToAnimate).getFxImage(), x, y - Sprite.SCALED_SIZE * 3);

                        }
                    }
                }
                if (!BombermanGame.map[yUnit + 1][xUnit].equals("#") && !exploded) {
                    gc.drawImage(Sprite.movingSprite(
                            Sprite.explosion_vertical,
                            Sprite.explosion_vertical1,
                            Sprite.explosion_vertical2,
                            Sprite.explosion_vertical1,
                            Sprite.explosion_vertical,
                            timeAfterExplode, timeToAnimate).getFxImage(), x, y + Sprite.SCALED_SIZE);

                    if (!BombermanGame.map[yUnit + 2][xUnit].equals("#") && !exploded && !brickonDown) {
                        gc.drawImage(Sprite.movingSprite(
                                Sprite.explosion_vertical,
                                Sprite.explosion_vertical1,
                                Sprite.explosion_vertical2,
                                Sprite.explosion_vertical1,
                                Sprite.explosion_vertical,
                                timeAfterExplode, timeToAnimate).getFxImage(), x, y + Sprite.SCALED_SIZE * 2);

                        if (!BombermanGame.map[yUnit + 3][xUnit].equals("#") && !exploded && !brickonDown1) {
                            gc.drawImage(Sprite.movingSprite(
                                    Sprite.explosion_vertical_down_last,
                                    Sprite.explosion_vertical_down_last1,
                                    Sprite.explosion_vertical_down_last2,
                                    Sprite.explosion_vertical_down_last1,
                                    Sprite.explosion_vertical_down_last,
                                    timeAfterExplode, timeToAnimate).getFxImage(), x, y + Sprite.SCALED_SIZE * 3);

                        }
                    }
                }
            }
        }
    }
}

