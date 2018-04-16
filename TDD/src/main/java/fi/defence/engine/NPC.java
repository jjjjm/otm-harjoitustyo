package fi.defence.engine;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class NPC {

    private double x, y, hitBoxRadius;
    private Path path;
    private int atNode, health,speed;

    public NPC(double xStart, double yStart, Path p, double hitBoxRadius, int health,int speed) {
        this.x = xStart;
        this.y = yStart;
        this.path = p;
        this.atNode = 0;
        this.hitBoxRadius = hitBoxRadius;
        this.health = health;
        this.speed = speed;
    }

    public Shape hitbox() {
        return new Circle(this.x, this.y, hitBoxRadius);
    }

    public void traverseToNextNode() {
        
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

}
