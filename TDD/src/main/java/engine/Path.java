package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.util.Pair;

public class Path {

    private final int width;
    private ArrayList<Pair<Integer, Integer>> coords;
    private List<Shape> hitBox;

    public Path(int width) {
        this.width = width;
    }

    public ArrayList<Pair<Integer, Integer>> getCoords() {
        return coords;
    }

    public void setCoords(ArrayList<Pair<Integer, Integer>> coords) {//debug
        this.coords = coords;
    }

    public List<Shape> getHitBox() {
        return hitBox;
    }

    public void generateRandomPath(int mapHeight, int mapWidth, int amountOfVertices) {
        Random rng = new Random();
        this.coords = new ArrayList<>();
        int intervalLenght = mapHeight / amountOfVertices;
        boolean flipFlop = true;
        int x = 0,y = 0;
        for (int i = 0; i <= amountOfVertices - 1; i++) {// remove start and end from the count
            if (flipFlop) {
                x = 0 + (rng.nextInt(mapWidth));
                flipFlop = false;
            } else {
                y = i * intervalLenght + rng.nextInt(intervalLenght);
                flipFlop = true;
            }
            this.coords.add(new Pair(x, y));
        }
        this.coords.add(new Pair(x, mapHeight));
        this.calculateHitBox();
    }

    public void setDefaultPath() {
        this.coords = new ArrayList<>();
        this.coords.add(new Pair(400, 0));
        this.coords.add(new Pair(400, 150));
        this.coords.add(new Pair(200, 150));
        this.coords.add(new Pair(200, 300));
        this.coords.add(new Pair(700, 300));
        this.coords.add(new Pair(700, 400));
        this.coords.add(new Pair(400, 400));
        this.coords.add(new Pair(400, 600));
        this.calculateHitBox();
    }

    public int getWidth() {
        return width;
    }

    private void calculateHitBox() {
        List<Shape> newHitbox = new ArrayList<>();
        for (int i = 1; i < coords.size(); i++) {
            Polygon pathPart = null;
            if (Objects.equals(coords.get(i).getKey(), coords.get(i - 1).getKey())) {
                pathPart = new Polygon(
                        (double) coords.get(i - 1).getKey() - this.width, (double) coords.get(i - 1).getValue() - this.width,
                        (double) coords.get(i - 1).getKey() + this.width, (double) coords.get(i - 1).getValue() - this.width,
                        (double) coords.get(i).getKey() + this.width, (double) coords.get(i).getValue() + this.width,
                        (double) coords.get(i).getKey() - this.width, (double) coords.get(i).getValue() + this.width
                );
            } else {
                pathPart = new Polygon(
                        (double) coords.get(i - 1).getKey(), (double) coords.get(i - 1).getValue() - this.width,
                        (double) coords.get(i - 1).getKey(), (double) coords.get(i - 1).getValue() + this.width,
                        (double) coords.get(i).getKey(), (double) coords.get(i).getValue() + this.width,
                        (double) coords.get(i).getKey(), (double) coords.get(i).getValue() - this.width
                );

            }
            newHitbox.add(pathPart);
        }
        this.hitBox = newHitbox;
    }
}
