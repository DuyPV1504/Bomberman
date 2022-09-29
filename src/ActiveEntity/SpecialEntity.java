package ActiveEntity;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class SpecialEntity extends Entity {
    public boolean isDead = false;
    public int AnimationBetween = 15;
    public Integer score = 0;
    public SpecialEntity(int yUnit, int xUnit, Image img) {
        super(yUnit, xUnit, img);
    }
}
