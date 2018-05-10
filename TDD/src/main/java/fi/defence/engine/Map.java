package fi.defence.engine;

import java.util.ArrayList;
import java.util.Optional;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;


public class Map {

    private Player player;
    private int width, length;
    private Path path;
    private ArrayList<Tower> objects;
    private ArrayList<NPC> enemies;
    private ArrayList<Tower> toAdd;
    private boolean resolved;

    /**
     * Luo uuden kartta olion joka hallinnoi pelin sovelluslogiikkaa.
     * @param width asettaa kartan leveyden
     * @param length asettaa kartan korkeuden
     */
    public Map(int width, int length) {
        this.player = new Player(50,10);
        this.width = width;
        this.length = length;
        this.path = new Path(25);
        this.objects = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.resolved = true;
    }

    /**
     * Generoi kartalle uuden satunnaisen polun käyttäen Path metodeita, ottaa parametrikseen risteysten lukumäärän
     * @param verticeAmount risteysten lkm
     * @see fi.defence.engine.Path.generateRandomPath
     */
    public void generateNewRandomPath(int verticeAmount) {
        this.path.generateRandomPath(this.length, this.width, verticeAmount);
    }
/**
 * Etsii viholliset jotka ovat tornien ampuma-alueilla ja kutsuu tornin ampumis metodia nille jotka ovat.
 * @see fi.defence.engine.Tower.shootableInRange
 * @see fi.defence.engine.Tower.engageShootable
 */
    public void resolveIntersects() {
        this.resolved = false;
        for (Tower t : this.objects) {
            for (NPC n : this.enemies) {
                if (t.shootableInRange(n)) {
                    t.engageShootable(n);
                }
            }
        }
    }

    private void resolveEnemies() {
        ArrayList<NPC> toRemove = new ArrayList<>();
        for (NPC n : this.enemies) {
            if (n.getHealth() <= 0) {
                toRemove.add(n);
                this.player.addScore();
            }
            if (n.getHitbox().getBoundsInLocal().intersects(new Line(0, length, length, length).getBoundsInLocal())) {
                this.player.takeDamage();
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
/**
 * Kutsuu useita omia sisäisiä metodejaan ajakseen yhden askeleen peliä eteenpäin.
 * Lisää uudet tornit, selvittää ampuma tilanteet, selvittää vihollisten tilanteet 
 * ja siirtää vielä elossa olevia vihollis olioita eteenpäin.
 * @see fi.defence.engine.NPC.traverseToNextNode
 */
    public void resolve() {
        this.addTowers();
        this.resolveIntersects();
        this.resolveEnemies();
        this.enemies.forEach(n -> n.traverseToNextNode());
    }

    /**
     * Asettaa kaikki kartalla olevat tornit ampumakykyisiksi
     * @see fi.defence.engine.Tower.reset
     */
    public void resetTowers() {
        this.objects.forEach(t -> t.reset());
    }
    /**
     * Lisää vihollisen jos se on mahdollista, eli jos vihollisia on kartalla vähemmän 
     * tai yhtä paljon kuin raja sallii
     * @return palauttaa uuden vihollisen käärittynä Optional säiliöön tai tyhjän Optional säiliön
     */
    public Optional<NPC> addEnemy() {
        NPC newNPC = new NPC(this.path.getCoords().get(0).getKey(), this.path.getCoords().get(0).getValue(), path, 10, 10, 1);
        Optional<NPC> toReturn;
        if (this.enemies.size() < 15) {
            toReturn = Optional.of(newNPC);
            this.enemies.add(newNPC);
            return toReturn;
        } else {
            toReturn = Optional.empty();
            return toReturn;
        }

    }
    /**
     * Lisää uuden tornin mikäli sen lisääminen on mahdollista.
     * Tornia ei lisätä mikäli pelaajalla ei ole tarpeeksi rahaa tai torni osuu johonkin muuhun torniin/polkuun
     * @param x uuden tornin x-koordinaatti
     * @param y uuden tornin y-koordinaatti
     * @return Palauttaa uuden tornin käärittynä Optional<> säiliöön mikä sen lisääminen on mahdollista
     *         muuten palautetaan tyhjä säiliö
     */
    public Optional<Tower> addTower(int x, int y) {
        Optional<Tower> toReturn = Optional.empty();
        if (this.player.getMoney() >= 5) {
            Tower t = new Tower(x, y, 1);
            for (Shape s : this.path.getHitBox()) {
                if (t.getHitBox().intersects(s.getBoundsInLocal())) {
                    return toReturn;
                }
            }
            for (Tower t2 : this.objects) {
                if (t.getHitBox().intersects(t2.getHitBox().getBoundsInLocal())) {
                    return toReturn;
                }
            }
            this.player.buildTower();
            this.toAdd.add(t);
            return Optional.of(t);
        }
        return toReturn;
    }
    
    /**
     * Poistaa tornin kartalta jos annetut koordinaatit osuvat johonkin torniin mikä on kartalla
     * @param x annettu x
     * @param y annettu y
     * @return Palauttaa true jos kyseinen koordinaatti osui torniin ja se poistettiin
     *         ,muuten palauttaa false
     */
    public boolean removeTower(int x, int y) {
        for (Tower t : this.objects) {
            if (t.getHitBox().intersects(x, y, 20, 20)) {
                this.player.addScore();
                this.objects.remove(t);
                return true;
            }
        }
        return false;
    }

    public int getMoney() {
        return this.player.getMoney();
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
        return this.player.getHealth();
    }

}
