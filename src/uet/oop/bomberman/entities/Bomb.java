package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {
    private int timeToExplode = 180;
    private int timeAfterExplode = 50;
    private boolean exploded = false;
    private boolean allowToPassThru = true;
    private int radius = 1;

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
        for (Entity entity : BombermanGame.entities) {
            for (int i = 0; i <= radius; i++) {

                if (entity.getxUnit() <= this.getxUnit() + i && entity.getxUnit() >= this.getxUnit() - i
                        && entity.getyUnit() == this.getyUnit()) {
                    entity.setAlive(false);
                }

                if (entity.getyUnit() <= this.getyUnit() + i && entity.getyUnit() >= this.getyUnit() - i
                        && entity.getxUnit() == this.getxUnit()) {
                    entity.setAlive(false);
                }

            }
        }

        for (Entity entity : BombermanGame.stillObjects) {
            for (int i = 0; i <= radius; i++) {
                if (entity instanceof Brick) {

                    if (entity.getxUnit() <= this.getxUnit() + i && entity.getxUnit() >= this.getxUnit() - i
                            && entity.getyUnit() == this.getyUnit()) {
                        entity.setAlive(false);
                    }

                    if (entity.getyUnit() <= this.getyUnit() + i && entity.getyUnit() >= this.getyUnit() - i
                            && entity.getxUnit() == this.getxUnit()) {
                        entity.setAlive(false);
                    }

                }
            }
        }
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
            } else {
                exploded = true;
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
                    gc.drawImage(Sprite.bomb_exploded.getFxImage(), x, y);
                }
                if (!BombermanGame.map[yUnit][xUnit - 1].equals("#") && !exploded) {
                    gc.drawImage(Sprite.explosion_horizontal_left_last.getFxImage(), x - Sprite.SCALED_SIZE, y);
                }
                if (!BombermanGame.map[yUnit][xUnit + 1].equals("#") && !exploded) {
                    gc.drawImage(Sprite.explosion_horizontal_right_last.getFxImage(), x + Sprite.SCALED_SIZE, y);
                }
                if (!BombermanGame.map[yUnit - 1][xUnit].equals("#") && !exploded) {
                    gc.drawImage(Sprite.explosion_vertical_top_last.getFxImage(), x, y - Sprite.SCALED_SIZE);
                }
                if (!BombermanGame.map[yUnit + 1][xUnit].equals("#") && !exploded) {
                    gc.drawImage(Sprite.explosion_vertical_down_last.getFxImage(), x, y + Sprite.SCALED_SIZE);
                }

            } else if (radius == 2) {
                if (!exploded) {
                    gc.drawImage(Sprite.bomb_exploded1.getFxImage(), x, y);
                }
                if (!BombermanGame.map[yUnit][xUnit - 1].equals("#")
                        && !BombermanGame.map[yUnit][xUnit - 2].equals("#") && !exploded) {
                    gc.drawImage(Sprite.explosion_horizontal_left_last1.getFxImage(), x - Sprite.SCALED_SIZE * 2, y);
                }
                if (!BombermanGame.map[yUnit][xUnit + 1].equals("#") &&
                        !BombermanGame.map[yUnit][xUnit + 2].equals("#") && !exploded) {
                    gc.drawImage(Sprite.explosion_horizontal_right_last1.getFxImage(), x + Sprite.SCALED_SIZE * 2, y);
                }
                if (!BombermanGame.map[yUnit - 1][xUnit].equals("#")
                        && !BombermanGame.map[yUnit - 2][xUnit].equals("#") && !exploded) {
                    gc.drawImage(Sprite.explosion_vertical_top_last1.getFxImage(), x, y - Sprite.SCALED_SIZE * 2);
                }
                if (!BombermanGame.map[yUnit + 1][xUnit].equals("#") &&
                        !BombermanGame.map[yUnit + 2][xUnit].equals("#") && !exploded) {
                    gc.drawImage(Sprite.explosion_vertical_down_last1.getFxImage(), x, y + Sprite.SCALED_SIZE * 2);
                }
                if (!BombermanGame.map[yUnit][xUnit - 1].equals("#") && !exploded) {
                    gc.drawImage(Sprite.explosion_horizontal1.getFxImage(), x - Sprite.SCALED_SIZE, y);
                }
                if (!BombermanGame.map[yUnit][xUnit + 1].equals("#") && !exploded) {
                    gc.drawImage(Sprite.explosion_horizontal1.getFxImage(), x + Sprite.SCALED_SIZE, y);
                }
                if (!BombermanGame.map[yUnit - 1][xUnit].equals("#") && !exploded) {
                    gc.drawImage(Sprite.explosion_vertical1.getFxImage(), x, y - Sprite.SCALED_SIZE);
                }
                if (!BombermanGame.map[yUnit + 1][xUnit].equals("#") && !exploded) {
                    gc.drawImage(Sprite.explosion_vertical1.getFxImage(), x, y + Sprite.SCALED_SIZE);
                }

            } else if (radius == 3) {
                if (!exploded) {
                    gc.drawImage(Sprite.bomb_exploded2.getFxImage(), x, y);
                }
                if (!BombermanGame.map[yUnit][xUnit - 1].equals("#") && !exploded) {
                    gc.drawImage(Sprite.explosion_horizontal2.getFxImage(), x - Sprite.SCALED_SIZE, y);
                    if (!BombermanGame.map[yUnit][xUnit - 2].equals("#") && !exploded) {
                        gc.drawImage(Sprite.explosion_horizontal2.getFxImage(), x - Sprite.SCALED_SIZE * 2, y);
                        if (!BombermanGame.map[yUnit][xUnit - 3].equals("#") && !exploded) {
                            gc.drawImage(Sprite.explosion_horizontal_left_last2.getFxImage(), x - Sprite.SCALED_SIZE * 3, y);
                        }
                    }
                }
                if (!BombermanGame.map[yUnit][xUnit + 1].equals("#") && !exploded) {
                    gc.drawImage(Sprite.explosion_horizontal2.getFxImage(), x + Sprite.SCALED_SIZE, y);
                    if (!BombermanGame.map[yUnit][xUnit + 2].equals("#") && !exploded) {
                        gc.drawImage(Sprite.explosion_horizontal2.getFxImage(), x + Sprite.SCALED_SIZE * 2, y);
                        if (!BombermanGame.map[yUnit][xUnit + 3].equals("#") && !exploded) {
                            gc.drawImage(Sprite.explosion_horizontal_right_last2.getFxImage(), x + Sprite.SCALED_SIZE * 3, y);
                        }
                    }
                }
                if (!BombermanGame.map[yUnit - 1][xUnit].equals("#") && !exploded) {
                    gc.drawImage(Sprite.explosion_vertical2.getFxImage(), x, y - Sprite.SCALED_SIZE);
                    if (!BombermanGame.map[yUnit - 2][xUnit].equals("#") && !exploded) {
                        gc.drawImage(Sprite.explosion_vertical2.getFxImage(), x, y - Sprite.SCALED_SIZE * 2);
                        if (!BombermanGame.map[yUnit - 3][xUnit].equals("#") && !exploded) {
                            gc.drawImage(Sprite.explosion_vertical_top_last2.getFxImage(), x, y - Sprite.SCALED_SIZE * 3);
                        }
                    }
                }
                if (!BombermanGame.map[yUnit + 1][xUnit].equals("#") && !exploded) {
                    gc.drawImage(Sprite.explosion_vertical2.getFxImage(), x, y + Sprite.SCALED_SIZE);
                    if (!BombermanGame.map[yUnit + 2][xUnit].equals("#") && !exploded) {
                        gc.drawImage(Sprite.explosion_vertical2.getFxImage(), x, y + Sprite.SCALED_SIZE * 2);
                        if (!BombermanGame.map[yUnit + 3][xUnit].equals("#") && !exploded) {
                            gc.drawImage(Sprite.explosion_vertical_down_last2.getFxImage(), x, y + Sprite.SCALED_SIZE * 3);
                        }
                    }
                }
            }
        }
    }
}

