package fi.defence.ui;

import fi.defence.engine.Map;
import fi.defence.engine.NPC;
import fi.defence.engine.Tower;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class Entities {

    private Map map;
    private HashMap<Tower, Polygon> friendly;
    private HashMap<NPC, Circle> enemies;
    private HashMap<Circle, NPC> projectile;

    public Entities(Map map) {
        this.map = map;
        this.friendly = new HashMap<>();
        this.enemies = new HashMap<>();
        this.projectile = new HashMap<>();
    }

    List<Circle> addEnemy() {
        List<Circle> toReturn = new LinkedList<>();
        Circle newEnemy = new Circle(map.getPath().getCoords().get(0).getKey(), map.getPath().getCoords().get(0).getValue(), 10);
        Optional<NPC> npc = this.map.addEnemy();
        if (npc.isPresent()) {
            this.enemies.put(npc.get(), newEnemy);
            toReturn.add(newEnemy);
        }
        return toReturn;
    }

    private List<Circle> translateEnemies() {
        List<Circle> toReturn = new LinkedList<>();
        List<NPC> toRemove = new LinkedList<>();
        for (java.util.Map.Entry<NPC, Circle> n : this.enemies.entrySet()) {
            if (n.getKey().getHealth() <= 0) {
                toReturn.add(n.getValue());
                toRemove.add(n.getKey());
            } else {
                n.getValue().setCenterX(n.getValue().getCenterX() + (n.getKey().getX() - n.getValue().getCenterX()));
                n.getValue().setCenterY(n.getValue().getCenterY() + (n.getKey().getY() - n.getValue().getCenterY()));
            }
        }
        toRemove.forEach(n -> this.enemies.remove(n));
        return toReturn;
    }

    List<Circle> translateProjectiles() {
        List<Circle> toReturn = new LinkedList<>();
        for (java.util.Map.Entry<Circle, NPC> n : this.projectile.entrySet()) {
            if (this.enemies.containsKey(n.getValue())) {
                Circle p = this.enemies.get(n.getValue());
                if (n.getKey().getBoundsInLocal().intersects(p.getBoundsInLocal())) {
                    toReturn.add(n.getKey());
                } else {
                    n.getKey().setCenterX(n.getKey().getCenterX() + (p.getCenterX() - n.getKey().getCenterX()) / 4);
                    n.getKey().setCenterY(n.getKey().getCenterY() + (p.getCenterY() - n.getKey().getCenterY()) / 4);
                }
            } else {
                toReturn.add(n.getKey());
            }
        }
        toReturn.forEach(p -> this.projectile.remove(p));
        return toReturn;
    }

    private List<Shape> calculateProjectiles() {
        List<Shape> toReturn = new LinkedList<>();
        for (Tower t : this.friendly.keySet()) {
            for (NPC  n : this.enemies.keySet()) {
                if (t.shootableInRange(n)) {
                    Circle newProjectile = new Circle(t.getX(), t.getY(), 4);
                    this.projectile.put(newProjectile, n);
                    toReturn.add(newProjectile);
                }
            }
        }
        return toReturn;
    }

    List<Shape> returnProjectiles() {
        return this.calculateProjectiles();
    }

    List<Circle> returnRemovableEnemyShapes() {
        return this.translateEnemies();
    }

    List<Shape> returnRemovableTowers() {
        return new LinkedList<>();
    }

    List<Circle> returnRemovableProjectiles() {
        return this.translateProjectiles();
    }

    List<Shape> addTower(double xd, double yd) {
        List<Shape> toReturn = new LinkedList<Shape>();
        int x = (int) Math.floor(xd);
        int y = (int) Math.floor(yd);
        Optional<Tower> t = this.map.addTower(x, y);
        if (t.isPresent()) {
            Polygon newTower
                    = new Polygon(
                            x - 5, y - 10,
                            x + 5, y - 10,
                            x + 10, y,
                            x + 5, y + 10,
                            x - 5, y + 10,
                            x - 10, y);
            newTower.setFill(Color.BROWN);
            toReturn.add(newTower);
            this.friendly.put(t.get(), newTower);
        }

        return toReturn;
    }

    public List<Polygon> getFriendly() {
        List<Polygon> toReturn = new LinkedList<>();
        for (Polygon p : this.friendly.values()) {
            toReturn.add(p);
        }
        return toReturn;
    }

}
