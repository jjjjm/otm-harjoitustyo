package fi.defence.engine;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Tower {

    private int x, y, range, damage;
    private Shape hitBox;
    private boolean reset;

    public Tower(int x, int y, int damage) {
        this.x = x;
        this.y = y;
        this.range = 75;
        this.damage = damage;
        hitBox = new Circle(x, y, 10);
        this.reset = false;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getRange() {
        return range;
    }

    public boolean shootableInRange(NPC npc) {
        Circle rangeArea = new Circle(x, y, range);
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
