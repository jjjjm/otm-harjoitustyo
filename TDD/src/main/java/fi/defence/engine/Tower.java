package fi.defence.engine;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

/**
 * Kuvaa yksittäistä tornia pelissä
 */
public class Tower {

    private int x, y, range, damage;
    private Shape hitBox;
    private boolean reset;

    /**
     *
     * Konstruktoi uuden Tower-olion jonka keskipiste ja sen viholliseen
     * aiheuttama vahinko on määritelty konstruktoreissa
     *
     * @param x tornin x-koordinaatti
     * @param y tornin y-koordinaatti
     * @param damage tornin tekemä vahinko
     */
    public Tower(int x, int y, int damage) {
        this.x = x;
        this.y = y;
        this.range = 75;
        this.damage = damage;
        hitBox = new Circle(x, y, 10);
        this.reset = false;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getRange() {
        return range;
    }

    /**
     * Metodin avulla voidaan tarkistaa onko jokin tietty vihollinen tornin
     * ampumaalueella
     *
     * @param npc Tarkistettava kohde (vihollinen kentällä)
     * @return palauttaa True vain jos NPC:n rajojen ja tornin-ampumaetäisyyden
     * määrittävät kuviot leikkaavat toisena ja tornin voi ampua (ts. reset ==
     * false)
     */
    public boolean shootableInRange(NPC npc) {
        Circle rangeArea = new Circle(x, y, range);
        if (!this.reset) {
            if (rangeArea.getBoundsInParent().intersects(npc.getHitbox().getBoundsInParent())) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * Vähentää kohde vihollisen terveyspisteitä, ei ota kantaa onko vihollinen
     * oikeasti ammuttavissa pelin sääntöjen mukaan Asettaa myös reset arvoksi
     * true jolloin torni ei voi ampua ennen kuin se asetataan takaisin falseksi
     *
     * @see fi.defence.engine.Tower#reset()
     *
     * @param n Ammuttava vihollinen
     */
    public void engageShootable(NPC n) {
        n.setHealth(n.getHealth() - this.damage);
        this.reset = true;
    }

    public Shape getHitBox() {
        return hitBox;
    }

    /**
     * Asettaa tornin ampumakelpoiseksi (ts. asettaa reset arvoksi false)
     *
     * @see fi.defence.engine.Tower#engageShootable(fi.defence.engine.NPC)
     * @see fi.defence.engine.Tower#shootableInRange(fi.defence.engine.NPC)
     */
    public void reset() {
        this.reset = false;
    }

}
