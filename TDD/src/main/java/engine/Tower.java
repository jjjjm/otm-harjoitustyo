package engine;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Tower extends PlaceableObject {

    private int range,damage;
    private Shape hitBox;

    public Tower(int x, int y, int damage) {
        super(x, y);
        this.range = 75;
        this.damage = damage;
        hitBox = new Circle(x, y, 10);
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
        return rangeArea.getBoundsInParent().intersects(npc.hitbox().getBoundsInParent());
    }

    public void engageShootable(NPC n) {
        n.setHealth(n.getHealth() - this.damage);
    }

    public Shape getHitBox() {
        return hitBox;
    }

}
