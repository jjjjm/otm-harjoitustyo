package engine;

import java.util.ArrayList;

public class Map {

    private int width, length;
    private double placeablePercent;
    private Path path;
    private ArrayList<PlaceableObject> objects;

    public Map(int width, int length) {
        this.width = width;
        this.length = length;
        this.placeablePercent = 0.85;
        this.path = new Path();
        this.objects = new ArrayList<>();
    }

    public void generateNewRandomPath(int xLower, int xHigher, int startX, int endX) {
        this.path.generateRandomPath(xLower, xHigher, 0, startX, length, endX, 15);
    }

    public ArrayList<PlaceableObject> getObjects() {
        return objects;
    }

    public void addObject(int x, int y) {
        this.objects.add(new PlaceableObject(x, y));
    }
    
     public void addTower(int x, int y) {
        this.objects.add(new Tower(x, y));
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
