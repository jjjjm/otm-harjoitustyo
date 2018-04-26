package fi.defence.engine;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Tower extends PlaceableObject {

    private int range, damage;
    private Shape hitBox;
    private boolean reset;

    public Tower(int x, int y, int damage) {
        super(x, y);
        this.range = 75;
        this.damage = damage;
        hitBox = new Circle(x, y, 10);
        this.reset = false;
    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public int getY() {
        return super.getY();
    }

    public int getRange() {
        return range;
    }

    public boolean shootableInRange(NPC npc) {
        Circle rangeArea = new Circle(super.getX(), super.getY(), range);
        if (!this.reset) {
            if (rangeArea.getBoundsInParent().intersects(npc.getHitbox().getBoundsInParent())) {
                return true;
            }
        }
        return false;
    }

    public void engageShootable(NPC n) {
        n.setHealth(n.getHealth() - this.damage);
        this.reset = true;
    }

    public Shape getHitBox() {
        return hitBox;
    }

    public void reset() {
        this.reset = false;
    }

}
