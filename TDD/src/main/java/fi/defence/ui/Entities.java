package fi.defence.ui;

import fi.defence.engine.Map;
import fi.defence.engine.Tower;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class Entities {

    private Map map;
    private List<Polygon> friendly;
    private List<Circle> rangeTest;

    public Entities(Map map) {
        this.map = map;
        this.friendly = new ArrayList<>();
        this.rangeTest = new ArrayList<>();
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
            Circle newCircle = new Circle(x, y, 75);
            newCircle.setStroke(Color.BLACK);
            newCircle.setFill(Color.TRANSPARENT);
            toReturn.add(newCircle);
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

    public List<Circle> getRangeTest() {
        return rangeTest;
    }

    public List<Polygon> getFriendly() {
        return friendly;
    }

}
