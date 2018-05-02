package fi.defence.engine;

import java.util.ArrayList;
import java.util.Optional;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class Map {

    private int width, length, health, money;
    private Path path;
    private ArrayList<Tower> objects;
    private ArrayList<NPC> enemies;
    private ArrayList<Tower> toAdd;
    private boolean resolved;

    public Map(int width, int length) {
        this.width = width;
        this.length = length;
        this.path = new Path(25);
        this.objects = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.toAdd = new ArrayList<>();
        this.health = 10;
        this.money = 50;
        this.resolved = true;
    }

    public void generateNewRandomPath(int verticeAmount) {
        this.path.generateRandomPath(this.length, this.width, verticeAmount);
    }

    public void resolveIntersects() {
        if (resolved) {
            this.resolved = false;
            for (Tower t : this.objects) {
                for (NPC n : this.enemies) {
                    if (t.shootableInRange(n)) {
                        t.engageShootable(n);
                    }
                }
            }
            this.resolved = true;
        }
    }

    private void resolveEnemies() {
        ArrayList<NPC> toRemove = new ArrayList<>();
        for (NPC n : this.enemies) {
            if (n.getHealth() <= 0) {
                toRemove.add(n);
                this.money += 1;
            }
            if (n.getHitbox().getBoundsInLocal().intersects(new Line(0, length, length, length).getBoundsInLocal())) {
                this.health -= 1;
                n.setHealth(-1);
                toRemove.add(n);
            }
        }
        this.enemies.removeAll(toRemove);
    }

    private void addTowers() {
        this.objects.addAll(toAdd);
        this.toAdd.clear();
    }

    public void resolve() {
        this.addTowers();
        this.resolveIntersects();
        this.resolveEnemies();
        this.enemies.forEach(n -> n.traverseToNextNode());
    }

    public void resetTowers() {
        this.objects.forEach(t -> t.reset());
    }

    public Optional<NPC> addEnemy() {
        NPC newNPC = new NPC(this.path.getCoords().get(0).getKey(), this.path.getCoords().get(0).getValue(), path, 10, 10, 1);
        Optional<NPC> toReturn;
        if (this.enemies.size() < 15) {
            toReturn = Optional.of(newNPC);
            this.enemies.add(newNPC);
            return toReturn;
        } else {
            toReturn = Optional.empty();
            return toReturn;
        }

    }

    public Optional<Tower> addTower(int x, int y) {
        Optional<Tower> toReturn = Optional.empty();
        if (this.money >= 5) {
            Tower t = new Tower(x, y, 1);
            for (Shape s : this.path.getHitBox()) {
                if (t.getHitBox().intersects(s.getBoundsInLocal())) {
                    return toReturn;
                }
            }
            for (Tower t2 : this.objects) {
                if (t.getHitBox().intersects(t2.getHitBox().getBoundsInLocal())) {
                    return toReturn;
                }
            }
            this.money -= 5;
            this.toAdd.add(t);
            return Optional.of(t);
        }
        return toReturn;
    }

    public boolean removeTower(int x, int y) {
        for (Tower t : this.objects) {
            if (t.getHitBox().intersects(x, y, 20, 20)) {
                this.money += 5;
                this.objects.remove(t);
                return true;
            }
        }
        return false;
    }

    public int getMoney() {
        return money;
    }

    public Path getPath() {
        return path;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHealth() {
        return health;
    }

}
