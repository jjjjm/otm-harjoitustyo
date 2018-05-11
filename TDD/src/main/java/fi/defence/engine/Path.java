package fi.defence.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.util.Pair;

/**
 * Kuvaa pelissä olevaa polkua mitä pitkin viholliset kulkevat
 */
public class Path {

    private final int width;
    private List<Pair<Integer, Integer>> coords;
    private List<Shape> hitBox;

    /**
     * Luo uuden Path-olion joka edustaa polkua kartalla (mitä pitkin viholliset
     * kulkevat)
     *
     * @param width kertoo polun maksimi leveyden (oltava korkeintaan sama kuin
     * kartan leveys)
     *
     */
    public Path(int width) {
        this.width = width;
    }

    public List<Pair<Integer, Integer>> getCoords() {
        return coords;
    }

    public void setCoords(List<Pair<Integer, Integer>> coords) {
        this.coords = coords;
    }

    /**
     * Palauttaa osuma alueen joka on määritelty polulle
     *
     * @return Osuma alue palautetaan Listana Shape-tyyppisiä olioita
     */
    public List<Shape> getHitBox() {
        return hitBox;
    }

    /**
     * Generoi ja asettaa polun pisteiksi uudet satunnaisesti generoidut
     * pisteet.
     *
     * @param mapHeight kartan korkeus (samalla polun viimeisen pisteen
     * y-koordinaatti)
     * @param mapWidth kartan leveys (samalla polun pisteiden x-koordinaattien
     * maksimi arvo)
     * @param amountOfVertices käännöskohtien lukumäärä
     */
    public void generateRandomPath(int mapHeight, int mapWidth, int amountOfVertices) {
        Random rng = new Random();
        this.coords = new ArrayList<>();
        int intervalLenght = mapHeight / amountOfVertices;
        boolean flipFlop = true;
        int x = 0, y = 0;
        for (int i = 0; i <= amountOfVertices - 1; i++) { // remove start and end from the count
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

    /**
     * Asettaa ennaltamääritellyt vakiopisteet polulle (Lähinnä debug ja testi
     * tarkoituksiin)
     */
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
            double x1 = coords.get(i - 1).getKey();
            double x2 = coords.get(i).getKey();
            double y1 = coords.get(i - 1).getValue();
            double y2 = coords.get(i).getValue();
            double w = this.width;
            if (Objects.equals(coords.get(i).getKey(), coords.get(i - 1).getKey())) {
                pathPart = new Polygon(x1 - w, y1 - w, x1 + w, y1 - w, x2 + w, y2 + w, x2 - w, y2 + w);
            } else {
                pathPart = new Polygon(x1, y1 - w, x1, y1 + w, x2, y2 + w, x2, y2 - w);
            }
            newHitbox.add(pathPart);
        }
        this.hitBox = newHitbox;
    }
}
