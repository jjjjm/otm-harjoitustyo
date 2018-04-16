package engine;

import java.util.LinkedList;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Map {

    private int width, length;
    private Path path;
    private LinkedList<Tower> objects;
    private LinkedList<NPC> enemies;

    public Map(int width, int length) {
        this.width = width;
        this.length = length;
        this.path = new Path(25);
        this.objects = new LinkedList<>();
    }

    public void generateNewRandomPath(int verticeAmount) {
        this.path.generateRandomPath(this.length, this.width, verticeAmount);
    }

    public void resolveIntersects() {
        for (Tower t : this.objects) {
            for (NPC n : this.enemies) {
                if (t.shootableInRange(n)) {
                    t.engageShootable(n);
                }
            }
        }
    }

    public void addEnemy(int x, int y) {

    }

    public boolean addTower(int x, int y) {
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
        this.objects.add(new Tower(x, y, 1));
        return true;
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

}
