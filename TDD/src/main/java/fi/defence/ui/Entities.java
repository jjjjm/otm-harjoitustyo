package fi.defence.ui;

import fi.defence.engine.Map;
import fi.defence.engine.NPC;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class Entities {

    private Map map;
    private List<Polygon> friendly;
    private HashMap<NPC, Circle> enemies;

    public Entities(Map map) {
        this.map = map;
        this.friendly = new ArrayList<>();
        this.enemies = new HashMap<>();
    }

    Shape addEnemy() {
        Circle newEnemy = new Circle(map.getPath().getCoords().get(0).getKey(), map.getPath().getCoords().get(0).getValue(), 10);
        this.enemies.put(this.map.addEnemy(), newEnemy);
        return newEnemy;
    }

    private List<Shape> translateEnemies() {
        List<Shape> toReturn = new LinkedList<>();
        for (java.util.Map.Entry<NPC, Circle> n : this.enemies.entrySet()) {
            if (n.getKey().getHealth() <= 0) {
                toReturn.add(n.getValue());
            }
            n.getValue().setCenterX(n.getValue().getCenterX() + (n.getKey().getX() - n.getValue().getCenterX()));
            n.getValue().setCenterY(n.getValue().getCenterY() + (n.getKey().getY() - n.getValue().getCenterY()));
        }
        return toReturn;
    }

    private void calculateProjectiles() {
        for(Polygon p : this.friendly){
            
        }
    }

    List<Shape> returnEnemyShapes() {
        return this.translateEnemies();
    }

    List<Shape> addTower(double xd, double yd) {
        List<Shape> toReturn = new LinkedList<Shape>();
        int x = (int) Math.floor(xd);
        int y = (int) Math.floor(yd);
        if (this.map.addTower(x, y)) {
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
            this.friendly.add(newTower);
        }

        return toReturn;
    }

//    List<Shape> mockUp(double xd, double yd) {
//        List<Shape> toReturn = new LinkedList<>();
//        int x = (int) Math.floor(xd);
//        int y = (int) Math.floor(yd);
//
//        Polygon newTower
//                = new Polygon(
//                        x - 5, y - 10,
//                        x + 5, y - 10,
//                        x + 10, y,
//                        x + 5, y + 10,
//                        x - 5, y + 10,
//                        x - 10, y);
//        newTower.setFill(Color.BROWN);
//        Circle newCircle = new Circle(x, y, 75);
//        newCircle.setFill(Color.TRANSPARENT);
//        newCircle.setStroke(Color.BLACK);
//        toReturn.add(newTower);
//        toReturn.add(newCircle);
//
//        return toReturn;
//    }
    public List<Polygon> getFriendly() {
        return friendly;
    }

}
