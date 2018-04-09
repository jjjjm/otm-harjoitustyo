package engine;

import java.util.ArrayList;
import java.util.Random;
import javafx.util.Pair;

public class Path {

    private ArrayList<Pair<Integer, Integer>> coords;

    public ArrayList<Pair<Integer, Integer>> getCoords() {
        return coords;
    }

    public void setCoords(ArrayList<Pair<Integer, Integer>> coords) {//debug
        this.coords = coords;
    }

    public void generateRandomPath(int lowerLimitX, int upperLimitX, int yStart, int xStart, int yEnd, int xEnd, int amountOfVertices) {
        Random rng = new Random();
        this.coords = new ArrayList<>();
        this.coords.add(new Pair(xStart, yStart));
        int intervalLenght = Math.abs(yStart - yEnd) / amountOfVertices;
        for (int i = 0; i <= amountOfVertices - 1; i++) {// remove start and end from the count
            int x = lowerLimitX + (rng.nextInt(upperLimitX - lowerLimitX));
            int y = i * intervalLenght + rng.nextInt(intervalLenght);
            this.coords.add(new Pair(x, y));
        }
        this.coords.add(new Pair(xEnd, yEnd));
    }
    
    public void setDefaultPath(){
        this.coords = new ArrayList<>();
        this.coords.add(new Pair(400,0));
        this.coords.add(new Pair(200,100));
        this.coords.add(new Pair(200,300));
        this.coords.add(new Pair(700,300));
        this.coords.add(new Pair(700,400));
        this.coords.add(new Pair(400,400));
        this.coords.add(new Pair(400,600));
    }
}
