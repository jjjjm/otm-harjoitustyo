package fi.defence.engine;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class NPC {

    private double x, y, hitBoxRadius;
    private Path path;
    private int atNode, health, speed;
/**
 * Luo uuden vihollista mallintavan NPC olion
 * @param xStart alku x-koordinaatti
 * @param yStart alku y-koordinaatti
 * @param p polku mitä pitkin olio kulkee
 * @param hitBoxRadius ammuttavan alueen rajat (määrittelee siis säteen ympyrälle jonka keskipiste on nykyinen x,y koordinaatti)
 * @param health
 * @param speed 
 */
    public NPC(double xStart, double yStart, Path p, double hitBoxRadius, int health, int speed) {
        this.x = xStart;
        this.y = yStart;
        this.path = p;
        this.atNode = 0;
        this.hitBoxRadius = hitBoxRadius;
        this.health = health;
        this.speed = speed;
    }

    public Shape getHitbox() {
        return new Circle(this.x, this.y, hitBoxRadius);
    }
/**
 * Kuljettaa NPC eteenpäin polkua pitkin
 * (Ts. Muuttaa olion x- tai y-koordinaattia riippuen speed-muuttujasta ja nykyisestä
 * sijainnista polulla
 */
    public void traverseToNextNode() {
        int nextX = this.path.getCoords().get(atNode).getKey();
        int nextY = this.path.getCoords().get(atNode).getValue();
        if (this.x == nextX && this.y == nextY) {
            this.atNode++;
            nextX = this.path.getCoords().get(atNode).getKey();
            nextY = this.path.getCoords().get(atNode).getValue();
        }
        if (this.y == nextY) {
            this.x += nextX < this.x ? -speed : speed;
        } else {
            this.y += speed;
        }
    }

    public int getSpeed() {
        return speed;
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
