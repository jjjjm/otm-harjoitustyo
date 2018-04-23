package fi.defence.engine;

import java.util.ArrayList;
import java.util.Iterator;
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
        this.health = 25;
        this.money = 30;
        this.resolved = true;
    }

    public void generateNewRandomPath(int verticeAmount) {
        this.path.generateRandomPath(this.length, this.width, verticeAmount);
    }

    public void resolveIntersects() {
        if (resolved) {
            this.resolved = false;
            Iterator<Tower> iter1 = this.objects.iterator();
            Iterator<NPC> iter2 = this.enemies.iterator();
            while (iter1.hasNext()) {
                Tower t = iter1.next();
                while (iter2.hasNext()) {
                    NPC n = iter2.next();
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
            if (n.getHealth() == 0) {
                toRemove.add(n);
                this.money += 1;
            }
            if (n.getHitbox().intersects(length, 5, width, 5)) {
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
        this.resolveIntersects();
        System.out.println("Resolving intersects");
        this.resolveEnemies();
        System.out.println("Resolving enemies");
        this.addTowers();
        System.out.println("Adding towers");
        this.enemies.forEach(n -> n.traverseToNextNode());
        System.out.println("Resolving enemy locations");
    }

    public NPC addEnemy() {
        NPC toReturn = new NPC(this.path.getCoords().get(0).getKey(), this.path.getCoords().get(0).getValue(), path, 10, 10, 1);
        if (this.enemies.size() < 15) {
            this.enemies.add(toReturn);
            return toReturn;
        } else {
            return toReturn;
        }

    }

    public boolean addTower(int x, int y) {
        if (this.money >= 5) {
            Tower t = new Tower(x, y, 1);
            for (Shape s : this.path.getHitBox()) {
                if (t.getHitBox().intersects(s.getBoundsInLocal())) {
                    return false;
                }
            }
            for (Tower t2 : this.objects) {
                if (t.getHitBox().intersects(t2.getHitBox().getBoundsInLocal())) {
                    return false;
                }
            }
            this.money -= 5;
            this.toAdd.add(new Tower(x, y, 1));
            return true;
        }
        return false;
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

    public void setPath(Path path) {
        this.path = path;
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
