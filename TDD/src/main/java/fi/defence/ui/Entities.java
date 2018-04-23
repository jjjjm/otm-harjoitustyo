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
    private HashMap<NPC, Shape> enemies;

    public Entities(Map map) {
        this.map = map;
        this.friendly = new ArrayList<>();
        this.enemies = new HashMap<>();
    }

    Shape addEnemy() {
        Shape newEnemy = new Circle(map.getPath().getCoords().get(0).getKey(), map.getPath().getCoords().get(0).getValue(), 10);
        this.enemies.put(this.map.addEnemy(),newEnemy);
        return newEnemy;
    }
    
    private List<Shape> translateEnemies(){
        List<Shape> toReturn = new LinkedList<>();
        toReturn.addAll(this.enemies.values());
        for(java.util.Map.Entry<NPC,Shape> n : this.enemies.entrySet()){
            n.getValue().setTranslateX(n.getValue().getTranslateX() + (n.getValue().getTranslateX() - n.getKey().getX()));
            n.getValue().setTranslateY(n.getValue().getTranslateY() + (n.getValue().getTranslateY() - n.getKey().getY()));
            System.out.println(n.getValue());
        }
        return toReturn;
    }
    
    List<Shape> returnEnemyShapes(){
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
            //Circle newCircle = new Circle(x, y, 75);
            //newCircle.setStroke(Color.BLACK);
            //newCircle.setFill(Color.TRANSPARENT);
            //toReturn.add(newCircle);
            toReturn.add(newTower);
            this.friendly.add(newTower);
        }

        return toReturn;
    }
//
//    Shape mockUp(double xd, double yd) {
//        Shape toReturn = new Polygon();
//        int x = (int) Math.floor(xd);
//        int y = (int) Math.floor(yd);
//        if (this.map.addTower(x, y)) {
//            Polygon newTower
//                    = new Polygon(
//                            x - 5, y - 10,
//                            x + 5, y - 10,
//                            x + 10, y,
//                            x + 5, y + 10,
//                            x - 5, y + 10,
//                            x - 10, y);
//            newTower.setFill(Color.BROWN);
//            Ellipse newCircle = new Ellipse(x, y, 75, 75);
//            toReturn = Shape.union(newCircle, newTower);
//            this.friendly.add(newTower);
//
//        }
//
//        return toReturn;
//    }


    public List<Polygon> getFriendly() {
        return friendly;
    }

}
